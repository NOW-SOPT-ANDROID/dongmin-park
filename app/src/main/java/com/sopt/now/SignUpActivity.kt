package com.sopt.now

import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signUpBtnClickListener()
        juryangEtvDoOnTextChanged()
    }

    private fun signUpBtnClickListener() {
        binding.btnSignUp.setOnClickListener {
            val idLength = binding.etvSignUpId.text.length
            val pwLength = binding.etvSignUpPw.text.length
            val nickname = binding.etvSignUpNickname.text
            val juryang = binding.etvSignUpJuryang.text

            when(val error = getMessageError(idLength, pwLength, nickname, juryang)) {
                SignUpError.SUCCESS -> showSnackBar("SUCCESS")
                else -> showSnackBar(error.errorMessage)
            }
        }
    }

    private fun getMessageError(
        idLength: Int,
        pwLength: Int,
        nickname: Editable,
        juryang: Editable
    ) = when {
        idLength !in 6..10 -> SignUpError.IdError
        pwLength !in 8..12 -> SignUpError.PwError
        nickname.isBlank() -> SignUpError.NicknameError
        juryang.isEmpty() -> SignUpError.JuryangError
        else -> SignUpError.SUCCESS
    }

    private fun juryangEtvDoOnTextChanged() {
        binding.etvSignUpJuryang.doOnTextChanged { text, _, _, _ ->
            val newValue = text.toString().filter { it.isDigit() }.toIntOrNull()
            if (newValue != null && newValue.toString() != text.toString()) {
                binding.etvSignUpJuryang.setText(newValue.toString())
            }
        }
    }

    private fun showSnackBar(message: String) = Snackbar.make(
        binding.root,
        message,
        Snackbar.LENGTH_SHORT
    ).show()
}

sealed class SignUpError(val errorMessage: String) {
    data object SUCCESS : SignUpError("SUCCESS")
    data object IdError : SignUpError("ID ERROR")
    data object PwError : SignUpError("PW ERROR")
    data object NicknameError : SignUpError("NICKNAME ERROR")
    data object JuryangError : SignUpError("JURYANG ERROR")
}
