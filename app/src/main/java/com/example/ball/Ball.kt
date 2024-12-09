package com.example.ball

import android.util.Log

class Ball(
    viewWidth: Double,
    viewHeight: Double,
    ) {
    var x = 0.0
    var y = 0.0
    var Vx = 100.0
    var Vy = 100.0
    var h = 0.0
    var w = 0.0
    init {
        w = viewWidth
        h = viewHeight
    }

    fun GetX(): Double {
        return x
    }
    fun GetY(): Double {
        return y
    }
    fun move() {
        if (x + 100.0 > w) {
            Vx = -20.0
        }
        if (x - 100.0 < 0) {
            Vx = 20.0
        }
        if (y + 100.0 > h) {
            Vy = -20.0
        }
        if (y - 100.0 < 0) {
            Vy = 20.0
        }
        x += Vx
        y += Vy
    }

}
