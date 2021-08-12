package kr.co.yeaeun.viewbasic

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Half.toFloat
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;


class MyPlusMinusView : View {
    var con:Context? = null
    var value = 0
    lateinit var plusBitmap: Bitmap
    lateinit var minusBitmap: Bitmap
    var plusRectDst: Rect = Rect()
    var minusRectDst: Rect = Rect()
    var textColor = 0
    var listeners: ArrayList<OnMyChangeListener>? = null


    constructor(_context: Context) : super(_context) {
        con = _context
        init(null)
    }

    constructor(_context: Context, _attrs: AttributeSet?) : super(_context, _attrs) {
        con = _context
        init(_attrs)
    }

    constructor(_context: Context, _attrs: AttributeSet?, _defStyleAttr: Int) : super(
        _context,
        _attrs,
        _defStyleAttr
    ) {
        con = _context
        init(_attrs)
    }

    private fun init(attrs: AttributeSet?) {
        plusBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.plus)
        minusBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.minus)
        plusRectDst = Rect(10, 10, 210, 210)
        minusRectDst = Rect(400, 10, 600, 210)
        if (attrs != null) {
            val a: TypedArray = con!!.obtainStyledAttributes(attrs, R.styleable.MyView)
            textColor = a.getColor(R.styleable.MyView_customTextColor, Color.RED) // 지정된 색이 없으면 디폴트로 레드를 넣으라는 의미일것
        }
        listeners = ArrayList()
    }

    fun setOnMyChangeListener(listener: OnMyChangeListener) {
        listeners!!.add(listener)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        var width = 0
        var height = 0
        if (widthMode == MeasureSpec.AT_MOST) {
            width = 700
        } else if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            height = 250
        } else if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize
        }
        setMeasuredDimension(width, height)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x.toInt()
        val y = event.y.toInt()
        if (plusRectDst!!.contains(x, y) && event.action == MotionEvent.ACTION_DOWN) {
            value++
            invalidate()
            for (listener in listeners!!) {
                listener.onChange(value)
            }
            return true
        } else if (minusRectDst!!.contains(x, y) && event.action == MotionEvent.ACTION_DOWN) {
            value--
            invalidate()
            for (listener in listeners!!) {
                listener.onChange(value)
            }
            return true
        }
        return false
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.alpha(Color.CYAN))
        var plusRectSource = Rect(0, 0, plusBitmap!!.width, plusBitmap!!.height)
        var minusRectSource = Rect(0, 0, minusBitmap!!.width, minusBitmap!!.height)
        var paint = Paint()
        canvas.drawBitmap(plusBitmap, plusRectSource, plusRectDst, null)
        paint.textSize = 80f
        paint.setColor(textColor) // 파란색으로 셋팅됨
        canvas.drawText(value.toString(), 260f, 150f, paint) // 숫자 수치, 위치, 속성
        canvas.drawBitmap(minusBitmap, minusRectSource, minusRectDst, null)
    }
}

