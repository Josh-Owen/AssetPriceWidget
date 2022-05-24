package com.joshowen.yahoofinancemodule.stockquote.response.workmanager

import android.content.Context
import androidx.work.*
import androidx.work.rxjava3.RxWorker
import com.joshowen.yahoofinancemodule.stockquote.repositories.quotes.StockQuoteRepositoryImpl
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class WidgetUpdater(appContext: Context, workerParams: WorkerParameters):
    RxWorker(appContext, workerParams) {



    override fun createWork(): Single<Result> {

        val stockRepo = StockQuoteRepositoryImpl()

        val language = inputData.getString(LANGUAGE_KEY)
        val ticker = inputData.getString(TICKER_KEY)
        val region = inputData.getString(REGION_KEY)

        return stockRepo.getStockQuote(listOf(ticker ?: ""), region, language).map {response ->
            val outputData = workDataOf(API_BUNDLE to response)
            Result.success(outputData)
        }.onErrorReturn { Result.failure() }
    }

    companion object {
        const val LANGUAGE_KEY = "LANGUAGE"
        const val TICKER_KEY = "TICKER"
        const val REGION_KEY = "REGION"
        const val API_BUNDLE = "API_RESPONSE"
    }
}
