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
     */
    val readOnlyValue: String = "READ_ONLY";
    var experiencePoint: Int = 7;
    var theOtherPoint: Int = 10;
    var thereMustBeSomething: Boolean = true;
    var someString: String = "I am here.";
    var toTen: Array<Int> = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    //var someString: List = 3, 1, 2, 4;
    //var someMap: Map = "stuff" to 1;

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
}
