package com.happyhour.safeguardpro.service.repository

import android.content.Context
import com.happyhour.safeguardpro.service.model.Funcionario
import com.happyhour.safeguardpro.service.repository.remote.FuncionarioService
import com.happyhour.safeguardpro.service.repository.remote.RetrofitClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class FuncionarioRepository(context: Context) {
    private val mRemote = RetrofitClient.createService(FuncionarioService::class.java)

    val funcionarioEmpty = Funcionario(0, "", "", "", "", false)

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

    suspend fun getFuncionarioByCpf(cpf: String): Funcionario {
        val response = mRemote.getFuncionarioByCpf(cpf)
        return if (response.isSuccessful) {
            response.body()?.first() ?: funcionarioEmpty
        } else {
            funcionarioEmpty
        }
    }

    suspend fun deleteFuncionario(id: Int): Boolean {
        return mRemote.deleteFuncionarioById(id).isSuccessful
    }

    suspend fun updateFuncionario(funcionario: Funcionario): Funcionario {
        return mRemote.updateFuncionario(
            nome = funcionario.nome.toRequestBody("text/plain".toMediaTypeOrNull()),
            cargo = funcionario.cargo.toRequestBody("text/plain".toMediaTypeOrNull()),
            cpf = funcionario.cpf.toRequestBody("text/plain".toMediaTypeOrNull()),
            senha = funcionario.senha.toRequestBody("text/plain".toMediaTypeOrNull()),
            admin = funcionario.admin.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
            funcionario_id = funcionario.id
        ).body() ?: funcionarioEmpty
    }

    suspend fun insertFuncionario(funcionario: Funcionario): Funcionario {
        return mRemote.createFuncionario(
            nome = funcionario.nome.toRequestBody("text/plain".toMediaTypeOrNull()),
            cpf = funcionario.cpf.toRequestBody("text/plain".toMediaTypeOrNull()),
            cargo = funcionario.cargo.toRequestBody("text/plain".toMediaTypeOrNull()),
            senha = funcionario.senha.toRequestBody("text/plain".toMediaTypeOrNull()),
            admin = funcionario.admin.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
        ).body() ?: funcionarioEmpty
    }
}