package com.joshowen.yahoofinancemodule.stockquote.retrofit

import com.joshowen.yahoofinancemodule.stockquote.response.getquote.StockQuoteResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface YahooService {

    @GET("quote")
    fun getStockQuote(
        @Header("X-API-KEY") apiKey : String,
        @Query("symbols") symbols: String,
        @Query("region") region: String?,
        @Query("lang") language: String?,
    ): Single<StockQuoteResponse>



}