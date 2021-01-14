package com.esgi.nova.users.infrastructure.api

import com.esgi.nova.users.dtos.ConnectedUserDto
import com.esgi.nova.models.User
import retrofit2.Call
import retrofit2.http.*

interface AuthService {

    @POST("login")
    fun logWithUsernameAndPassword(@Body user: User): Call<ConnectedUserDto>

    @FormUrlEncoded
    @GET()
    fun logWithToken(@Field("token") token: String): Call<ConnectedUserDto>
}