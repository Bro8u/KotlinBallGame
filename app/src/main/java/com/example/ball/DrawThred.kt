package com.example.ball

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.SurfaceHolder


class DrawThred(var context: Context, var holder: SurfaceHolder, var ball: Ball) : Thread() {

    var Px = 0.0
    var Py = 0.0
    var pDirection = 0
    var running = true

    fun setCoordinates(Px: Double, Py: Double) {
        this.Px = Px
        this.Py = Py
    }
    fun setDirection(direction: Int) {
        this.pDirection = direction
    }
    override fun run() {
        val displayMetrics = context.resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels
        Log.d("DrawThred", "Размер экрана: ширина=$screenWidth, высота=$screenHeight")
        fun intersect() {
            var circleX = ball!!.GetX()
            var circleY = ball!!.GetY()
            val left = (Px - screenWidth / 5)
            val top = (Py - 100)
            val right = (Px + screenWidth / 5)
            val bottom = (Py + 100)
            val nearestX = circleX.coerceIn(left, right)
            val nearestY = circleY.coerceIn(top, bottom)

            val deltaX = circleX - nearestX
            val deltaY = circleY - nearestY
            val distanceSquared = deltaX * deltaX + deltaY * deltaY

            if (distanceSquared <= 100 * 100) {
                if (circleX < Px) { // left part
                    ball.Vx = -10.0
                }
                else { // right part
                    ball.Vx = 10.0
                }
                ball.Vy *= -1

            }
        }
        while (running) {
            val canvas: Canvas = holder.lockCanvas()

            try {
                canvas.drawColor(Color.WHITE)
                var p = Paint()
                p.color = Color.RED
                intersect()
                ball.move()
                canvas.drawCircle(
                    ball!!.GetX().toFloat(),
                    ball!!.GetY().toFloat(),
                    100F,
                    p,
                )

                if (Px + screenWidth / 5 > screenWidth) {
                    pDirection = 1
                }
                if (Px - screenWidth / 5 < 0) {
                    pDirection = 0
                }
                if (pDirection == 1) {
                    Px -= 5;
                }
                else {
                    Px += 5;
                }
                val left = (Px - screenWidth / 5).toFloat()
                val top = (Py - 100).toFloat()
                val right = (Px + screenWidth / 5).toFloat()
                val bottom = (Py + 100).toFloat()
                canvas.drawRect(left, top, right, bottom, p)

            } catch (e:Exception) {

            } finally {
                holder.unlockCanvasAndPost(canvas)
            }
        }

    }

    fun startDraw() {
        running = true
    }

    fun stopDraw() {
        running = false
    }


}