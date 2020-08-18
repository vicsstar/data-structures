package main

import "fmt"

const initialCapacity int = 8

type IndexOutOfBoundsError struct {
  Index int
}

func (e IndexOutOfBoundsError) Error() string {
  return fmt.Sprint("Array index out of bounds: %v", e.Index)
}

type DynamicArray interface {
  Size() int
  IsEmpty() bool
  Add(interface{})
  Get(int) (interface{}, error)
  IndexOf(interface{}) int
  RemoveAt(int) (interface{}, error)
  Remove(interface{}) bool
  Contains(interface{}) bool
  Clear()
  Set(interface{}, int)
  String() string
}

type DynamicArrayImpl struct {
  arr      []interface{}
  length   int
  capacity int
}

func New(capacity int) *DynamicArrayImpl {
  if capacity == 0 {
    capacity = initialCapacity
  }
  dynArray := &DynamicArrayImpl{}
  dynArray.capacity = capacity
  dynArray.arr = make([]interface{}, capacity)
  return dynArray
}

func (a* DynamicArrayImpl) Size() int {
  return a.length
}

func (a* DynamicArrayImpl) IsEmpty() bool {
  return a.length == 0
}

func (a* DynamicArrayImpl) Add(item interface{}) {
  if a.length + 1 > a.capacity {
    // double internal array capacity
    a.capacity = a.capacity * 2
    newArray := make([]interface{}, a.capacity)
    
    for i := 0; i < a.length; i++ {
      newArray[i] = a.arr[i]
    }
    a.arr = newArray
  }
  a.arr[a.length] = item
  a.length += 1
}

func (a* DynamicArrayImpl) Get(index int) (interface{}, error) {
  if index >= a.length || index < 0 {
    return nil, IndexOutOfBoundsError{ index }
  }
  return a.arr[index], nil
}

func (a* DynamicArrayImpl) IndexOf(item interface{}) int {
  for i := 0; i < a.length; i++ {
    it := a.arr[i]

    if item == nil {
      if it == nil {
        return i
      }
    } else if it == item {
      return i
    }
  }
  return -1
}

func (a* DynamicArrayImpl) RemoveAt(index int) (interface{}, error) {
  if index >= a.length || index < 0 {
    return nil, IndexOutOfBoundsError{ index }
  }
  a.capacity -= 1
  newArray := make([]interface{}, a.capacity)
  var item interface{}
  j := 0

  for i := 0; i < a.length; i++ {
    it := a.arr[i]

    if i != index {
      newArray[j] = it
      j += 1
    } else {
      item = it
    }
  }
  a.arr = newArray
  a.length -= 1
  return item, nil
}

func (a* DynamicArrayImpl) Remove(item interface{}) bool {
  index := a.IndexOf(item)
  _, err := a.RemoveAt(index)

  if err != nil {
    panic(err)
  }
  return index != -1
}

func (a* DynamicArrayImpl) Contains(item interface{}) bool {
  return a.IndexOf(item) != -1
}

func (a* DynamicArrayImpl) Clear() {
  a.capacity = initialCapacity
  a.arr = make([]interface{}, a.capacity)
  a.length = 0
}

func (a* DynamicArrayImpl) Set(item interface{}, index int) {
  a.arr[index] = item
}

func (a* DynamicArrayImpl) String() string {
  if a.length == 0 {
    return "[]"
  }
  var str string = "["

  for i := 0; i < a.length; i++ {
    str = fmt.Sprintf("%v%v", str, a.arr[i])

    if i != a.length - 1 { // last item
      str = fmt.Sprintf("%v, ", str)
    }
  }
  str = fmt.Sprintf("%v]", str)
  return str
}
