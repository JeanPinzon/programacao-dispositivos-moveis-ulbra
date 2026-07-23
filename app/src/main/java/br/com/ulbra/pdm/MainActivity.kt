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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PDMUlbraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Saudacao(
                        nome = "Jean",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Saudacao(nome: String, modifier: Modifier = Modifier) {
    Text(
        text = "Olá, $nome! Este é o meu primeiro app em Programação para Dispositivos Móveis.",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun SaudacaoPreview() {
    PDMUlbraTheme {
        Saudacao("Ulbra")
    }
}
