package com.jagteshwar.newsapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jagteshwar.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.jagteshwar.newsapp.presentation.navgraph.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val addEntryUseCases: AppEntryUseCases
): ViewModel() {

    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Routes.AppStartNavigation.route)
        private set

    init {
        addEntryUseCases.readAppEntry()
            .onEach {shouldStartFromHomeScreen ->
                if(shouldStartFromHomeScreen){
                    startDestination = Routes.NewsNavigation.route
                }else{
                    startDestination = Routes.AppStartNavigation.route
                }
                delay(300)
                splashCondition = false
            }.launchIn(viewModelScope)
    }
}