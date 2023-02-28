package com.xmpp

import com.xmpp.modal.ChatConnectionConfiguration
import org.jivesoftware.smack.*
import org.jivesoftware.smack.sasl.SASLErrorException
import org.jivesoftware.smack.tcp.XMPPTCPConnection
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration
import org.jivesoftware.smackx.iqregister.AccountManager
import org.jxmpp.jid.parts.Localpart
import java.net.InetAddress

class AuthenticateUser constructor(private val chatConfig: ChatConnectionConfiguration) {
    init {
        SmackConfiguration.DEBUG = chatConfig.debug
    }

    private var connection: AbstractXMPPConnection? = null

    private fun connect(userName: String, password: String?) {
        val config: XMPPTCPConnectionConfiguration = XMPPTCPConnectionConfiguration.builder()
            .setUsernameAndPassword(
                userName, if (chatConfig.useDefaultPassword) {
                    chatConfig.defaultUserPassword
                } else {
                    password
                }
            ).setHost(chatConfig.host).setXmppDomain(chatConfig.domain)
            .setHostAddress(InetAddress.getByName(chatConfig.hostAddress))
            .setSecurityMode(ConnectionConfiguration.SecurityMode.ifpossible)
            .setPort(5222).build()
        connection = XMPPTCPConnection(config)
    }

    private fun addConnectionListener(callback: AuthCallback?) {
        connection?.addConnectionListener(object : ConnectionListener {
            override fun connected(connection: XMPPConnection?) {
                connection.toString()
                if(connection?.isAuthenticated == true){
                    callback?.loginSuccessFull()
                }
            }

            override fun authenticated(connection: XMPPConnection?, resumed: Boolean) {
                callback?.loginSuccessFull()
            }

            override fun connectionClosed() {
                connection.toString()
            }

            override fun connectionClosedOnError(e: java.lang.Exception?) {
                connection.toString()
            }

        })
    }

    fun authenticate(userName: String? = null, password: String? = null,callback:AuthCallback?=null) {
        if (connection == null && userName != null) {
            connect(userName, password)
            addConnectionListener(callback)
        }
        try {
            connection?.let {
                if (it.isConnected) {
                    if(!it.isAuthenticated){
                        it.login()
                    } else {
                        callback?.loginSuccessFull()
                    }
                } else {
                    it.connect()
                }
            }
        } catch (e: SASLErrorException) {
            registerUser()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun registerUser() {
        connection?.let {
            val accountManager: AccountManager = AccountManager.getInstance(it)
            try {
                if (accountManager.supportsAccountCreation()) {
                    accountManager.sensitiveOperationOverInsecureConnection(true)
                    accountManager.createAccount(
                        Localpart.from(it.configuration.username.toString()),
                        it.configuration.password
                    )
                    authenticate()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}