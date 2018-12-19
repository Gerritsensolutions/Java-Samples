/**
 * ObjectStackInterface.java
 * @author Tyler Gerritsen
 * @version 3-5-2017
 */
public interface ObjectStackInterface {
	 public boolean isEmpty();
	 public boolean isFull();
	 public void clear();
	 public void push(Object o);
	 public Object pop();
	 public Object top(); 
}
