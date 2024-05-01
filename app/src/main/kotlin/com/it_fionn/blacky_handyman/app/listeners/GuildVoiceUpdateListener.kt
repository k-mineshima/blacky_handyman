package com.it_fionn.blacky_handyman.app.listeners

import com.it_fionn.blacky_handyman.app.entities.Guild
import com.it_fionn.blacky_handyman.app.entities.GuildsTable
import org.jetbrains.exposed.sql.transactions.transaction
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.events.guild.GuildJoinEvent
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent
import net.dv8tion.jda.api.entities.channel.ChannelType
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel

class GuildVoiceUpdateListener(): ListenerAdapter() {
    override fun onGuildVoiceUpdate(event: GuildVoiceUpdateEvent) {
        if (event.channelLeft != null) return
        val channelJoined = event.channelJoined ?: return
        if (channelJoined.type != ChannelType.VOICE) return
        if (channelJoined.members.size > 1) return

        val primaryChannelId: String = transaction {
            Guild.find { GuildsTable.guildId eq event.guild.idLong }.single().primaryChannelId
        } ?: return
        val primaryChannel: TextChannel = event.guild.getTextChannelById(primaryChannelId) ?: return
        primaryChannel.sendMessage("${event.entity.asMention} が ${channelJoined.asMention} でボイスチャットを始めました！").queue()
    }
}
