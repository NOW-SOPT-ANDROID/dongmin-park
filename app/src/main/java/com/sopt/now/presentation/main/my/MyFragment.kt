package com.sopt.now.presentation.main.my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sopt.now.R
import com.sopt.now.databinding.FragmentMyBinding
import com.sopt.now.presentation.main.MainViewModel

class MyFragment : Fragment() {
    val viewmodel: MainViewModel by activityViewModels()

    private var _binding: FragmentMyBinding? = null
    private val binding: FragmentMyBinding
        get() = requireNotNull(_binding) { getString(R.string.binding_error) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyBinding.inflate(inflater, container, false)
        return binding.root
    }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}