package com.sopt.now.presentation.main

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.databinding.ItemFriendBinding
import com.sopt.now.model.Profile

class FriendViewHolder(private val binding: ItemFriendBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendProfileData: Profile.FriendProfile) {
        binding.run {
            ivProfile.setImageResource(friendProfileData.profileImage)
            tvName.text = friendProfileData.name
            tvSelfDescription.text = friendProfileData.selfDescription
        }
    }
}