package pgdp.collections;

public class LinkedQueue<T> implements Queue<T>{
    private List<T> first = null;
    private List<T> last = null;
    @Override
    public int size() {
        if(first == null) return 0;
        return first.length();
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public void enqueue(T t) {
        if(first == null){
            first = new List<>(t, null);
            last = first;
        }else{
            last.insert(t);
            last = last.getNext();
        }
    }

    @Override
    public T dequeue() {
        if(first == null) return null;
        T temp = first.getInfo();
        if(first == last){
            first = null;
            last = null;
        }
        first = first.getNext();
        return temp;
    }
}
