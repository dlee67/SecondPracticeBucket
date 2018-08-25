package main

import (
	//"fmt"
   "net/http"
   //"io/ioutil"
	//"log"
)

//func main(){
//   var port int = 8080
//
//   http.HandleFunc("/helloworld", helloWorldHandler)
//   log.Printf("Server starting on port %v\n", port)
//   log.Fatal(http.ListenAndServe(fmt.Sprintf(":%v", port), nil))
//}
//
//func helloWorldHandler(w http.ResponseWriter, r *http.Request){
//   fmt.Fprint(w, "Hello World\n")
//}

//func handler(writer http.ResponseWriter, request *http.Request) {
//    fmt.Fprintf(writer, "Hello World, %s!", request.URL.Path[1:])
//}
//
//func handler_two(writer http.ResponseWriter, request *http.Request){
//   fmt.Fprintf(writer, "I am the lulest, %s", request.URL.Path[1:])
//}

/*
   func Marshal(v interface{}) ([]byte, error)
   The above function passes (v interface{}) and returns ([]byte, error).
*/
//func main() {
//    http.HandleFunc("/", handler)
//    http.HandleFunc("/lulest", handler_two)
//    http.ListenAndServe(":8080", nil)
//}

func main(){
   mux := http.NewServerMux()
   files := http.FileServer(http.Dir("/public"))
}
