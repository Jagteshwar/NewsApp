package com.jagteshwar.newsapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jagteshwar.newsapp.domain.usecases.app_entry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntry: AppEntryUseCases
): ViewModel() {

    fun onEvent(event: OnBoardingEvent){
        when(event){
            is OnBoardingEvent.SaveAppEntry -> {
                saveAppEntry()
            }
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch{
        appEntry.saveAppEntry()
        }
    }
}