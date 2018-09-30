package com.telecomuc.bookqr

import androidx.multidex.MultiDexApplication
import com.squareup.leakcanary.LeakCanary
import com.telecomuc.bookqr.koin.appModule
import org.koin.android.ext.android.startKoin

class BookQR : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        super.onCreate()

        if (BuildConfig.DEBUG) {

            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return
            }
            LeakCanary.install(this)

        }



        startKoin(this, listOf(appModule))
    }
}