package com.sopt.now.presentation.auth.signin

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.sopt.now.R
import com.sopt.now.databinding.ActivitySignInBinding
import com.sopt.now.model.User
import com.sopt.now.presentation.auth.signup.SignUpActivity
import com.sopt.now.presentation.auth.signup.SignUpActivity.Companion.USER_KEY
import com.sopt.now.presentation.main.MainActivity
import com.sopt.now.util.base.BaseActivity
import com.sopt.now.util.ext.getParcelable
import com.sopt.now.util.ext.snackBar

class SignInActivity : BaseActivity<ActivitySignInBinding>({ ActivitySignInBinding.inflate(it) }) {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSignUpActivityLauncher()
        setSignInBtnClickListener()
        setSignUpBtnClickListener()
    }

    private fun setSignUpActivityLauncher() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                user = result.data?.getParcelable(USER_KEY, User::class.java)
                    ?: return@registerForActivityResult

                setLoginInfo()
            }
        }
    }

    private fun setLoginInfo() {
        with(binding) {
            etvSignInId.setText(user?.id)
            etvSignInPw.setText(user?.pw)
        }
    }

    private fun setSignInBtnClickListener() {
        binding.btnSignIn.setOnClickListener {
            val id = binding.etvSignInId.text.toString()
            val pw = binding.etvSignInPw.text.toString()

            if (id == user?.id && pw == user?.pw) {
                navigateToMainActivity()
            } else {
                snackBar(binding.root, getString(R.string.user_error))
            }
        }
    }

    private fun navigateToMainActivity() {
        runCatching {
            MainActivity.createIntent(
                this,
                user ?: throw IllegalStateException(),
            ).also(::startActivity)

        }.onFailure {
            snackBar(binding.root, getString(R.string.user_error))
        }.onSuccess {
            finish()
        }

    }

    private fun setSignUpBtnClickListener() {
        binding.btnSignUp.setOnClickListener {
            Intent(this, SignUpActivity::class.java).apply {
                resultLauncher.launch(this)
            }
        }
    }
}
