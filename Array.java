@SuppressWarnings("unchecked")
class Array<T> implements Iterable<T> {
  private T[] arr;
  private int length = 0;
  private int capacity;

  public Array() {
    this(8);
  }

  public Array(int capacity) {
    if (capacity < 0)
      throw new IllegalArgumentException("Capacity must be greater than or equal 0 - got " + capacity);
    this.capacity = capacity;
    arr = (T[]) new Object[capacity];
  }

  public int size() {
    return length;
  }

  public boolean isEmpty() {
    return this.size() == 0;
  }

  public void add(T item) {
    if (this.length + 1 > capacity) {
      // double internal array capacity
      this.capacity = this.capacity * 2;
      T[] arrCopy = (T[]) new Object[this.capacity];

      for (int i = 0; i < this.size(); i++)
        arrCopy[i] = this.arr[i];

      this.arr = arrCopy;
    }

    this.arr[length++] = item;
  }

  public T get(int index) {
    return this.arr[index];
  }

  public java.util.Iterator<T> iterator() {
    return null;
  }
}
