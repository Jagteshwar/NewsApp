package com.jagteshwar.newsapp.domain.usecases.app_entry

import com.jagteshwar.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {

    operator fun invoke(): Flow<Boolean>{
        return localUserManager.readAppEntry()
    }
}