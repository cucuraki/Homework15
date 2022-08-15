package com.example.homework15.pagefragments.registerpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework15.inputenum.InputEnum
import com.example.homework15.models.LoginRequestModel
import com.example.homework15.models.LoginResponseModel
import com.example.homework15.models.RegisterModel
import com.example.homework15.responsestate.ResponseState
import com.example.homework15.retrofitclient.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.lang.Exception

class RegisterViewModel : ViewModel() {
    private val _processState = MutableStateFlow<ResponseState<RegisterModel>>(
        ResponseState.Success(
            RegisterModel(-1,"")
        )
    )
    val processState = _processState.asStateFlow()

    fun register(email: String, password: String) {
        viewModelScope.launch {
            registerFlaw(email,password).collect{
                _processState.value = it
            }
        }
    }


    private fun registerFlaw(email: String, password: String) =
        flow {
            emit(ResponseState.Load())
            val check = check(email, password)
            if (check != InputEnum.ALL_IS_OK) {
                emit(ResponseState.Error(check.toString()))
            } else {
                try {
                    val response = RetrofitClient.register.registerUser(LoginRequestModel(email, password))
                    if(response.isSuccessful){
                        val body = response.body()
                        if(body == null)
                            emit(ResponseState.Error("Error"))
                        else
                            emit(ResponseState.Success(body))
                    }else{
                        emit(ResponseState.Error(response.errorBody()?.string()?:"Error"))
                    }
                } catch (e: Exception) {
                    emit(ResponseState.Error(e.message.toString()))
                }
            }
        }


    private fun check(email: String, password: String): InputEnum =
        when {
            //i don't check for username since it is not needed at the moment
            email.isEmpty() -> InputEnum.EMAIL_IS_EMPTY
            password.isEmpty() -> InputEnum.PASSWORD_IS_EMPTY
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches() -> InputEnum.EMAIL_DOES_NOT_MATCH
            else -> InputEnum.ALL_IS_OK
        }
}