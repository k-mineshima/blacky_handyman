package com.it_fionn.blacky_handyman.config.properties

data class Database(
    val vendor: String,
    val driver: String,
    val host: String,
    val port: Int,
    val databaseName: String,
    val user: String,
    val password: String,
    val timezone: String,
    val poolSize: Int,
) {
    fun getDatabaseUrl(): String {
        return "jdbc:${this.vendor}://${this.host}:${this.port}/${this.databaseName}?serverTimezone=${this.timezone}"
    }
}
