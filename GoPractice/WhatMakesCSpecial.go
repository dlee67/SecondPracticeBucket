//Regarding pass by reference and pass by value.
package main

import "fmt"

type Student struct{
   name string
   GPA float32
   age int
}

func main(){

   var x int = 5 //Which is same as x := 5
   var someNumber *int = new(int) //A way to make a point in GO is to use new keyword, which is a built in function in this case.

   passByValue(x)
   fmt.Println("Thus, x will still equal to: ", x)
   fmt.Println("However, Go provides pointers, which enhances the experience of programming/debugging concurrent programs!")
   passByReference(&x)
   fmt.Println("See?:", x)

   setInt(someNumber) //Makes sense, the parameter of setInt is already *int.
   fmt.Println(*someNumber)

   var someStudent *Student = new(Student)
   someStudent.name = "Lulz!"
   someStudent.GPA = 9000.0
   someStudent.age = 16
}

func passByValue(x int){
   fmt.Println("When pass by value, the mutation caused in the lexical context doesn't change anything outside it's context.")
   x = 10
}

func passByReference(x *int){
   *x = 15
}

func setInt(y *int) {
   *y = 10 //Dereference it again to mutate it.
}
