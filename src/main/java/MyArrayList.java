import java.util.Arrays;
import java.util.StringJoiner;

public class MyArrayList<T> {
/**
 *     add(value)
 *     get(index)
 *     remove(index)
 *     isEmpty()
 *     contains(item)
 *     length()
 *     reservedLength()
 *     clear()
*/

    private final int INITIAL_SIZE = 4;
    private Object[] data;
    private int index;

    public MyArrayList() {
        clear();
    }

    public void add(T value) {
        resizeIfNeed();
        index++;
        data[index] = (Object) value;
    }

    private void resizeIfNeed() {
        if (index == data.length - 1) {
            int newSize = data.length + data.length / 2;
            Object[] newData = Arrays.copyOf(data, newSize);
            data = newData;
            System.out.println("data resized to " + data.length);
        }

    }

    public T get(int i) {
//        if (i > index) {
//            return null;
//        }
        return (T) data[i];
    }

    public T remove(int ind) {
        if (ind > index) {
            return null;
        }
        T value = (T) data[ind];
        for (int i = ind; i <= index; i++) {
            if (i == index) {
                data[i] = null;
            } else {
                data[i] = data[i + 1];
            }
        }
        index--;
        return value;
    }

    public boolean isEmpty() {
        return index == -1;
    }

    public boolean contains(T value) {
        for (Object element: data) {
            if (value.equals((T) element)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return index + 1;
    }

    public int reservedLength() {
        return data.length;
    }

    public void clear() {
        data = new Object[INITIAL_SIZE];
        index = -1;
    }

    @Override
    public String toString() {
        StringJoiner result = new StringJoiner(", ");
        for (int i = 0; i <= index; i++) {
            result.add(((T) data[i]).toString());
        }
        return "[" + result + "]";
    }

    public static void main(String[] args) {
        MyArrayList<Integer> myList = new MyArrayList();

        System.out.println("myList.isEmpty() = " + myList.isEmpty());

        System.out.println("myList = " + myList);

        myList.add(15);
        System.out.println("myList = " + myList);
        myList.add(7);
        System.out.println("myList = " + myList);
        myList.add(14);
        myList.add(16);
        myList.add(39);
        myList.add(45);
        System.out.println("myList = " + myList);
        myList.add(-10);
        System.out.println("myList = " + myList);
        System.out.println("myList.length() = " + myList.size());
        System.out.println("myList.get(4) = " + myList.get(4));
        System.out.println("myList.reservedLength() = " + myList.reservedLength());
        System.out.println("myList.remove(3) = " + myList.remove(3));
        System.out.println("myList = " + myList);
        System.out.println("myList.size() = " + myList.size());
        System.out.println("myList.isEmpty() = " + myList.isEmpty());
        myList.clear();
        System.out.println("myList = " + myList);
        System.out.println("myList.isEmpty() = " + myList.isEmpty());
    }
}
