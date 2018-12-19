/**
 * ObjectListNodeInterface.java
 * @author Tyler Gerritsen
 * @version 5/19/2017
 *
 * interface for a single node in an object list
 */
public interface ObjectListNodeInterface {
	public void setInfo(Object o);
	public Object getInfo();
	public void setNext(ObjectListNode p);
	public ObjectListNode getNext();
}