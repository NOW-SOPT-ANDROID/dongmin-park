package com.sopt.now.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sopt.now.R
import com.sopt.now.databinding.ItemFriendBinding
import com.sopt.now.databinding.ItemMyBinding
import com.sopt.now.model.Profile
import com.sopt.now.util.base.BaseViewHolder
import com.sopt.now.util.callback.ItemDiffCallback

class ProfileAdapter : ListAdapter<Profile, ViewHolder>(
    HomeDiffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater by lazy { LayoutInflater.from(parent.context) }

        return when (viewType) {
            R.layout.item_my -> {
                val binding = ItemMyBinding.inflate(inflater, parent, false)
                MyViewHolder(binding)
            }

            else -> {
                val binding = ItemFriendBinding.inflate(inflater, parent, false)
                FriendViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList.getOrNull(position) ?: return
        (holder as BaseViewHolder<*>).bind(item)
    }

    override fun getItemViewType(position: Int) = currentList[position].view

    companion object {
        private val HomeDiffCallback =
            ItemDiffCallback<Profile>(
                onItemsTheSame = { old, new -> old.name == new.name },
                onContentsTheSame = { old, new -> old == new }
            )
    }
}
