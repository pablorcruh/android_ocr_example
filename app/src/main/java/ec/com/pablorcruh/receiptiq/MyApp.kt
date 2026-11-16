package ec.com.pablorcruh.receiptiq

import android.app.Application
import ec.com.pablorcruh.receiptiq.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.annotation.KoinApplication
import org.koin.plugin.module.dsl.startKoin

@KoinApplication(modules = [AppModule::class])
class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin<MyApp>{
            androidContext(this@MyApp)
        }
    }
}