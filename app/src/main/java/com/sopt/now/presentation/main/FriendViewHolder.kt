package com.sopt.now.presentation.main

import com.sopt.now.databinding.ItemFriendBinding
import com.sopt.now.model.Profile
import com.sopt.now.util.base.BaseViewHolder

class FriendViewHolder(private val binding: ItemFriendBinding) : BaseViewHolder<Profile.FriendProfile>(binding.root) {
    override fun bind(item: Profile) {
        item as Profile.FriendProfile
        binding.run {
            ivProfile.setImageResource(item.profileImage)
            tvName.text = item.name
            tvSelfDescription.text = item.selfDescription
        }
    }
}
