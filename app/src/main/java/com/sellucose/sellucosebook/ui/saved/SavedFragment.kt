package com.sellucose.sellucosebook.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sellucose.sellucosebook.R
import com.sellucose.sellucosebook.adapter.GetSavedBookAdapter
import com.sellucose.sellucosebook.databinding.FragmentSavedBinding
import com.sellucose.sellucosebook.ui.ViewModelFactory

class SavedFragment : Fragment() {

    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SavedViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    private lateinit var adapter: GetSavedBookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the AppBar
        val toolbar = binding.toolbarSaved
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.title = "Saved Books"
        setHasOptionsMenu(true)

        // Set up the RecyclerView
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 2)
//        recyclerView.layoutManager = LinearLayoutManager(context)


        adapter = GetSavedBookAdapter()
        recyclerView.adapter = adapter

        viewModel.books.observe(viewLifecycleOwner) { books ->
            adapter.submitList(books)
        }

        viewModel.getBooks()
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
}