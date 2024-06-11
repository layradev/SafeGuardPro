package com.happyhour.safeguardpro.service.repository

import android.content.Context
import com.happyhour.safeguardpro.service.model.Entrega
import com.happyhour.safeguardpro.service.repository.remote.EntregaService
import com.happyhour.safeguardpro.service.repository.remote.RetrofitClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class EntregaRepository(context: Context) {
    private val mRemote = RetrofitClient.createService(EntregaService::class.java)

    val entregaEmpty = Entrega(0, "", 0, 0)

    suspend fun getEntregas(): List<Entrega> {
        return mRemote.getEntregas()
    }

    suspend fun getEntrega(id: Int): Entrega {
        val response = mRemote.getEntregaById(id)
        return if (response.isSuccessful) {
            response.body()?.first() ?: entregaEmpty
        } else {
            entregaEmpty
        }
    }

    suspend fun deleteEntrega(id: Int): Boolean {
        return mRemote.deleteEntregaById(id).isSuccessful
    }

    suspend fun updateEntrega(id: Int, entrega: Entrega): Entrega {
        return mRemote.updateEntrega(
            epi_id = entrega.epi_id.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            data_entrega = entrega.data_entrega.toRequestBody("text/plain".toMediaTypeOrNull()),
            funcionario_id = entrega.funcionario_id.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            entrega_id = id
        ).body() ?: entregaEmpty
    }

    suspend fun insertEntrega(entrega: Entrega): Entrega {
        return mRemote.createEntrega(
            epi_id = entrega.id.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            data_entrega = entrega.data_entrega.toRequestBody("text/plain".toMediaTypeOrNull()),
            funcionario_id = entrega.funcionario_id.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        ).body() ?: entregaEmpty
    }
}