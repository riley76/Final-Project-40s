
package collections;

import java.lang.reflect.Array;

/**
 * LinkedList.java - 
 * @Class Computer Science grade 12
 * @author riley.w
 * @since 6-May-2019
 */
 public class LinkedList <T>{
        
    // reference (link) to the first node in the list (entry Point)
    private Node head;
    // reference (link) to the last node in the list (entry Point)
    private Node tail;
     
    // The number of nodes in the list . Imumutable Property
    private int length;
     
    public static final int NOT_FOUND = -1;
     
    /**
     * default constructor for this class
     */
    public LinkedList() {
        finalize();
    }        
    
    public LinkedList(T[] array) {
        finalize();
        addAll(array);
    }
    
    
    public LinkedList(LinkedList list) {
        finalize();
        addAll(list);
    }
    
    public int getLength() {
        return length;
    }
    
    public boolean isEmpty() {
        return length == 0;
    }
    
    /**
     * accesor method
     * @return number of nodes in this list
     */
    public int size() {
        return length;
    }
    
    public boolean addFront(T data) {
        if(data == null) return false;
        Node node = new Node(data);
        if(isEmpty()) {
            head = tail = node;
        } else {
            node.next = head;
            head.previous = node;
            head = node;
        }
        length++;
        return true;
    }
    
    
    public boolean addBack(T data) {
        if(data == null) return false;
        Node node = new Node(data);
        if(isEmpty()) {
            head = tail = node;
        } else {
            node.previous = tail;
            tail.next = node;
            tail = node;
        }
        length++;
        return true;
    }
    
    public T get(int index) {
        if(!inRange(index)) return null;
        return (T) getNode(index).data;
    }
    
    
    public boolean set(int index, T data) {
        if(data == null) return false;
        Node current = getNode(index);
        if(current == null) return false;
        current.data = data;
        return true;
    }
    
     
    /**
     * String representation of this object
     * @return The object represented as a String
     */
    @Override
    public String toString() {
        if(isEmpty()) return "List is Empty";
        String text = "Linked List ["; 
        Node current = head;
        while(current.next != null) {
            text += current.toString() + ", ";
            current = current.next;
        }
        return text + current.toString() +"]";
    }
     
    /**
     * Determines if two objects are "equal" in this context
     * @param object the object to compare to
     * @return the objects are "equal" (true) or not (false)
     */
    @Override
    public boolean equals(Object object) {
        LinkedList<T> that = (LinkedList<T>)object;
        if(this.size() != that.size()) return false;
        Node current1 = this.getFirstNode();
        Node current2 = that.getFirstNode();
        while(current1 != null) {
            if(!current1.equals(current2)) return false;
            current1 = current1.next;
            current2 = current2.next;
        }
        
        return true;
    }
     
    /**
     * Creates a duplicate object using new memory
     * @return a "clone" of the object using new memory
     */
    @Override
    public LinkedList clone() {
        LinkedList<T> list = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            T data = this.get(i);
            list.addBack(data);
        }
        
        
        return list;
    }
    
    /**
     * frees up all memory that this list is using
     */
    @Override
    public void finalize() {
        length = 0;
        tail = head = null;
        System.gc();
    }
    
    
    protected Node getNode(int index) {
        if(!inRange(index)) return null;
        if(index == 0) return getFirstNode();
        if(index == length - 1) return getLastNode();
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        return current;
    }
    
    protected Node getFirstNode() {
        return head;
    }
    
    protected Node getLastNode() {
        return tail;
    }
    
    
    
    private boolean inRange(int index) {
        if(index < 0) return false;
        if(index >= length) return false;
        return true;
    }
    
    public T front() {
        return get(0);
    }
    
    public T back() {
        return get(length - 1);
    }
    
    /**
     * removes the front part of the list
     * @return the data that was removed
     */
    public T removeFront() {
        if(isEmpty()) return null;
        T data = front();
        if(length == 1) finalize();
        else {
            head = head.next;
            head.previous.finalize();
            head.previous = null;
            length--;
            System.gc();
        }
        return data;
    }
    
    /**
     * removes the back part of the list
     * @return the data that was removed
     */
    public T removeBack() {
        if(isEmpty()) return null;
        T data = back();
        if(length == 1) finalize();
        else {
            tail = tail.previous;
            tail.next.finalize();
            tail.next = null;
            length--;
            System.gc();
        }
        return data;
    }
    
    /**
     * returns if the list contains that data
     * @param data the data to find
     * @return 
     */
    public boolean contains(T data) {
        Node current = head;
        while(current != null) {
            if(current.data.equals(data)) return true;
            current = current.next;
        }
        
        return false;
    }
    
    public boolean addAfter(T data, int index) {
        if(data == null) return false;
        if(!inRange(index)) return false;
        if(index == length -1) return addBack(data);
        Node<T> node = new Node<>(data);
        Node current = getNode(index);
        node.next = current.next;
        current.next.previous = node;
        current.next = node;
        node.previous = current;
        length++;
        return  true;
    }
    
    
    public boolean addBefore(T data, int index) {
        if(data == null) return false;
        if(!inRange(index)) return false;
        if(index == 0) return addFront(data);
        Node<T> node = new Node<>(data);
        Node current = getNode(index);
        node.previous = current.previous;
        current.previous.next = node;
        current.previous = node;
        node.next = current;
        length++;
        return  true;
    }
    
    
    public boolean add(T data) {
        return addBack(data);
    }
    
    
    public boolean add(T data, int index) {
        return addAfter(data, index);
    }
    
    
    public T remove(int index) {
        if(!inRange(index)) return null;
        if(index == 0) return removeFront();
        if(index == length - 1) return removeBack();
        Node current = getNode(index);
        current.next.previous = current.previous;
        current.previous.next = current.next;
        current.next = current.previous = null;
        length--;
        System.gc();
        return (T)current.data;
    }
    
    
    public int firstIndexOf(T data) {
        Node current = head;
        int index = 0;
        while(current != null) {
            if(current.data.equals(data)) return index;
            index++;
            current = current.next;    
        }
        return NOT_FOUND;
    }
    
     public int lastIndexOf(T data) {
        Node current = tail;
        int index = length - 1;
        while(current != null) {
            if(current.data.equals(data)) return index;
            index--;
            current = current.previous;    
        }
        return NOT_FOUND;
    }
    
     public int numberOf(T data) {
         if(data == null) return 0;
         int counter = 0;
         Node current = head;
         while(current != null) {
             if(current.data.equals(data)) counter++;
             current = current.next;
         }
         return counter;
     }
     
     public LinkedList allIndicesOf(T data) {
        if(data == null) return null;
        if(!contains(data)) return null;
        LinkedList<Integer> list = new LinkedList<>();
        int size = numberOf(data);
        Node current = head;
        int counter = 0;
        for (int i = 0; i < length; i++) {
            if(current.data.equals(data))  {
                list.add(i);
                counter++;
                if(counter >= size) return list;
            }
            current = current.next;
        }
        return list;
     }
     
     
     public boolean remove(T data) {
        if(data == null) return false;
        int index = firstIndexOf(data);
        if(index == NOT_FOUND) return false;
        remove(index);
        return true;
     }
     
     public boolean removeLast(T data) {
         if(data == null) return false;
         int index = lastIndexOf(data);
         if(index == NOT_FOUND) return false;
         remove(index);
         return true;
     }
     
     public boolean removeAll(T data) {
        if(data == null) return false;
        if(!contains(data)) return false;
        while(contains(data)) {
            remove(data);
        } 
        return true;
     }
     
     public void addAll(T[] items) {
         for (T item : items) {
             add(item);
         }
     }
     
     
     public void addAll(LinkedList<T> that) {
        for (int i = 0; i < that.size(); i++) {
         this.add(that.get(i));
        }
     }
     
     
    public T[] toArray(T[] array) {
        
        array = (T[]) (Array.newInstance(array.getClass().getComponentType(), length));
        for (int i = 0; i < length; i++) {
            array[i] = get(i);
        }
        return array;
    }
     
}
