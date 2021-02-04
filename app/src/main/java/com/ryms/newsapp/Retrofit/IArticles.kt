package com.ryms.newsapp.Retrofit

import com.ryms.newsapp.Model.Articles
import io.reactivex.Observable
import retrofit2.http.GET

interface IArticles {
    @get: GET("?apiKey=5d49f713fa6f4d7eb64d7a425b05c921&category=technology")
    val articles: Observable<Articles>
}