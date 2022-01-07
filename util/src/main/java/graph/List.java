package graph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class List<E> implements Iterator<E>, Iterable<E> {

	private static final int INITIAL_LENGTH = 10;

	private static final int DEFAULT_FACTOR = 50;
	private final int factor;
	private Object[] array = new Object[INITIAL_LENGTH];
	private int cursor = 0;

	private int size = 0;

	public List(int factor) {
		this.factor = factor;
	}

	public List() {
		this(DEFAULT_FACTOR);
	}

	public void add(E item) {
		if (size == array.length) {
			resize();
		}

		array[size++] = item;
	}

	@SuppressWarnings("unchecked")
	public E get(int index) {
		return (E) array[index];
	}

	public E set(int index, E item) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(index);
		}

		final E oldValue = get(index);
		array[index] = item;

		return oldValue;
	}

	@SuppressWarnings("unchecked")
	public E remove(int index) {
		final E item = (E) array[index];

		System.arraycopy(array, index + 1, array, index, size - index);
		size--;

		return item;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Object[] toArray() {
		return Arrays.copyOf(array, size);
	}

	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] dest) {
		if (dest.length < size) {
			return (T[]) Arrays.copyOf(array, size, dest.getClass());
		}

		System.arraycopy(array, 0, dest, 0, size);

		if (dest.length > size) {
			dest[size] = null;
		}

		return dest;
	}

	private void resize() {
		final Object[] extendedArray = new Object[array.length + array.length * factor / 100];
		System.arraycopy(array, 0, extendedArray, 0, array.length);
		array = extendedArray;
	}

	@Override
	public boolean hasNext() {
		return cursor < size;
	}

	@Override
	public E next() {
		return get(cursor++);
	}

	@Override
	public Iterator<E> iterator() {
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final List<?> list = (List<?>) o;

		if (size != list.size) {
			return false;
		}

		for (int i = 0; i < size; i++) {
			if (array[i] != list.array[i]) {
				return false;
			}
		}

		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Arrays.hashCode(array), size);
	}
}
