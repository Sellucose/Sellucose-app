package com.sellucose.sellucosebook.ui.rated

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.sellucose.sellucosebook.databinding.FragmentRatedBinding
import com.sellucose.sellucosebook.ui.explore.ExploreViewModel

class RatedFragment : Fragment() {
    private var _binding: FragmentRatedBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val ratedViewModel =
            ViewModelProvider(this).get(RatedViewModel::class.java)

        _binding = FragmentRatedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.tvRated
        ratedViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }
}