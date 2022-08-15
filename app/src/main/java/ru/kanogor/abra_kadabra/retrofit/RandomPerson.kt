package ru.kanogor.abra_kadabra.retrofit

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://randomuser.me"

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    val searchUserApi: SearchUserApi = retrofit.create(SearchUserApi::class.java)
}

interface SearchUserApi {
    @GET("/api/")
    suspend fun getInfo(): Response<UserData>
}
