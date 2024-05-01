package com.it_fionn.blacky_handyman.app.entities

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object GuildsTable: LongIdTable(name = "guilds") {
    val guildId: Column<Long> = long("guild_id").uniqueIndex()
    val name: Column<String> = varchar("name", 255)
    val prefix: Column<String> = varchar("prefix", 255)
    val primaryChannelId: Column<String?> = varchar("primary_channel_id", 255).nullable()
    val createdAt: Column<LocalDateTime> = datetime("created_at").default(LocalDateTime.now())
    val updatedAt: Column<LocalDateTime> = datetime("updated_at").default(LocalDateTime.now())
}

class Guild(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<Guild>(GuildsTable)

    var guildId by GuildsTable.guildId
    var name by GuildsTable.name
    var prefix by GuildsTable.prefix
    var primaryChannelId by GuildsTable.primaryChannelId
    val createdAt by GuildsTable.createdAt
    val updatedAt by GuildsTable.updatedAt
}
