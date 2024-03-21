package com.example.kotlinbasics

fun main() {
   //immutable variable
   //TODO: add new functionalities

   /*
   This is
   a multiline
   comment
    */

   // type String
   val myName = "Malik"
   // type int 32 bit
   //type inference finds out the type from context
   var age = 27

   // Integer TYPES: Byte(8 bit), Short(16 bit),
   // Int(32 bit), Long(64 bit)
   val myByte: Byte = 13
   val myShort: Short = 125
   val myInt: Int = 123123123
   val myLong: Long = 39_819_453_564_854_300

   // Floating point number types: Float(32 bit), Double(64 bit)
   val myFloat: Float = 13.37F
   val myDouble: Double = 3.14193462656789787675454322121

   // Booleans he type boolean is used to represent logical values.
   // It can have two possible values true and false.

   var isSunny: Boolean = true
   isSunny = false

   //Character
   val letterChar = 'A'
   val digitChar = '$'

   //Strings
   val myStr = "Hello World"
   var firstCharInStr = myStr[0]
   var lastCharInStr = myStr[myStr.length - 1]
   var myLength = myStr.length

//   print("First Character $firstCharInStr and the length of the myStr is ${myStr.length}")

   // Arithmetic Operators (+, -, *, /, %)
   var result = 5 + 3
   val a = 5.0
   val b = 3
   var resultDouble: Double
   resultDouble = a / b
   result = (a / b).toInt()
   //print(resultDouble)

   // Comparison Operator (==, !=, <, >, <=, >=)
   val equal = 5 == 3
//   println("isEqual is $equal")

   val isNotEqual = 5 != 5

   // string interpolation
//   println("isNotEqual is $equal")
//   println("isGreater ${5>3}")
//   println("is5LowerEqual3 ${5 <= 3}")
//   println("is5GreaterEqual3 ${5>=3}")

   // Assignment Operators
   var myNum = 5
   myNum += 3
   myNum *= 4
//   println("myNum is $myNum")

   //Increment and Decrement Operators (++ , --)
   myNum++
//   println("myNum is $myNum")
//   println("myNum is ${++myNum}")
//   println("myNum is ${myNum++}")
//   println("myNum is ${--myNum}")

   var heightPerson1 = 170
   var heightPerson2 = 159

   if (heightPerson1 > heightPerson2)
      println("use raw force")
   else if (heightPerson1 == heightPerson2) {
      println("use your power technique 1337")
   } else {
      println("use technique")
   }

   val age1 = 31

   if (age1 >= 30) {
      println("you're over 30")
   }

   if (age1 >= 21)
      println("Now you can drink in US")
   else if (age1 >= 18) {
      println("you may vote now")
   } else if (age1 <= 16) {
      println("you may drive now")
   } else {
      println("you're too young")
   }

   var name = "Denis"
   if (name == "Denis")
      println("Welcome home")

   var isRainy = true
   if (isRainy)
      println("It's rainy")

   var season = 3
   when (season) {
      1 -> println("Spring")
      2 -> println("Summer")
      3 -> {
         println("Fall")
         println("Autumn")
      }
      4 -> println("Winter")
      else -> println("Invalid Season")

   }

   var month = 3
   when (month) {
      in 3..5 -> println("Spring")
      in 6..8 -> println("Summer")
      in 9..11 -> println("Fall")
      12, 1, 2 -> println("Winter")
      else -> println("Invalid Season")

   }

   age = 31
   when (age) {
      !in 0..15 -> println("Now you may drive in US")
      in 20..30 -> println("age is $age")
      16, 17 -> println("you now may drive")
      in 21..35 -> println("the correct age is $age")
      else -> println("None of the above")
   }


   var x: Any = 13.37f
   when (x) {
      is Int -> println("$x is an Int")
      !is Double -> println("$x is not a Double")
      is String -> println("$x is a String")
      else -> println("$x is none of the above")
   }

   // While loop executes a block of code repeatedly as long as a given condition is true
   x = 1
   while (x <= 10) {
      print("$x")
      x++
   }
   println(" \nwhile loop is done")

   x = 100
   while (x >= 1) {
      print("$x")
      x--
   }

   x = 100
   while (x >= 0) {
      print("$x")
      x -= 2
   }
   println(" \nwhile loop is done")

   x = 15
   do {
      print("$x")
      x++
   } while (x <= 10)
   println("\ndo while loop is done")

   var feltTemp = "cold"
   var roomTemp = 10
   while (feltTemp == "cold") {
      roomTemp++
      if (roomTemp >= 20) {
         feltTemp = "comfy"
         println("It's comfy now")
      }
   }

   for (num in 1..10) {
      print("$num")
   }
   for (i in 1 until 10){
      print("$i")// same as - for(i in 1.until(10))
   }
   println("--------")
   for (i in 10 downTo 1 step 2){ // same as - for(i in 10.downTo(1).step(2))
      print("$i")
   }
   for (i in 0..10000){
      if (i == 90001){
         println(" IT'S OVER 9000!!!! ")
      }
   }

   var humidityLevel = 80
   var humidity = "humid"
   while( humidity == "humid"){
      humidityLevel -=5
      println("\nhumidity decreased")
      if (humidityLevel < 60){
         humidity = "comfy"
         println("It's comfy now")

      }
   }
    x = 0
   for (y in 0..9) {
      x += y
   }

   println("$x")

   for(i in 1 until 20){
      print("$i ")
      if (i/2 == 5){
         continue
      }
   }


   // Exercise Variables and Datatype
   var string: String = "Android Masterclass"
   val float: Float = 13.37F
   val double: Double = 3.14159265358979
   var byte: Byte = 20
   var short: Short = 2020
   var long: Long = 18881234567
   var boolean: Boolean = true
   var character: Char = 'a'

}

