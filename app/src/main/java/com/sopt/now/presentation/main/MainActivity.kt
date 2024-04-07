package com.sopt.now.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sopt.now.R
import com.sopt.now.databinding.ActivityMainBinding
import com.sopt.now.model.User
import com.sopt.now.presentation.auth.signup.SignUpActivity.Companion.USER_KEY
import com.sopt.now.util.base.BaseActivity
import com.sopt.now.util.ext.getParcelable

class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentFragment = supportFragmentManager.findFragmentById(binding.fcvHome.id)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(binding.fcvHome.id, HomeFragment())
                .commit()
        }

        clickBottomNavigation()
    }

    private fun clickBottomNavigation() {
        binding.bnvHome.setOnItemSelectedListener{
            when (it.itemId) {
                R.id.menu_home-> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.menu_search-> {
                    replaceFragment(SearchFragment())
                    true
                }

                R.id.menu_mypage-> {
                    replaceFragment(MyFragment())
                    true
                }

                else -> false
            }
        }
    }

    // Activity에서 Fragment를 다뤄야 하니 supportFragmentManager를 사용합니다.
// 트렌젝션을 시작하고 replace 메서드를 통해 Fragment를 교체합니다.
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_home, fragment)
            .commit()
    }

//    private fun getUserInfo() {
//        val intent = intent
//        val user = intent.getParcelable(USER_KEY, User::class.java)
//
//        with(binding) {
//            tvMainIdValue.text = user?.id
//            tvMainPwValue.text = user?.pw
//            tvMainNickname.text = user?.nickname
//            tvMainJuryangValue.text = getString(R.string.main_juryang_count, user?.juryang)
//        }
//    }

    companion object {
        fun createIntent(context: Context, user: User): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra(USER_KEY, user)
            }
        }
    }
}
