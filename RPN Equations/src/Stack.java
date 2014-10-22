import java.util.LinkedList;

public class Stack {
	
	public LinkedList<String> myList;
	
	public Stack() {
		myList = new LinkedList<String>();
	}
	public void push(String myString){
		myList.addLast(myString);
	}
	public String pop() {
		return myList.removeLast();
	}
}
