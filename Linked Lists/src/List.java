
public class List {
	private Node head;
	private int findCount;
	
	public List() {
		head = null;
	}
	public void addInOrder(String data) {
		Node myNode = new Node(data);
		Node iterator = head;
		Node previous = head;
		if(head == null) {
			head = myNode;
		}
		else if (myNode.getData().compareTo(iterator.getData()) < 0) {
			head = myNode;
			myNode.setNext(iterator);
		}
		else {
			while (iterator != null && myNode.getData().compareTo(iterator.getData()) > 0) {
				previous = iterator;
				iterator = iterator.getNext();
			}
			previous.setNext(myNode);
			myNode.setNext(iterator);
		}
	}
	public void delete(String word) {
		Node temp = head;
		Node previous = null;
		Node next = temp.getNext();
		while (temp != null) {
			if (temp.getData().equals(word)) {
				if (previous != null) {
					previous.setNext(next);
					temp = null;
				}
				else {
					head = next;
					temp = head;
				}
				temp = null;
				if ( next != null) {
					next = next.getNext();
				}
				temp = head;
			}
			else {
				previous = temp;
				temp = next;
				if (next != null) {
					next = next.getNext();
				}
			}
		}
	}
	public boolean find(String word) {
		Node temp = head;
		findCount = 0;
		while (temp != null) {
			if (temp.getData().equals(word)) {
				findCount++;
				temp = temp.getNext();
			}
			else {
				temp = temp.getNext();
			}
		}
		if (findCount > 0) {
			return true;
		}
		return false;
	}
	public int findCount() {
		return findCount;
	}
	public void display() {
		int count = 0;
		Node temp = head;
		while(temp != null) {
			if (count < 12) { 
				System.out.print(temp.getData() + "\t");
				count++;
				temp = temp.getNext();
			}
			else {
				count = 0;
				System.out.println("");
			}
		}
	}
	public void display(String word) {
		int count = 0;
		Node temp = head;
		while(temp != null) {
			if (count < 12) { 
				if (temp.getData().equals(word)) {
					System.out.print("*" + temp.getData() + "\t");
				}
				else {
					System.out.print(temp.getData() + "\t");
				}
				count++;
				temp = temp.getNext();
			}
			else {
				count = 0;
				System.out.println("");
			}
		}
	}
	public int size() {
		Node temp = head;
		int count = 0;
		while(temp != null) {
			count++;
			temp = temp.getNext();
		}
		return count;
	}

private class Node {
	private String savedData;
	private Node next; // creates a node "reference"
	public Node(String data) {
		savedData = data;
		next = null; // sets reference to null. When list is empty, this is the end.
	}
	public String getData() {
		return savedData;
	}
	/*public int getValue(String data) {
		int length = data.length();
		int value = 0;
		for (int i = 0; i <= length-1; i++) {
			String tempString = data.substring(i,i+1);
			char tempChar = tempString.charAt(0);
			value = value + (int) tempChar;
		}
		return value;
	}*/
	public void setNext(Node aNode) {
		next = aNode;
	}
	public Node getNext() {
		return next;
	}
}

}
