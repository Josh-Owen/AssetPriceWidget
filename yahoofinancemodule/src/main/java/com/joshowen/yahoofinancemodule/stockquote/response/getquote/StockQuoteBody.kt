package com.joshowen.yahoofinancemodule.stockquote.response.getquote

data class StockQuoteBody (
    val result : List<Quote> = emptyList()
)