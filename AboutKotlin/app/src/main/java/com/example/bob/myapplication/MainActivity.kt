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

    }

    fun sum(a: Int, b: Int): Int /* Return type being the Int*/{
        return a + b;
    }

    /*
        Wow, that's actually cool, the null safe calls are freaking awesome.
        Coming back to it again, they are actually called nullable.
        And the nullable is not a type or a definition or some sort,
        it's a declaration that "this particular variable can be null."
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

}
