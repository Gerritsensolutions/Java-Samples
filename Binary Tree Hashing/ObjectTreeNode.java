/**
 * ObjectTreeNode.java
 * @author Tyler Gerritsen
 * @version 5/19/2017
 * 
 * binary tree node
 */
public class ObjectTreeNode implements ObjectTreeNodeInterface{
    private Object info;
    private ObjectTreeNode left;
    private ObjectTreeNode right;
    
    /**
     * default constructor
     */
    public ObjectTreeNode() {
        info = null;
        left = null;
        right = null;
    }
    
    /**
     * single-argument constructor
     * @param o object which will be the info of this node
     */
    public ObjectTreeNode (Object o) {
        info = o;
        left = null;
        right = null;
    }
    
    /**
     * sets the info for this node
     * @param o object that the info will be set to
     */
    public void setInfo(Object o) {
        info = o;
    }
    
    /**
     * gets the info from this node
     * @return object from the info field of this node
     */
    public Object getInfo() {
        return info;
    }
    
    /**
     * sets the left child of this node
     * @param p node to be set as the child
     */
    public void setLeft(ObjectTreeNode p) {
        left = p;
    }
    
    /**
     * gets the left child of this node
     * @return ObjectTreeNode object which is the left child
     */
    public ObjectTreeNode getLeft() {
        return left;
    }

   /**
    * sets the right child of this node
    * @param p node to be set as the child
    */   
    public void setRight(ObjectTreeNode p) {
        right = p;
    }
   /**
    * gets the right child of this node
    * @return ObjectTreeNode object which is the right child
    */
    public ObjectTreeNode getRight() {
        return right;
    }
}