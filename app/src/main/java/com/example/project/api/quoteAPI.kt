package com.example.project.api

import com.google.gson.annotations.SerializedName
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class quote(@SerializedName("q") val quote1: String,
                 @SerializedName("a") val author: String)

interface quoteAPI {
    @GET("random")
    suspend fun fetchFact(): Array<quote>

    /**
     * Factory class for convenient creation of the Api Service interface
     */

    companion object Factory {
        fun create(): quoteAPI {
            val retrofit: Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                // Must end in /!
                .baseUrl("https://zenquotes.io/api/")
                .build()
            return retrofit.create(quoteAPI::class.java)
        }
    }
}