package br.com.ulbra.pdm

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import br.com.ulbra.pdm.ui.theme.PDMUlbraTheme

/**
 * Ponto de entrada do aplicativo.
 *
 * Modulo 2: os seis callbacks do ciclo de vida registram no Logcat (etiqueta CICLO).
 * Modulo 3 em diante: o conteudo e a casca de navegacao AppPDM(), cujo menu
 * cresce a cada modulo.
 */
class MainActivity : ComponentActivity() {

    private val tag = "CICLO"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate: a tela foi criada")
        enableEdgeToEdge()
        setContent {
            PDMUlbraTheme {
                AppPDM()
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
