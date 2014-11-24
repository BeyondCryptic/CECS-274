
public class Node {
	
	public int value;
	public Node leftChild;
	public Node rightChild;
	
	public Node(int data) {
		value = data;
		leftChild = null;
		rightChild = null;
	}
	
	public void setValue(int aValue) {
		value = aValue;
	}
	
	public void setLeft(Node left) {
		leftChild = left;
	}
	
	public void setRight(Node right) {
		rightChild = right;
	}
	
	public Node getNextLeft() {
		return leftChild;
	}
	
	public Node getNextRight() {
		return rightChild;
	}
	
	public int getValue() {
		return value;
	}
	
}
