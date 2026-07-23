package br.com.ulbra.pdm.exemplos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Exemplos do MODULO 2: fundamentos da linguagem Kotlin.
 *
 * Cada funcao abaixo corresponde a um trecho de codigo da apostila do modulo 2,
 * na mesma ordem. Para ver todos os resultados na tela, abra este arquivo no
 * Android Studio e use a pre-visualizacao de [ExemplosKotlinPreview].
 */

// 1. val (imutavel) e var (mutavel)
fun exemploValVar(): String {
    val nome = "Ulbra"        // imutavel: nao pode ser reatribuida
    var contador = 0          // mutavel: pode mudar
    contador = contador + 1
    return "nome=$nome | contador=$contador"
}

// 2. templates de string
fun exemploTemplates(): String {
    val produto = "Notebook"
    val quantidade = 3
    return "Comprei $quantidade unidades de $produto (o dobro seria ${quantidade * 2})"
}

// 3. funcoes: com retorno, com valor padrao e de expressao unica
fun saudacao(nome: String): String {
    return "Olá, $nome!"
}

fun boasVindas(nome: String = "visitante") = "Bem-vindo, $nome"

// 4. data class: agrupa dados relacionados
data class Aluno(val nome: String, val matricula: Int, val ativo: Boolean = true)

fun exemploDataClass(): String {
    val a1 = Aluno("Pandora", 12345)
    return "$a1 | a1.nome=${a1.nome}"
}

// 5. controle de fluxo: if como expressao, when e for
fun exemploFluxo(nota: Double): String {
    val situacao = if (nota >= 6.0) "Aprovado" else "Reprovado"

    val conceito = when {
        nota >= 9.0 -> "A"
        nota >= 7.0 -> "B"
        nota >= 6.0 -> "C"
        else -> "D"
    }

    val cursos = listOf("Android", "Kotlin", "Compose")
    val estudando = StringBuilder()
    for (curso in cursos) {
        estudando.append(curso).append(" ")
    }

    return "nota=$nota -> $situacao (conceito $conceito) | estudando: ${estudando.toString().trim()}"
}

// 6. seguranca contra nulos
fun exemploNulos(sobrenome: String?): String {
    val tamanhoSeguro = sobrenome?.length ?: 0   // operador elvis
    val descricao = if (sobrenome != null) {
        "tem $tamanhoSeguro letras"
    } else {
        "não informado (o elvis devolveu $tamanhoSeguro)"
    }
    return "sobrenome=$sobrenome -> $descricao"
}

@Preview(showBackground = true)
@Composable
fun ExemplosKotlinPreview() {
    val linhas = listOf(
        exemploValVar(),
        exemploTemplates(),
        saudacao("Camila"),
        boasVindas(),
        boasVindas("Jean"),
        exemploDataClass(),
        exemploFluxo(7.5),
        exemploNulos(null),
        exemploNulos("Pinzon")
    )

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        linhas.forEach { linha -> Text(linha) }
    }
}
