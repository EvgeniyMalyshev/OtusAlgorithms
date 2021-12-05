package arrays.array;

public interface DynamicArray<T> {

    void add(T item, int index);

    T remove(int index);

    T get(int index);

    int size();
}
