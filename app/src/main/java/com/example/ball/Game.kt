package com.example.ball

import android.content.Context
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class Game(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

    var draw : DrawThred? = null
    var panel: Panel? = null
    var ball: Ball? = null
    var viewWidth = 0
    var viewHeight = 0
    init {
        getHolder().addCallback(this);
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewWidth = w
        viewHeight = h
//        Log.d("GameWidth","viewWidth=$w, viewHeight=$h")
        panel = Panel(viewWidth.toDouble(), viewHeight.toDouble())
        ball = Ball(viewWidth.toDouble(), viewHeight.toDouble())
    }
    override fun surfaceCreated(p0: SurfaceHolder) {
        draw = DrawThred(getContext(), p0, ball!!)
        draw!!.setCoordinates(panel!!.GetX(), panel!!.GetY())
        draw!!.start()
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        draw?.stopDraw()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            if (event.x >= panel!!.GetX()){
                draw?.setDirection(0)
            }
            else {
                draw?.setDirection(1)
            }
        }
        return true
    }


}