package com.happyhour.safeguardpro.service.repository

import android.content.Context
import com.happyhour.safeguardpro.service.model.Epi
import com.happyhour.safeguardpro.service.repository.remote.EpiService
import com.happyhour.safeguardpro.service.repository.remote.RetrofitClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class EpiRepository(context: Context) {
    private val mRemote = RetrofitClient.createService(EpiService::class.java)

    val epiEmpty = Epi(0, "", "", "", 0, "", "")

    suspend fun getEpis(): List<Epi> {
        return mRemote.getEpis()
    }

    suspend fun getEpi(id: Int): Epi {
        val response = mRemote.getEpiById(id)
        return if (response.isSuccessful) {
            response.body()?.first() ?: epiEmpty
        } else {
            epiEmpty
        }
    }

    suspend fun getEpiByCa(ca: Int): Epi {
        val response = mRemote.getEpiByCa(ca)
        return if (response.isSuccessful) {
            response.body()?.first() ?: epiEmpty
        } else {
            epiEmpty
        }
    }

    suspend fun deleteEpi(id: Int): Boolean {
        return mRemote.deleteEpiById(id).isSuccessful
    }

    suspend fun updateEpi(epi: Epi): Epi {
        return mRemote.updateEpi(
            nome = epi.nome.toRequestBody("text/plain".toMediaTypeOrNull()),
            data_fabricacao = epi.data_fabricacao.toRequestBody("text/plain".toMediaTypeOrNull()),
            tempo_uso = epi.tempo_uso.toRequestBody("text/plain".toMediaTypeOrNull()),
            ca = epi.ca.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            validade = epi.validade.toRequestBody("text/plain".toMediaTypeOrNull()),
            modo_uso = epi.modo_uso.toRequestBody("text/plain".toMediaTypeOrNull()),
            id = epi.id
        ).body() ?: epiEmpty
    }

    suspend fun insertEpi(epi: Epi): Epi {
        return mRemote.createEpi(
            nome = epi.nome.toRequestBody("text/plain".toMediaTypeOrNull()),
            data_fabricacao = epi.data_fabricacao.toRequestBody("text/plain".toMediaTypeOrNull()),
            tempo_uso = epi.tempo_uso.toRequestBody("text/plain".toMediaTypeOrNull()),
            ca = epi.ca.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            validade = epi.validade.toRequestBody("text/plain".toMediaTypeOrNull()),
            modo_uso = epi.modo_uso.toRequestBody("text/plain".toMediaTypeOrNull())
        ).body() ?: epiEmpty
    }
}