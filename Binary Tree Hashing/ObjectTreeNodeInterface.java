/**
 * ObjectTreeNodeInterface.java
 * @author Tyler Gerritsen
 * @version 5/19/2017
 * 
 * binary tree node interface
 */
public interface ObjectTreeNodeInterface {
	public void setInfo(Object o);
	public Object getInfo();
	public void setLeft(ObjectTreeNode p);
	public ObjectTreeNode getLeft();
	public void setRight(ObjectTreeNode p);
	public ObjectTreeNode getRight();
}
