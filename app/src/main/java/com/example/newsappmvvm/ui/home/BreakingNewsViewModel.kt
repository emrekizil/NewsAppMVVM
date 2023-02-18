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
class BreakingNewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private var _breakingNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val breakingNews : LiveData<Resource<NewsResponse>> get() = _breakingNews
    var breakingNewsResponse : NewsResponse? = null
    var breakingNewsPage =1

    init {
        getBreakingNews("us")
    }
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
}