package br.com.ulbra.pdm.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.location.LocationServices

/**
 * Modulo 4: solicita a permissao de localizacao em tempo de execucao e,
 * uma vez concedida, exibe a ultima localizacao conhecida (latitude/longitude).
 * Se recusada, mostra uma mensagem cordial sem quebrar o app.
 */
@SuppressLint("MissingPermission")
@Composable
fun LocalizacaoScreen(onBack: () -> Unit) {
    val contexto = LocalContext.current
    var mensagem by remember { mutableStateOf("Toque no botão para ver sua localização") }

    val solicitarPermissao = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { concedida ->
        if (concedida) {
            val cliente = LocationServices.getFusedLocationProviderClient(contexto)
            cliente.lastLocation.addOnSuccessListener { local ->
                mensagem = if (local != null) {
                    "Latitude: ${local.latitude}\nLongitude: ${local.longitude}"
                } else {
                    "Não foi possível obter a localização agora. " +
                        "Em emulador, defina uma posição nos controles estendidos."
                }
            }
        } else {
            mensagem = "Permissão negada. O app continua funcionando, " +
                "apenas sem centralizar o mapa em você."
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Localização", style = MaterialTheme.typography.headlineSmall)
        Text(mensagem)
        Button(onClick = {
            solicitarPermissao.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }) {
            Text("Usar minha localização")
        }
        Button(onClick = onBack) {
            Text("Voltar")
        }
    }
}
