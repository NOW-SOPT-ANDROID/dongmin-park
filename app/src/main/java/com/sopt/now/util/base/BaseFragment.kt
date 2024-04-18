package com.sopt.now.util.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.sopt.now.R

abstract class BaseFragment<T : ViewBinding> : Fragment() {
    private var _binding: T? = null
    val binding: T
        get() = requireNotNull(_binding) { getString(R.string.binding_error) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = getFragmentBinding(inflater, container)
        return binding.root
    }

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): T

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
