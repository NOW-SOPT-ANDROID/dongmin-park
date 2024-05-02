package com.sopt.now.presentation.auth.signup

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.sopt.now.data.dto.request.RequestSignUpDto
import com.sopt.now.data.dto.response.ResponseSignUpDto
import com.sopt.now.data.remote.service.ServicePool
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.model.User
import com.sopt.now.util.base.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(ActivitySignUpBinding::inflate) {
    private val authService by lazy { ServicePool.authService }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSignUpBtnClickListener()
    }

    private fun setSignUpBtnClickListener() {
        binding.btnSignUp.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val signUpRequest = getSignUpRequestDto()
//        authService.postSignUp(signUpRequest).enqueue(object : Callback<ResponseSignUpDto> {
//            override fun onResponse(
//                call: Call<ResponseSignUpDto>,
//                response: Response<ResponseSignUpDto>,
//            ) {
//                if (response.isSuccessful) {
//                    val data: ResponseSignUpDto? = response.body()
//                    val userId = response.headers()["location"]
//                    Toast.makeText(
//                        this@SignUpActivity,
//                        "회원가입 성공 유저의 ID는 $userId 입니둥",
//                        Toast.LENGTH_SHORT,
//                    ).show()
//                    Log.d("SignUp", "data: $data, userId: $userId")
//                } else {
//                    val error = response.message()
//                    Toast.makeText(
//                        this@SignUpActivity,
//                        "로그인이 실패 $error",
//                        Toast.LENGTH_SHORT,
//                    ).show()
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
//                Toast.makeText(this@SignUpActivity, "서버 에러 발생 ", Toast.LENGTH_SHORT).show()
//            }
//        })
    }

    private fun getSignUpRequestDto(): RequestSignUpDto {
        val id = binding.etvSignUpId.text.toString()
        val password = binding.etvSignUpPw.text.toString()
        val nickname = binding.etvSignUpNickname.text.toString()
        val phoneNumber = binding.etvSignUpPhoneNumber.text.toString()
        return RequestSignUpDto(
            authenticationId = id,
            password = password,
            nickname = nickname,
            phone = phoneNumber
        )
    }

    private fun navigateSignIn() {
        val user = binding.run {
            User(
                id = etvSignUpId.text.toString(),
                pw = etvSignUpPw.text.toString(),
                nickname = etvSignUpNickname.text.toString(),
                juryang = etvSignUpPhoneNumber.text.toString()
            )
        }

        intent.run {
            putExtra(USER_KEY, user)
            setResult(RESULT_OK, this)
        }
        finish()
    }

    companion object {
        const val USER_KEY = "user"
    }
}
