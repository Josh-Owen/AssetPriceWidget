package com.joshowen.assetpricewidget.di

import androidx.lifecycle.ViewModel
import com.joshowen.assetpricewidget.MainActivityVM
import com.joshowen.assetpricewidget.base.BaseViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun bindMainActivityVM(mainActivityVM: MainActivityVM): BaseViewModel

}