package arrays.queue;

public class PriorityQueueItem<T> {

	private final T item;

	private final int priority;

	public PriorityQueueItem(T item, int priority) {
		this.item = item;
		this.priority = priority;
	}

	public T getItem() {
		return item;
	}

	public int getPriority() {
		return priority;
	}
}
