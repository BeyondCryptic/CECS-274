import java.util.ArrayList;

public class RPNSolver {
	
	public Queue myQueue;
	public Stack myStack;
	public int[] parenthesisLoc;
	public int[] counterDB;
	public int parenthesisCount;
	public int numOfParenthesis;
	
	public RPNSolver() {
		myQueue = new Queue();
		myStack = new Stack();
		parenthesisCount = 0;
		numOfParenthesis = 0;
	}
	public int operatorValue(String operator) {
		int value = 0;
		if (operator.equals("(")) {
			value = 4;
		} else if (operator.equals(")")) {
			value = 4;
		} else if (operator.equals("^")) {
			value = 3;
		} else if (operator.equals("*")) {
			value = 2;
		} else if (operator.equals("/")) {
			value = 2;
		} else if (operator.equals("-")) {
			value = 1;
		} else if (operator.equals("+")) {
			value = 1;
		}
		return value;
	}
	public ArrayList<String> convertToPost(ArrayList<String> formula) {
		ArrayList<String> postfixNotation = new ArrayList<String>();
		//4+(2-3*(4*3-4)) = 4 2 3 4 3 * 4 - * - +
		//4+(2-3*(4*3-4*6*7-4)) = 4 2 3 4 3 * 4 6 * 7 * - 4 - * - +
		for (int i = 0; i < formula.size(); i++) {
			//System.out.println("The current item is: " + formula.get(i));
			//System.out.println("Items in Stack: " + myStack.view());
			//System.out.println("Items in Queue: " + myQueue.view());
			if (formula.get(i).equals("(") || formula.get(i).equals(")") ||
				formula.get(i).equals("^") ||
				formula.get(i).equals("*") || formula.get(i).equals("/") ||
				formula.get(i).equals("-") || formula.get(i).equals("+")) {
				if (myStack.getCount() == 0) {
					myStack.push(formula.get(i));
				} else if (formula.get(i).equals("(")) {
					myStack.push(formula.get(i)); // Creates a "mini-stack".
				} else if (formula.get(i).equals(")")) {
					while (!myStack.viewTop().equals("(")) {
						myQueue.enqueue(myStack.pop());
					}
					myStack.pop(); // Gets rid of "(" after finishing off mini-stack.
				} else if (operatorValue(formula.get(i)) > operatorValue(myStack.viewTop()) && !myStack.viewTop().equals("(")) {						
					myStack.push(formula.get(i));
				} else if (operatorValue(formula.get(i)) <= operatorValue(myStack.viewTop()) && !myStack.viewTop().equals(")")) {
					if (myStack.viewTop().equals("(")) {
						myStack.push(formula.get(i));
					} else {
						myQueue.enqueue(myStack.pop());
						i--;
					}
				}
			} else {
				myQueue.enqueue(formula.get(i));
			}
		}
		int stackCount = myStack.getCount();
		for (int i = 0; i < stackCount; i++) {
			if (!myStack.viewTop().equals("(")) {
				myQueue.enqueue(myStack.pop());
			}
		}
		int queueCount = myQueue.getCount();
		for (int i = 0; i < queueCount; i++) {
			postfixNotation.add(myQueue.dequeue());
		}
		//System.out.println("Final Stack: " + myStack.view());
		//System.out.println("Final Queue: " + myQueue.view());
		return postfixNotation;
	}
}
