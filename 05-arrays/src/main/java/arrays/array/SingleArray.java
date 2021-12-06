package arrays.array;

import static java.lang.System.arraycopy;

public class SingleArray<T> implements DynamicArray<T> {

    private Object[] array = new Object[0];

    @Override
    public void add(T item, int index) {
        if (index > array.length) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        final Object[] newArray = new Object[array.length + 1];

        arraycopy(array, 0, newArray, 0, index);
        arraycopy(array, index, newArray, index + 1, array.length - index);
        array = newArray;

        array[index] = item;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        final T result = (T) array[index];
        final Object[] newArray = new Object[array.length - 1];

        arraycopy(array, 0, newArray, 0, index);
        arraycopy(array, index + 1,  newArray, index, newArray.length - index);

        array = newArray;

        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) array[index];
    }

    @Override
    public int size() {
        return array.length;
    }
}
