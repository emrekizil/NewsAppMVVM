package com.example.newsappmvvm.ui

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsappmvvm.R
import com.example.newsappmvvm.data.Resource
import com.example.newsappmvvm.databinding.FragmentSearchNewsBinding
import com.example.newsappmvvm.ui.adapters.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchNewsFragment : Fragment() {

    private val viewModel by viewModels<NewsViewModel>()

    private lateinit var binding: FragmentSearchNewsBinding

    private lateinit var newsAdapter: NewsAdapter

    private val TAG = "SearchNewsFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchNewsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(R.id.action_searchNewsFragment_to_articleFragment,
                bundle)
        }
        var job:Job?=null
        binding.searchEdit.addTextChangedListener {editable->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                editable?.let {
                    if(editable.toString().isNotEmpty()){
                        viewModel.getSearchNews(editable.toString())
                    }
                }
            }
        }
        viewModel.searchNews.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let { response ->
                        newsAdapter.differ.submitList(response.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    it.message?.let {
                        Log.e(TAG, it)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }
    private fun hideProgressBar(){
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }
    private fun showProgressBar(){
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}