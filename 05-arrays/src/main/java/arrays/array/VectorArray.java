package arrays.array;

import static java.lang.System.arraycopy;

public class VectorArray<T> implements DynamicArray<T> {

	private static final int DEFAULT_VECTOR = 16;

	private final int vector;

	private int size = 0;

	private Object[] array = new Object[size];

	public VectorArray(int vector) {
		this.vector = vector;
	}

	public VectorArray() {
		this(DEFAULT_VECTOR);
	}

	@Override
	public void add(T item, int index) {
		if (index > size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}

		if (array.length == size) {
			final Object[] newArray = new Object[size + vector];
			arraycopy(array, 0, newArray, 0, index);
			arraycopy(array, index, newArray, index + 1, size - index);
			array = newArray;
		} else {
			arraycopy(array, index, array, index + 1, size - index);
		}

		array[index] = item;
		size++;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T remove(int index) {
		final T result = (T) array[index];

		System.arraycopy(array, index + 1, array, index, size - index - 1);
		size--;

		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T get(int index) {
		return (T) array[index];
	}

	@Override
	public int size() {
		return size;
	}
}
