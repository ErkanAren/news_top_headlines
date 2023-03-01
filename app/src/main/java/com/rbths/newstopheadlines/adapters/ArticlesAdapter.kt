package com.rbths.newstopheadlines.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rbths.newstopheadlines.R
import com.rbths.newstopheadlines.model.Article
import com.rbths.newstopheadlines.model.Source
import com.rbths.newstopheadlines.utils.Utils

class ArticlesAdapter(sourcesList: MutableList<Article>): RecyclerView.Adapter<ArticlesAdapter.SourceViewHolder>() {

    private var articleList= mutableListOf<Article>()

    private val utils = Utils()
    init {
        this.articleList = sourcesList
    }
    class SourceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var headlineTV : TextView
        var publishedAtTV : TextView

        init {
            headlineTV = itemView.findViewById(R.id.headlineTV)
            publishedAtTV = itemView.findViewById(R.id.publishedAtTV)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        // create a new view
        val layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.source_item, parent, false)

        return SourceViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        val article = articleList[position]

        // show the title of the article
        holder.headlineTV.text = article.title

        // show published date of the article
        holder.publishedAtTV.text = utils.getDateStringFromISO8601(article.publishedAt)
    }

    override fun getItemCount(): Int {
        return if (articleList != null) articleList.size else 0
    }
}