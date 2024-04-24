package com.it_fionn.blacky_handyman.config.properties

import kotlinx.serialization.Serializable

@Serializable
data class Emoji(
    val success: String,
    val warning: String,
    val error: String,
)
