package br.com.ulbra.pdm.exemplos

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.ulbra.pdm.ui.theme.PDMUlbraTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Exemplos do MODULO 5: estado efemero, estado no ViewModel e preferencias
 * no DataStore. Correspondem aos trechos da apostila do modulo 5.
 *
 * O terceiro nivel de duracao de um dado (banco Room) e a leitura de API REST
 * ja estao implementados no proprio aplicativo, em data/ e ui/screens/.
 */

// --- Nivel 1: estado efemero, mantido por remember ---

@Composable
fun Contador() {
    var cliques by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Você tocou $cliques vezes")
        Button(onClick = { cliques++ }) {
            Text("Tocar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContadorPreview() {
    PDMUlbraTheme { Contador() }
}

// --- Nivel 2: estado da tela, preservado pelo ViewModel (sobrevive a rotacao) ---

class ContadorViewModel : ViewModel() {
    var cliques by mutableStateOf(0)
        private set                       // so o proprio ViewModel altera

    fun incrementar() {
        cliques++
    }
}

@Composable
fun TelaContador(viewModel: ContadorViewModel = viewModel()) {
    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Cliques: ${viewModel.cliques}")
        Button(onClick = { viewModel.incrementar() }) {
            Text("Somar")
        }
    }
}

// --- Nivel 3 (preferencias): DataStore, pares de chave e valor em disco ---

val Context.dataStore by preferencesDataStore(name = "config")
val CHAVE_TEMA = booleanPreferencesKey("tema_escuro")

/** Le a preferencia como um fluxo observavel: emite a cada mudanca. */
fun temaEscuro(contexto: Context): Flow<Boolean> =
    contexto.dataStore.data.map { prefs -> prefs[CHAVE_TEMA] ?: false }

/** Grava a preferencia. E suspend: precisa ser chamada de dentro de uma corrotina. */
suspend fun salvarTema(contexto: Context, escuro: Boolean) {
    contexto.dataStore.edit { prefs -> prefs[CHAVE_TEMA] = escuro }
}
