package com.example.newsappmvvm.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import com.example.newsappmvvm.R
import com.example.newsappmvvm.data.dto.Article
import com.example.newsappmvvm.databinding.ItemArticleBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>()   {
    private lateinit var binding : ItemArticleBinding

    inner class ArticleViewHolder(itemView:View): RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsAdapter.ArticleViewHolder {
        binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context))
        return ArticleViewHolder(
            binding.root
        )
    }

    override fun onBindViewHolder(holder: NewsAdapter.ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        binding.tvSource.text = article.source.name
        binding.tvTitle.text = article.title
        binding.tvDescription.text = article.content
        binding.tvPublishedAt.text = article.publishedAt
        println(article.title)
        holder.itemView.apply {
            Glide.with(this).load(
                article.urlToImage
            ).into(binding.articleImage)
            setOnClickListener{
                onItemClickListener?.let {
                    it(article)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener:((Article)->Unit)? = null

    fun setOnItemClickListener(listener:(Article)->Unit){
        onItemClickListener = listener
    }

}