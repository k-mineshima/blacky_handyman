package com.it_fionn.blacky_handyman.config

import com.it_fionn.blacky_handyman.config.properties.Database

data class Configuration(
    val database: Database
) {
    companion object {
        fun load(): Configuration {
            return Configuration(
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
