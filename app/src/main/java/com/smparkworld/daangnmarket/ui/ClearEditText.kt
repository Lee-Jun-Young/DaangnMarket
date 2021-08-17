package com.smparkworld.daangnmarket.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.view.marginRight

import com.smparkworld.daangnmarket.R

class ClearEditText : AppCompatEditText, TextWatcher, OnTouchListener {

    /**
     * CustomView로 만들 때 @JvmOverloads 어노테이션을 사용하면 기본값 Style이 적용되지
     * 않는 이슈가 있음. 기본값 Style을 하드코딩하지 않기 위해 아래와 같이 사용.
     *
     * 참고: http://smparkworld.com/blog/detail/31
     */
    constructor(context: Context) : super(context) { init() }
    constructor(context: Context, attrs: AttributeSet?): super(context, attrs) { init(attrs) }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) { init(attrs) }

    private var leftDrawable: Drawable? = null
    private var clearDrawable: Drawable? = null
    private var underlineColor: Int = 0

    private fun init(attrs: AttributeSet? = null) {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.ClearEditText)
        leftDrawable = typeArray.getDrawable(R.styleable.ClearEditText_android_drawableLeft)?.apply {
            setBounds(0, 0, (intrinsicWidth * 0.9).toInt(), (intrinsicHeight * 0.9).toInt())
            setCompoundDrawables(leftDrawable, null, null, null)
        }
        clearDrawable = typeArray.getDrawable(R.styleable.ClearEditText_clearButton)?.apply {
            setBounds(0, 0, (intrinsicWidth * 0.7).toInt(), (intrinsicHeight * 0.7).toInt())
        }
        underlineColor = typeArray.getColor(
                R.styleable.ClearEditText_underlineColor,
                ContextCompat.getColor(context, R.color.gray)
        )
        typeArray.recycle()

        background = null
        setClearButtonVisibility(false)
        super.setOnTouchListener(this)
        addTextChangedListener(this)
    }

    private fun setClearButtonVisibility(visible: Boolean) {
        clearDrawable?.setVisible(visible, false)
        setCompoundDrawables(
            leftDrawable, null, if (visible) clearDrawable else null, null
        )
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        setClearButtonVisibility(text?.length ?: 0 > 0)
    }

    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    // onTouch -> onClick -> onLongClick 중
    // 여기서 해당 이벤트를 소비하고 싶을 때 return true, 뒤로 전달하고 싶으면 return false
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        val x = event?.x
        if (x != null) {
            clearDrawable?.let {
                if (it.isVisible && x > width - paddingRight - it.intrinsicWidth) {
                    text = null
                    error = null
                    return true
                }
            }
        }
        return false
    }
    
    // Underline 그리기
    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        canvas?.drawLine(
                paddingLeft.toFloat(),
                height.toFloat(),
                width.toFloat() - paddingRight,
                height.toFloat(),
                Paint().apply {
                    color = underlineColor
                    strokeWidth = 5f
                }
        )
    }
}