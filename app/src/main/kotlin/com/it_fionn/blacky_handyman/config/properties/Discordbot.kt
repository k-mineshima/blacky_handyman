package com.it_fionn.blacky_handyman.config.properties

data class Discordbot(
    val token: String,
    val defaultPrefix: String,
    val ownerId: String,
    val emojis: Emoji,
)
