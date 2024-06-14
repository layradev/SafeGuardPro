package com.happyhour.safeguardpro.service.model

data class Funcionario(
    var id: Int = 0,
    var nome: String = "",
    var cpf: String = "",
    var cargo: String = "",
    var senha: String = "",
    var admin: Boolean = false
)