package com.happyhour.safeguardpro.service.repository.remote

import com.happyhour.safeguardpro.service.model.Funcionario
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface FuncionarioService {

    @GET("select_funcionarios/{funcionarios_id}")
    suspend fun getFuncionarioById(@Path("funcionario_id") id: Int): Response<List<Funcionario>>

    @GET("select_funcionariocpf/{funcionario_cpf}")
    suspend fun getFuncionarioByCpf(@Path("funcionario_cpf") cpf: String): Response<List<Funcionario>>

    @GET("select_funcionario")
    suspend fun getFuncionarios(): List<Funcionario>

    @DELETE("delete_funcionario/{funcionario_id}")
    suspend fun deleteFuncionarioById(@Path("funcionario_id") id: Int): Response<Funcionario>

    @Multipart
    @POST("add_funcionario")
    suspend fun createFuncionario(
        @Part("nome") nome: RequestBody,
        @Part("cpf") cpf: RequestBody,
        @Part("cargo") cargo: RequestBody,
        @Part("senha") senha: RequestBody,
        @Part("admin") admin: RequestBody,
    ): Response<Funcionario>

    @Multipart
    @PUT("update_funcionario/{funcionario_id}")
    suspend fun updateFuncionario(
        @Path("funcionario_id") funcionario_id: Int,
        @Part("nome") nome: RequestBody,
        @Part("cpf") cpf: RequestBody,
        @Part("cargo") cargo: RequestBody,
        @Part("senha") senha: RequestBody,
        @Part("admin") admin: RequestBody,
    ): Response<Funcionario>
}