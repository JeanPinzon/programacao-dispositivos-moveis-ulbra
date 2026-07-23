package br.com.ulbra.pdm.exemplos

import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp

/**
 * Exemplo do MODULO 4: captura de foto acionando o aplicativo de camera do
 * sistema, o caminho mais simples descrito na apostila.
 *
 * Observacao: este contrato (TakePicturePreview) delega a captura ao app de
 * camera do proprio aparelho e devolve uma miniatura, por isso NAO exige a
 * permissao CAMERA declarada no manifesto. Para pre-visualizacao dentro da
 * propria interface, com controles, usa-se a biblioteca CameraX.
 *
 * Nao ha @Preview aqui porque o exemplo depende da camera do aparelho: para
 * testar, chame BotaoTirarFoto() em uma tela e rode em um celular fisico.
 */
@Composable
fun BotaoTirarFoto() {
    var imagem by remember { mutableStateOf<Bitmap?>(null) }

    val tirarFoto = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        imagem = bitmap   // recebe a miniatura capturada
    }

    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Câmera", style = MaterialTheme.typography.headlineSmall)
        Button(onClick = { tirarFoto.launch(null) }) {
            Text("Tirar foto")
        }
        imagem?.let { bitmap ->
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Foto capturada"
            )
        }
    }
}
