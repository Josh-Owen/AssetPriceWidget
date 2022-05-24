package com.joshowen.assetpricewidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetManager.ACTION_APPWIDGET_UPDATE
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.activity.viewModels
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
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
        if(intent.action == ACTION_APPWIDGET_UPDATE) {
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

        val data = Data.Builder()
            .putString(TICKER_KEY, "GME")
            .putString(REGION_KEY, "US")
            .putString(LANGUAGE_KEY, "en")
            .build()



        val alwaysPendingWork = OneTimeWorkRequestBuilder<WidgetUpdater>()
            .setInitialDelay(30, TimeUnit.SECONDS)

            .setInputData(data)
            .build()

        WorkManager.getInstance(context).enqueueUniqueWork(
            ACTION_UPDATE_STOCK_PRICE,
            ExistingWorkPolicy.KEEP,
            alwaysPendingWork
        )

    }

    override fun onDisabled(context: Context) {
        super.onDisabled(context)
        WorkManager.getInstance(context).cancelUniqueWork(ACTION_UPDATE_STOCK_PRICE)
    }

    companion object {
        private const val ACTION_UPDATE_STOCK_PRICE = "UPDATE_WIDGET"
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
    data: Data?= null
) {
     val remoteViews = RemoteViews(context.packageName, R.layout.stock_widget).also {remoteView ->
        data?.let {
            remoteView.setTextViewText(R.id.tvAssetCurrentValue, "60")
        }
         remoteView.setOnClickPendingIntent(
            R.id.ivSettings,
            PendingIntent.getActivity(context, 0,
                Intent(context, MainActivity::class.java),
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                else
                    PendingIntent.FLAG_UPDATE_CURRENT
            )
        )
//        it.setOnClickPendingIntent(
//            R.id.tvUpdate,
//            PendingIntent.getActivity(context, 0,
//                Intent(context, MainActivity::class.java),
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
//                    PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
//                else
//                    PendingIntent.FLAG_UPDATE_CURRENT
//            )
//        )
    }
//
//     val viewModel : MainActivityVM by viewModels()
//
//    viewModel.getAPIResponse().subscribe ({ response ->
//        Log.e("Repsonse",  response.quoteResponse?.result.toString())
//    }, {error ->
//        Log.e("Error: ", error.toString())
//    })
    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
}