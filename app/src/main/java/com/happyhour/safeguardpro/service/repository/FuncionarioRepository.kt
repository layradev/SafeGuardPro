package com.happyhour.safeguardpro.service.repository

import android.content.Context
import com.happyhour.safeguardpro.service.model.Funcionario
import com.happyhour.safeguardpro.service.repository.remote.FuncionarioService
import com.happyhour.safeguardpro.service.repository.remote.RetrofitClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class FuncionarioRepository(context: Context) {
    private val mRemote = RetrofitClient.createService(FuncionarioService::class.java)

    val funcionarioEmpty = Funcionario(0, "", "", "")

    suspend fun getFuncionarios(): List<Funcionario> {
        return mRemote.getFuncionarios()
    }

    suspend fun getFuncionario(id: Int): Funcionario {
        val response = mRemote.getFuncionarioById(id)
        return if (response.isSuccessful) {
            response.body()?.first() ?: funcionarioEmpty
        } else {
            funcionarioEmpty
        }
    }

    suspend fun deleteFuncionario(id: Int): Boolean {
        return mRemote.deleteFuncionarioById(id).isSuccessful
    }

    suspend fun updateFuncionario(id: Int, funcionario: Funcionario): Funcionario {
        return mRemote.updateFuncionario(
            nome = funcionario.nome.toRequestBody("text/plain".toMediaTypeOrNull()),
            cargo = funcionario.cargo.toRequestBody("text/plain".toMediaTypeOrNull()),
            cpf = funcionario.cpf.toRequestBody("text/plain".toMediaTypeOrNull()),
            funcionario_id = id
        ).body() ?: funcionarioEmpty
    }

    suspend fun insertFuncionario(funcionario: Funcionario): Funcionario {
        return mRemote.createFuncionario(
            nome = funcionario.nome.toRequestBody("text/plain".toMediaTypeOrNull()),
            cpf = funcionario.cpf.toRequestBody("text/plain".toMediaTypeOrNull()),
            cargo = funcionario.cargo.toRequestBody("text/plain".toMediaTypeOrNull())
        ).body() ?: funcionarioEmpty
    }

}