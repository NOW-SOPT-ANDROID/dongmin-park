package com.sopt.now.presentation.auth.signup

import android.os.Bundle
import android.text.Editable
import androidx.core.widget.doOnTextChanged
import com.sopt.now.R
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.model.User
import com.sopt.now.util.base.BaseActivity
import com.sopt.now.util.ext.snackBar

class SignUpActivity : BaseActivity<ActivitySignUpBinding>({ ActivitySignUpBinding.inflate(it) }) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        signUpBtnClickListener()
        juryangEtvDoOnTextChanged()
    }

    private fun signUpBtnClickListener() {
        binding.btnSignUp.setOnClickListener {
            val idLength = binding.etvSignUpId.text.length
            val pwLength = binding.etvSignUpPw.text.length
            val nickname = binding.etvSignUpNickname.text
            val juryang = binding.etvSignUpJuryang.text

            when (val error = getMessageError(idLength, pwLength, nickname, juryang)) {
                SignUpState.SUCCESS -> navigateSignIn()
                is SignUpState.ERROR -> snackBar(binding.root, getString(error.errorMessage))
            }
        }
    }

    private fun getMessageError(
        idLength: Int,
        pwLength: Int,
        nickname: Editable,
        juryang: Editable
    ) = when {
        idLength !in ID_MIN_LEN..ID_MAX_LEN -> SignUpState.ERROR(R.string.id_error)
        pwLength !in PW_MIN_LEN..PW_MAX_LEN -> SignUpState.ERROR(R.string.pw_error)
        nickname.isBlank() -> SignUpState.ERROR(R.string.nickname_error)
        juryang.isEmpty() -> SignUpState.ERROR(R.string.juryang_error)
        else -> SignUpState.SUCCESS
    }

    private fun navigateSignIn() {
        val user = binding.run {
            User(
                id = etvSignUpId.text.toString(),
                pw = etvSignUpPw.text.toString(),
                nickname = etvSignUpNickname.text.toString(),
                juryang = etvSignUpJuryang.text.toString()
            )
        }

        intent.run {
            putExtra(USER_KEY, user)
            setResult(RESULT_OK, this)
        }
        finish()
    }

    private fun juryangEtvDoOnTextChanged() {
        binding.etvSignUpJuryang.doOnTextChanged { text, _, _, _ ->
            val newValue = text.toString().filter { it.isDigit() }.toIntOrNull()
            if (newValue != null && newValue.toString() != text.toString()) {
                binding.etvSignUpJuryang.setText(newValue.toString())
            }
        }
    }

    companion object {
        private const val ID_MIN_LEN = 6
        private const val ID_MAX_LEN = 10
        private const val PW_MIN_LEN = 8
        private const val PW_MAX_LEN = 12

        const val USER_KEY = "user"
    }
}
