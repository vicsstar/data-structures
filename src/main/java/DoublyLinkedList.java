import java.util.Iterator;

class DoublyLinkedList<T> implements Iterable<T> {
  private int size = 0;
  private Node<T> head;
  private Node<T> tail;

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      Node<T> trav = head;

      @Override
      public boolean hasNext() {
        return trav.next != null;
      }

      @Override
      public T next() {
        if (trav == null) {
          throw new IllegalStateException("Reached end of iterator");
        }
        T data = trav.data;
        trav = trav.next;
        return data;
      }

      @Override
      public void remove() {
        throw new IllegalStateException("Cannot remove using this iterator");
      }
    };
  }

  private static class Node<T> {
    T data;
    Node<T> prev;
    Node<T> next;

    Node(T data, Node<T> prev, Node<T> next) {
      this.data = data;
      this.prev = prev;
      this.next = next;
    }

    public boolean isHead() {
      return prev == null;
    }

    public boolean isTail() {
      return next == null;
    }

    public String toString() {
      return data.toString();
    }
  }

  public void clear() {
    if (isEmpty()) return;

    Node<T> trav = head;

    for (; trav != null; trav = trav.next) {
      trav.data = null;
      trav.prev = null;
    }

    if (tail != null) {
      tail.prev = null;
    }
    head = tail = null;
    size = 0;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return head == null && tail == null;
  }

  @SafeVarargs
  public final void add(T... elem) {
    for (T item : elem) addLast(item);
  }

  public void addFirst(T elem) {
    Node<T> newNode = new Node<>(elem, null, null);

    if (isEmpty()) {
      head = newNode;
    } else {
      Node<T> prevHead = head;
      head = newNode;
      head.next = prevHead;
      prevHead.prev = head;
    }
    ++size;
  }

  public void addLast(T elem) {
    Node<T> newNode = new Node<>(elem, null, null);

    if (isEmpty()) {
      head = newNode;
    } else {
      Node<T> prevTail = tail;
      tail = newNode;

      if (prevTail != null) {
        tail.prev = prevTail;
        prevTail.next = tail;
      } else {
        tail.prev = head;
        head.next = tail;
      }
    }
    ++size;
  }

  public T peekFirst() {
    assertListNotEmpty();
    return head.data;
  }

  public T peekLast() {
    assertListNotEmpty();

    if (tail != null) {
      return tail.data;
    }
    return head.data;
  }

  public T removeFirst() {
    assertListNotEmpty();

    Node<T> trav = head;
    T data = trav.data;

    head = head.next;
    head.prev = trav.next = null;
    trav.data = null;
    --size;

    return data;
  }

  public T removeLast() {
    assertListNotEmpty();

    Node<T> trav = tail;
    T data = tail.data;

    tail = tail.prev;
    tail.next = trav.prev = null;
    trav.data = null;
    --size;

    return data;
  }

  private T remove(Node<T> node) {
    T data = node.data;

    Node<T> prev = node.prev;
    Node<T> next = node.next;

    if (prev != null) {
      prev.next = next;
    }

    if (next != null) {
      next.prev = prev;
    }
    --size;
    return data;
  }

  public T removeAt(int index) {
    assertListNotEmpty();
    if (index < 0 || index > (size - 1)) throw new IndexOutOfBoundsException();

    final boolean firstHalf = index < (size / 2);

    Node<T> node = firstHalf ? head : tail;

    if (firstHalf) {
      for (int i = 0; i < index; i++) {
        node = node.next;
      }
    } else {
      for (int i = size - 1; i > index; i--) {
        node = node.prev;
      }
    }
    return remove(node);
  }

  public boolean remove(T data) {
    assertListNotEmpty();

    Node<T> trav = head;
    T travData;

    while ((travData = trav.data) != data && !trav.isTail()) {
      trav = trav.next;
    }

    if (travData != data) return false;

    return remove(trav) != null;
  }

  public int indexOf(T item) {
    int index = 0;
    Node<T> trav = head;

    while (trav != null) {
      if (trav.data == item) {
        return index;
      }
      ++index;
      trav = trav.next;
    }
    return -1;
  }

  public T getAt(int index) {
    assertListNotEmpty();
    if (index < 0 || index > (size - 1)) throw new IndexOutOfBoundsException("Index out of bounds " + index);

    final boolean firstHalf = index < (size / 2);

    Node<T> node = firstHalf ? head : tail;

    if (firstHalf) {
      for (int i = 0; i < index; i++) {
        node = node.next;
      }
    } else {
      for (int i = size - 1; i > index; i--) {
        node = node.prev;
      }
    }
    return node != null ? node.data : null;
  }

  public boolean contains(T item) {
    return indexOf(item) != -1;
  }

  private void assertListNotEmpty() {
    if (isEmpty()) throw new IllegalStateException("List is empty");
  }

  @Override
  public String toString() {
    if (isEmpty()) return "[]";

    Node<T> trav = head;
    final StringBuilder buffer = new StringBuilder("[" + trav.data);

    while ((trav = trav.next) != null) {
      buffer.append(", ").append(trav.data);
    }
    buffer.append("]");
    return buffer.toString();
  }
}
