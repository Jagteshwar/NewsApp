package com.jagteshwar.newsapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.jagteshwar.newsapp.presentation.Dimens.MediumPadding1
import com.jagteshwar.newsapp.presentation.common.ArticleList
import com.jagteshwar.newsapp.presentation.common.SearchBar
import com.jagteshwar.newsapp.presentation.navgraph.Routes

@Composable
fun SearchScreen(
    searchState: SearchState,
    event: (SearchEvent) -> Unit,
    navigate: (String)-> Unit
) {

    Column(
        modifier = Modifier
            .padding(
                top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1
            )
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        SearchBar(
            text = searchState.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = {event(SearchEvent.SearchNews)})
        
        Spacer(modifier = Modifier.height(MediumPadding1))

        searchState.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticleList(articles = articles, onclick = {navigate(Routes.DetailsScreen.route)})
        }
    }
}