package com.example.newsappmvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.newsappmvvm.R
import com.example.newsappmvvm.databinding.FragmentArticleBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ArticleFragment : Fragment() {

    private val viewModel by viewModels<NewsViewModel>()

    private lateinit var binding: FragmentArticleBinding

    private val args : ArticleFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = args.article
        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }
    }

}