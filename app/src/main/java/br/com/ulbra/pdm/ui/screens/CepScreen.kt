package br.com.ulbra.pdm.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.ulbra.pdm.data.Endereco
import br.com.ulbra.pdm.data.Rede
import kotlinx.coroutines.launch

/** Modulo 5: os tres estados possiveis de uma tela que depende de rede. */
sealed interface EstadoCep {
    data object Inicial : EstadoCep
    data object Carregando : EstadoCep
    data class Sucesso(val endereco: Endereco) : EstadoCep
    data class Erro(val mensagem: String) : EstadoCep
}

class CepViewModel : ViewModel() {
    var estado by mutableStateOf<EstadoCep>(EstadoCep.Inicial)
        private set

    fun buscar(cep: String) {
        viewModelScope.launch {
            estado = EstadoCep.Carregando
            estado = try {
                val endereco = Rede.servico.buscar(cep.filter { it.isDigit() })
                if (endereco.erro == true) {
                    EstadoCep.Erro("CEP não encontrado")
                } else {
                    EstadoCep.Sucesso(endereco)
                }
            } catch (e: Exception) {
                EstadoCep.Erro("Não foi possível consultar o CEP. Verifique a conexão.")
            }
        }
    }
}

@Composable
fun CepScreen(onBack: () -> Unit, viewModel: CepViewModel = viewModel()) {
    var cep by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Consulta de CEP (Retrofit)", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = cep,
            onValueChange = { cep = it },
            label = { Text("Digite um CEP, ex: 01001000") },
            singleLine = true
        )
        Button(onClick = { viewModel.buscar(cep) }) {
            Text("Buscar")
        }

        when (val estado = viewModel.estado) {
            is EstadoCep.Inicial -> Text("Informe um CEP e toque em Buscar.")
            is EstadoCep.Carregando -> CircularProgressIndicator()
            is EstadoCep.Sucesso -> {
                val e = estado.endereco
                Text("Logradouro: ${e.logradouro.orEmpty()}")
                Text("Bairro: ${e.bairro.orEmpty()}")
                Text("Cidade: ${e.localidade.orEmpty()} / ${e.uf.orEmpty()}")
            }
            is EstadoCep.Erro -> Text(estado.mensagem, color = MaterialTheme.colorScheme.error)
        }

        Button(onClick = onBack) {
            Text("Voltar")
        }
    }
}
