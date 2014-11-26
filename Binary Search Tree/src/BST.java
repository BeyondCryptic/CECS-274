import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BST {
	
	public static Node root;
	public static Scanner scan;
	public static Scanner scan2;
	public static String userChoice;
	public static int rootValue;
	public static int numberInTree;
	public static boolean found;
	public static boolean foundDel;
	public static boolean foundDelAction;
	public static ArrayList<Integer> numberEntered;
	
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		scan2 = new Scanner(System.in);
		root = null;
		numberInTree = 0;
		found = false;
		foundDel = false;
		foundDelAction = false;
		askUser();
	}
	
	public static void askUser() {
		System.out.println("");
		System.out.println("What do you want to do?");
		System.out.println("1. Add item to tree\n2. Delete item from tree\n3. Find item\n4. Balance tree\n5. List contents of tree\n6. Display statistics");
		String tempUserChoice = scan.nextLine();
		userChoice = tempUserChoice;
		selectChoice();
	}
	
	public static void selectChoice() {
		if (userChoice.equals("1")) {
			addToBST();
			System.out.println("Item added.");
		} else if (userChoice.equals("2")) {
			deleteFromBST();
		} else if (userChoice.equals("3")) {
			findItem();
		} else if (userChoice.equals("4")) {
			numberEntered = new ArrayList<Integer>();
			addToArray(root);
			root = null;
			numberInTree = 0;
			balanceTree(numberEntered, 0, numberEntered.size()-1);
			System.out.println("Tree balanced.");
		} else if (userChoice.equals("5")) {
			System.out.print("Content of tree in order: ");
			listContents(root);
			System.out.println("");
		} else if (userChoice.equals("6")) {
			displayStatistics();
		} else {
			System.out.println("You have entered an invalid choice!");
		}
		askUser();
	}
	
	public static void addToBST() {
		System.out.println("What number do you want to add?");
		int userNum = scan2.nextInt();
		Node aNode = new Node(userNum);
		Node temp = null;
		boolean exitWhile = false;
		if (root == null) {
			root = aNode;
			numberInTree++;
		} else {
			if (root.getValue() > userNum) {
				temp = root.getNextLeft();
				if (temp == null) {
					temp = aNode;
					root.setLeft(temp);
					numberInTree++;
				} else {
					exitWhile = false;
					while (exitWhile != true) {
						if (temp.getValue() > userNum && temp.getNextLeft() != null) {
							temp = temp.getNextLeft();
						} else if (temp.getValue() < userNum && temp.getNextRight() != null) {
							temp = temp.getNextRight();
						} else if (temp.getValue() > userNum && temp.getNextLeft() == null) {
							temp.setLeft(aNode);
							numberInTree++;
							exitWhile = true;
						} else if (temp.getValue() < userNum && temp.getNextRight() == null) {
							temp.setRight(aNode);
							numberInTree++;
							exitWhile = true;
						}
					}
				}
			} else if (root.getValue() < userNum) {
				temp = root.getNextRight();
				if (temp == null) {
					temp = aNode;
					root.setRight(temp);
					numberInTree++;
				} else {
					exitWhile = false;
					while (exitWhile != true) {
						if (temp.getValue() > userNum && temp.getNextLeft() != null) {
							temp = temp.getNextLeft();
						} else if (temp.getValue() < userNum && temp.getNextRight() != null) {
							temp = temp.getNextRight();
						} else if (temp.getValue() > userNum && temp.getNextLeft() == null) {
							temp.setLeft(aNode);
							numberInTree++;
							exitWhile = true;
						} else if (temp.getValue() < userNum && temp.getNextRight() == null) {
							temp.setRight(aNode);
							numberInTree++;
							exitWhile = true;
						}
					}
				}
			}
		}
	}
	
	public static void addToBST(int arrayNumber) {
		int userNum = arrayNumber;
		Node aNode = new Node(userNum);
		Node temp = null;
		boolean exitWhile = false;
		if (root == null) {
			root = aNode;
			rootValue = root.getValue();
			numberInTree++;
		} else {
			if (root.getValue() > userNum) {
				temp = root.getNextLeft();
				if (temp == null) {
					temp = aNode;
					root.setLeft(temp);
					numberInTree++;
				} else {
					exitWhile = false;
					while (exitWhile != true) {
						if (temp.getValue() > userNum && temp.getNextLeft() != null) {
							temp = temp.getNextLeft();
						} else if (temp.getValue() < userNum && temp.getNextRight() != null) {
							temp = temp.getNextRight();
						} else if (temp.getValue() > userNum && temp.getNextLeft() == null) {
							temp.setLeft(aNode);
							numberInTree++;
							exitWhile = true;
						} else if (temp.getValue() < userNum && temp.getNextRight() == null) {
							temp.setRight(aNode);
							numberInTree++;
							exitWhile = true;
						}
					}
				}
			} else if (root.getValue() < userNum) {
				temp = root.getNextRight();
				if (temp == null) {
					temp = aNode;
					root.setRight(temp);
					numberInTree++;
				} else {
					exitWhile = false;
					while (exitWhile != true) {
						if (temp.getValue() > userNum && temp.getNextLeft() != null) {
							temp = temp.getNextLeft();
						} else if (temp.getValue() < userNum && temp.getNextRight() != null) {
							temp = temp.getNextRight();
						} else if (temp.getValue() > userNum && temp.getNextLeft() == null) {
							temp.setLeft(aNode);
							numberInTree++;
							exitWhile = true;
						} else if (temp.getValue() < userNum && temp.getNextRight() == null) {
							temp.setRight(aNode);
							numberInTree++;
							exitWhile = true;
						}
					}
				}
			}
		}
	}
	
	public static void deleteFromBST() {
		rootValue = root.getValue();
		System.out.println("What would you like to delete?");
		int findThis = scan2.nextInt();
		foundDel = false;
		foundDelAction = false;
		if(findThis == rootValue && root.getNextLeft() == null && root.getNextRight() == null) {
			root = null;
			numberInTree--;
			System.out.println("Item deleted.");
			foundDelAction = true;
		} else {
			deleteThisItem(root, findThis);
		}
		if (foundDelAction == false) {
			System.out.println("The number could not be found.");
		}
	}
	
	public static void findItem() {
		System.out.println("What would you like to find?");
		int findThis = scan2.nextInt();
		found = false;
		findThisItem(root, findThis, 0);
		if (found == false) {
			System.out.println("The number could not be found.");
		}
	}
	
	public static void balanceTree(ArrayList<Integer> aList, int min, int max) {
		if (min > max) {
			return;
		} else {
			int midpoint = ((max-min)/2) + min;
			addToBST(aList.get(midpoint));
			balanceTree(aList, min, midpoint - 1);
			balanceTree(aList, midpoint + 1, max);
		}
	}
	
	public static void listContents(Node myNode) {
		if (root == null) {
			System.out.println("Tree is empty.");
			return;
		}
		if (myNode.getNextLeft() != null) {
			listContents(myNode.getNextLeft());
		}
		System.out.print(myNode.getValue() + " ");
		if (myNode.getNextRight() != null) {
			listContents(myNode.getNextRight());
		}
	}
	
	public static void displayStatistics() {
		if (root != null) {
			rootValue = root.getValue();
			System.out.println("Root Value: " + rootValue);
			System.out.println("Depth of Tree: " + getLevel(root));
			System.out.println("Number of Items in Tree: " + numberInTree);
		} else {
			System.out.println("Tree is empty.");
			System.out.println("Root Value: Tree is empty.");
			System.out.println("Depth of Tree: 0, Tree is empty.");
			System.out.println("Number of Items in Tree: " + numberInTree);
		}
	}

	public static void findThisItem(Node userNode, int userNumber, int level) {
		int onLevel = level + 1;
		if (userNode.getValue() == userNumber) {
			//onLevel--;
			System.out.println("Item is on level: " + onLevel);
			found = true;
		}
		if (userNode.getNextLeft() != null) {
			findThisItem(userNode.getNextLeft(), userNumber, onLevel);
		}
		if (userNode.getNextRight() != null) {
			findThisItem(userNode.getNextRight(), userNumber, onLevel);
		}
	}
	
	public static Node findNode(Node userNode, int userNumber) {
		if (userNode.getNextLeft() != null) {
			return findNode(userNode.getNextLeft(), userNumber);
		}
		return userNode;
	}

	public static void deleteThisItem(Node userNode, int userNumber) {
		if (userNode.getValue() == userNumber) {
			if(userNode.getNextLeft() == null && userNode.getNextRight() == null) {
				foundDel = true;
			} else {
				deleteThisItemNonChild(userNode, userNumber);
			}
		}
		if (userNode.getNextLeft() != null && userNode.getNextLeft().getValue() == userNumber) {
			if (userNode.getNextLeft().getNextLeft() != null) {
				if (userNode.getNextLeft().getNextRight() == null) {
					Node temp = userNode.getNextLeft().getNextLeft();
					userNode.setRight(temp);
					numberInTree--;
					foundDelAction = true;
					System.out.println("Item deleted.");
				}
			} else if (userNode.getNextLeft().getNextLeft() == null) {
				if (userNode.getNextLeft().getNextRight() != null) {
					Node temp = userNode.getNextLeft().getNextRight();
					userNode.setRight(temp);
					numberInTree--;
					foundDelAction = true;
					System.out.println("Item deleted.");
				}
			}
		}
		if (userNode.getNextRight() != null && userNode.getNextRight().getValue() == userNumber) {
			if (userNode.getNextRight().getNextLeft() != null) {
				if (userNode.getNextRight().getNextRight() == null) {
					Node temp = userNode.getNextRight().getNextLeft();
					userNode.setRight(temp);
					numberInTree--;
					foundDelAction = true;
					System.out.println("Item deleted.");
				}
			} else if (userNode.getNextRight().getNextLeft() == null) {
				if (userNode.getNextRight().getNextRight() != null) {
					Node temp = userNode.getNextRight().getNextRight();
					userNode.setRight(temp);
					numberInTree--;
					foundDelAction = true;
					System.out.println("Item deleted.");
				}
			}
		}
		if (userNode.getNextLeft() != null) {
			deleteThisItem(userNode.getNextLeft(), userNumber);
			if (foundDel == true) {
				userNode.setLeft(null);
				System.out.println("Item deleted.");
				numberInTree--;
				foundDel = false;
				foundDelAction = true;
			}
		}
		if (userNode.getNextRight() != null) {
			deleteThisItem(userNode.getNextRight(), userNumber);
			if (foundDel == true) {
				userNode.setRight(null);
				System.out.println("Item deleted.");
				numberInTree--;
				foundDel = false;
				foundDelAction = true;
			}
		}
	}
	
	public static void deleteThisItemNonChild(Node userNode, int userNumber) {
		int minimum = findMinimum(userNode.getNextRight());
		Node swapThisNode = findNode(userNode.getNextRight(), minimum);
		swapThisNode.setValue(userNumber);
		userNode.setValue(minimum);
		deleteThisItem(userNode, userNumber);
	}
	
	public static int findMinimum(Node userNode) {
		if (userNode.getNextLeft() != null) {
			return findMinimum(userNode.getNextLeft());
		}
		return userNode.getValue();
	}
	
	public static void addToArray(Node myNode) {
		if (myNode.getNextLeft() != null) {
			addToArray(myNode.getNextLeft());
		}
		numberEntered.add(myNode.getValue());
		if (myNode.getNextRight() != null) {
			addToArray(myNode.getNextRight());
		}
	}
	
	public static int getLevel(Node myNode) {
		if (myNode == null) { 
			return 0;
		} else {
			return 1 + Math.max(getLevel(myNode.getNextLeft()), getLevel(myNode.getNextRight()));
		}
	}
	
}
