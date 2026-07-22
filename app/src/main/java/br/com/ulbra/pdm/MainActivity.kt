package br.com.ulbra.pdm

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.ulbra.pdm.ui.theme.PDMUlbraTheme

/**
 * Modulo 2: ciclo de vida da Activity.
 *
 * Cada callback registra uma mensagem no Logcat com a etiqueta "CICLO".
 * Rode o app, abra o Logcat, filtre por CICLO e experimente: pressione home,
 * volte ao app e, principalmente, GIRE a tela para ver a Activity ser
 * destruida e recriada.
 */
class MainActivity : ComponentActivity() {

    private val tag = "CICLO"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate: a tela foi criada")
        enableEdgeToEdge()
        setContent {
            PDMUlbraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaCicloDeVida(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart: a tela ficou visivel")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume: a tela esta em primeiro plano")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause: a tela perdeu o foco")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop: a tela deixou de ser visivel")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy: a tela foi destruida")
    }
}

@Composable
fun TelaCicloDeVida(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Observando o ciclo de vida", style = MaterialTheme.typography.titleLarge)
        Text(
            "Abra a janela Logcat no Android Studio e filtre pela etiqueta CICLO. " +
                "Depois experimente: pressione o botao home, volte ao app e gire a tela. " +
                "Observe a ordem em que os callbacks aparecem."
        )
    }
}
