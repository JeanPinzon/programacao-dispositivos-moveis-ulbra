package br.com.ulbra.pdm.exemplos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.ulbra.pdm.ui.theme.PDMUlbraTheme

/**
 * Exemplos do MODULO 3: componentes, estado de um campo de texto e navegacao.
 *
 * Correspondem aos trechos de codigo da apostila do modulo 3. Use as
 * pre-visualizacoes (@Preview) para ver cada um isoladamente.
 */

// --- Campo de texto ligado a um estado ---

@Composable
fun CampoNome() {
    var nome by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Digite seu nome") }
        )
        Text("Olá, $nome!")
    }
}

@Preview(showBackground = true)
@Composable
fun CampoNomePreview() {
    PDMUlbraTheme { CampoNome() }
}

// --- Navegacao entre duas telas (exemplo isolado da apostila) ---

@Composable
fun AppNavegacao() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "inicio") {
        composable("inicio") {
            TelaInicio(aoAbrirDetalhe = { id ->
                navController.navigate("detalhe/$id")
            })
        }
        composable("detalhe/{id}") { entrada ->
            val id = entrada.arguments?.getString("id") ?: ""
            TelaDetalhe(
                id = id,
                aoVoltar = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun TelaInicio(aoAbrirDetalhe: (String) -> Unit) {
    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Tela inicial", style = MaterialTheme.typography.headlineSmall)
        Button(onClick = { aoAbrirDetalhe("42") }) {
            Text("Abrir detalhe do item 42")
        }
    }
}

@Composable
fun TelaDetalhe(id: String, aoVoltar: () -> Unit) {
    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Detalhe do item $id", style = MaterialTheme.typography.headlineSmall)
        Button(onClick = aoVoltar) { Text("Voltar") }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaInicioPreview() {
    PDMUlbraTheme { TelaInicio(aoAbrirDetalhe = {}) }
}
