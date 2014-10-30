import java.util.ArrayList;

public class RPNSolver {
	
	public RPNSolver() {
		
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
		Queue myQueue = new Queue();
		Stack myStack = new Stack();
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
	public String doMath(double opTwo, double opOne, String operator) {
		double answer = 0;
		if (operator.equals("+")) {
			//System.out.print(opOne + " + " + opTwo + " = ");
			answer = opOne + opTwo;
		} else if (operator.equals("-")) {
			//System.out.println(opOne + " - " + opTwo + " = ");
			answer = opOne - opTwo;
		} else if (operator.equals("*")) {
			//System.out.println(opOne + " * " + opTwo + " = ");
			answer = opOne * opTwo;
		} else if (operator.equals("/")) {
			//System.out.println(opOne + " / " + opTwo + " = ");
			answer = opOne / opTwo;
		} else if (operator.equals("^")) {
			//System.out.println(opOne + " ^ " + opTwo + " = ");
			answer = Math.pow(opOne, opTwo);
		}
		String answerInString = String.valueOf(answer);
		//System.out.println(answerInString);
		return answerInString;
	}
	public double solveRPN(ArrayList<String> postfixFormula) {
		Stack operatorStack = new Stack();
		Stack operandStack = new Stack();
		double answer = 0;
		
		//System.out.println(postfixFormula);
		
		for (int i = 0; i < postfixFormula.size(); i++) {
			//System.out.println("Operator Stack: " + operatorStack.view());
			//System.out.println("Operand Stack: " + operandStack.view());
			if (postfixFormula.get(i).equals("^") ||
				postfixFormula.get(i).equals("*") || postfixFormula.get(i).equals("/") ||
				postfixFormula.get(i).equals("-") || postfixFormula.get(i).equals("+")) {
				operatorStack.push(postfixFormula.get(i));
				if (operatorStack.getCount() >= 1 && operandStack.getCount() >= 2) {
					while (operatorStack.getCount() != 0) {
						//System.out.println("Operator Stack (before math): " + operatorStack.view());
						//System.out.println("Operand Stack (before math): " + operandStack.view());
						operandStack.push(doMath(Double.parseDouble(operandStack.pop()), Double.parseDouble(operandStack.pop()), operatorStack.pop()));
						//System.out.println("Operator Stack (after math): " + operatorStack.view());
						//System.out.println("Operand Stack (after math): " + operandStack.view());
					}
				}
			} else {
				operandStack.push(postfixFormula.get(i));
			}
			//System.out.println("Operator Stack (after): " + operatorStack.view());
			//System.out.println("Operand Stack (after): " + operandStack.view());
		}
		answer = Double.parseDouble(operandStack.viewTop());
		return answer;
	}
}
