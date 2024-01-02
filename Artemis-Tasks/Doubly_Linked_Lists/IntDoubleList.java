package fop.w5dll;

import java.util.ArrayList;

public class IntDoubleList {
    private IntDoubleListElement head;
    private IntDoubleListElement tail = null;

    public IntDoubleList(){
        this.head = null;
    }
    public IntDoubleListElement getFirstElement(){
        return head;
    }
    public IntDoubleListElement getLastElement(){
        return tail;
    }
    public void append(int info){
        IntDoubleListElement newElement = new IntDoubleListElement(info);
        if(head == null){
            head = newElement;
            tail = newElement;
        }else{
            tail.next = newElement;
            newElement.prev = tail;
            tail = newElement;
        }
    }
    public int size(){
        int count = 0;
        for(IntDoubleListElement current = head; current != null; current = current.next){
            count++;
        }
        return count;
    }
    public int get(int pos){
        if(pos < 0){
            throw new IndexOutOfBoundsException("Position cannot be negative: " + pos);
        }
        IntDoubleListElement current = head;
        int count = 0;

        while (current != null){
            if(count == pos){
                return current.getInfo();
            }
            count++;
            current = current.next;
        }
        throw new IndexOutOfBoundsException("Position exceeds the size of the list: " + pos);
    }
    public void remove(int pos){
        if(pos < 0){
            throw new IndexOutOfBoundsException("Position cannot be negative: " + pos);
        }
        if (head == null) {
            throw new IndexOutOfBoundsException("The list is empty.");
        }
        IntDoubleListElement current = head;
        int count = 0;



        while (current != null){
            if(count == pos){
                if(current.prev != null){
                    current.prev.next = current.next;
                }else{
                    head = current.next;
                }

                if(current.next != null){
                    current.next.prev = current.prev;
                }else{
                    tail = current.prev;
                }
                if(head == null){
                    tail = null;
                }
                return;
            }
            count++;
            current = current.next;
        }

        if (count <= pos) {
            throw new IndexOutOfBoundsException("Position exceeds the size of the list: " + pos);
        }
    }
    public String toString(){
        StringBuilder result = new StringBuilder();
        IntDoubleListElement current = head;

        while (current != null){
            result.append(current.getInfo());
            if(current.next != null){
                result.append(", ");
                break;
            }
            current = current.next;
        }
        return result.toString();
    }
    public boolean isEqual(IntDoubleList other){
        IntDoubleListElement current = head;
        IntDoubleListElement otherCurrent = other.head;
        if(current == null && otherCurrent == null){
            return true;
        }else if(current == null || otherCurrent == null){
            return false;
        }

        while(current != null && otherCurrent != null){
            if(current.getInfo() != otherCurrent.getInfo()){
                return false;
            }

            current = current.next;
            otherCurrent = otherCurrent.next;
        }
        return current == null && otherCurrent == null;
    }
    public int sum(){
        IntDoubleListElement current = head;
        int result = 0;
        while (current != null){
            result += current.getInfo();
            current = current.next;
        }
        return result;
    }
    public IntDoubleList copy(){
        if(head != null) {
            IntDoubleList newList = new IntDoubleList();
            newList.append(head.getInfo());
            IntDoubleListElement current = head.next;
            while (current != null) {
                newList.append(current.getInfo());
                current = current.next;
            }
            return newList;
        }else{
            return new IntDoubleList();
        }
    }
    public IntDoubleListElement[] search(int intValue){
        ArrayList<IntDoubleListElement> arrayOfList = new ArrayList<IntDoubleListElement>();
        IntDoubleListElement current = head;
        while(current != null){
            if(current.getInfo() == intValue){
                arrayOfList.add(current);
            }
            current = current.next;
        }
        return arrayOfList.toArray(new IntDoubleListElement[0]);
    }
}
