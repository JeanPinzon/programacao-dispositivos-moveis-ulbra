package br.com.ulbra.pdm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.ulbra.pdm.ui.screens.CepScreen
import br.com.ulbra.pdm.ui.screens.LocalizacaoScreen
import br.com.ulbra.pdm.ui.screens.PerfilScreen
import br.com.ulbra.pdm.ui.screens.SensorScreen
import br.com.ulbra.pdm.ui.screens.SobreScreen
import br.com.ulbra.pdm.ui.screens.TarefasScreen

/**
 * Um destino do menu principal. A cada modulo, novas telas sao acrescentadas
 * a lista [destinos] e ao [NavHost].
 */
data class Destino(val rota: String, val titulo: String, val descricao: String)

val destinos = listOf(
    Destino("perfil", "Cartão de perfil", "Módulo 3: componentes, layout e navegação"),
    Destino("localizacao", "Localização", "Módulo 4: permissões e GPS"),
    Destino("sensor", "Acelerômetro", "Módulo 4: leitura de sensores"),
    Destino("tarefas", "Lista de tarefas", "Módulo 5: persistência com Room"),
    Destino("cep", "Consulta de CEP", "Módulo 5: API REST com Retrofit"),
    Destino("sobre", "Sobre", "Módulo 6: versão e publicação")
)

/**
 * Casca de navegacao do aplicativo. A tela inicial (home) lista os destinos;
 * tocar em um deles navega para a tela correspondente.
 */
@Composable
fun AppPDM() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(onNavigate = { rota -> navController.navigate(rota) })
        }
        composable("perfil") {
            PerfilScreen(onBack = { navController.popBackStack() })
        }
        composable("localizacao") {
            LocalizacaoScreen(onBack = { navController.popBackStack() })
        }
        composable("sensor") {
            SensorScreen(onBack = { navController.popBackStack() })
        }
        composable("tarefas") {
            TarefasScreen(onBack = { navController.popBackStack() })
        }
        composable("cep") {
            CepScreen(onBack = { navController.popBackStack() })
        }
        composable("sobre") {
            SobreScreen(onBack = { navController.popBackStack() })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigate: (String) -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("PDM Ulbra") }) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            items(destinos) { destino ->
                Card(
                    onClick = { onNavigate(destino.rota) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 6.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(destino.titulo, style = MaterialTheme.typography.titleMedium)
                        Text(destino.descricao, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
