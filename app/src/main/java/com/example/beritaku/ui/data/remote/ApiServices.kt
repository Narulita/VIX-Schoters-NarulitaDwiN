package com.example.beritaku.ui.data.remote

import com.example.beritaku.ui.data.response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {

    @GET ("top-headlines?country=id&apiKey=16e9e27efd1449c592c8ff37e941e32b")
    fun getNews() : Call<NewsResponse>
}