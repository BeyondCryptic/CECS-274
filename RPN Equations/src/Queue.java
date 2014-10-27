import java.util.LinkedList;

public class Queue {
	public int counter;
	public LinkedList<String> myList;
	
	public Queue() {
		counter = 0;
		myList = new LinkedList<String>();
	}
	public void enqueue(String myString) {
		count++;
		myList.addLast(myString);
	}
	public String dequeue() {
		count--;
		return myList.removeFirst();
	}
	public int getCount() {
		return counter;
	}
}
