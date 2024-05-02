package com.sopt.now.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sopt.now.R
import com.sopt.now.databinding.ActivityMainBinding
import com.sopt.now.model.User
import com.sopt.now.presentation.auth.signup.SignUpActivity.Companion.USER_KEY
import com.sopt.now.presentation.main.home.HomeFragment
import com.sopt.now.presentation.main.my.MyFragment
import com.sopt.now.presentation.main.search.SearchFragment
import com.sopt.now.util.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    enum class BottomNavigationType(val menuId: Int) {
        HOME(R.id.menu_home),
        SEARCH(R.id.menu_search),
        MY_PAGE(R.id.menu_mypage);

        companion object {
            fun getScreenEnum(id: Int) = BottomNavigationType.entries.first { it.menuId == id }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initFragmentView()
        initBottomNavigation()
    }

    private fun initFragmentView() {
        val currentFragment = supportFragmentManager.findFragmentById(binding.fcvHome.id)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(binding.fcvHome.id, HomeFragment())
                .commit()
        }
    }

    private fun initBottomNavigation() {
        binding.bnvHome.setOnItemSelectedListener {
            val screenEnum = BottomNavigationType.getScreenEnum(it.itemId)

            when (screenEnum) {
                BottomNavigationType.HOME -> {
                    replaceFragment(HomeFragment())
                    true
                }

                BottomNavigationType.SEARCH -> {
                    replaceFragment(SearchFragment())
                    true
                }

                BottomNavigationType.MY_PAGE -> {
                    replaceFragment(MyFragment())
                    true
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_home, fragment)
            .commit()
    }

    companion object {
        fun createIntent(context: Context, user: User): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra(USER_KEY, user)
            }
        }
    }
}
