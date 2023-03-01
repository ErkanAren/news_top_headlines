package com.rbths.newstopheadlines.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rbths.newstopheadlines.R
import com.rbths.newstopheadlines.model.Source

class SourcesAdapter(sourcesList: MutableList<Source>): RecyclerView.Adapter<SourcesAdapter.SourceViewHolder>() {

    private var sourcesList= mutableListOf<Source>()

    init {
        this.sourcesList = sourcesList
    }
    class SourceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var headlineTV : TextView
        init {
            headlineTV = itemView.findViewById(R.id.headlineTV)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        // create a new view
        val layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.source_item, parent, false)

        return SourceViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        val source = sourcesList[position]

        holder.headlineTV.text = source.name
    }

    override fun getItemCount(): Int {
        return if (sourcesList != null) sourcesList.size else 0
    }
}