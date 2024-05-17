package com.sopt.now.presentation.main.my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sopt.now.databinding.FragmentMyBinding
import com.sopt.now.model.User
import com.sopt.now.presentation.main.MainViewModel
import com.sopt.now.util.base.BaseFragment
import com.sopt.now.util.ext.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MyFragment : BaseFragment<FragmentMyBinding>() {
    private val viewmodel: MainViewModel by activityViewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentMyBinding = FragmentMyBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUserData()
        observeMyState()
    }

    private fun getUserData() {
        viewmodel.getProfileInfo()
    }

    private fun observeMyState() {
        viewmodel.myState.flowWithLifecycle(lifecycle).onEach { state ->
            when (state) {
                is MyState.ERROR -> requireActivity().toast(getString(state.errorMessage))
                MyState.LOADING -> {}
                is MyState.SUCCESS -> setUserInfo(state.user)
            }
        }.launchIn(lifecycleScope)
    }

    private fun setUserInfo(user: User) {
        with(binding) {
            tvMyIdValue.text = user.id
            tvMyPwValue.text = user.pw
            tvMyNickname.text = user.nickname
            tvMainJuryangValue.text = user.phoneNumber
        }
    }
}
