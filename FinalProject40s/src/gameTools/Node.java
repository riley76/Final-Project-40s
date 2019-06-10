
package gameTools;

import java.io.Serializable;

/**
 * Node.java - 
 * @Class Computer Science grade 12
 * @author riley.w
 * @since 6-May-2019
 */
 public class Node <T> implements Serializable {
        
    public T data;
    public Node next;
    public Node previous;
     
     
    /**
     * constructor for this class
     * @param data
     * @param next
     * @param previous
     */
    public Node(T data, Node next, Node previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }   
    
    /**
     * constructor for this class
     * @param data
     */
    public Node(T data) {
        this.data = data;
        next = previous = null;
    }   
    
    
    public Node() {
        this(null, null, null);
    }
    
    
     
    /**
     * String representation of this object
     * @return The object represented as a String
     */
    @Override
    public String toString() {
        if(data == null) return "Node Has no Data";
        return data.toString();
    }
     
    /**
     * Determines if two objects are "equal" in this context
     * @param object the object to compare to
     * @return the objects are "equal" (true) or not (false)
     */
    @Override
    public boolean equals(Object object) {
        if(object == null) return false;
        Node node = (Node) object;
        return this.data.equals(node.data);
    }
     
    /**
     * Creates a duplicate object using new memory
     * @return a "clone" of the object using new memory
     */
    @Override
    public Node clone() {
        return new Node(this.data, this.next, this.previous);
    }
    
    
    @Override
    public void finalize() {
        data = null;
        next = previous = null;
        System.gc();
    }
    
    
}
