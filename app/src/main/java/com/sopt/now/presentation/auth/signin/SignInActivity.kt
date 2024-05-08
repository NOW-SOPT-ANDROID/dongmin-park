package com.sopt.now.presentation.auth.signin

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sopt.now.R
import com.sopt.now.databinding.ActivitySignInBinding
import com.sopt.now.model.User
import com.sopt.now.presentation.auth.signup.SignUpActivity
import com.sopt.now.presentation.auth.signup.SignUpActivity.Companion.USER_KEY
import com.sopt.now.presentation.main.MainActivity
import com.sopt.now.util.base.BaseActivity
import com.sopt.now.util.ext.getParcelable
import com.sopt.now.util.ext.snackBar
import com.sopt.now.util.ext.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding>(ActivitySignInBinding::inflate) {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val viewModel by viewModels<SignInViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSignUpActivityLauncher()
        setSignInBtnClickListener()
        setSignUpBtnClickListener()
        observeSignInState()
    }

    private fun setSignUpActivityLauncher() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                viewModel.setUser(
                    result.data?.getParcelable(USER_KEY, User::class.java)
                        ?: return@registerForActivityResult
                )

                setLoginInfo()
            }
        }
    }

    private fun setLoginInfo() {
        with(binding) {
            etvSignInId.setText(viewModel.user?.id)
            etvSignInPw.setText(viewModel.user?.pw)
        }
    }

    private fun setSignInBtnClickListener() {
        binding.btnSignIn.setOnClickListener {
            val id = binding.etvSignInId.text.toString()
            val pw = binding.etvSignInPw.text.toString()

            if (id == viewModel.user?.id && pw == viewModel.user?.pw) {
                viewModel.signInBtnClicked(id, pw)
            } else {
                snackBar(binding.root, getString(R.string.user_error))
            }
        }
    }

    private fun setSignUpBtnClickListener() {
        binding.btnSignUp.setOnClickListener {
            Intent(this, SignUpActivity::class.java).apply {
                resultLauncher.launch(this)
            }
        }
    }

    private fun observeSignInState() {
        viewModel.signInState.flowWithLifecycle(lifecycle).onEach { state ->
            when (state) {
                is SignInState.ERROR -> toast(getString(state.errorMessage))
                SignInState.LOADING -> {}
                SignInState.SUCCESS -> {
                    navigateToMainActivity()
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun navigateToMainActivity() {
        runCatching {
            MainActivity.createIntent(
                this,
                viewModel.user ?: throw IllegalStateException(),
            ).also(::startActivity)
        }.onFailure {
            snackBar(binding.root, getString(R.string.user_error))
        }.onSuccess {
            finish()
        }
    }
}
