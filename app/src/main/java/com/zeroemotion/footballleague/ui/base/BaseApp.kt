package com.zeroemotion.footballleague.ui.base

import android.app.Application
import com.zeroemotion.footballleague.di.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApp : Application() {
    companion object {
        private lateinit var instance: BaseApp

        fun getInstance(): BaseApp {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@BaseApp)
            modules(module)
        }
    }
}