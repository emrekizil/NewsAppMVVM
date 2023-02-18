package com.example.newsappmvvm.ui.base

import android.view.ViewGroup
import com.example.newsappmvvm.data.dto.Article
import com.example.newsappmvvm.ui.adapters.NewsAdapter

class NewsRecyclerViewAdapter(private val onClick:(Article) -> Unit) : BaseRecyclerViewAdapter<Article,NewsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.createForm(parent)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val card = getItem(position)
        holder.itemView.setOnClickListener {
            onClick(card)
        }
    }
}