package app.vut.secnote.data.remote

import retrofit2.http.GET

interface ApiService {

    @GET("/api/user/2")
    suspend fun user(): String
}
