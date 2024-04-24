package com.it_fionn.blacky_handyman.config

import com.it_fionn.blacky_handyman.config.properties.Discordbot
import com.it_fionn.blacky_handyman.config.properties.Emoji
import com.it_fionn.blacky_handyman.config.properties.Database
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Configuration(
    val discordbot: Discordbot,
    val database: Database,
) {
    companion object {
        fun load(): Configuration {
            return Json.decodeFromString<Configuration>(
                Configuration::class.java.getResource("/application.json").readText()
            )
        }
    }
}
