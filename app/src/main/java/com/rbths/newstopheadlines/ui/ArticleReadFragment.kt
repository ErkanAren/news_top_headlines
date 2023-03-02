package com.rbths.newstopheadlines.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rbths.newstopheadlines.R
import com.rbths.newstopheadlines.databinding.FragmentArticleListBinding
import com.rbths.newstopheadlines.databinding.FragmentArticleReadBinding


class ArticleReadFragment : Fragment() {

    private var _binding: FragmentArticleReadBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentArticleReadBinding.inflate(inflater, container, false)




        return  binding.root
    }


}