package fop.w12synch;

import java.util.LinkedList;
import java.util.List;

public class SynchronizedList<T> {
    private List<T> innerList = new LinkedList<>();
    private RW rw = new RW();
    public void add(int index, T e) throws InterruptedException {
        rw.startWrite();
        innerList.add(index, e);
        rw.endWrite();
    }

    public T remove(int index) throws InterruptedException {
        rw.startWrite();
        T result =  innerList.remove(index);
        rw.endWrite();
        return  result;
    }

    public T get(int index) throws InterruptedException {
        rw.startRead();
        T result =  innerList.get(index);
        rw.endRead();
        return  result;
    }

    public boolean contains(T e) throws InterruptedException {
        rw.startRead();
        boolean result =  innerList.contains(e);
        rw.endRead();
        return result;
    }
}
