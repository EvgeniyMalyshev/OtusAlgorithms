import arrays.queue.PriorityQueueImpl;
import org.junit.Assert;
import org.junit.Test;


public class Queue {
    private final PriorityQueueImpl<Integer> priorityQueue = new PriorityQueueImpl<>();

    @Test
    public void testQueue() {
        priorityQueue.enqueue(2, 1);
        priorityQueue.enqueue(-1, 2);
        priorityQueue.enqueue(1, 3);
        priorityQueue.enqueue(3, 4);
        priorityQueue.enqueue(-2, 5);

        Assert.assertEquals(Integer.valueOf(4), priorityQueue.dequeue());
        Assert.assertEquals(Integer.valueOf(1), priorityQueue.dequeue());
        Assert.assertEquals(Integer.valueOf(3), priorityQueue.dequeue());
    }
}
