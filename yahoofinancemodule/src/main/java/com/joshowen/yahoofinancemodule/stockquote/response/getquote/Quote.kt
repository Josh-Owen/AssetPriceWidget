package com.joshowen.yahoofinancemodule.stockquote.response.getquote

data class Quote (
    val ask : Double?= null,
    val displayName : String= "",
    val longName : String? = "",
    val currency : String? = "",
    val exchangeTimezoneShortName : String = "",
    val regularMarketOpen : Double?= 0.0,
    val regularMarketPrice :Double?= 0.0,
    val marketState : String?= "",
    val postMarketPrice : Double?= 0.0,
)