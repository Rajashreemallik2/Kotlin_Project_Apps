package com.example.kotlinbasics

abstract class Mammal(private val name: String, private val origin: String,
       private val weight: Double) { //concrete (non abstract) properties

    //abstract property ( must be overridden by subclass )
    abstract var maxSpeed: Double

    // abstract method must be implemented by subclass
    abstract fun run()
    abstract fun breath()

    //concrete (non abstract) method
    fun displayDetails() {
        println("Name: $name, Origin: $origin, Weight: $weight, MaxSpeed: $maxSpeed")
    }
}
class Human(name : String, origin: String, weight: Double,
            override var maxSpeed: Double) : Mammal(name, origin, weight){

        override fun run() {
            println("runs on two legs")
        }

        override fun breath() {
            println("breath through mouth or nose")
        }
    }
class Elephant(name : String, origin : String, weight : Double,
               override var maxSpeed: Double) : Mammal(name, origin, weight){


    override fun run() {
        //code to run
        println("Runs on four legs")
    }

    override fun breath() {
        println("Breath through the trunk")
    }

    }
fun main(){
    val human = Human("Raj","India",50.0, 200.0)
    val elephant = Elephant("Rosy","Russia",400.0,240.0)

    human.run()
    elephant.run()

    human.breath()
    elephant.breath()
}
