package com.example.newsappmvvm.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappmvvm.data.Resource
import com.example.newsappmvvm.data.dto.NewsResponse
import com.example.newsappmvvm.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response


import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) :ViewModel() {

    private var _breakingNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val breakingNews :LiveData<Resource<NewsResponse>> get() = _breakingNews
    var breakingNewsPage =1

    private var _searchNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val searchNews :LiveData<Resource<NewsResponse>> get() = _searchNews
    var searchNewsPage =1

    init {
        getBreakingNews("us")
    }

    fun getBreakingNews(countryCode:String){
         viewModelScope.launch {
             _breakingNews.postValue(Resource.Loading())
             val response = newsRepository.getBreakingNews(countryCode,breakingNewsPage)
             _breakingNews.postValue(handleBreakingNewsResponse(response))
         }
    }

    fun getSearchNews(searchQuery:String){
        viewModelScope.launch {
            _searchNews.postValue(Resource.Loading())
            val response = newsRepository.getNewsWithSearchQuery(searchQuery,searchNewsPage)
            _searchNews.postValue(handleSearchNewsResponse(response))
        }
    }

    private fun handleBreakingNewsResponse( response: Response<NewsResponse>) : Resource<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return  Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
    private fun handleSearchNewsResponse( response: Response<NewsResponse>) : Resource<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return  Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}