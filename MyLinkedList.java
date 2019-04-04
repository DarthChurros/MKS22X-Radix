import java.util.NoSuchElementException;

public class MyLinkedList<E> {
  private int size;
  private Node start, end, current;

  public MyLinkedList() {
    size = 0;
  }

  public int size() {
    return size;
  }

  public void clear() {
    size = 0;
    start = null;
    end = null;
  }

  public boolean add(E value) {
    if (size == 0) {
      start = new Node(value);
      end = start;
      size++;
      return true;
    }
    end.setNext(new Node(end));
    end = end.next();
    end.set(value);
    size++;
    return true;
  }

  public E removeFront() {
    if (size == 0) throw new IndexOutOfBoundsException();
    if (size == 1) {
      E ans = start.value();
      start = null;
      end = null;
      size = 0;
      return ans;
    }
    Node old = start;
    start = old.next();
    start.setPrev(null);
    size--;
    return old.value();
  }

  public void extend(MyLinkedList<E> other) {
    if (size == 0) {
      start =  other.start;
      end = other.end;
      size = other.size;
    } else if (other.size != 0) {
      end.setNext(other.start);
      other.start.setPrev(end);
      end = other.end;
      size += other.size();
    }
    other.end = null;
    other.start = null;
    reset();
  }

  public boolean hasNext() {
    return current != end;
  }

  public E next() {
    if (current != null && current.next() == null) throw new NoSuchElementException();
    if (current == null) current = start;
    else current = current.next();
    return current.value();
  }

  public void reset() {
    current = null;
  }

  public String toString() {
    String ans = "[";
    try {
      Node current = start;
      while (current.hasNext()) {
        ans += current.value() + ", ";
        current = current.next();
      }
      return ans + current.value() + "]";
    } catch (NullPointerException e) {
      return "[]";
    }
  }

  private Node getNode(int index) {
    Node current = start;
    while (index > 0) {
      current = current.next();
      index--;
    }
    return current;
  }

  private class Node {
    private E data;
    private Node next, prev;

    public Node(Node last) {
      prev = last;
    }

    public Node(E val) {
      data = val;
    }

    public Node next() {
      return next;
    }

    public Node prev() {
      return prev;
    }

    public void setNext(Node newNext) {
      next = newNext;
    }

    public void setPrev(Node newPrev) {
      prev = newPrev;
    }

    public boolean hasNext() {
      return next != null;
    }

    public E value() {
      return data;
    }

    public void set(E value) {
      data = value;
    }
  }
}
