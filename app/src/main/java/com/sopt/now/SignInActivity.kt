package com.sopt.now

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signInBtnClickListener()
        signUpBtnClickListener()
    }

    private fun signInBtnClickListener() {
        binding.btnSignUp.setOnClickListener {
            navigateSignUpActivity()
        }
    }

    private fun signUpBtnClickListener() {
        binding.btnSignIn.setOnClickListener {
            val idLength = binding.etvId.text.length
            val pwLength = binding.etvPw.text.length

            val message = getMessage(idLength, pwLength)

            if (message.isNullOrBlank()) {
                navigateMainActivity()
            } else {
                showSnackBar(message)
            }
        }
    }

    private fun getMessage(idLength: Int, pwLength: Int): String? = when {
        idLength < 6 -> "ID ERROR"
        pwLength !in 6..10 -> "PW ERROR"
        else -> null
    }

    private fun showSnackBar(message: String) = Snackbar.make(
        binding.root,
        message,
        Snackbar.LENGTH_SHORT
    ).show()

    private fun navigateMainActivity() {
        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
        }
    }

    private fun navigateSignUpActivity() {
        Intent(this, SignUpActivity::class.java).apply {
            startActivity(this)
        }
    }
}
