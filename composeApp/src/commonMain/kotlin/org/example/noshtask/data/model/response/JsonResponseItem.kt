package org.example.noshtask.data.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JsonResponseItem(
    @SerialName("dishId")
    val dishId: String,
    @SerialName("dishName")
    val dishName: String,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("isPublished")
    val isPublished: Boolean
)