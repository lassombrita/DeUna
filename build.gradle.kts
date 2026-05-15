// ─────────────────────────────────────────────────────────────
// build.gradle.kts  ←  RAÍZ del proyecto
// Registra los plugins a nivel global; ninguno se aplica aquí.
// ─────────────────────────────────────────────────────────────
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android)      apply false
    alias(libs.plugins.kotlin.compose)      apply false
    alias(libs.plugins.hilt)                apply false
    alias(libs.plugins.ksp)                 apply false
}
