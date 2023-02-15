package com.example.newsappmvvm.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import com.example.newsappmvvm.data.Resource
import com.example.newsappmvvm.data.dto.Article
import com.example.newsappmvvm.data.dto.NewsResponse
import com.example.newsappmvvm.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.Response


import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
) :ViewModel() {

    private var _breakingNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val breakingNews :LiveData<Resource<NewsResponse>> get() = _breakingNews
    var breakingNewsResponse : NewsResponse? = null
    var breakingNewsPage =1

    private var _searchNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val searchNews :LiveData<Resource<NewsResponse>> get() = _searchNews
    var searchNewsResponse: NewsResponse?=null
    var searchNewsPage =1

    private var _savedNews : MutableLiveData<List<Article>> = MutableLiveData()
    val savedNews:LiveData<List<Article>> get() = _savedNews

    init {
        getBreakingNews("us")
        getSavedNews()
    }

   /* fun getBreakingNews(countryCode:String){
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
    }*/

    fun getBreakingNews(countryCode:String){
        viewModelScope.launch {
            newsRepository.getBreakingNews(countryCode,breakingNewsPage).collectLatest {
                when(it){
                    is Resource.Success -> {
                        _breakingNews.postValue(handleBreakingNewsResponse(it))
                    }
                    is Resource.Error ->{
                        _breakingNews.postValue(Resource.Error(it.message.toString()))
                    }
                    is Resource.Loading ->{
                        _breakingNews.postValue(Resource.Loading())
                    }
                }
            }
        }
    }

    fun getSearchNews(searchQuery:String){
        viewModelScope.launch {
            newsRepository.getNewsWithSearchQuery(searchQuery,searchNewsPage).collectLatest {
                when(it){
                    is Resource.Success ->{
                        _searchNews.postValue(handleSearchNewsResponse(it))
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

    private fun handleBreakingNewsResponse( response: Resource<NewsResponse>) : Resource<NewsResponse>{
            response.let { resultResponse ->
                breakingNewsPage++
                if(breakingNewsResponse == null){
                    breakingNewsResponse = resultResponse.data
                }else{
                    val oldArticles = breakingNewsResponse?.articles
                    val newArticles = resultResponse.data?.articles
                    if (newArticles != null) {
                        oldArticles?.addAll(newArticles)
                    }
                }
                return  Resource.Success(breakingNewsResponse ?: resultResponse.data)
            }
    }
    private fun handleSearchNewsResponse( response: Resource<NewsResponse>) : Resource<NewsResponse>{
            println("letsgo")
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

    fun saveArticle(article: Article) {
        viewModelScope.launch {
            newsRepository.insert(article)
        }
    }

    fun deleteArticle(article: Article){
        viewModelScope.launch {
            newsRepository.delete(article)
        }
    }

    fun getSavedNews() {
        viewModelScope.launch{
            newsRepository.getSavedNews().collectLatest {
                _savedNews.postValue(it)
            }
        }
    }


}