package com.example.kotlinbasics

fun main(){

   // val numbers:IntArray = intArrayOf(1,2,3,4,5,6)

  //  val numbers = intArrayOf(1,2,3,4,5,6)

    val numbers = arrayOf(1,2,3,4,5,6)
    val numberD = doubleArrayOf(1.0,2.0,3.0,4.0,5.0,6.0)
    print("Initial value ${numbers.contentToString()}")

    numberD[0] = 4.0
    numberD[1] = 2.0
    numberD[2] = 5.0
    numberD[3] = 1.0

//    print("\nFinal value ${numbers.contentToString()}")

    val days = arrayOf("Mon","Tue","Wed","Thu","Fri","Sat")
    print(days.contentToString())

    val fruit = arrayOf(Fruits("Apple",12.5), Fruits("Grapes",20.5), Fruits("Orange",30.0))
    for(fruit in fruit){
        print("${fruit.name}")
    }
    for (index in fruit.indices)
        print("${fruit[index].name} is in index $index")
//    print(fruit.contentToString())

    val mix = arrayOf("Sun", "Mon","Tue",1,2,3,Fruits("Apple",2.3))
    print(mix.contentToString())

//    print(numbers)
//    print(numbers.contentToString())
//    for (element in numbers){
//        print(" ${element+2}")
//        print(numbers[0])
    }
data class Fruits(val name:String, val price:Double)

