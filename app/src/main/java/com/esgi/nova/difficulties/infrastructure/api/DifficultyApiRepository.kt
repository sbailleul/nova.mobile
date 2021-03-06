package com.esgi.nova.difficulties.infrastructure.api

import android.content.Context
import com.esgi.nova.difficulties.infrastructure.api.models.ResumedDifficulty
import com.esgi.nova.infrastructure.api.AuthenticatedApiRepository
import com.esgi.nova.users.application.GetUserToken
import com.esgi.nova.users.application.UpdateUserToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class DifficultyApiRepository @Inject constructor(
    @ApplicationContext context: Context,
    getUserToken: GetUserToken,
    updateUserToken: UpdateUserToken
) : AuthenticatedApiRepository(
    getUserToken, updateUserToken, context
) {

    private var difficultiesService: DifficultyService = apiBuilder()
        .build()
        .create(DifficultyService::class.java)

    suspend fun getAllTranslatedDifficulties(language: String): List<ResumedDifficulty> {
        return difficultiesService
            .getAllTranslatedDifficulties(language = language)
            .map { it.toDifficultyWithResourceResumes() }
    }
}


