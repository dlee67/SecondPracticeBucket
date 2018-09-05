rpackage main

import (
   "fmt"
   "time"
//   "strings" // Go doesn't let you compile codes, unless the all the imports are used.
//   "os"
)

//func f(n int){
//   for i := 0; i < 10; i++{
//      fmt.Println(n, ":", i)
//      time.Sleep(time.Second)
//   }
//}
//
//func main() {
//   go f(0)
//   var input string
//   fmt.Scanln(&input) //So, that's how the user input is taken...
//   fmt.Println("You typed:", input)
//}

//func pinger(c chan string) {
//    for i := 0; ; i++ {
//        c <- "ping"
//    }
//}
//
//func printer(c chan string) {
//    for {
//        msg := <- c
//        fmt.Println(msg)
//        time.Sleep(time.Second * 1)
//    }
//}
//
//func main() {
//    var c chan string = make(chan string)
//    go pinger(c)
//    go printer(c)
//    var input string
//    fmt.Scanln(&input)
//}

/*
   In the world of Go, there are send only type and read only type.
                              c chan<- String       c <-chan String
*/

func ka(c chan string){
   for{
      c <- "ka.."
      time.Sleep(time.Second)
   }
}

func ha(c chan string){
   for{
      c <- "ha.."
      time.Sleep(time.Second)
   }
}

func me(c chan string){
   for{
      c <- "me.."
      time.Sleep(time.Second)
   }
}

func kiCharger(c chan string){
   for{
      fmt.Println(<- c)
      time.Sleep(time.Second)
   }
}

func main(){
   //var c chan string = make(chan string)
   //Perfect example why go functions are not self concurrent.
   //go kiCharger(c)
   //go ka(c)
   //go me(c)
   //go ha(c)
   //go me(c)
   var input string
   fmt.Scanln(&input)
   fmt.Println("HA!!!!!")

   //Wow, that's neat.
   go func(){
      for{
         fmt.Println("Spinning")
         time.Sleep(time.Second)
      }
   }()
   fmt.Scanln(&input)
}
