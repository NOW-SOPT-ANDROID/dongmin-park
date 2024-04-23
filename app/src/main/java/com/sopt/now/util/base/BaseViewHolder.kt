package com.sopt.now.util.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.model.Profile

abstract class BaseViewHolder<T : Profile>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: Profile)
}
