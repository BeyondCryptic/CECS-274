import java.util.LinkedList;

public class Stack {
	public int counter;
	public LinkedList<String> myList;
	
	public Stack() {
		counter = 0;
		myList = new LinkedList<String>();
	}
	public void push(String myString){
		counter++;
		myList.addLast(myString);
	}
	public String pop() {
		counter--;
		return myList.removeLast();
	}
	public int getCount() {
		return counter;
	}
}
