package com.it_fionn.blacky_handyman

import com.it_fionn.blacky_handyman.config.Configuration
import org.jetbrains.exposed.sql.Database

class BlackyHandyman(
    private val config: Configuration
) {
    init {
        this.connectDatabase()
    }

    fun start() {
        println("starting...")
    }

    private fun connectDatabase() {
        Database.connect(
            this.config.database.getDatabaseUrl(),
            driver = this.config.database.driver,
            user = this.config.database.user,
            password = this.config.database.password,
        )
    }
}
