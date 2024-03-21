package com.example.kotlinbasics

fun main(){
    val myArrayList: ArrayList<Double> = ArrayList()
    myArrayList.add(13.1234)
    myArrayList.add(15.4533)
    myArrayList.add(12.4565)
    myArrayList.add(23.4321)
    myArrayList.add(18.4543)
    var total = 0.0
    for (i in myArrayList){
        total += i
    }
    var average = total / myArrayList.size
    print("Average is $average")
}