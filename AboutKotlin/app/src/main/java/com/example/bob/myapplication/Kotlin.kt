package com.example.bob.myapplication

import android.util.Log

//Fields in Kotlin's behaviors are largely determined by the var or val.
//var enables them to be mutable, and val makes them immutable; that's cool and all,
//but this is where things get kinda trippy.
/*
    var <propertyName>[: <PropertyType>] [= <property_initializer>]
    [<getter>]
    [<setter>]

    That above is the grammars for the setters and getters... wtf?
 */
//The line directly below is considered to be a primary constructor.
class Kotlin (_name: String, _age: Int){

    //Putting the underscore next to the arguments of the constructors
    //seems to be convention of the Kotlin.
    private var name: String = _name
    private var age: Int = _age
    //private var aboutLazy: String by lazy{lazyExample()}
    private val aboutLazy: String by lazy{lazyExample()} // Turns out, by lazy can only be used with vals, not vars.

    //initializer block obligates the attributes of programs to be, bounded by
    //certain conditions, or the illegalArgumentException is thrown.
    init {
        require(name != "Java", {"You mustn't use Java."})
        require(age < 10, {"Hmm, prefer to use young languages at the moment."})
    }

    //And this is called a secondary constructor.
    constructor(name: String):this(name, 10){
        //I can add logics into Kotlin!
        if(name == "Java"){
            this.name = "Not Kolin"
            this.age = 0
        }
    }

    fun introduceYourself(){
        Log.i("dhl", name + age + " years old!")
    }

    fun returnSomeString(): String{
        return "Emm kay... I will return something."
    }

    fun lazyExample(): String = "Counter intuitively, lazy initialization significantly improves your program's performance."
}