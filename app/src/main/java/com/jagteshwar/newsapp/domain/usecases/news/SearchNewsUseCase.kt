package com.jagteshwar.newsapp.domain.usecases.news

import androidx.paging.PagingData
import com.jagteshwar.newsapp.domain.model.Article
import com.jagteshwar.newsapp.domain.repository.NewsRepository
import com.jagteshwar.newsapp.presentation.search.SearchEvent
import kotlinx.coroutines.flow.Flow

class SearchNewsUseCase(
    private val repository: NewsRepository
) {

    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>{
        return repository.searchNews(searchQuery = searchQuery, sources = sources)
    }
}