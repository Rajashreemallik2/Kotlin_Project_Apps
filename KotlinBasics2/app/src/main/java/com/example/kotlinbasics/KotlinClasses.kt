package com.example.kotlinbasics

var b = 3
fun main(){
    // creating instances of classes
    var raj:Person = Person("Rajashree", "Mallik",27)
    raj.hobby = "to skateboard"
    raj.age = 27
    println("raj is ${raj.age} years old")
    raj.stateHobby()
    var joe = Person()
    joe.hobby = "play video games"
    joe.stateHobby()
//    var johnPeterSon = Person(lastName = "peterson")

    myFunction(5)
     b = 3

}

class Person(firstName: String = "john", lastName: String = "doe"){
    // Member variables - properties
    var age: Int?= null
    var hobby: String = "watch netflix"
    var firstName: String? = null

    // Initializer Block
    init {
        this.firstName = firstName
        println("Initialized a new Person object with " +
                "firstName = $firstName and lastname = $lastName")
    }

    //member secondary constructor
    constructor(firstName: String, lastName: String, age: Int)
            : this(firstName, lastName){
        this.age = age
        println("Initialized a new Person object with " +
                "firstName = $firstName and lastname = $lastName and age = $age")



    }
    // Member functions - methods
    fun stateHobby(){
        println("$firstName\'s My hobby is $hobby")
    }

}

//This is a parameter
fun myFunction(a: Int){
    // a is a variable
     b = a
    println("b is $b")
}