package com.joshowen.yahoofinancemodule.stockquote.response.getquote

import okhttp3.internal.http2.ErrorCode

data class StockQuoteResponse (
    val error : ErrorCode?= null,
    val stockQuoteBody : StockQuoteBody?= null
)