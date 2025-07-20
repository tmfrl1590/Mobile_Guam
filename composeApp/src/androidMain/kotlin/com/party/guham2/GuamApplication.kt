package com.party.guham2

import android.app.Application
import com.party.guham2.di.initKoin
import org.koin.android.ext.koin.androidContext

class GuamApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@GuamApplication)
        }
    }
}