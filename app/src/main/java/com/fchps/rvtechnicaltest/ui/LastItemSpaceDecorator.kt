package com.fchps.rvtechnicaltest.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class LastItemSpaceDecorator : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        parent.adapter?.let {
            if (parent.getChildLayoutPosition(view) == it.itemCount.minus(1)) {
                outRect.bottom = 6
            }
        }
    }
}