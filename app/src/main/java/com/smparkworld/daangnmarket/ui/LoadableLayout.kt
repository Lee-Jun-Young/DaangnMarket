package com.smparkworld.daangnmarket.ui

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.smparkworld.daangnmarket.R

class LoadableLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var loadingView: LinearLayout

    private val backgroundColor: Int

    private var isLoading: Boolean = false

    init {
        context.obtainStyledAttributes(attrs, R.styleable.LoadableLinearLayout).apply {
            backgroundColor = getColor(
                R.styleable.LoadableLinearLayout_android_background,
                ContextCompat.getColor(context, R.color.opaqueGray)
            )
        }.recycle()

        initLoadingView()
    }

    private fun initLoadingView() {
        loadingView = LinearLayout(context).apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT).apply {
                setGravity(Gravity.CENTER)
                setBackgroundColor(backgroundColor)
            }
        }
        val innerLayout = LinearLayout(context).apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                setGravity(Gravity.CENTER)
                orientation = LinearLayout.VERTICAL
            }
        }
        val progress = ProgressBar(context, null, android.R.attr.progressBarStyle).apply {
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                setPadding(30, 30, 30, 30)
            }
            setBackgroundResource(R.drawable.ic_baseline_circle_white_24)
        }
        innerLayout.addView(progress)
        loadingView.addView(innerLayout)
        loadingView.isClickable = true
    }

    fun setLoading(loading: Boolean) {
        isLoading = loading && !isLoading
        if (isLoading) addView(loadingView) else removeView(loadingView)
    }
}