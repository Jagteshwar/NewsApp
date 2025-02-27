package com.jagteshwar.newsapp.domain.usecases

import com.jagteshwar.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke(): Flow<Boolean>{
        return localUserManager.readAppEntry()
    }
}