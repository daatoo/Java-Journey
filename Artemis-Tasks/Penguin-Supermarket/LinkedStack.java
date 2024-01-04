package pgdp.collections;

public class LinkedStack<T> implements Stack<T>{
    private List<T> stack = null;


    @Override
    public int size() {
        if(isEmpty()) return 0;
        return stack.length();
    }

    @Override
    public boolean isEmpty() {
        return stack == null;
    }

    @Override
    public void push(T t) {
        stack = new List<>(t, stack);
    }

    @Override
    public T pop() {
        if(stack == null) return null;
        T info = stack.getInfo();
        stack = stack.getNext();
        return info;
    }

}
