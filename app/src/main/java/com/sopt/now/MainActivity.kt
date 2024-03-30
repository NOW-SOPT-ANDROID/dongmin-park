package com.sopt.now

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignIn.setOnClickListener {
            val idLength = binding.etvId.text.length
            val pwLength = binding.etvPw.text.length

            val message = getMessage(idLength, pwLength)

            if (message.isNullOrBlank()){
                navigateSignInActivity()
            } else{
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

    private fun navigateSignInActivity() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }
}
