package org.example.noshtask.data.factory

import io.ktor.client.HttpClient

expect class ApiService() {
    fun build(): HttpClient
}


