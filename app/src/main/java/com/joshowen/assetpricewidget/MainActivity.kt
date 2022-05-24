package com.joshowen.assetpricewidget

import android.appwidget.AppWidgetManager
import android.os.Bundle
import android.widget.RemoteViews
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel : MainActivityVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()

        val remoteViews = RemoteViews(this.packageName, R.layout.stock_widget)
        val appWidgetManager = AppWidgetManager.getInstance(this)
        val ids = appWidgetManager.getAppWidgetIds(componentName)
        for (id in ids) {
            //update textView here
            appWidgetManager.updateAppWidget(id, remoteViews)
        }
    }
}