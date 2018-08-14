//Regarding pass by reference and pass by value.
package main

import "fmt"

func main(){

   var x int = 5 //Which is same as x := 5
   passByValue(x)
   fmt.Println("Thus, x will still equal to: ", x)
   fmt.Println("However, Go provides pointers, which enhances the experience of programming/debugging concurrent programs!")
   passByReference(&x)
   fmt.Println("See?:", x)
}

func passByValue(x int){
   fmt.Println("When pass by value, the mutation caused in the lexical context doesn't change anything outside it's context.")
   x = 10
}

func passByReference(x *int){
   *x = 15
}
