package com.esgi.nova.games.infrastructure.api

import com.esgi.nova.games.application.models.GameForCreation
import com.esgi.nova.games.infrastructure.api.models.GameForUpdate
import com.esgi.nova.games.infrastructure.api.models.GameState
import com.esgi.nova.games.infrastructure.dto.LeaderBoardGameView
import com.esgi.nova.infrastructure.api.ApiConstants
import com.esgi.nova.infrastructure.api.HttpConstants
import com.esgi.nova.infrastructure.api.pagination.PageMetadata
import com.esgi.nova.models.Score
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface GameService {

    @Headers(HttpConstants.Headers.Accept + ": " + ApiConstants.CustomMediaType.Application.LeaderBoardGame)
    @GET("${ApiConstants.BaseUrl}${ApiConstants.EndPoints.Games}")
    fun getLeaderBoardGamesByDifficulty(
        @Query("difficultyId") difficultyId: String,
        @Query("page") page: Int,
        @Query("size") pageSize: Int
    ): Call<PageMetadata<LeaderBoardGameView>>

    @Headers(HttpConstants.Headers.Accept + ": " + ApiConstants.CustomMediaType.Application.GameState)
    @GET("${ApiConstants.BaseUrl}${ApiConstants.EndPoints.Games}")
    fun getGameStateByUsername(@Query("username") username: String): Call<GameState>

    @POST(ApiConstants.EndPoints.Games)
    fun createGame(@Body gameForCreation: GameForCreation): Call<ResponseBody>

    @PUT("${ApiConstants.EndPoints.Games}{id}")
    fun uploadGame(@Path("id") id: UUID, @Body gameForUpdate: GameForUpdate ): Call<ResponseBody>
}