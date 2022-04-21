package ru.babaets.bitcoinprices.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.babaets.bitcoinprices.app.presentation.features.feed.feedModule

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MainApp)
            modules(
                networkModule,
                feedModule
            )
        }
    }
}
