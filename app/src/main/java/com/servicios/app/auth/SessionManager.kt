package com.servicios.app.auth

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// FIX: Se cambió context.getSharedPreferences por applicationContext
// para evitar memory leaks al pasar Activity como contexto.
object SessionManager {

    private const val PREF_NAME    = "tuhogar_sessions"
    private const val KEY_SESSIONS = "saved_sessions"
    private const val KEY_CURRENT  = "current_session"
    private val gson = Gson()

    data class Sesion(
        val id        : String,
        val nombre    : String,
        val email     : String,
        val avatarUrl : String = "",
        val token     : String = ""
    )

    // FIX: Siempre usar applicationContext para evitar leaks
    private fun prefs(ctx: Context): SharedPreferences =
        ctx.applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun getSesiones(ctx: Context): List<Sesion> {
        val json = prefs(ctx).getString(KEY_SESSIONS, null) ?: return emptyList()
        val type = object : TypeToken<List<Sesion>>() {}.type
        return gson.fromJson(json, type)
    }

    fun guardarSesion(ctx: Context, sesion: Sesion) {
        val lista = getSesiones(ctx).toMutableList()
        lista.removeAll { it.id == sesion.id }
        lista.add(0, sesion)
        prefs(ctx).edit().putString(KEY_SESSIONS, gson.toJson(lista)).apply()
    }

    fun getSesionActual(ctx: Context): Sesion? {
        val json = prefs(ctx).getString(KEY_CURRENT, null) ?: return null
        return gson.fromJson(json, Sesion::class.java)
    }

    fun setSesionActual(ctx: Context, sesion: Sesion) {
        prefs(ctx).edit().putString(KEY_CURRENT, gson.toJson(sesion)).apply()
    }

    fun cerrarSesion(ctx: Context) {
        prefs(ctx).edit().remove(KEY_CURRENT).apply()
    }

    fun precargarSesionesEjemplo(ctx: Context) {
        if (getSesiones(ctx).isNotEmpty()) return
        listOf(
            Sesion("1", "Laura García",    "laura@email.com"),
            Sesion("2", "Carlos Pérez",    "carlos.perez@gmail.com"),
            Sesion("3", "María Rodríguez", "mrodriguez@outlook.com")
        ).forEach { guardarSesion(ctx, it) }
        setSesionActual(ctx, Sesion("1", "Laura García", "laura@email.com"))
    }
}
