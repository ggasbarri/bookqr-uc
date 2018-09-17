package com.telecomuc.bookqr

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.telecomuc.bookqr.koin.appModule
import org.koin.android.ext.android.startKoin

class BookQR : Application() {

    override fun onCreate() {
        super.onCreate()

        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
        // Normal app init code...


        startKoin(this, listOf(appModule))
    }
}