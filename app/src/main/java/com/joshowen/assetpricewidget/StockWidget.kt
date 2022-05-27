package com.joshowen.assetpricewidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetManager.ACTION_APPWIDGET_UPDATE
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.widget.RemoteViews
import androidx.lifecycle.Observer
import androidx.transition.Visibility
import androidx.work.*
import com.joshowen.yahoofinancemodule.stockquote.response.workmanager.WidgetUpdater
import com.joshowen.yahoofinancemodule.stockquote.response.workmanager.WidgetUpdater.Companion.LANGUAGE_KEY
import com.joshowen.yahoofinancemodule.stockquote.response.workmanager.WidgetUpdater.Companion.REGION_KEY
import com.joshowen.yahoofinancemodule.stockquote.response.workmanager.WidgetUpdater.Companion.TICKER_KEY
import java.util.concurrent.TimeUnit


/**
 * Implementation of App Widget functionality.
 */
class StockWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == ACTION_APPWIDGET_UPDATE) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val thisAppWidgetComponentName = ComponentName(context.packageName, javaClass.name)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidgetComponentName)
            for (appWidgetId in appWidgetIds) {
                updateAppWidget(context, appWidgetManager, appWidgetId)
            }
        }
    }

    override fun onEnabled(context: Context) {
        super.onEnabled(context)
    }


    override fun onDisabled(context: Context) {
        super.onDisabled(context)
    }

    private fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val remoteViews =
            RemoteViews(context.packageName, R.layout.stock_widget).also { remoteView ->
                remoteView.setOnClickPendingIntent(
                    R.id.ivSettings,
                    PendingIntent.getActivity(
                        context, 0,
                        Intent(context, MainActivity::class.java),
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                        else
                            PendingIntent.FLAG_UPDATE_CURRENT
                    )
                )

            }
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
    }

    companion object {
        const val ACTION_UPDATE_STOCK_PRICE = "UPDATE_WIDGET"
    }
}

