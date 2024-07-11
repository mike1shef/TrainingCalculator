package msha.trainingcalculator

import android.app.Application
import di.KoinInitializer
class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        KoinInitializer(applicationContext).init()
    }
}