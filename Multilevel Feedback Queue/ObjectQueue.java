/**
 * ObjectQueue.java
 * @author Tyler Gerritsen
 * @version 4-7-2017
 * 
 * This class is a queue data structure of objects
 */
public class ObjectQueue implements ObjectQueueInterface {
	private Object[] item;
	private int front;
	private int rear;
	private int count;

	/**
	 * default constructor
	 */
    public ObjectQueue() {
        item = new Object[1];
        front = 0;
        rear  = -1;
        count = 0;
    }

    /**
     * checks if queue is empty
     * @return true if the queue is empty
     */
    public boolean isEmpty() {
        return count == 0;
    }
    
    /**
     * checks if the queue is full
     * @return true if the queue is full
     */
    public boolean isFull() {
        return count == item.length;
    }
    
    /**
     * clears the entire queue
     */
    public void clear() {
        item = new Object[1];
        front = 0;
        rear  = -1;
        count = 0;
    }
    
    /**
     * inserts an object into the back of the queue
     */
    public void insert(Object o) {
        if (isFull())
            resize(2 * item.length);
        rear = (rear+1) % item.length;
        item[rear] = o;
        ++count;
    }
    
    /**
     * removes an object from the front of the queue
     * @return temp the object at the front
     */
    public Object remove() {
        if (isEmpty()) {
            new Exception("Remove Runtime Error: Queue Underflow").printStackTrace();
            System.exit(1);
        }
        Object temp = item[front];
        item[front] = null;
        front = (front+1) % item.length;
        --count;
        if (count == item.length/4 && item.length != 1)
            resize(item.length/2);
        return temp;
    }
    
    /**
     * queries the front of the queue
     * @return item[front] the object at the front
     */
    public Object query() {
        if (isEmpty()) {
        	new Exception("Query Runtime Error: Queue Underflow").printStackTrace();
            System.exit(1);
        }
        return item[front];
    }
    
    /**
     * resizes the queue
     * @param size the current size of the queue
     */
    private void resize(int size) {
        Object[] temp = new Object[size];
        for (int i = 0; i < count; ++i) {
            temp[i] = item[front];
            front = (front+1) % item.length;
        }
        front = 0;
        rear = count-1;
        item = temp;
    }
}

