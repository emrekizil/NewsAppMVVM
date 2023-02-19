package com.example.newsappmvvm.ui.home

import android.view.ViewGroup
import com.example.newsappmvvm.data.dto.Article
import com.example.newsappmvvm.databinding.AdapterNewsItemBinding
import com.example.newsappmvvm.ui.base.BaseViewHolder
import com.example.newsappmvvm.utility.inflateAdapterItem

class NewsViewHolder(private val binding:AdapterNewsItemBinding): BaseViewHolder<Article>(binding.root) {

    companion object{
        fun createForm(parent:ViewGroup) =
            NewsViewHolder(parent.inflateAdapterItem(AdapterNewsItemBinding::inflate))
    }
    override fun onBind(data: Article) {
        binding.newsComponent.setNewsData(data)
    }


}