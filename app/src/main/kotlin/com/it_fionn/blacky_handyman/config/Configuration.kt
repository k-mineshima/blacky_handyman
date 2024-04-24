package com.it_fionn.blacky_handyman.config

import com.it_fionn.blacky_handyman.config.properties.Discordbot
import com.it_fionn.blacky_handyman.config.properties.Emoji
import com.it_fionn.blacky_handyman.config.properties.Database

data class Configuration(
    val discordbot: Discordbot,
    val database: Database,
) {
    companion object {
        fun load(): Configuration {
            return Configuration(
                discordbot = Discordbot(
                    token = System.getenv("BOT_TOKEN"),
                    defaultPrefix = "!",
                    ownerId = "763057879057170432",
                    emojis = Emoji(
                        success = ":thumbsup:",
                        warning =":exclamation:",
                        error = ":x:",
                    )
                ),
                database = Database(
                    vendor = "mysql",
                    driver = "com.mysql.cj.jdbc.Driver",
                    host = "localhost",
                    port = 3306,
                    databaseName = "blacky_handyman",
                    user = "fionn",
                    password = "Kana_19940806",
                    timezone = "JST",
                    poolSize = 10
                )
            )
        }
    }
}
