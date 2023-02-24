import java.util.StringJoiner;

public class MyLinkedList<T> {
    //add(Object value)
    //remove(int index)
    //clear()
    //size()
    //get(int index)

    int size = 0;
    Node<T> first;
    Node<T> last;

    public MyLinkedList() {
    }

    public void add(T e) {
        Node<T> l = last;
        Node<T> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    public T get(int index) {
        if (index >= 0 && index < size) {
            return node(index).item;
        } else {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    public T remove(int index) {
        if (index >= 0 && index < size) {
            Node<T> x = node(index);
            T element = x.item;
            Node<T> prev = x.prev;
            Node<T> next = x.next;
            if (prev == null) {
                first = next;
            } else {
                prev.next = next;
                x.prev = null;
            }

            if (next == null) {
                last = prev;
            } else {
                next.prev = prev;
                x.next = null;
            }
            x.item = null;
            size--;
            return element;
        } else {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    public int size() {
        return size;
    }

    public void clear() {
        Node<T> x = first;
        while (x != null) {
            Node<T> next = x.next;
            x.prev = null;
            x.item = null;
            x.next = null;
            x = next;
        }
        first = null;
        last = null;
        size = 0;
    }

    private Node<T> node(int index) {
        if (index < (size >> 1)) {
            Node<T> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<T> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    private static class Node<T> {
        T item;
        Node<T> prev;
        Node<T> next;

        public Node(Node<T> prev, T element, Node<T> next) {
            this.prev = prev;
            this.item = element;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        StringJoiner result = new StringJoiner(", ");
        Node<T> x = first;
        while (x != null) {
            Node<T> next = x.next;
            result.add(x.item.toString());
            x = next;
        }
        return "[" + result + "]";
    }

    public static void main(String[] args) {
        MyLinkedList<String> myList = new MyLinkedList<>();

        System.out.println("myList.size = " + myList.size);
        System.out.println("myList = " + myList);

        myList.add("Item #1");
        System.out.println("myList = " + myList);
        myList.add("Item #2");
        myList.add("Item #3");
        myList.add("Hello");
        myList.add("World");
        myList.add("test");
        myList.add("myId");
        System.out.println("myList = " + myList);
        System.out.println("myList.size() = " + myList.size());
        System.out.println("myList.get(2) = " + myList.get(2));
        System.out.println("myList.remove(3) = " + myList.remove(3));
        System.out.println("myList = " + myList);
        System.out.println("myList.size() = " + myList.size());
        myList.clear();
        System.out.println("myList = " + myList);
        System.out.println("myList.size() = " + myList.size());

    }
}
