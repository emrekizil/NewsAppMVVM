package com.example.newsappmvvm.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.newsappmvvm.data.dto.Article
import com.example.newsappmvvm.databinding.ItemArticleBinding
import com.example.newsappmvvm.utility.loadImage

class NewsUiComponent @JvmOverloads constructor(
    context: Context,
    attributesSet:AttributeSet?=null,
    defStyleAttr:Int = 0
) : FrameLayout(context,attributesSet,defStyleAttr){
    private val binding = ItemArticleBinding.inflate(LayoutInflater.from(context),this,false)

    init {
        addView(binding.root)
    }
    fun setNewsData(article: Article){
        binding.tvTitle.text = article.title
        binding.tvSource.text = article.source?.name
        binding.tvPublishedAt.text = article.publishedAt
        binding.tvDescription.text = article.description
        binding.articleImage.loadImage(article.urlToImage)
    }
}