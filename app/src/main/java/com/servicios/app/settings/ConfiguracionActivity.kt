package com.servicios.app.settings

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.servicios.app.databinding.ActivityConfiguracionBinding
import java.util.Locale

/**
 * Pantalla: Configuración de la app
 */
class ConfiguracionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfiguracionBinding
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfiguracionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = getSharedPreferences("tuhogar_prefs", Context.MODE_PRIVATE)

        binding.btnBack.setOnClickListener { finish() }

        // ─── A) APARIENCIA ─────────────────────────────────────

        val temaActual = prefs.getString("tema", "sistema")
        when (temaActual) {
            "claro"   -> binding.rgTema.check(binding.rbTemaClaro.id)
            "oscuro"  -> binding.rgTema.check(binding.rbTemaOscuro.id)
            else       -> binding.rgTema.check(binding.rbTemaSistema.id)
        }

        binding.rgTema.setOnCheckedChangeListener { _, checkedId ->
            val tema = when (checkedId) {
                binding.rbTemaClaro.id  -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    "claro"
                }
                binding.rbTemaOscuro.id -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    "oscuro"
                }
                else -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    "sistema"
                }
            }
            prefs.edit().putString("tema", tema).apply()
        }

        binding.swModoGrises.isChecked = prefs.getBoolean("modo_grises", false)
        binding.swModoGrises.setOnCheckedChangeListener { _, checked ->
            prefs.edit().putBoolean("modo_grises", checked).apply()
            recreate()
        }

        binding.sliderTexto.value = prefs.getFloat("tamano_texto", 1.0f)
        binding.sliderTexto.addOnChangeListener { _, value, _ ->
            prefs.edit().putFloat("tamano_texto", value).apply()
            val config = Configuration(resources.configuration)
            config.fontScale = value
            createConfigurationContext(config)
        }

        // ─── B) IDIOMA ─────────────────────────────────────────

        val idiomaActual = prefs.getString("idioma", "es")
        binding.swIdiomaIngles.isChecked = idiomaActual == "en"
        binding.swIdiomaIngles.setOnCheckedChangeListener { _, checked ->
            val locale = if (checked) Locale.forLanguageTag("en") else Locale.forLanguageTag("es")
            Locale.setDefault(locale)
            val config = Configuration(resources.configuration)
            config.setLocale(locale)
            prefs.edit().putString("idioma", if (checked) "en" else "es").apply()
            
            val intent = Intent(this, ConfiguracionActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        // ─── C) NOTIFICACIONES ─────────────────────────────────

        binding.swNotifMensajes.isChecked    = prefs.getBoolean("notif_mensajes",     true)
        binding.swNotifServicios.isChecked   = prefs.getBoolean("notif_servicios",    true)
        binding.swNotifPromociones.isChecked = prefs.getBoolean("notif_promociones",  false)
        binding.swNotifEmail.isChecked       = prefs.getBoolean("notif_email",        true)

        listOf(
            binding.swNotifMensajes    to "notif_mensajes",
            binding.swNotifServicios   to "notif_servicios",
            binding.swNotifPromociones to "notif_promociones",
            binding.swNotifEmail       to "notif_email",
        ).forEach { (sw, key) ->
            sw.setOnCheckedChangeListener { _, checked ->
                prefs.edit().putBoolean(key, checked).apply()
            }
        }

        // ─── D) PRIVACIDAD ─────────────────────────────────────

        binding.swCompartirUbicacion.isChecked = prefs.getBoolean("compartir_ubicacion", true)
        binding.swHistorialBusqueda.isChecked  = prefs.getBoolean("historial_busqueda",  true)

        binding.swCompartirUbicacion.setOnCheckedChangeListener { _, c ->
            prefs.edit().putBoolean("compartir_ubicacion", c).apply()
        }
        binding.swHistorialBusqueda.setOnCheckedChangeListener { _, c ->
            prefs.edit().putBoolean("historial_busqueda", c).apply()
        }

        binding.btnBorrarHistorial.setOnClickListener {
            prefs.edit().remove("historial_busqueda_data").apply()
            Toast.makeText(this, "Historial borrado", Toast.LENGTH_SHORT).show()
        }

        // ─── E) SEGURIDAD ──────────────────────────────────────

        binding.swBiometrico.isChecked = prefs.getBoolean("biometrico", false)
        binding.swBiometrico.setOnCheckedChangeListener { _, checked ->
            prefs.edit().putBoolean("biometrico", checked).apply()
            if (checked)
                Toast.makeText(this, "Autenticación biométrica activada", Toast.LENGTH_SHORT).show()
        }

        // ─── F) ACERCA DE ──────────────────────────────────────

        binding.tvVersion.text = "Versión 1.0.0 (Build 100)"

        binding.rowTerminos.setOnClickListener {
            Toast.makeText(this, "Abriendo Términos y Condiciones", Toast.LENGTH_SHORT).show()
        }
        binding.rowPolitica.setOnClickListener {
            Toast.makeText(this, "Abriendo Política de Privacidad", Toast.LENGTH_SHORT).show()
        }
    }
}
