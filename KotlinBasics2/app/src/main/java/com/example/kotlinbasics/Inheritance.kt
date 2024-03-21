package com.example.kotlinbasics
//
////Super Class, Parent Class, Base Class
//open class Vehicle{
//    //Properties
//    //methods
//
//}

interface Drivable{
    val maxSpeed: Double
    fun drive():String
    fun brake(){
        println("The drivable is braking")
    }
}

// Super Class, Base Class Or Parent Class of Vehicle
open class Bus(override val maxSpeed: Double, val name : String, val brand : String) : Drivable {
  open var range: Double = 0.0
    fun extendRange(amount: Double){
        if (amount > 0)
            range += amount
    }
   // override fun drive(): String ="driving the interface drive"
    override fun drive(): String {
        return "driving the interface drive"
    }

   open fun drive(distance : Double){
        println("Drove for $distance KM")
    }

}
//Sub Class, Child Class, Or Derived Class of Bus
class ElectricBus(maxSpeed: Double, name: String, brand: String, batteryLife : Double)
    : Bus(maxSpeed, name, brand){

    var chargerTpe = "Type1"

        override var range = batteryLife*6
    override fun drive(distance: Double) {
        println("Drove for $distance KM on electricity")
    }

    override fun drive() : String{
        return "Drove for $range KM on electricity"
    }

    override fun brake() {
        super.brake()
        println("brake inside the electric bus")
    }
}

// Any class inherits from the any class
fun main(){
    var audi = Bus(200.0, "A3", "VOLVO")
    var tesla = ElectricBus(240.0,"A-Model","tesla",85.0)
    tesla.extendRange(200.0)
    tesla.chargerTpe = "Type2"

    tesla.drive()
    audi.brake()
    tesla.brake()

    //Polymorphism
    audi.drive(200.0)
    tesla.drive(200.0)

}