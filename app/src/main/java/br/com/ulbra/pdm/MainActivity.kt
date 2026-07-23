package br.com.ulbra.pdm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.ulbra.pdm.ui.theme.PDMUlbraTheme

/**
 * Projeto base da disciplina: um app Android limpo, sem nenhum conteudo das aulas.
 *
 * O conteudo de cada modulo esta nas branches modulo-1 a modulo-6.
 * Comece pela branch modulo-1: git checkout modulo-1
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PDMUlbraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaInicial(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TelaInicial(modifier: Modifier = Modifier) {
    Text(
        text = "Projeto base da disciplina. Troque para a branch do módulo que quer estudar.",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun TelaInicialPreview() {
    PDMUlbraTheme {
        TelaInicial()
    }
}
