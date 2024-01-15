package fop.w8trees;
import java.util.Comparator;
import java.util.function.Predicate;

public interface TreeElement<T> {
    /**
     * Inserts the given value in the tree while maintaining the invariant
     * 
     * @param value the value to insert
     * @param comp  the comparator defining the tree's order
     * @return the parent node's new child; this if nothing changes, a new node
     *         otherwise
     */
    public TreeElement<T> insert(T value, Comparator<T> comp);

    /**
     * generates a comma-separated String of the tree's elements
     * 
     * @param sb the {@link StringBuilder} to append to 
     */
    public void toString(StringBuilder sb);

    /**
     * calculates the tree's size
     * 
     * @return the tree's size
     */
    public int size();

    /**
     * gets the minimal value for the given (sub-)tree
     * 
     * @return the minimal value
     */
    public T getMin();

    /**
     * removes the given value's first occurrence from the tree
     * 
     * @param value the value to remove
     * @param comp  the comparator defining the tree's order
     * @return the parent node's new child; this if nothing changes, a different
     *         node otherwise
     */
    public TreeElement<T> remove(T value, Comparator<T> comp);

    /**
     * checks whether an element is present in the tree
     * 
     * @param value the value to check for
     * @param comp the tree's comparator
     * @return true if the value is present, false otherwise
     */
    public boolean contains(T value, Comparator<T> comp);

    /**
     * calculates the amount of elements satisfying the given predicate
     * 
     * @param filter the condition elements should satisfy
     * @return the amount of elements
     */
    public int countMatches(Predicate<T> filter);

    /**
     * gets all elements satisfying the given Predicate
     * 
     * @param filter the condition elements should satisfy
     * @param array the array to insert to. Must have sufficient length
     * @param index the current max index of the array
     * @return the new max index of the array
     */
    public int getAll(Predicate<T> filter, T[] array, int index);
}
