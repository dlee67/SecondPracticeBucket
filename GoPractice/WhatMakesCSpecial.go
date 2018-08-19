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
   fmt.Println("Student's name: ", someStudent.name)
   fmt.Println("Student's GPA: ", someStudent.GPA)
   fmt.Println("Student's age: ", someStudent.age)
   setStudent(someStudent)
   fmt.Println("Student's name: ", someStudent.name)
   fmt.Println("Student's GPA: ", someStudent.GPA)
   fmt.Println("Student's age: ", someStudent.age)

   var otherStudent Student = Student{name: "You!", GPA: 4.0, age: 20}
   //Regarding the line above,
   //var otherStudent *Student = Student{name: "You!", GPA: 4.0, age: 20} //The left doesn't work...
   fmt.Println("Your name: ", otherStudent.name)
   fmt.Println("Your GPA: ", otherStudent.GPA)
   fmt.Println("Your age: ", otherStudent.age)

   fmt.Println("This is how receiver works: ", otherStudent.getAge())
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

func setStudent(someStudent *Student){
   someStudent.name = "Not so funny"
   someStudent.GPA = 0.0
   someStudent.age = 15
}

//There is this thing called receiver, and they are defined outside the lexical context of the structs;
//however, it's suppose to immitate a method from OOP capable languages.
//It does, but it's really hard to read...
//so, if I am invoking getAge() like: someStudent.getAge(), I am getting the Age of the someStudent.
func (s *Student) getAge() int{
   return s.age
}
