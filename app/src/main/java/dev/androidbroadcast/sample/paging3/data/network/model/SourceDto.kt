package dev.androidbroadcast.sample.paging3.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceDto(
    @SerialName("id") val id: String?,
    @SerialName("name") val name: String,
)
