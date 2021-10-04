package com.example.progressiveoverload.test

import com.example.progressiveoverload.data.Exercise
import com.example.progressiveoverload.data.Sets

fun main(){
    var a = mutableListOf(
        Exercise("운동1", mutableListOf(Sets(0.0,0))),
        Exercise("운동2", mutableListOf(Sets(0.0,0))),
        Exercise("운동3", mutableListOf(Sets(0.0,0))),
        Exercise("운동4", mutableListOf(Sets(0.0,0))),
        Exercise("운동5", mutableListOf(Sets(0.1,0))),
        Exercise("운동6", mutableListOf(Sets(0.0,0))),
        Exercise("운동7", mutableListOf(Sets(0.0,0))),
        Exercise("운동8", mutableListOf(Sets(0.0,0)))
    )
    a.add(Exercise("운동9", mutableListOf(Sets(0.0,0))))
    println(a)
    println(a.size)
    a.apply {
        var b: Exercise? = null
        forEach {
            if (it.name == "운동2" || it.name == "운동7")
                b = it
        }
        println(b)
        remove(b)
    }
    println(a.size)
    println(a)
}