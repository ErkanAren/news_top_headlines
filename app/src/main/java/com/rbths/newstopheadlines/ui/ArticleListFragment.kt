package com.rbths.newstopheadlines.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.rbths.newstopheadlines.BuildConfig

import com.rbths.newstopheadlines.R
import com.rbths.newstopheadlines.adapters.ArticlesAdapter
import com.rbths.newstopheadlines.databinding.FragmentArticleListBinding
import com.rbths.newstopheadlines.model.Article
import com.rbths.newstopheadlines.utils.Utils
import com.rbths.newstopheadlines.viewmodel.MainViewModel


class ArticleListFragment : Fragment() {

    private var _binding: FragmentArticleListBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var mViewModel: MainViewModel
    lateinit var articlesAdapter: ArticlesAdapter

    var articlesList = mutableListOf<Article>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentArticleListBinding.inflate(inflater, container, false)
        val view = binding.root

        setupRecyclerView()

        binding.newsProviderTitleTV.text = BuildConfig.SOURCE_NAME

        //articles will be filled after getHeadlines returns a successful response, getHeadlines is first called in SplashFragment
        mViewModel.articlesLiveData.observe(viewLifecycleOwner) { articlesResponse ->
            if(articlesResponse.status == "ok"){
                // we clear the articles list before we add new articles so it shows only the new ones
                articlesList.clear()
                if(!articlesResponse.articles.isNullOrEmpty()) {
                    //the articles were sorted but I added this code to sort them since it was in the requirements of
                    articlesList.addAll(articlesResponse.articles.sortedBy { Utils.getLongFromISO8601(it.publishedAt) }.reversed())
                }
                articlesAdapter.notifyDataSetChanged()
            }
        }
        return view
    }


    private fun setupRecyclerView() {
        //the clicked item of our RecyclerView will be returned here
        articlesAdapter = ArticlesAdapter(requireContext(),articlesList){ clickedArticle ->
            val action = ArticleListFragmentDirections.actionArticleListFragmentToArticleReadFragment(clickedArticle)
            findNavController().navigate(action)
        }
        binding.articlesRV.layoutManager = GridLayoutManager(
            requireContext(),
            resources.getInteger(R.integer.articles_recyclerview_columns),
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.articlesRV.adapter = articlesAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}