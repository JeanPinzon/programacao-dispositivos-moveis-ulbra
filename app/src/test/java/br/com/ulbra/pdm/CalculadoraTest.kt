package br.com.ulbra.pdm

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Modulo 6: exemplo de teste de unidade (roda na JVM, sem aparelho).
 * Execute com: ./gradlew test  (ou pelo botao de play ao lado do metodo).
 */
class CalculadoraTest {

    @Test
    fun soma_de_dois_numeros_retorna_o_total() {
        val resultado = 2 + 3
        assertEquals(5, resultado)
    }

    @Test
    fun titulo_em_branco_nao_deve_ser_aceito() {
        val titulo = "   "
        assertTrue(titulo.isBlank())
        assertFalse(titulo.isNotBlank())
    }
}
