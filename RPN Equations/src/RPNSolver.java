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
		/*if (operator.equals("(")) {
			value = 4;
		} else if (operator.equals(")")) {
			value = 4;
		} else */if (operator.equals("^")) {
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
	public String convertToPost(ArrayList<String> formula) {
		String postfixNotation = "";
		for (int i = 0; i < formula.size(); i++) {
			if (formula.get(i).equals("(")) {
				numOfParenthesis++;
			}
		}
		parenthesisLoc = new int[numOfParenthesis];
		counterDB = new int[numOfParenthesis];
		for (int x = 0; x < counterDB.length; x++) {
			counterDB[x] = 0;
		}
		for (int i = 0; i < formula.size(); i++) {
			//System.out.println("The current item is: " + formula.get(i));
			if (formula.get(i).equals("(") || formula.get(i).equals(")") ||
				formula.get(i).equals("^") ||
				formula.get(i).equals("*") || formula.get(i).equals("/") ||
				formula.get(i).equals("-") || formula.get(i).equals("+")) {
				if (formula.get(i).equals("(")) {
					parenthesisLoc[parenthesisCount] = i;
					if (parenthesisCount < parenthesisLoc.length-1 && parenthesisCount != 0) {
						parenthesisCount++;
					}
					//System.out.println("Current count: " + parenthesisCount);
					//System.out.println("Location of current parenthesis: " + i);
				}
				if (formula.get(i).equals(")")) {
					parenthesisLoc[parenthesisCount] = -1;
					if (parenthesisCount != 0) {
						parenthesisCount--;
					}
					for (int j = 0; j < counterDB[parenthesisCount]; j++) {
						myQueue.enqueue(myStack.pop());
					}
					counterDB[parenthesisCount] = 0;
				}
				if (parenthesisLoc[parenthesisCount] != -1 && formula.get(i).equals("(")) {
					int counterForParenthesis = counterDB[parenthesisCount];
					counterForParenthesis++;
					counterDB[parenthesisCount] = counterForParenthesis;
					/*for (int x = 0; x < counterDB.length; x++) {
						System.out.println("Counter for items inside parenthesis: " + counterDB[x]);
					}*/
				}
				if (myStack.getCount() >= 1 && !formula.get(i).equals("(") && !formula.get(i).equals(")")) {
					if (operatorValue(formula.get(i)) >= operatorValue(myStack.viewTop()) && !formula.get(i).equals(")")) {
						myStack.push(formula.get(i));
					} else if (!formula.get(i).equals("(") && !formula.get(i).equals(")")){
						if ((operatorValue(formula.get(i)) < operatorValue(myStack.viewTop())) && myStack.getCount() != 0 && !formula.get(i).equals(")")) {
							myQueue.enqueue(myStack.pop());
							i--;
						}
					}
				} else if (!formula.get(i).equals("(") && !formula.get(i).equals(")")){
					myStack.push(formula.get(i));
				}
			} else if (!formula.get(i).equals("(") && !formula.get(i).equals(")")){
				myQueue.enqueue(formula.get(i));
			}
		}
		int stackSize = myStack.getCount();
		for (int i = 0; i < stackSize; i++) {
			myQueue.enqueue(myStack.pop());
			//System.out.println("Stack Size: " + myStack.getCount());
			//System.out.println("Items in Stack: " + myStack.view());
		}
		int queueSize = myQueue.getCount();
		for (int i = 0; i < queueSize; i++) {
			postfixNotation = postfixNotation + myQueue.dequeue();
			//System.out.println("Queue Size: " + myQueue.getCount());
			//System.out.println("Items in Queue: " + myQueue.view());
			if (i < queueSize-1) {
				postfixNotation = postfixNotation + " ";
			}
		}
		return postfixNotation;
	}
}
