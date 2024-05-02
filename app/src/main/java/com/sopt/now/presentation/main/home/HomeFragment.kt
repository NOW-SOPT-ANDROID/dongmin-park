package com.sopt.now.presentation.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.databinding.FragmentHomeBinding
import com.sopt.now.presentation.main.MainViewModel
import com.sopt.now.presentation.main.ProfileAdapter
import com.sopt.now.util.base.BaseFragment
import com.sopt.now.util.ext.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewmodel: MainViewModel by activityViewModels()
    private val friendAdapter = ProfileAdapter()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFriendList()
        bindingAdapter()
        observeHomeState()
    }

    private fun setFriendList() {
        viewmodel.getFriendList()
    }

    private fun bindingAdapter() {
        binding.rvFriends.run {
            adapter = friendAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeHomeState() {
        viewmodel.homeState.flowWithLifecycle(lifecycle).onEach { state ->
            when (state) {
                is HomeState.ERROR -> requireActivity().toast(getString(state.errorMessage))
                HomeState.LOADING -> {}
                is HomeState.SUCCESS -> {
                    friendAdapter.submitList(state.profileList)
                }
            }
        }.launchIn(lifecycleScope)
    }
}
