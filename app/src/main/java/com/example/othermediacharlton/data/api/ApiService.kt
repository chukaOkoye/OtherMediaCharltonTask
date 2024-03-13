package com.example.othermediacharlton.data.api

import com.example.othermediacharlton.data.model.Match
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import io.reactivex.Observable

interface ApiService {
    @GET("fixtures")
    fun getFixtures(): Observable<List<Match>>

    companion object {
        private lateinit var apiService: ApiService
        fun getInstance(): ApiService {
            return if (this::apiService.isInitialized) {
                apiService
            } else {
                apiService = Retrofit.Builder()
                    .baseUrl("https://3e0093ee-a397-4a87-bc26-ef97480c5292.mock.pstmn.io/")
                    .client(OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create() )
                    .build().create(ApiService::class.java)
                apiService
            }
        }
    }

}