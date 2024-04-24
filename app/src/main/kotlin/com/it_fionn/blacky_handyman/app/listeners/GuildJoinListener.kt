package com.it_fionn.blacky_handyman.app.listeners

import com.it_fionn.blacky_handyman.app.entities.Guild
import org.jetbrains.exposed.sql.transactions.transaction
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.events.guild.GuildJoinEvent

class GuildJoinListener(
    private val defaultPrefix: String
): ListenerAdapter() {
    override fun onGuildJoin(event: GuildJoinEvent) {
        transaction {
            Guild.new {
                guildId = event.guild.idLong
                name = event.guild.name
                prefix = defaultPrefix
            }
        }
    }
}
