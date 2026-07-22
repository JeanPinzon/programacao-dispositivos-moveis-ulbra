package br.com.ulbra.pdm.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.ulbra.pdm.BuildConfig

/**
 * Modulo 6: exibe o nome e o codigo da versao (vindos do BuildConfig, definidos
 * no build.gradle.kts). Sao esses valores que crescem a cada publicacao na loja.
 */
@Composable
fun SobreScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Sobre o aplicativo", style = MaterialTheme.typography.headlineSmall)
        Text("PDM Ulbra")
        Text("Versão (versionName): ${BuildConfig.VERSION_NAME}")
        Text("Código da versão (versionCode): ${BuildConfig.VERSION_CODE}")
        Text("ID do aplicativo: ${BuildConfig.APPLICATION_ID}")
        Button(onClick = onBack) {
            Text("Voltar")
        }
    }
}
