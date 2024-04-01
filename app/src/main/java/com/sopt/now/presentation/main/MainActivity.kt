package com.sopt.now.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.sopt.now.R
import com.sopt.now.databinding.ActivityMainBinding
import com.sopt.now.model.User
import com.sopt.now.presentation.auth.signup.SignUpActivity.Companion.USER_KEY
import com.sopt.now.util.base.BaseActivity
import com.sopt.now.util.ext.getParcelable

class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getUserInfo()
    }

    private fun getUserInfo() {
        val intent = intent
        val user = intent.getParcelable(USER_KEY, User::class.java)

        with(binding) {
            tvMainIdValue.text = user?.id
            tvMainPwValue.text = user?.pw
            tvMainNickname.text = user?.nickname
            tvMainJuryangValue.text = getString(R.string.juryang_count, user?.juryang)
        }
    }

    companion object {
        fun createIntent(context: Context, user: User): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra(USER_KEY, user)
            }
        }
    }
}
