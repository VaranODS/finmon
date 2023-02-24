import java.util.StringJoiner;

public class MyQueue<T> {

    private int putIndex;
    private int getIndex;
    private int size;
    private Object[] items;

    private static final int DEFAULT_QUEUE_SIZE = 1024;
    public MyQueue() {
        items = new Object[DEFAULT_QUEUE_SIZE];
    }

    public MyQueue(int queueSize) {
        items = new Object[queueSize];
    }

    public int size() {
        return size;
    }

    public void clear() {
        putIndex = 0;
        getIndex = 0;
        size = 0;
        items = new Object[items.length];
    }

    public boolean add(Object e) {
        if (size == items.length) {
            return false;
        }
        items[putIndex] = e;
        if (++putIndex == items.length) putIndex = 0;
        size++;
        return true;
    }

    public T peek() {
        if (size > 0) return (T) items[getIndex];
        else return null;
    }

    public T poll() {
        if (size > 0) {
            T result = (T) items[getIndex];
            items[getIndex] = null;
            if (++getIndex == items.length) getIndex = 0;
            size--;
            return result;
        } else return null;
    }

    @Override
    public String toString() {
        StringJoiner result = new StringJoiner(", ");
        int index = getIndex;
        for (int i = 0; i < size ; i++) {
            result.add(((T) items[index]).toString());
            if (++index == items.length) index = 0;
        }
        return "[" + result + "]";
    }

    public static void main(String[] args) {
        MyQueue<Number> queue = new MyQueue<>(8);

        System.out.println("queue.size = " + queue.size);
        System.out.println("queue = " + queue);

        queue.add(-6.3);
        queue.add(3);
        queue.add(0.5);
        queue.add(24);
        queue.add(9.8f);
        queue.add(4);
        System.out.println("queue = " + queue);
        System.out.println("queue.size = " + queue.size);
        System.out.println("queue.peek() = " + queue.peek());
        System.out.println("queue.peek() = " + queue.peek());
        System.out.println("queue.size = " + queue.size);
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.size = " + queue.size);
        System.out.println("queue = " + queue);
        System.out.println("queue.add(41) = " + queue.add(41));
        System.out.println("queue.add(42) = " + queue.add(42));
        System.out.println("queue.add(43) = " + queue.add(43));
        System.out.println("queue.add(44) = " + queue.add(44));
        System.out.println("queue.add(45) = " + queue.add(45));
        System.out.println("queue = " + queue);
        System.out.println("queue.size() = " + queue.size());
        queue.clear();
        System.out.println("queue.size() = " + queue.size());
        System.out.println("queue.add(0.5) = " + queue.add(0.5));
        System.out.println("queue.add(24) = " + queue.add(24));
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.size() = " + queue.size());
    }
}
