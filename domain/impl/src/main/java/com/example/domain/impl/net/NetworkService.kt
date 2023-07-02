package com.example.domain.impl.net

object NetworkService {
    private val client = Ktor().client
    val serverAPI = ServerAPI(client)
}