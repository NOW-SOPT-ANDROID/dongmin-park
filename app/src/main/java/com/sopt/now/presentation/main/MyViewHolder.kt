package com.sopt.now.presentation.main

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.databinding.ItemMyBinding
import com.sopt.now.model.Profile

class MyViewHolder(private val binding: ItemMyBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendProfileData: Profile.MyProfile) {
        binding.run {
            ivProfile.setImageResource(friendProfileData.profileImage)
            tvName.text = friendProfileData.name
            tvSelfDescription.text = friendProfileData.selfDescription
        }
    }
}
