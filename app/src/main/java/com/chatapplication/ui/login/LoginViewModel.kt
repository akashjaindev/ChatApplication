package com.chatapplication.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xmpp.AuthCallback
import com.xmpp.AuthenticateUser
import com.xmpp.modal.ChatConnectionConfiguration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val authUser = AuthenticateUser(ChatConnectionConfiguration(useDefaultPassword = true))
    fun login(phoneNumber: String, callback: LoginCallback) {
        viewModelScope.launch(Dispatchers.IO) {
            authUser.authenticate(
                phoneNumber, null, object : AuthCallback {
                    override fun loginSuccessFull() {
                        viewModelScope.launch(Dispatchers.Main) {
                            callback.loginSuccessfully()
                        }
                    }
                }
            )
        }
    }
}