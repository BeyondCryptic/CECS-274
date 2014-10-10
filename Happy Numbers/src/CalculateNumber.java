import java.util.ArrayList;


public class CalculateNumber {
	public int numberAtPosition = 0;
	public int counter = 0;
	public ArrayList<Integer> numberBank;
	public boolean fail = false;
	public CalculateNumber() {
		numberBank = new ArrayList<Integer>();
	}
	public void number(int userNumber) {
		int storage = 0;
		int finalNumber = 0;
		int length = String.valueOf(userNumber).length();
		for (int i = 1; i <= length; i++) {
			numberAtPosition = (userNumber%((int) Math.pow(10, i)));
			storage = (numberAtPosition)/(int) Math.pow(10, i-1);
			finalNumber = finalNumber + (storage * storage);
		}
		numberBank.add(counter, finalNumber);
		counter++;
		for (int i = 0; i < numberBank.size(); i++) {
			int checkNumber = numberBank.get(i);
			for (int j = 1; j < numberBank.size(); j++) {
				int checkNumber2 = numberBank.get(j);
				if (checkNumber == checkNumber2) {
					fail = true;
				}
			}
		}
		if (finalNumber != 1 && !fail) {
			System.out.print(finalNumber + ", ");
		}
		if (finalNumber == 1) {
			System.out.println(finalNumber + " - The number is happy!");
			System.exit(0);
		}
		if (!fail) {
			number(finalNumber);
		} else {
			System.out.println(finalNumber + " - Not a happy number!");
			System.exit(0);
		}
	}
}
