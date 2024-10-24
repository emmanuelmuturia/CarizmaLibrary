package emmanuelmuturia.sonux.application

import android.app.Application
import emmanuelmuturia.sonux.di.androidMainKoinModule
import emmanuelmuturia.sonux.di.sharedAndroidMainKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SonuxApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(androidContext = applicationContext)
            modules(modules = listOf(sharedAndroidMainKoinModule, androidMainKoinModule))
        }
    }
}