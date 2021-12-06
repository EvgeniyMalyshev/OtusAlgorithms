package arrays.array;

public class MatrixArray<T> implements DynamicArray<T> {

	private static final int DEFAULT_VECTOR = 16;

	private final int vector;

	private int size = 0;

	private final SingleArray<T[]> array = new SingleArray<>();

	public MatrixArray(int vector) {
		this.vector = vector;
	}

	public MatrixArray() {
		this(DEFAULT_VECTOR);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void add(T item, int index) {
		if (index > size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}

		if (size == array.size() * vector) {
			array.add((T[]) new Object[vector], array.size());
		}

		final boolean indexIsNotBorder = index % vector + 1 < vector;
		for (int i = array.size() * vector - 2; i >= index; i--) {
			if (indexIsNotBorder) {
				array.get(i / vector)[index % vector + 1] = get(i);
			} else {
				array.get(array.size() - 1)[0] = get(i);
			}
		}

		array.get(index / vector)[index % vector] = item;
		size++;
	}

	@Override
	public T remove(int index) {
		final T result = get(index);

		for (int i = index; i < size - 1; i++) {
			array.get(i / vector)[i % vector] = get(i + 1);
		}

		size--;
		array.get(size / vector)[size % vector] = null;

		return result;
	}

	@Override
	public T get(int index) {
		return array.get(index / vector)[index % vector];
	}

	@Override
	public int size() {
		return size;
	}
}
