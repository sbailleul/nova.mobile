package com.esgi.nova.infrastructure.api.interceptors

import com.esgi.nova.infrastructure.api.HttpConstants
import com.esgi.nova.users.application.GetUserToken
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthorizationRequestInterceptor(val getUserToken: GetUserToken) : Interceptor {


    val token
        get() = getUserToken.execute()

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request().signedRequest())
    }

    private fun Request.signedRequest(): Request {
        val finalToken = "Bearer $token"
        return this.newBuilder()
            .addHeader(HttpConstants.Headers.Authorization, finalToken)
            .build()
    }


}

