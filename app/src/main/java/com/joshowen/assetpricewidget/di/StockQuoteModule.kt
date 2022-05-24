package com.joshowen.assetpricewidget.di

import com.joshowen.yahoofinancemodule.stockquote.repositories.quotes.StockQuoteRepository
import com.joshowen.yahoofinancemodule.stockquote.repositories.quotes.StockQuoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface StockModule {

    @Binds
    fun getStockQuoteRepository(repository: StockQuoteRepositoryImpl): StockQuoteRepository

}