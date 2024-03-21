package com.example.kotlinbasics

fun main(){
    var myCar = Car()
    myCar.maxSpeed = 200
    myCar.maxSpeed = 240
    println("My car model is ${myCar.myModel}")
    println("Brand is ${myCar.myBrand}")
    println("MaxSpeed is ${myCar.maxSpeed}")

}

class Car{
    lateinit var owner: String
    val myBrand: String = "BMW"
        //Custom getter
    get() {
        return field.lowercase()
    }

    var maxSpeed: Int =250
   // get() = field
    set(value) {
        field = if(value > 0) value else throw IllegalArgumentException("maxSpeed can not be less than 0")
    }

    var myModel: String = "M5"
    private set

    init {
       this.owner = "Frank"
    }
}