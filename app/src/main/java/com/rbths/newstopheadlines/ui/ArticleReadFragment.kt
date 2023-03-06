package com.rbths.newstopheadlines.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.rbths.newstopheadlines.R
import com.rbths.newstopheadlines.databinding.FragmentArticleReadBinding


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
        //check the article values, if they are empty or null show a message
        binding.articleReadTitleTV.text = if(article.title.isNullOrEmpty()) getString(R.string.empty_title_warning) else article.title
        binding.articleReadDescriptionTV.text = if(article.description.isNullOrEmpty()) getString(R.string.empty_description_warning) else article.description
        binding.articleReadContentTV.text = if(article.content.isNullOrEmpty()) getString(R.string.empty_content_warning) else article.content
        // Show the image of the article, show no image if the url is empty or broken
        Glide.with(requireContext()).load(article.urlToImage).error(R.drawable.no_image).into(binding.articleReadImageTV)


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