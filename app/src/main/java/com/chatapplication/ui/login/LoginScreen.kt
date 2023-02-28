package com.chatapplication.ui.login

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.chatapplication.R

@Composable
fun LoginScreen(navigationCallback: NavHostController?) {
    val phoneNumberValue = remember { mutableStateOf(TextFieldValue()) }
    val viewModel: LoginViewModel = viewModel()
    val ctx = LocalContext.current
    val sharedPref =
        ctx.getSharedPreferences(ctx.getString(R.string.app_name), Context.MODE_PRIVATE)
    val string = sharedPref.getString("phoneNumber", null)
    if (string != null) {
        loginUser(viewModel, string, sharedPref, navigationCallback)
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = phoneNumberValue.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                label = { Text(text = "Enter Phone Number") },
                onValueChange = { phoneNumberValue.value = it })
            Button(onClick = {
                val phoneNumber = phoneNumberValue.value.text
                if (phoneNumber.isNotEmpty()) {
                    if (android.util.Patterns.PHONE.matcher(phoneNumber).matches()) {
                        loginUser(viewModel, phoneNumber, sharedPref, navigationCallback)
                    } else {
                        Toast.makeText(ctx, "Phone Number is invalid..", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(ctx, "Please enter phone number", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Login")
            }
        }
    }
}

private fun loginUser(
    viewModel: LoginViewModel,
    phoneNumber: String,
    sharedPref: SharedPreferences,
    navigationCallback: NavHostController?
) {
    viewModel.login(phoneNumber, object : LoginCallback {
        override fun loginSuccessfully() {
            with(sharedPref.edit()) {
                putString("phoneNumber", phoneNumber)
                apply()
            }
//            navigationCallback?.popBackStack()
            navigationCallback?.navigate("chat_list_route") {
//                popUpTo("login_route"){
//                    inclusive = true
//                }
            }
        }
    })
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    LoginScreen(null)
}