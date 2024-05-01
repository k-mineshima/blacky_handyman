package com.it_fionn.blacky_handyman.app.listeners

import com.it_fionn.blacky_handyman.app.entities.Guild
import com.it_fionn.blacky_handyman.app.entities.GuildsTable
import org.jetbrains.exposed.sql.transactions.transaction
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.entities.Member
import org.jetbrains.exposed.sql.transactions.transaction

class MessageReceivedListener(): ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        val prefix: String = transaction {
            Guild.find { GuildsTable.guildId eq event.guild.idLong }.single().prefix
        }

        val member: Member = event.member ?: return

        if (event.message.contentDisplay == "${prefix}leave") {
            member.modifyNickname("${member.effectiveName}(離席中)").queue()
            event.message.reply("ニックネームに離席中を付与しました。いってらっしゃい :wave:").queue()
        }

        if (event.message.contentDisplay == "${prefix}back") {
            member.modifyNickname(member.effectiveName.replace("(離席中)", "")).queue()
            event.message.reply("ニックネームから離席中を削除しました。おかえりなさい :thumbsup:").queue()
        }
    }
}
