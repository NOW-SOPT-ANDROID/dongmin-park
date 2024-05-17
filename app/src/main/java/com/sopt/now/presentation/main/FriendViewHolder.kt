package com.sopt.now.presentation.main

import com.bumptech.glide.Glide
import com.sopt.now.databinding.ItemFriendBinding
import com.sopt.now.model.Profile
import com.sopt.now.util.base.BaseViewHolder

class FriendViewHolder(private val binding: ItemFriendBinding) : BaseViewHolder<Profile.FriendProfile>(binding.root) {
    override fun bind(item: Profile) {
        item as Profile.FriendProfile
        binding.run {
            Glide.with(binding.root)
                .load(item.profileImage)
                .into(ivProfile)
            tvName.text = item.name
            tvSelfDescription.text = item.selfDescription
        }
    }
}
