import java.util.LinkedList;

public class Queue {
	
	public LinkedList<String> myList;
	
	public Queue() {
		myList = new LinkedList<String>();
	}
	public void enqueue(String myString) {
		myList.addLast(myString);
	}
	public String dequeue(String myString) {
		return myList.removeFirst();
	}
}
