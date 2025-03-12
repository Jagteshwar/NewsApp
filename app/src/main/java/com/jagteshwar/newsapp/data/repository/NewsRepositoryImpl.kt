package com.jagteshwar.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jagteshwar.newsapp.data.remote.NewsApi
import com.jagteshwar.newsapp.data.remote.NewsPagingSource
import com.jagteshwar.newsapp.data.remote.SearchNewsPagingSource
import com.jagteshwar.newsapp.domain.model.Article
import com.jagteshwar.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi
): NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    source = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    newsApi = newsApi,
                    seachQuery = searchQuery,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }
}