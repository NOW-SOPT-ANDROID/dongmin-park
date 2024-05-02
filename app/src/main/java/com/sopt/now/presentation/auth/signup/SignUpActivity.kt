package com.sopt.now.presentation.auth.signup

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.model.User
import com.sopt.now.util.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding>(ActivitySignUpBinding::inflate) {
    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSignUpBtnClickListener()
        viewModel.signUpState.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is SignUpState.ERROR -> {
                    Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
                }

                SignUpState.LOADING -> {}
                SignUpState.SUCCESS -> navigateSignIn()
            }
        }.launchIn(lifecycleScope)
    }

    private fun setSignUpBtnClickListener() {
        with(binding) {
            btnSignUp.setOnClickListener {
                viewModel.signUpBtnClicked(
                    etvSignUpId.text.toString(),
                    etvSignUpPw.text.toString(),
                    etvSignUpNickname.text.toString(),
                    etvSignUpPhoneNumber.text.toString()
                )
            }
        }
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
