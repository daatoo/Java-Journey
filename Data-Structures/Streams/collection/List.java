package collection;


import iterator.Iterable;
import iterator.Iterator;
import stream.Stream;


public class List<T> implements Iterable<T>{
    public T info;
    public List<T> next;
    public List(T x, List<T> l){
        info = x;
        next = l;
    }
    public List(T x){
        info = x;
        next = null;
    }

    //public methods
    public void insert(T x){
        next = new List<T>(x);
    }
    public void delete(){
        if(next != null){
            next = next.next;
        }
    }
    public static boolean isEmpty(List l){
        return l == null;
    }

    static class ListIterator<T> implements Iterator<T>{
        List<T> current;
        public ListIterator(List<T> l){
            this.current = l;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T result = current.info;
            current = current.next;
            return result;
        }
    }
    public Iterator<T> iterator(){
        return new ListIterator<>(this);
    }
    public Stream<T> stream(){
        class State{
            Iterator<T> it = iterator();
            Stream<T> stream(){
                return () -> it.hasNext() ? new Stream.Pair<>(it.next(), this.stream()) : null;
            }
        }
        return new State().stream();
    }
    public void printList(){
        Iterator<T> it = iterator();
        while(it.hasNext()) System.out.println(it.next());
    }
}