package com.rbths.newstopheadlines.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rbths.newstopheadlines.R
import com.rbths.newstopheadlines.databinding.ArticleItemBinding
import com.rbths.newstopheadlines.model.Article
import com.rbths.newstopheadlines.utils.Utils

class ArticlesAdapter(private val context: Context, articlesList: MutableList<Article>, private val onItemClicked: (Article) -> Unit ): RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    private var articleList= mutableListOf<Article>()

    init {
        this.articleList = articlesList
    }
    class ArticleViewHolder(binding: ArticleItemBinding, onItemClicked: (Int) -> Unit ) : RecyclerView.ViewHolder(binding.root) {
        var headlineTV : TextView
        var publishedAtTV : TextView
        var headlineImageIV : ImageView
        init {
            headlineTV = itemView.findViewById(R.id.headlineTV)
            publishedAtTV = itemView.findViewById(R.id.publishedAtTV)
            headlineImageIV = itemView.findViewById(R.id.headlineImageIV)

            itemView.setOnClickListener { onItemClicked(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        // create a new view

        val binding = ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //val layoutInflater = LayoutInflater.from(parent.context)
        //    .inflate(R.layout.article_item, parent, false)

        val viewHolder = ArticleViewHolder(binding){ position ->
            onItemClicked(articleList[position])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articleList[position]

        // show the title of the article
        holder.headlineTV.text = article.title

        // show published date of the article
        if(article.publishedAt.isNullOrEmpty()){
            holder.publishedAtTV.visibility = View.GONE
        }else{
            holder.publishedAtTV.text = Utils.getDateStringFromISO8601(article.publishedAt)
        }


        // SHow the image of the article
        Glide.with(context).load(article.urlToImage).error(R.drawable.news_temp_image).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.headlineImageIV)
    }

    override fun getItemCount(): Int {
        return if (articleList != null) articleList.size else 0
    }
}