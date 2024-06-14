package com.happyhour.safeguardpro.service.repository.remote

import com.happyhour.safeguardpro.service.model.Epi
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface EpiService {
    @GET("get_epi/{epi_id}")
    suspend fun getEpiById(@Path("epi_id") id: Int): Response<List<Epi>>

    @GET("get_epi/{epi_id}")
    suspend fun getEpiByCa(@Path("epi_ca") ca: Int): Response<List<Epi>>

    @GET("delete_epi/{epi_id}")
    suspend fun deleteEpiById(@Path("epi_id") id: Int): Response<Epi>

    @GET("select_epi")
    suspend fun getEpis(): List<Epi>

    @Multipart
    @POST("create_epi")
    suspend fun createEpi(
        @Part("nome") nome: RequestBody,
        @Part("data_fabricacao") data_fabricacao: RequestBody,
        @Part("tempo_uso") tempo_uso: RequestBody,
        @Part("ca") ca: RequestBody,
        @Part("validade") validade: RequestBody,
        @Part("modo_uso") modo_uso: RequestBody
    ): Response<Epi>

    @Multipart
    @PUT("update_epi/{epi_id}")
    suspend fun updateEpi(
        @Path("epi_id") id: Int,
        @Part("nome") nome: RequestBody,
        @Part("data_fabricacao") data_fabricacao: RequestBody,
        @Part("tempo_uso") tempo_uso: RequestBody,
        @Part("ca") ca: RequestBody,
        @Part("validade") validade: RequestBody,
        @Part("modo_uso") modo_uso: RequestBody
    ): Response<Epi>
}