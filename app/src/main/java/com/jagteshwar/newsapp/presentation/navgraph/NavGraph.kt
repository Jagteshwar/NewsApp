package com.jagteshwar.newsapp.presentation.navgraph

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.jagteshwar.newsapp.R
import com.jagteshwar.newsapp.presentation.onboarding.OnBoardingScreen
import com.jagteshwar.newsapp.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Routes.AppStartNavigation.route,
            startDestination = Routes.OnBoardingScreen.route
        ){
            composable(
                route = Routes.OnBoardingScreen.route
            ){
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(viewModel::onEvent)
            }
        }

        navigation(
            route = Routes.NewsNavigation.route,
            startDestination = Routes.NewsNavigatorScreen.route
        ){
            composable(
                route = Routes.NewsNavigatorScreen.route
            ){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "News Navigator Screen", color = colorResource(id = R.color.display_small))
                }
            }
        }
    }
}