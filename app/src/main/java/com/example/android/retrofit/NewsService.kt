package com.example.android.retrofit

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.Retrofit.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/everything?domains=wsj.com&apiKey=f19d9c29e1d34d64a77ed4c5ecf40a7f
//https://newsapi.org/v2/top-headlines?country=in&apiKey=f19d9c29e1d34d64a77ed4c5ecf40a7f

const val BASE_URL = "https://newsapi.org"
const val API_KEY =  "f19d9c29e1d34d64a77ed4c5ecf40a7f"

//1. Create The interface And create the method which we have to call
interface NewsInterface{

         @GET("v2/top-headlines?apiKey=$API_KEY")
        fun getHeadLines(@Query("country")country: String , @Query("page")page: Int): Call<News>

        //what it will return https://newsapi.org/v2/top-headlines?apiKey=$API_KEY&country=in&page=1
}
// 2. Create the object of the retrofit(Using Singleton because we have to create the single object of the Retrofit object)

object NewsService{
    val newsInstance: NewsInterface

    init {
      val retrofit: Retrofit = Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}

