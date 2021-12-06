package arrays.queue;

import arrays.array.DynamicArray;
import arrays.array.FactorArray;

public class PriorityQueueImpl<T> implements PriorityQueue<T> {

	private final DynamicArray<PriorityQueueItem<T>> array = new FactorArray<>();

	@Override
	public void enqueue(int priority, T item) {
		if(array.size() == 0) {
			array.add(new PriorityQueueItem<>(item, priority), 0);
			return;
		}

		int insertIndex = 0;
		while (insertIndex < array.size() && priority > array.get(insertIndex).getPriority()) {
			insertIndex++;
		}

		array.add(new PriorityQueueItem<>(item, priority), insertIndex);
	}

	@Override
	public T dequeue() {
		return array.remove(array.size() - 1).getItem();
	}
}
