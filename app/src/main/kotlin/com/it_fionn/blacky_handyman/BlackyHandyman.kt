package com.it_fionn.blacky_handyman

import com.it_fionn.blacky_handyman.config.Configuration
import org.jetbrains.exposed.sql.Database
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent

class BlackyHandyman(
    private val config: Configuration
) {
    init {
        this.connectDatabase()
    }

    fun start() {
        val discordbot = JDABuilder.createDefault(this.config.discordbot.token).enableIntents(GatewayIntent.MESSAGE_CONTENT).build()
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
