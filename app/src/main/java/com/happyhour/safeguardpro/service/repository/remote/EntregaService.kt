package com.happyhour.safeguardpro.service.repository.remote

import com.happyhour.safeguardpro.service.model.Entrega
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface EntregaService {

    @GET("get_entrega/{entrega_id}")
    suspend fun getEntregaById(@Path("entrega_id") id: Int): Response<List<Entrega>>

    @GET("delete_entrega/{entrega_id}")
    suspend fun deleteEntregaById(@Path("entrega_id") id: Int): Response<Entrega>

    @GET("get_entrega")
    suspend fun getEntregas(): List<Entrega>

    @Multipart
    @POST("create_entrega")
    suspend fun createEntrega(
        @Part("data_entrega") data_entrega: RequestBody,
        @Part("funcionario_id") funcionario_id: RequestBody,
        @Part("epi_id") epi_id: RequestBody,
    ): Response<Entrega>

    @PUT("update_entrega/{entrega_id}")
    suspend fun updateEntrega(
        @Part("data_entrega") data_entrega: RequestBody,
        @Part("funcionario_id") funcionario_id: RequestBody,
        @Part("epi_id") epi_id: RequestBody,
        @Path("entrega_id") entrega_id: Int,
    ): Response<Entrega>
}
