package com.horizonlabs.kotlindemo.utility

import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager


class SnapHelper : LinearSnapHelper() {

    override fun findTargetSnapPosition(
        layoutManager: RecyclerView.LayoutManager?,
        velocityX: Int,
        velocityY: Int
    ): Int {

        if (layoutManager !is RecyclerView.SmoothScroller.ScrollVectorProvider) {
            return RecyclerView.NO_POSITION
        }

        val currentView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION

        val myLayoutManager = layoutManager as LinearLayoutManager?

        val position1 = myLayoutManager!!.findFirstVisibleItemPosition()
        val position2 = myLayoutManager.findLastVisibleItemPosition()

        var currentPosition = layoutManager.getPosition(currentView)

        if (velocityX > 400) {
            currentPosition = position2
        } else if (velocityX < 400) {
            currentPosition = position1
        }

        return if (currentPosition == RecyclerView.NO_POSITION) {
            RecyclerView.NO_POSITION
        } else currentPosition

    }
}