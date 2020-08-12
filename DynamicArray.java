@SuppressWarnings("unchecked")
class DynamicArray<T> implements Iterable<T> {
  private T[] arr;
  private int length = 0;
  private int capacity;
  private static final int INITIAL_CAPACITY = 8;

  public DynamicArray() {
    this(DynamicArray.INITIAL_CAPACITY);
  }

  public DynamicArray(int capacity) {
    if (capacity < 0)
      throw new IllegalArgumentException("Capacity must be greater than or equal 0 - got " + capacity);
    this.capacity = capacity;
    arr = (T[]) new Object[capacity];
  }

  public int size() {
    return length;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public void add(T item) {
    if (length + 1 > capacity) {
      // double internal array capacity
      capacity = capacity * 2;
      T[] arrCopy = (T[]) new Object[capacity];

      for (int i = 0; i < size(); i++)
        arrCopy[i] = arr[i];

      arr = arrCopy;
    }

    arr[length++] = item;
  }

  public T get(int index) {
    return arr[index];
  }

  public T removeAt(int index) {
    if (index >= size() || index < 0) {
      throw new ArrayIndexOutOfBoundsException(index);
    }
    T[] newArray = (T[]) new Object[capacity];
    T itemAtIndex = null;

    for (int i = 0, j = 0; i < size(); i++, j++) {
      if (i == index) {
        j--;
        itemAtIndex = arr[i];
        continue;
      }
      newArray[j] = arr[i];
    }
    arr = newArray;
    capacity = --length;
    return itemAtIndex;
  }

  public int indexOf(T item) {
    for (int i = 0; i < size(); i++) {
      if (item == null) {
        if (arr[i] == null) return i;
      } else if (item.equals(arr[i])) {
        return i;
      }
    }
    return -1;
  }

  public boolean remove(T item) {
    int index = indexOf(item);

    if (index != -1) {
      return removeAt(index) != null;
    }
    return false;
  }

  public boolean contains(T item) {
    return indexOf(item) != -1;
  }

  public void clear() {
    arr = (T[]) new Object[capacity = DynamicArray.INITIAL_CAPACITY];
    length = 0;
  }

  public void set(T item, int index) {
    if (index >= size() || index < 0) {
      throw new ArrayIndexOutOfBoundsException(index);
    }
    arr[index] = item;
  }

  public java.util.Iterator<T> iterator() {
    return new java.util.Iterator<T>() {
      int index = 0;

      public boolean hasNext() {
        return index < size();
      }

      public T next() {
        return arr[index++];
      }
    };
  }
}
