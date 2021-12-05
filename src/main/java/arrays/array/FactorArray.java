package arrays.array;

import static java.lang.System.arraycopy;

public class FactorArray<T> implements DynamicArray<T> {

	private static final int INITIAL_LENGTH = 16;

	private static final int DEFAULT_FACTOR = 50;

	private Object[] array = new Object[INITIAL_LENGTH];

	private final int factor;

	private int size = 0;

	public FactorArray(int factor) {
		this.factor = factor;
	}

	public FactorArray() {
		this(DEFAULT_FACTOR);
	}

	@Override
	public void add(T item, int index) {
		if (index > size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}

		if (array.length == size) {
			final Object[] newArray = new Object[size + size * factor / 100];
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

		System.arraycopy(array, index + 1, array, index, size - index);
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
