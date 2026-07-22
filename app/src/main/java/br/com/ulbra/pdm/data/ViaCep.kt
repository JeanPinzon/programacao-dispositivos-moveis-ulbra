package br.com.ulbra.pdm.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/** Modulo 5: modelo que espelha o JSON devolvido pela API ViaCEP. */
data class Endereco(
    val logradouro: String? = null,
    val bairro: String? = null,
    val localidade: String? = null,
    val uf: String? = null,
    val erro: Boolean? = null
)

/** Interface do servico REST descrita como uma interface Kotlin. */
interface ViaCepService {
    @GET("ws/{cep}/json/")
    suspend fun buscar(@Path("cep") cep: String): Endereco
}

/** Objeto Retrofit configurado, com a URL base e o conversor de JSON. */
object Rede {
    val servico: ViaCepService = Retrofit.Builder()
        .baseUrl("https://viacep.com.br/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ViaCepService::class.java)
}
