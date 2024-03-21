package com.example.kotlinbasics

data class User(val id: Long, var name: String)

fun main(){
    val user1 = User(1,"Raj")
//     val name = user1.name
//     user1.name = "Michel"
    val user2 = User(1,"Michel")

    println(user1 == user2)
//    println(name)

    println("User Details : $user1")

    val updateUser = user1.copy(name = "Rajashre Mallik")
    println(user1)
    println(updateUser)
    println(updateUser.component1()) // print 1
    println(updateUser.component2()) // print Rajashree Mallik

    val(id, name) = updateUser
    println("id=$id, name=$name")
}