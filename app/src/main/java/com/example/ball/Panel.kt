package com.example.ball

import android.util.Log

class Panel(
    viewWidth: Double,
    viewHeight: Double,
) {
    var x = 0.0
    var y = 0.0
    init {
        x = viewWidth / 2
        y = viewHeight - 200
    }
    fun GetX(): Double {
        return x
    }
    fun GetY(): Double {
        return y
    }

}