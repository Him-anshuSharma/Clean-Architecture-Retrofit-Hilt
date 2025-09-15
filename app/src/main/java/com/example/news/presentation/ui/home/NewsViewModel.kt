package com.example.news.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.domain.model.Article
import com.example.news.domain.usecase.GetNewsByCountryUseCase
import com.example.news.domain.usecase.GetNewsUseCase
import com.example.news.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    val getNewsUseCase: GetNewsUseCase,
    val getNewsByCountryUseCase: GetNewsByCountryUseCase
): ViewModel() {
    private val _articles = MutableStateFlow<Resource<List<Article>>>(Resource.Loading())
    val articles = _articles.asStateFlow()

    init {
        getNews()
    }

    fun getNews(){
        viewModelScope.launch {
            _articles.value = getNewsUseCase()
        }
    }

    fun getNewsByCountry(country: String){
        viewModelScope.launch {
            _articles.value = getNewsByCountryUseCase(country)
        }
    }

}