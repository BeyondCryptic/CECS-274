import java.util.ArrayList;
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
		askUser();
		displayData();
	}
	public static void askUser() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter an infix formula you want to calculate: ");
		userFormula = scan.nextLine() + " ";
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
		for (int i = 0; i < finalFormula.size(); i++) {
			System.out.println(finalFormula.get(i));
		}
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
		System.out.println("Postfix notation: " + solver.convertToPost(finalFormula));
	}
}
