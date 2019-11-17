// MXQuadTest.java

import java.util.Scanner;

public class MXQuadTest {

	public static void main(String[] args) {
//		
//		MXQuadNode head = new MXQuadNode(3);
//		head.insert(head, 4, 2);		
//		head.insert(head, 3, 1);
//		head.insert(head, 6, 2);
//		head.insert(head, 5, 3);
//		head.insert(head, 3, 6);
//		head.print(head, "");
//		
//		head.search(head, 6, 2);
//		
//		head = head.delete(head, 4, 2);
//		head.print(head, "");
//		
//		MXQuadNode head = new MXQuadNode(4);
//		head.insert(head, 3, 7);		
//		head.insert(head, 12, 11);
//		head.insert(head, 1, 14);
//		head.insert(head, 15, 2);
//		head.print(head, "");
//		
//		head.search(head, 3, 7);
//		
//		head = head.delete(head, 1, 14);
//		head.print(head, "");
		
		Scanner scanner = new Scanner(System.in);
		int x, y, choice; 
		boolean flag = false;
		System.out.print("MX Quad Tree\n\nEnter k value : ");
		MXQuadNode head = new MXQuadNode(scanner.nextInt());
		while (true) {
			System.out.println("1. Insert \n2. Delete \n3. Search \n4. Display Tree \n(5 to exit)");
			choice = scanner.nextInt();
				switch(choice) {
				case 1: 
						System.out.println("Enter the number of nodes that you wish to insert : ");
						int numberOfNodes = scanner.nextInt();
						for (int i = 0; i < numberOfNodes; i++) {
							System.out.print("Enter x, y to insert (x, y) : ");
							x = scanner.nextInt();
							y = scanner.nextInt();
							head.insert(head, x, y);
						}
						break;
					
				case 2: 
						System.out.print("Enter x, y to delete (x, y) : ");
						x = scanner.nextInt();
						y = scanner.nextInt();
						head = head.delete(head, x, y);
						break;
						
				case 3 :
						System.out.print("Enter x, y to search for (x, y) : ");
					 	x = scanner.nextInt();
					 	y = scanner.nextInt();
					 	head.search(head, x, y);
					 	break;
					 	
				case 4 : 
						System.out.println("Printing leaves of the tree : ");
						head.print(head, "");
						break;
						
				case 5 : flag = true;
			}
			if (flag) {
				scanner.close();
				break;
			}
		}
	}

}
