package com.trangiabao.sixjars.utils.component

import android.support.v4.view.ViewPager
import android.view.View

class ViewPagerTransformer : ViewPager.PageTransformer {

    private val MIN_SCALE = 0.85f
    private val MIN_ALPHA = 0.5f

    override fun transformPage(view: View, position: Float) {
        val pageWidth = view.width
        val pageHeight = view.height
        if (position < -1) {
            view.alpha = 0F
        } else if (position <= 1) {
            val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
            val vertMargin = pageHeight * (1 - scaleFactor) / 2
            val horzMargin = pageWidth * (1 - scaleFactor) / 2
            if (position < 0)
                view.translationX = horzMargin - vertMargin / 2
            else
                view.translationX = -horzMargin + vertMargin / 2
            view.scaleX = scaleFactor
            view.scaleY = scaleFactor
            view.alpha = MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA)

        } else {
            view.alpha = 0F
        }
    }
}