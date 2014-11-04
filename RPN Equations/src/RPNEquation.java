import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class RPNEquation {
	
	public static RPNSolver solver;
	public static int operatorCounter = 0;
	public static int operandCounter = 0;
	public static String userFormula = "";
	public static ArrayList<String> finalFormula;
	
	
	public static void main(String[] args) {
		solver = new RPNSolver();
		finalFormula = new ArrayList<String>();
		/*String[] test = "-1.2+(2*5-3/5+(3*(3-5^5)))".split("[\\s\\(\\)\\*\\/\\^+-]+");
		for (int i = 0; i < test.length; i++) {
			System.out.println(test[i]);
		}*/
		askUser();
		displayData();
	}
	public static void askUser() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter an infix formula you want to calculate: ");
		
		userFormula = scan.nextLine();
		String[] formula = userFormula.split("\\s");
		
		System.out.println(Arrays.asList(formula));
		
		for (int i = 0; i < formula.length; i++) {
			finalFormula.add(formula[i]);
		}
		/*userFormula = scan.nextLine() + " ";
		String[] formula = userFormula.split("");
		int counter = 0;
		for (int i = 1; i < formula.length; i++) {
			while (Character.isDigit(formula[i].charAt(0))) {
				counter++;
				i++;
				//System.out.println("The difference is: " + counter);
			}
			if (!Character.isDigit(formula[i].charAt(0))) {
				int math = i - counter;
				int math2 = i - 1;
				int difference = i - math;
				String number = "";
				//System.out.println("First number appears at index: " + math);
				//System.out.println("Last number appears at index: " + math2);
				if (difference >= 1) {
					for (int j = math; j < i; j++) {
					number = number + formula[j];
					}
				} else {
					number = formula[i-1];
				}
				//System.out.println(number);
				finalFormula.add(number);
			}
			while (!Character.isDigit(formula[i].charAt(0)) && i < formula.length-1) {
				//System.out.println("The index is: " + i);
				//System.out.println(formula[i]);
				finalFormula.add(formula[i]);
				//System.out.println("The difference is: " + counter);
				i++;
			}
			counter = 1;
		}
		// Does not work if formula is too short!
		// Below processes the formula to see if there are any negative signs that mean a negative number instead of minus that number.
		// Example: 2+-3 should be 2 + (-3).
		// Uses regex, "\\d+$".
		// \\d means all digits, 0-9.
		// + means include previous items (digits in this case).
		// $ means until the end of the line (all of the digits in this case).
		System.out.println(finalFormula);
		if (finalFormula.get(0).equals("")) {
			finalFormula.remove(0);
			String temp = finalFormula.get(1);
			temp = "-" + temp;
			finalFormula.remove(0);
			finalFormula.remove(0);
			finalFormula.add(0, temp);
		}*/
		// Fail attempt at getting decimals to work...
		/*for (int i = 0; i < finalFormula.size(); i++) {
			if (finalFormula.get(i).matches("\\d+$")) {
				if (finalFormula.get(i+1).equals(".") && finalFormula.get(i+2).matches("\\d+$")) {
					String temp = finalFormula.get(i+2);
					temp = "-" + temp;
					finalFormula.remove(i+1);
					finalFormula.remove(i+1);
					finalFormula.add(i+1, temp);
				}
			}
		}*/
		/*for (int i = 0; i < finalFormula.size(); i++) {
			if (finalFormula.get(i).equals("^") ||
				finalFormula.get(i).equals("*") || finalFormula.get(i).equals("/") ||
				finalFormula.get(i).equals("-") || finalFormula.get(i).equals("+")) {
				if (finalFormula.get(i+1).equals("-") && finalFormula.get(i+2).matches("\\d+$")) {
					String temp = finalFormula.get(i+2);
					temp = "-" + temp;
					finalFormula.remove(i+1);
					finalFormula.remove(i+1);
					finalFormula.add(i+1, temp);
				}
			}
		}*/
		System.out.println(finalFormula);
		processData(finalFormula);
	}
	
	public static void processData(ArrayList<String> stringArray) {
		for (int i = 0; i < stringArray.size(); i++) {
			if (stringArray.get(i).equals("(") || stringArray.get(i).equals(")") ||
				stringArray.get(i).equals("^") ||
				stringArray.get(i).equals("*") || stringArray.get(i).equals("/") ||
				stringArray.get(i).equals("-") || stringArray.get(i).equals("+")) {
				operatorCounter++;
				if (stringArray.get(i).equals("(") || stringArray.get(i).equals(")")) {
					operatorCounter--;
				}
			} else {
				operandCounter++;
			}
		}
		//System.out.println("Number of operators (symbols): " + operatorCounter);
		//System.out.println("Number of operands (numbers): " + operandCounter);
		if (operatorCounter >= operandCounter) {
			System.out.println("Error, invalid formula! There are too many operators!");
			System.exit(0);
		}
	}
	
	public static void displayData() {
		System.out.println("Infix notation: " + userFormula);
		ArrayList<String> postfixFormula = solver.convertToPost(finalFormula);
		String postfixNotation = "";
		for (int i = 0; i < postfixFormula.size(); i++) {
			postfixNotation = postfixNotation + postfixFormula.get(i);
			if (i < postfixFormula.size()-1) {
				postfixNotation = postfixNotation + " ";
			}
		}
		double answer = solver.solveRPN(postfixFormula);
		System.out.println("Postfix notation: " + postfixNotation);
		System.out.println("Answer: " + answer);
	}
}
