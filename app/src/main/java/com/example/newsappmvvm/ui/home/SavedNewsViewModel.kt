package com.example.newsappmvvm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappmvvm.data.dto.Article
import com.example.newsappmvvm.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


import javax.inject.Inject

@HiltViewModel
class SavedNewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
) :ViewModel() {

    private var _savedNews : MutableLiveData<List<Article>> = MutableLiveData()
    val savedNews:LiveData<List<Article>> get() = _savedNews

    init {
        getSavedNews()
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