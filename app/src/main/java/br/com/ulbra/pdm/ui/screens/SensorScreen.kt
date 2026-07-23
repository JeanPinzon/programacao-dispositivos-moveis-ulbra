package br.com.ulbra.pdm.ui.screens

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

/**
 * Modulo 4: le o acelerometro. O listener e registrado quando a tela aparece e
 * cancelado quando ela some (onDispose do DisposableEffect), respeitando o
 * ciclo de vida para nao desperdicar bateria.
 */
@Composable
fun SensorScreen(onBack: () -> Unit) {
    val contexto = LocalContext.current
    var leitura by remember { mutableStateOf("Aguardando leitura...") }

    DisposableEffect(Unit) {
        val gerenciador = contexto.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val acelerometro = gerenciador.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        val listener = object : SensorEventListener {
            override fun onSensorChanged(evento: SensorEvent) {
                val x = evento.values[0]
                val y = evento.values[1]
                val z = evento.values[2]
                leitura = "x = %.2f\ny = %.2f\nz = %.2f".format(x, y, z)
            }

            override fun onAccuracyChanged(sensor: Sensor?, precisao: Int) {}
        }

        gerenciador.registerListener(listener, acelerometro, SensorManager.SENSOR_DELAY_UI)
        onDispose { gerenciador.unregisterListener(listener) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Acelerômetro", style = MaterialTheme.typography.headlineSmall)
        Text(
            "Movimente o aparelho e observe os valores dos três eixos. " +
                "No emulador, use os controles estendidos (Virtual sensors)."
        )
        Text(leitura, style = MaterialTheme.typography.titleMedium)
        Button(onClick = onBack) {
            Text("Voltar")
        }
    }
}
