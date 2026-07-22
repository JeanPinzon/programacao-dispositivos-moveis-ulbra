package br.com.ulbra.pdm.ui.screens

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.ulbra.pdm.data.AppDatabase
import br.com.ulbra.pdm.data.Tarefa
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Modulo 5: ViewModel que expoe a lista observada do banco Room e as operacoes
 * de adicionar, alternar e remover tarefas.
 */
class TarefaViewModel(app: Application) : AndroidViewModel(app) {

    private val dao = AppDatabase.obter(app).tarefaDao()

    val tarefas: StateFlow<List<Tarefa>> =
        dao.listar().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun adicionar(titulo: String) = viewModelScope.launch {
        if (titulo.isNotBlank()) dao.inserir(Tarefa(titulo = titulo.trim()))
    }

    fun alternar(tarefa: Tarefa) = viewModelScope.launch {
        dao.atualizar(tarefa.copy(concluida = !tarefa.concluida))
    }

    fun remover(tarefa: Tarefa) = viewModelScope.launch {
        dao.remover(tarefa)
    }
}

/**
 * Lista de tarefas persistente. Feche o app por completo e reabra: as tarefas
 * continuam la, pois estao no banco. A lista se atualiza sozinha (Flow).
 */
@Composable
fun TarefasScreen(onBack: () -> Unit, viewModel: TarefaViewModel = viewModel()) {
    val tarefas by viewModel.tarefas.collectAsState()
    var texto by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Lista de tarefas (Room)", style = MaterialTheme.typography.headlineSmall)

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = texto,
                onValueChange = { texto = it },
                modifier = Modifier.weight(1f),
                label = { Text("Nova tarefa") },
                singleLine = true
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = {
                viewModel.adicionar(texto)
                texto = ""
            }) {
                Text("Add")
            }
        }

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(tarefas, key = { it.id }) { tarefa ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = tarefa.concluida,
                        onCheckedChange = { viewModel.alternar(tarefa) }
                    )
                    Text(tarefa.titulo, modifier = Modifier.weight(1f))
                    IconButton(onClick = { viewModel.remover(tarefa) }) {
                        Icon(Icons.Filled.Delete, contentDescription = "Remover")
                    }
                }
            }
        }

        Button(onClick = onBack) {
            Text("Voltar")
        }
    }
}
