package com.trangiabao.sixjars.ui

import android.content.Context
import android.graphics.Canvas
import android.support.v7.widget.AppCompatTextView
import android.text.StaticLayout
import android.util.AttributeSet

class JustifyTextView(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {

    private var mLineY: Int = 0
    private var mViewWidth: Int = 0

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas) {
        val paint = paint
        paint.color = currentTextColor
        paint.drawableState = drawableState
        mViewWidth = measuredWidth
        val text = text.toString()
        mLineY = 0
        mLineY += textSize.toInt()
        val layout = layout
        for (i in 0..layout.lineCount - 1) {
            val lineStart = layout.getLineStart(i)
            val lineEnd = layout.getLineEnd(i)
            val line = text.substring(lineStart, lineEnd)
            val width = StaticLayout.getDesiredWidth(text, lineStart, lineEnd, getPaint())
            if (needScale(line, i)) {
                drawScaledText(canvas, line, width)
            } else {
                canvas.drawText(line, 0f, mLineY.toFloat(), paint)
            }
            mLineY += lineHeight
        }
    }

    private fun drawScaledText(canvas: Canvas, _line: String, lineWidth: Float) {
        var line = _line
        var x = 0f
        if (isFirstLineOfParagraph(line)) {
            val blanks = "  "
            canvas.drawText(blanks, x, mLineY.toFloat(), paint)
            val bw = StaticLayout.getDesiredWidth(blanks, paint)
            x += bw
            line = line.substring(3)
        }

        val d = (mViewWidth - lineWidth) / line.length - 1
        for (i in 0..line.length - 1) {
            val c = line[i].toString()
            val cw = StaticLayout.getDesiredWidth(c, paint)
            canvas.drawText(c, x, mLineY.toFloat(), paint)
            x += cw + d
        }
    }

    private fun isFirstLineOfParagraph(line: String): Boolean {
        return line.length > 3 && line[0] == ' ' && line[1] == ' '
    }

    private fun needScale(line: String, lineNumber: Int): Boolean {
        val layout = layout
        return !(line.isEmpty() || layout.lineCount == 1 || lineNumber == layout.lineCount - 1) && line[line.length - 1] != '\n'
    }
}