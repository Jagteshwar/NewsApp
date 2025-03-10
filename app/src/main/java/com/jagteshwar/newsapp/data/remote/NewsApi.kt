package com.jagteshwar.newsapp.data.remote

import com.jagteshwar.newsapp.BuildConfig
import com.jagteshwar.newsapp.data.remote.dto.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getNews(
        @Query("page")page: Int,
        @Query("sources")sources: String,
        @Query("apiKey")apiKey: String = BuildConfig.API_KEY
    ): NewsDto
}