package com.sopt.now.presentation.auth.signin

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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
        signInBtnClickListener()
        signUpBtnClickListener()
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

    private fun signInBtnClickListener() {
        binding.btnSignIn.setOnClickListener {
            val id = binding.etvSignInId.text.toString()
            val pw = binding.etvSignInPw.text.toString()

            if (id == user?.id && pw == user?.pw) {
                navigateToMainActivity()
            } else {
                snackBar(binding.root, "회원정보가 일치하지 않습니다")
            }
        }
    }

    private fun navigateToMainActivity() {
        MainActivity.createIntent(
            this,
            user ?: throw IllegalStateException(),
        ).also(::startActivity)
        finish()
    }

    private fun signUpBtnClickListener() {
        binding.btnSignUp.setOnClickListener {
            Intent(this, SignUpActivity::class.java).apply {
                resultLauncher.launch(this)
            }
        }
    }
}
