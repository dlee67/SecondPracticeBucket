//In Go, in order for the module to be runnable,
//the package and function needs to be main like below.
package main

import "fmt"

//Fields are allowed in Go.
var someNum uint8 = 5;

func main(){
   fmt.Println("2+2=", 5);
   fmt.Println("The len of str is: ", len("str"))
   fmt.Println("This is not a ", "concatnation")
   fmt.Println("This is a " + "concatnation")
//Variables in Go is more verbose than Java's.
   var someVar string = "This is someVar."
//variable declaration is required, afterwards, there needs to be
//an identifier, followed by the data type.
   fmt.Println(someVar)
   fmt.Println(someNum)

//Under the assumption that variable counter is the first time being used,
//Go will immediately recognize that counter is typed int.
   counter := 0
//Pyhon needed to do something like this.
   for counter <= 10{
      fmt.Print(counter, " ")
      counter++;
   }
   fmt.Print("\n")
//And Go kinda one ups Java in this regard too...
   for c := 0; c <= 10; c++{
      fmt.Print(c, " ")
   }
   fmt.Print("\n")

   saySomething()
//Go compiler will not concatnate int and String like Java can.
   fmt.Println("From getInt, I got: ", getInt())
   fmt.Println("From getUintE, I got: ", getUintE())
//Golang has this concept called Closure, where I could have function inside a function.
//It's not an anonymous function, like Java has, because closure functions is not coupled
//with things like interface.
   fmt.Println(makeEvenGenerator())
//If you want to be fancy about it, Go allows you to return the function
//inside a function.
//And recursion works pretty much the same.
   fmt.Println(recurTillTen(0))

   defer third() // It might seem like defer will obligate the function to be executed last in the lexical context.
   first()
   second()
   combine() //But it seems like defer moves the function call to a special stack or some sort.
   //panicAndRecover()
   //fmt.Println("This is Yang's magic!") //Will not be printed, it seems like, unless specified, the recover()
                                          //progress the program counter further.   
}

func saySomething(){
   fmt.Println("I will say something.")
}

func getInt() int {
   return 5
}

func getUintE() uint8{
   return 100
}

func makeEvenGenerator() func() uint {
    i := uint(0)
    return func() (ret uint) {
        ret = i
        i += 2
        return
    }
}

func first(){
   fmt.Println("This is first.")
}

func second(){
   fmt.Println("This is second.")
}

func third(){
   fmt.Println("This is third.")
}

func combine(){
   defer third()
   first()
   second()
}

func uselessPanicAndRecover(){ //Pretty useless function because panic immediately throws a run-time error, which immediately
                              //halts the program.
   panic("UWAAAAAAAA!!! WE ARE ALL GOING TO DIEEEEE!!!")
   str := recover()
   fmt.Println(str)
}

func panicAndRecover(){
   defer fmt.Println(recover())
   panic("Fire 3 shots at the traspassing ships and run away!")
}

//Type declarations are always after the variable.
func recurTillTen(val int) int {
   fmt.Println("Inside the recursion.")
   val++;
   if val != 10{
      return recurTillTen(val)
   }
   return val
}
