package main

import "fmt"

func main() {
  var a DynamicArray = New(10)

  a.Add(3)
  a.Add(5)
  a.Add(9)
  a.Add("Hello")

  fmt.Println(a.IndexOf(5))

  fmt.Println(a)

  fmt.Println(a.Size())

  fmt.Println(a.IsEmpty())

  fmt.Println(a.Contains("Hello"))

  fmt.Println(a.Get(2))

  a.Clear()

  fmt.Println(a.IsEmpty())
}
