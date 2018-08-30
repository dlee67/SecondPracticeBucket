package com.example.bob.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    /*
        For some strange reason, Kotlin offers only one type of data,
        reference types.

        Ultimately, functions are types in Kotlin.

        Compiled language, meaning, my program is translated into machine-language
        instructions priori to execution by a special program.
        The vice versa is the runtime.

        When the errors are caught during the compile time, those errors are called "compiled time errors,"
        Kotlin circumvents this issue by having nullables, where null-pointer exceptions are usually caught
        during the run-time, and crashes the system more often than not.

        "A note about terminology: Technically, an object is instantiated when memory is allocated for it, and it is initialized when it is assigned a value. However, in practice the terms are often used slightly differently. Often, initialization is used to mean “everything required to make a variable, property, or class instance ready to use,” while instantiation tends to be limited to “creating an instance of a class.” In this book, we follow this more typical usage."
        Did not know the above until now.
     */
    val readOnlyValue: String = "READ_ONLY";
    var experiencePoint: Int = 7;
    var theOtherPoint: Int = 10;
    var thereMustBeSomething: Boolean = true;
    var someString: String = "I am here.";
    var toTen: Array<Int> = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    //var someString: List = 3, 1, 2, 4;
    //var someMap: Map = "stuff" to 1;
    var lolKotlin: Kotlin = Kotlin("Kotlin", 7)
    //var notSoLolKotlin: Kotlin = Kotlin("Java")
    var cPP: CPP = CPP("pointers", "difficult")
    var java: Java = Java("James Gosling")
    var coordinate: Coordinate = Coordinate(5, 5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView: TextView = findViewById(R.id.text_view) as TextView
        //textView.setText("There is something in the water.");
        //textView.setText(sum(experiencePoint, theOtherPoint) as String);
        //textView.setTextSize(100.0F);
        if(experiencePoint == 5){
            Log.i("dhl", "This is my experience point: " + experiencePoint);
        }else{
            Log.i("dhl", "Something about CS programs and stuff.");
        }
        Log.i("dhl", readOnlyValue);
        Log.i("dhl", withAnime("baki"));
        withNothing();
        withNothing("Something");
        countToTen(null);
        countToTen(toTen);
        alotOfStuff(var4 = "Not so wow.");
        //Unit is almost same things as the void.
        var saySomething: (String, String) -> Unit = { lol, lul ->
                Log.i("dhl", lol + lul)
        }
        var stringSomething: (String, String) -> Unit = { lol, lul ->
            Log.i("dhl", lol + lul)
        }
        saySomething("I will now proceed to say something ", "KONO AZORA NI YAKUSOKUO!");
        //In Kotlin, a function could take in a function as if they are a variable.
        //But this where things are kinda... well, weird.
        //The book (Big Nerd Ranch Kotlin) states that the below is categorized underneath as
        //function inlining.
        thatTakesLambda(stringSomething)
        Log.i("dhl", letExample("I should see this here."))
        Log.i("dhl", letExample(null))

        var thisString: String? = null
        //checkNotNull(thisString) // checkNotNull() is an integrated function in the Kotlin.
        var string_one: String = "lol"
        var string_two: String = "lol"
        if(string_one == string_two){
            Log.i("dhl", "Strings can be compared via equal signs.")
        }
        secondLetExample()
        lolKotlin.introduceYourself()
        //Apparently, Kotlin has setters and getters automatically;
        //however, we are allowed to create our own custom getters.
        Log.i("dhl", lolKotlin.returnSomeString())
        //notSoLolKotlin.introduceYourself()

        sayNiceThings(lolKotlin)
        sayNiceThings(java)
        sayNiceThings(cPP)

        cPP.description()
        java.description()

        Log.i("dhl", SomeThing.grabAString()) //Why not just use a design pattern?
        javaEight.description()

        javaEight.Complains().complain("Do I have to create an interface, "
        + "just for the sake of creating a lambda function?")
        Log.i("dhl", PureJava.saySomething())
        var someLanguageRight: SomeLanguage = SomeLanguage()
        Log.i("dhl", someLanguageRight.desc.toString())
        Log.i("dhl", someLanguageRight.age.toString())
    }

    fun sum(a: Int, b: Int): Int /* Return type being the Int*/{
        return a + b;
    }

    /*
        Wow, that's actually cool, the null safe calls are freaking awesome.
        Coming back to it again, they are actually called nullable.
        And the nullable is not a type or a definition or some sort,
        it's a declaration that "this particular variable can be null."

        To go over it again, the question mark notation is me telling the compiler,
        "I am allowing nulls to be passed here."
     */
    private fun withAnime (someString: String?): String? {
        if(someString == null){
            Log.i("dhl", "No anime fer you.")
            return null;
        }else if(someString.equals("aot")){
            return "You are watching aot";
        }else if(someString.equals("baki")){
            return "You are a real man"
        }
        return "Lol nothing here."
    }

    //Kotlin supports default value, nice!
    private fun withNothing(someString: String = "Nothing here"){
        Log.i("dhl", someString);
    }

    private fun countToTen(upToTen: Array<Int>?){
        if(upToTen != null) {
            for (item: Int in toTen) {
                Log.i("dhl", "" + item);
            }
            return;
        }
        Log.i("dhl", "There is nothing but us chickens here.");
    }

    private fun alotOfStuff(var1: Int = 5, var2: Int = 7, var3: Int = 9, var4: String = "Wow!"){
        Log.i("dhl", "var1 is: " + var1);
        Log.i("dhl", "var2 is: " + var2)
        Log.i("dhl", "var3 is: " + var3)
        Log.i("dhl", "var4 is: " + var4)
    }

    //I guess Nothing type is like a pass keyword in Python?
    private fun shouldReturnString(): String{
        TODO("implement the string building functionality here to return a string")
    }

    //Kotlin provides something called Nothing type, is a return type that
    //something is guranteed to fail.
    private fun TODO(): Nothing{
        throw NotImplementedError();
    }

    private fun thatTakesLambda(someThingFunction: (String, String) -> Unit){
        someThingFunction("Well,", " there's that.")
    }

    //let keyword allows us to create our function scopes; however,
    //in order to truly appreciate let, one needs to use it keyword with it...
    private fun letExample(arg: String?): String{
        return arg.let {
            if(arg == null){
                return "Default stuff"
            }else{
                return arg
            }
        }
    }

    private fun secondLetExample(){
        /*
            At least from the looks of things, the let keyword can only be used
            next to a function parameter, where let immediately creates a lambda function
            for the function let keyword is being used for.
            Thus, enabling the user to immediately apply an operation to the element that's
            returned from a function.

            Like, the combination of using coalescing operator and the let keyword could
            almost always the guarantee that certain elements will be not null.
         */
        var listOfNumbers = listOf(1, 2, 3, 4, 5).let{
            for(element in it){
                Log.i("dhl", "From secondLetExample: " + element)
            }
        }
    }

    //The coalescing operator is me telling compiler that,
    //"hey, if the thing on the left is null, do the one on the right instead.
    private fun coalescingOperator(arg: String?): String{
        return arg ?: return "WOW"
    }

    //Any data type is nearly the same thing as Object data type.
    //Any data type could be treated as many things; for example,
    //any as Java, or any as CPP, there are both valid.
    fun sayNiceThings(any: Any?){
        if(any is Java){
            Log.i("dhl", "Verbose enough, fun to write with enough; also, prone to failure enough.")
        }else if(any is CPP) {
            Log.i("dhl", "Very, very powerful tool to use, can pretty much immitate what the programming realm can offer; however, TOO HARD TO USE!")
        }else if(any is Kotlin){
            Log.i("dhl", "Mends everything Java doesn't do well in. ")
        }else{
            Log.i("dhl", "Is this null?")
        }
    }

    //Object is also a keyword, which promises that certain objects in the memory
    //is promised to be a singleton.
    object /* What the heck? */ SomeThing{
        fun grabAString(): String{
            return "SomeThing object is entirely Singleton."
        }
    }

    object javaEight: Java("James Gosling"){
        override fun description(){
            Log.i("dhl", "Has \"Lambdas\" and Streams.")
        }

        class Complains{
            fun complain(complains: String){
                Log.i("dhl", complains)
            }
        }
    }

    //There is something called data class, which is specifically made to hold data.
    data class Coordinate(val x: Int, val y: Int){
        val isInBounds = x >= 0 && y >= 0
    }
}
