package com.jagteshwar.newsapp.domain.usecases.app_entry

import com.jagteshwar.newsapp.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}