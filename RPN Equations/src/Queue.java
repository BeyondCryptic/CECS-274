import java.util.LinkedList;

public class Queue {
	public int counter;
	public LinkedList<String> myList;
	
	public Queue() {
		counter = 0;
		myList = new LinkedList<String>();
	}
	public void enqueue(String myString) {
		myList.addLast(myString);
	}
	public String dequeue() {
		return myList.removeFirst();
	}
	public int getCount() {
		return counter;
	}
}
