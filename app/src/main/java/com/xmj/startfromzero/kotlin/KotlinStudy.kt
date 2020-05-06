package com.xmj.startfromzero.kotlin

/**
 * kotlin
 * 1、变量的声明
 *  val（value的简写）：不可变变量，初始赋值后不能再赋值，相当于Java中的final
 *  var（variable的简写）：可变变量，与val相反
 *  kotlin类型推导机制将推导出变量具体类型
 * 2、函数的声明
 *  fun 函数名(参数名:参数类型，...):返回值类型{}
 */

fun main(){
    println(numAdd(5, 9))
}

fun numAdd(x: Int, y: Int): Int{
    return x + y
}

//语法糖
fun addNum(x: Int, y: Int) = x + y

fun largeNum(x: Int, y: Int): Int{
    return if (x > y){
        x
    }else{
        y
    }
}

//语法糖
fun largeNumber(x: Int, y: Int) = if (x > y) x else y































































