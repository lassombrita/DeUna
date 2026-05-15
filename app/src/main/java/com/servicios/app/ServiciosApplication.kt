package com.servicios.app

import android.app.Application
import com.servicios.app.auth.SessionManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ServiciosApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Precargar sesiones de ejemplo para la v2.0
        SessionManager.precargarSesionesEjemplo(this)
    }
}
