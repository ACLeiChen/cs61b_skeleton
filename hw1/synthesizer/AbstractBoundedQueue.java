package synthesizer;

/**
 * Created by ChenLei on 2016/3/24.
 */
public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T>{
    protected int fillCount;
    protected int capacity;
    public int capacity() {
        return capacity;
    }
    public int fillCount() {
        return fillCount;
    }
    // is the buffer empty (fillCount equals zero)?
    public boolean isEmpty() {
        return (fillCount() == 0);
    }

    // is the buffer full (fillCount is same as capacity)?
    public boolean isFull() {
        return (fillCount() == capacity());
    }
    public abstract T peek();
    public abstract T dequeue();
    public abstract void enqueue(T x);
}
