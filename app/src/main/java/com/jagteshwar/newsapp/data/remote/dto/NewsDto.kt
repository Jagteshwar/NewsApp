package com.jagteshwar.newsapp.data.remote.dto

import com.jagteshwar.newsapp.domain.model.Article

data class NewsDto(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)