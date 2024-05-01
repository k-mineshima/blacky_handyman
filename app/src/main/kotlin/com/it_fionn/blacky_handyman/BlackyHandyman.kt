package com.it_fionn.blacky_handyman

import com.it_fionn.blacky_handyman.config.Configuration
import com.it_fionn.blacky_handyman.app.listeners.GuildJoinListener
import com.it_fionn.blacky_handyman.app.listeners.GuildVoiceUpdateListener
import com.it_fionn.blacky_handyman.app.listeners.MessageReceivedListener
import org.jetbrains.exposed.sql.Database
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource

class BlackyHandyman(
    private val config: Configuration
) {
    init {
        this.connectDatabase()
    }

    fun start() {
        val discordbot = JDABuilder.createDefault(this.config.discordbot.token).let {
            it.enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_VOICE_STATES)
              .addEventListeners(
                GuildJoinListener(this.config.discordbot.defaultPrefix),
                GuildVoiceUpdateListener(),
                MessageReceivedListener(),
              )
              .build()
        }
    }

    private fun connectDatabase() {
        val hikariConfig: HikariConfig = HikariConfig().also {
            it.jdbcUrl = this.config.database.getDatabaseUrl()
            it.driverClassName = this.config.database.driver
            it.username = this.config.database.user
            it.password = this.config.database.password
            it.maximumPoolSize = this.config.database.poolSize
        }

        Database.connect(HikariDataSource(hikariConfig))
    }
}
