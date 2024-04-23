package com.sopt.now.presentation.main

import com.sopt.now.databinding.ItemMyBinding
import com.sopt.now.model.Profile
import com.sopt.now.util.base.BaseViewHolder

class MyViewHolder(private val binding: ItemMyBinding) : BaseViewHolder<Profile.MyProfile>(binding.root) {
    override fun bind(item: Profile) {
        item as Profile.MyProfile
        binding.run {
            ivProfile.setImageResource(item.profileImage)
            tvName.text = item.name
            tvSelfDescription.text = item.selfDescription
        }
    }
}
