package com.fchps.rvtechnicaltest.utils

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.ViewHolder.doSafelyOnItemClicked(block: (Int) -> Unit) {
    this.itemView.setOnClickListener {
        adapterPosition.takeIf { it != RecyclerView.NO_POSITION }?.let(block)
    }
}