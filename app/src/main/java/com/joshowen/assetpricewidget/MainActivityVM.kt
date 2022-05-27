package com.joshowen.assetpricewidget

import com.joshowen.assetpricewidget.base.BaseViewModel
import com.joshowen.yahoofinancemodule.stockquote.repositories.quotes.StockQuoteRepository
import com.joshowen.yahoofinancemodule.stockquote.response.getquote.StockQuoteResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(private val stockRepo : StockQuoteRepository) : BaseViewModel() {


    fun getAPIResponse() : Single<StockQuoteResponse> {
        return stockRepo.getStockQuote("GME", "GB", "en")
    }

}