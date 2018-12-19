/**
 * ObjectListNode.java
 * @author Tyler Gerritsen
 * @version 4/28/2017
 * 
 * a single node class for the object list
 */
public class ObjectListNode implements ObjectListNodeInterface {
    private Object info;
    private ObjectListNode next;
    
	/**
	 * default constructor
	 */ 
    public ObjectListNode() {
        info = null;
        next = null;
    }

    /**
     * One-argument constructor
     * @param o info of node to be constructed
     */
    public ObjectListNode (Object o) {
        info = o;
        next = null;
    }    
    
    /**
     * Two-argument constructor
     * @param o info of node to be constructed
     * @param p pointer to the next node
     */
    public ObjectListNode (Object o, ObjectListNode p) {
        info = o;
        next = p;
    }       

    /**
     * Sets info field
     * @param o info to be set
     */
    public void setInfo(Object o) {
        info = o;
    }    

    /**
     * Returns object in info field
     * @return info of node
     */
    public Object getInfo() {
        return info;
    }

    /**
     * Sets next field
     * @param p pointer to next node
     */
    public void setNext(ObjectListNode p) {
        next = p;
    }

    /**
     * Returns pointer to the next node
     * @return pointer to the next node
     */
    public ObjectListNode getNext() {
        return next;
    }
}
