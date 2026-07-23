package br.com.ulbra.pdm.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.ulbra.pdm.ui.theme.PDMUlbraTheme

/**
 * Modulo 3: tela que demonstra componentes, layout, Modifier e Material Design.
 */
@Composable
fun PerfilScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Cartão de perfil", style = MaterialTheme.typography.headlineSmall)
        CartaoPerfil(
            nome = "Camila",
            curso = "Análise e Desenvolvimento de Sistemas"
        )
        Button(onClick = onBack) {
            Text("Voltar")
        }
    }
}

@Composable
fun CartaoPerfil(nome: String, curso: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Foto de perfil de $nome",
                modifier = Modifier.size(64.dp)
            )
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(nome, style = MaterialTheme.typography.titleMedium)
                Text(curso, style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(8.dp))
                Button(onClick = { /* acao: ver perfil */ }) {
                    Text("Ver perfil")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartaoPerfilPreview() {
    PDMUlbraTheme {
        CartaoPerfil(nome = "Pandora", curso = "Análise e Desenvolvimento de Sistemas")
    }
}
