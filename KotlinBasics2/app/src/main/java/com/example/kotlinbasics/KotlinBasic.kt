package com.example.kotlinbasics

fun main(){

//    var name : String = "Rajashree"
  //  name = null -> Compilation Error
    var nullableName : String? = "Malik"
//    nullableName = null
//    var len = name.length
    var len2 = nullableName?.length
//    println(nullableName?.toLowerCase())
     nullableName?.let { println(it.length) }

    // ?: Elvis Operator
    val name = nullableName ?: "Raj"
    println("name is $name")

    println(nullableName !!.lowercase())


    /* if (nullableName != null){
        var len2 = nullableName.length
    }else{
        null
    } */



    //argument
    var avg = avg(5.3,13.37)
    print("result is $avg")
}
fun avg(a: Double, b: Double): Double{
    return (a+b)/2
}
// Method - a method is a Function within a class
// Parameter(Input)
fun addUp( a: Int, b: Int) : Int{
    //Output
    return a+b

}

fun myFunction(){
    print("called from myFunction ")

}