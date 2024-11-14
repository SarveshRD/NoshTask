package org.example.noshtask.data.repository

import com.connectup.company.data.repository.NetWorkResult
import com.connectup.company.data.repository.toResultFlow
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import org.example.noshtask.data.model.response.JsonResponse

class CookRepository(private val httpClient: HttpClient) {

    fun getData(): Flow<NetWorkResult<JsonResponse?>> {
        return toResultFlow {
            val response =
                httpClient.get("https://fls8oe8xp7.execute-api.ap-south-1.amazonaws.com/dev/nosh-assignment") {
                    contentType(ContentType.Application.Json)
                }.body<JsonResponse>()
            NetWorkResult.Success(response)
        }
    }
}