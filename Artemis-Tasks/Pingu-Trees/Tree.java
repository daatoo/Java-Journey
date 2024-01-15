package fop.w8trees;
import java.util.Comparator;
import java.util.function.Predicate;

public class Tree<T> {
    private Comparator<T> comp;
    private TreeElement<T> root;

    public Tree(Comparator<T> comp) {
        this.comp = comp;
        root = new Leaf<>();
    }

    public void insert(T value) {
        if(value == null) return;
        if(root instanceof Leaf<T>){
            root = new InnerNode<>(value);
        }else{
            root.insert(value, comp);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        root.toString(sb);
        return sb.toString();
    }

    public int size() {
        return root.size();
    }

    public void remove(T value) {
        root = root.remove(value, comp);
    }
    
    public boolean contains(T value) {
        return root.contains(value, comp);
    }

    public int countMatches(Predicate<T> filter) {
        return root.countMatches(filter);
    }

    public T[] getAll(Predicate<T> filter) {
        // Generics und Arrays vertragen sich nicht besonders gut
        @SuppressWarnings("unchecked")
        T[] array = (T[]) new Object[countMatches(filter)];
        root.getAll(filter, array, 0);
        return null;
    }
}
