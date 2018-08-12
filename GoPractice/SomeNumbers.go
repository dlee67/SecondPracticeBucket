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
}
