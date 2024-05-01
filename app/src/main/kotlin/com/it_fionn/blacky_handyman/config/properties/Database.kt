package com.it_fionn.blacky_handyman.config.properties

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Database(
    val vendor: String,
    val driver: String,
    val host: String,
    val port: Int,
    val databaseName: String,
    val user: String,
    val password: String,
    @SerialName("pool-size")
    val poolSize: Int,
) {
    fun getDatabaseUrl(): String {
        return "jdbc:${this.vendor}://${this.host}:${this.port}/${this.databaseName}"
    }
}
