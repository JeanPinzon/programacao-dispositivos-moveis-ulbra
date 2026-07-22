// Plugins do projeto (aplicados nos modulos)
plugins {
    id("com.android.application") version "8.5.2" apply false
    id("org.jetbrains.kotlin.android") version "2.0.20" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.20" apply false
    // Modulo 5: processador de anotacoes do Room
    id("com.google.devtools.ksp") version "2.0.20-1.0.25" apply false
}
