package com.sellucose.sellucosebook.ui.myBooks

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.sellucose.sellucosebook.repository.BookRepository
import com.sellucose.sellucosebook.databinding.FragmentMybooksBinding
import com.sellucose.sellucosebook.R


class MyBooksFragment : Fragment() {

    private var _binding: FragmentMybooksBinding? = null
    private val binding get() = _binding!!
    private lateinit var myBooksAdapter: MyBooksAdapter
    private lateinit var myBooksViewModel: MyBooksViewModel
    private val REQUEST_CODE_PICK_EPUB_FILE = 1
    private val REQUEST_CODE_READ_EXTERNAL_STORAGE = 2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        myBooksViewModel = ViewModelProvider(this).get(MyBooksViewModel::class.java)

        _binding = FragmentMybooksBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.title = "My Books"
        setHasOptionsMenu(true)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        myBooksAdapter = MyBooksAdapter()
        recyclerView.adapter = myBooksAdapter

        myBooksAdapter.submitList(myBooksViewModel.books)

        binding.fab.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_CODE_READ_EXTERNAL_STORAGE
                )
            } else {
                pickEpubFile()
            }
        }

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search -> {
                // Handle search action
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_READ_EXTERNAL_STORAGE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickEpubFile()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_PICK_EPUB_FILE && resultCode == AppCompatActivity.RESULT_OK) {
            val uri = data?.data
            if (uri != null) {
                val bookRepository = BookRepository()
                val book = bookRepository.getBook(uri)
                myBooksViewModel.books.add(book)
                myBooksAdapter.submitList(myBooksViewModel.books.toList())
                myBooksAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun pickEpubFile() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "application/epub+zip"
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        startActivityForResult(intent, REQUEST_CODE_PICK_EPUB_FILE)
    }
}