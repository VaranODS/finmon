import java.util.StringJoiner;

public class MyStack<T> {

    private static final int DEFAULT_STACK_SIZE = 1024;
    private int index = -1;

    private Object[] stackData;

    public MyStack() {
        this(DEFAULT_STACK_SIZE);
    }

    public MyStack(int stackSize) {
        stackData = new Object[stackSize];
    }

    public boolean push(Object value) {
        if (index == stackData.length - 1) return false;
        stackData[++index] = value;
        return true;
    }

    public T peek() {
        if (index < 0) return null;
        return (T) stackData[index];
    }

    public T pop() {
        if (index < 0) return null;
        return (T) stackData[index--];

    }

    public int size() {
        return index + 1;
    }

    public void clear() {
        index = -1;
        stackData = new Object[stackData.length];
    }

    public T remove(int ind) {
        if (ind < 0 || ind > index) return null;
        T result = (T) stackData[ind];
        for (int i = ind; i < index; i++) {
            stackData[i] = stackData[i + 1];
        }
        index--;
        return result;
    }

    @Override
    public String toString() {
        StringJoiner result = new StringJoiner(", ");
        for (int i = 0; i <= index ; i++) {
            result.add(((T) stackData[i]).toString());
        }
        return "[" + result + "]";
    }

    public static void main(String[] args) {
        MyStack<Number> stack = new MyStack<>(8);
        System.out.println("stack.size() = " + stack.size());
        System.out.println("stack = " + stack);
        System.out.println("stack.push(32) = " + stack.push(32));
        System.out.println("stack = " + stack);
        System.out.println("stack.push(3.56f) = " + stack.push(3.56f));
        for (float i = 23.15f; i < 35.0 ; i += 1.25) {
            System.out.println("stack.push(" + i + ") = " + stack.push(i));
        }
        System.out.println("stack.size() = " + stack.size());
        System.out.println("stack = " + stack);
        System.out.println("stack.remove(4) = " + stack.remove(4));
        System.out.println("stack = " + stack);
        System.out.println("stack.size() = " + stack.size());
        int count = stack.size() + 2;
        for (int j = 0; j < count; j++) {
            System.out.println("stack.pop() = " + stack.pop());
        }
        System.out.println("stack.size() = " + stack.size());
        System.out.println("stack = " + stack);

        System.out.println("stack.push(10) = " + stack.push(10));
        System.out.println("stack.push(20) = " + stack.push(20));
        System.out.println("stack = " + stack);
        stack.clear();
        System.out.println("stack.size() = " + stack.size());
        System.out.println("stack = " + stack);

    }
}
