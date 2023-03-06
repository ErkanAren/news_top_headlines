package com.rbths.newstopheadlines.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rbths.newstopheadlines.R
import com.rbths.newstopheadlines.databinding.FragmentArticleListBinding
import com.rbths.newstopheadlines.databinding.FragmentArticleReadBinding
import com.rbths.newstopheadlines.model.ArticlesResponse


class ArticleReadFragment : Fragment() {

    private var _binding: FragmentArticleReadBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!




    val args : ArticleReadFragmentArgs by navArgs()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentArticleReadBinding.inflate(inflater, container, false)



        val article = args.article
        binding.articleReadTitleTV.text = article.title
        binding.articleReadDescriptionTV.text = article.description
        binding.articleReadContentTV.text = article.content
        // Show the image of the article
        Glide.with(requireContext()).load(article.urlToImage).into(binding.articleReadImageTV)


        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}