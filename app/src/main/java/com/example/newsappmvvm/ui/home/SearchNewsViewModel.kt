package com.example.newsappmvvm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappmvvm.data.Resource
import com.example.newsappmvvm.data.dto.NewsResponse
import com.example.newsappmvvm.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchNewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private var _searchNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val searchNews : LiveData<Resource<NewsResponse>> get() = _searchNews
    var searchNewsResponse: NewsResponse?=null
    var searchNewsPage =1

    fun getSearchNews(searchQuery:String){
        viewModelScope.launch {
            newsRepository.getNewsWithSearchQuery(searchQuery,searchNewsPage).collectLatest {
                when(it){
                    is Resource.Success ->{
                        _searchNews.postValue(it)
                    }
                    is Resource.Error ->{
                        _searchNews.postValue(Resource.Error(it.message.toString()))
                    }
                    is Resource.Loading ->{
                        _searchNews.postValue(Resource.Loading())
                    }
                }
            }
        }
    }


    private fun handleSearchNewsResponse( response: Resource<NewsResponse>) : Resource<NewsResponse>{
        response.let { resultResponse ->
            searchNewsPage++
            if(searchNewsResponse == null){
                searchNewsResponse = resultResponse.data
            }else{
                val oldArticles = searchNewsResponse?.articles
                val newArticles = resultResponse.data?.articles
                if (newArticles != null) {
                    oldArticles?.addAll(newArticles)
                }

            }
            return Resource.Success(searchNewsResponse ?: resultResponse.data)
        }
    }
}