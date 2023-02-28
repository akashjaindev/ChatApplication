package com.xmpp.modal

data class ChatConnectionConfiguration(
    val debug: Boolean = false,
    val host: String = "localhost",
    val domain: String = "localhost",
    val hostAddress: String = "192.168.29.39",
    val useDefaultPassword: Boolean = true,
    val defaultUserPassword: String = "defaultUserPassword"
)
