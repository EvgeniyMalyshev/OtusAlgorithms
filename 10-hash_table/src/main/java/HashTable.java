public class HashTable<K, V> {

	private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

	private static final float LOAD_FACTOR = 0.5f;

	private static final int MAXIMUM_CAPACITY = 1 << 30;

	private static final int NOT_EXISTING_INDEX = -1;

	private int size;

	private Entry<K, V>[] entries;

	public HashTable(int initialCapacity) {
		if (initialCapacity < 0) {
			throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
		}

		entries = new Entry[initialCapacity];
		size = 0;
	}

	public HashTable() {
		this(DEFAULT_INITIAL_CAPACITY);
	}

	private static int hash(Object key, int iteration, Object[] entries) {
		return (Math.abs(key.hashCode()) + iteration * iteration) % entries.length;
	}

	public int size() {
		return size;
	}

	public V put(K key, V value) {
		int deletedEntryIndex = NOT_EXISTING_INDEX;

		for (int i = 0; i < MAXIMUM_CAPACITY; i++) {
			final int index = hash(key, i);

			if (entries[index] == null) {
				if (deletedEntryIndex == NOT_EXISTING_INDEX) {
					entries[index] = new Entry<>(key, value);
					if (++size > entries.length * LOAD_FACTOR) {
						resize();
					}
				} else {
					entries[deletedEntryIndex] = new Entry<>(key, value);
				}

				return null;
			}

			if (entries[index].key == key) {
				final V previousValue = entries[index].value;
				entries[index] = new Entry<>(key, value);
				return previousValue;
			}

			if (entries[index].isDeleted && deletedEntryIndex == NOT_EXISTING_INDEX) {
				deletedEntryIndex = index;
			}
		}

		return null;
	}

	public V get(Object key) {
		int deletedEntryIndex = NOT_EXISTING_INDEX;

		for (int i = 0; i < MAXIMUM_CAPACITY; i++) {
			final int index = hash(key, i);
			if (entries[index] == null) {
				return null;
			}

			if (entries[index].key == key && !entries[index].isDeleted) {
				final Entry<K, V> foundEntry = entries[index];

				if (deletedEntryIndex != NOT_EXISTING_INDEX) {
					entries[index] = entries[deletedEntryIndex];
					entries[deletedEntryIndex] = foundEntry;
				}

				return foundEntry.value;
			}

			if (entries[index].isDeleted && deletedEntryIndex == NOT_EXISTING_INDEX) {
				deletedEntryIndex = index;
			}
		}

		return null;
	}

	public V remove(Object key) {
		for (int i = 0; i < MAXIMUM_CAPACITY; i++) {
			final int index = hash(key, i);
			if (entries[index] == null) {
				return null;
			}

			if (entries[index].key == key) {
				entries[index].isDeleted = true;
				return entries[index].value;
			}
		}

		return null;
	}

	private int hash(Object key, int iteration) {
		return hash(key, iteration, entries);
	}

	private void resize() {
		final int newCapacity = Math.min(entries.length * 2, MAXIMUM_CAPACITY);
		final Entry<K, V>[] newEntries = new Entry[newCapacity];

		for (Entry<K, V> entry : entries) {
			if (entry != null) {
				int i = 0, index = hash(entry.key, i, newEntries);
				while (newEntries[index] != null) {
					index = hash(entry.key, ++i, newEntries);
				}
				newEntries[index] = entry;
			}
		}

		entries = newEntries;
	}

	private static class Entry<K, V> {

		private final K key;

		private final V value;

		private boolean isDeleted;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
			isDeleted = false;
		}
	}
}
