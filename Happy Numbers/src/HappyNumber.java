import java.util.Scanner;

public class HappyNumber {
	public static int userInput;
	public static CalculateNumber calculate;
	public static void main(String[] args) {
		calculate = new CalculateNumber();
		askForNumber();
		calculate.number(userInput);
	}
	public static void askForNumber() {
		System.out.println("Please enter a number to check if it's happy or not.");
		Scanner scan = new Scanner(System.in);
		userInput = scan.nextInt();
		while (userInput < 0) {
			System.out.println("Please enter a positive integer!");
			userInput = scan.nextInt();
		}
	}
}
