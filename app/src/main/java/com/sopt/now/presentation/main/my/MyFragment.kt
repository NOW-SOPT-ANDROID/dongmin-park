package com.sopt.now.presentation.main.my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sopt.now.R
import com.sopt.now.databinding.FragmentHomeBinding
import com.sopt.now.databinding.FragmentMyBinding
import com.sopt.now.presentation.main.MainViewModel
import com.sopt.now.util.base.BaseFragment

class MyFragment : BaseFragment<FragmentMyBinding>() {
    private val viewmodel: MainViewModel by activityViewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMyBinding = FragmentMyBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUserInfo()
    }

    private fun setUserInfo() {
        val user = viewmodel.userInfo

        with(binding) {
            tvMyIdValue.text = user.id
            tvMyPwValue.text = user.pw
            tvMyNickname.text = user.nickname
            tvMainJuryangValue.text = user.juryang
        }
    }
}
