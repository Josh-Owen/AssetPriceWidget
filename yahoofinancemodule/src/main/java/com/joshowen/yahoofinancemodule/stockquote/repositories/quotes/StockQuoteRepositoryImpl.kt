package com.joshowen.yahoofinancemodule.stockquote.repositories.quotes

import com.joshowen.yahoofinancemodule.stockquote.retrofit.YahooService
import com.joshowen.yahoofinancemodule.stockquote.response.getquote.StockQuoteResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockQuoteRepositoryImpl @Inject constructor(): StockQuoteRepository {


    private fun getAPIService(): YahooService {
        val retrofit = Retrofit.Builder()
            .baseUrl(YAHOO_API_BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(YahooService::class.java)
    }


    override fun getStockQuote(
        symbol : String,
        region: String,
        language: String
    ): Single<StockQuoteResponse> {
        return getAPIService().getStockQuote(YAHOO_FINANCE_API_KEY, symbol, region, language)
    }

    companion object {
        private const val YAHOO_API_BASE_URL : String = "https://yfapi.net/v6/finance/"
    }
}