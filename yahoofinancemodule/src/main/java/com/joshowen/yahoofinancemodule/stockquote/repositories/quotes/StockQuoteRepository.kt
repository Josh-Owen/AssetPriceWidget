package com.joshowen.yahoofinancemodule.stockquote.repositories.quotes

import com.joshowen.yahoofinancemodule.stockquote.response.getquote.StockQuoteResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface StockQuoteRepository {
    fun getStockQuote(symbols : List<String>, region : String? = "GB", lang : String? = "en") : Single<StockQuoteResponse>
}