package com.example.bob.myapplication

interface Language {
    var age: Int                       // Kotlin allows you to have default getters and setters.
        get(){return 9001}             //
        set(arg: Int){this.age = 9001} //
    var desc: String
        get(){return "No lan"}
        set(arg: String){ this.desc = "...? Gotta give me a desc to bash the language."}
}