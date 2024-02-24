package me.jose.credit.application.system.exception

import java.time.LocalDateTime

data class exceptionDetails(
    val title: String,
    val timestamp: LocalDateTime,
    val status: Int,
    val exception: String,
    val details: MutableMap<String, String?>
)
