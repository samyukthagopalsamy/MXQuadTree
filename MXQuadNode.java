// MXQuadNode.java

public class MXQuadNode {

    int k; // K value that is chosen for granularity
    int level;  // level same as height of the node in the tree.
	int x, y; // (x, y) coordinates
	MXQuadNode NW, NE, SW, SE; // Pointers to 4 directions
	
	public MXQuadNode(int k) { // for the root only
		this.k = k;
		this.level = 0;
		x = (int) (Math.pow(2, k) / 2);
		y = (int) (Math.pow(2, k) / 2);
		NW = NE = SW = SE = null;
	}
	public MXQuadNode(int x, int y, int k, int level) { // for all nodes excluding the root node
		this.x = x;
		this.y = y;
		this.k = k;
		this.level = level;
		NW = NE = SW = SE = null;
	}
	
	public void print(MXQuadNode head, String direction) {
	
		if (head != null) {	
			
			if (head.level == k) {
				System.out.println(direction + "("+ head.x + ", "+ head.y + ")");
			}
			
			print(head.NW, direction  + "NW -> ");
			print(head.SW, direction + "SW -> ");
			print(head.NE, direction  + "NE -> ");
			print(head.SE, direction  +"SE -> ");
			
		}
	}
	
	public void search(MXQuadNode head,int x,int y)
	{
		MXQuadNode temp = head;
		String direction = "";
		
		while (temp.level <= k) {
			
			if (x < temp.x && y >= temp.y) { // NW
				if (temp.NW == null) 
					break;
				temp = temp.NW;
				direction += "NW -> ";
				
			} else if (x < temp.x && y < temp.y) { // SW
				if (temp.SW == null) 
					break;
				temp = temp.SW;
				direction += "SW -> ";

			} else if (x >= temp.x && y >= temp.y) { // NE
				if (temp.NE == null) 
					break;
				temp = temp.NE;
				direction += "NE -> ";

			} else if (x >= temp.x && y < temp.y) { // SE
				if (temp.SE == null) {
					break;
				}
				temp = temp.SE;
				direction += "SE -> ";
			}
		}
		direction = direction.substring(0, direction.length()-4);
		
		if (temp.level == k && x == temp.x && y == temp.y) 
				System.out.println("(" + x + ", " + y + ")  found at " + direction);
		else
			System.out.println("(" + x + ", " + y + ")  not found.");

	}

	public MXQuadNode delete(MXQuadNode head,int x,int y)
	{
		if (head != null) {	
			
			if (x < head.x && y >= head.y)  // NW
				head.NW = delete(head.NW, x, y);
		    else if (x < head.x && y < head.y)  // SW
		    	head.SW = delete(head.SW, x, y);
		    else if (x >= head.x && y >= head.y) // NE
		    	head.NE = delete(head.NE, x, y);
		    else if (x >= head.x && y < head.y) // SE
		    	head.SE = delete(head.SE, x, y);
			
			if (x == head.x && y == head.y && head.level == k) {
				System.out.println("("+ x + ", "+ y + ") found and deleted.");
				return null;
			}
		}
		return deleteOptimize(head);

	}
	
	public MXQuadNode deleteOptimize(MXQuadNode head) {
		
		if (head != null) {
			head.NW = deleteOptimize(head.NW);
			head.NE = deleteOptimize(head.NE);
			head.SW = deleteOptimize(head.SW);
			head.SE = deleteOptimize(head.SE);
			
			if (head.NE == null && head.NW == null && head.SW == null && head.SE == null && head.level != k) {
				return null;
			}
		}
		return head;
	}
	
	public void insert(MXQuadNode head, int x, int y) {
		MXQuadNode temp = head;
		while (temp.level+1 < k) { // while creating non leaf nodes
			int nextPointDifference = (int) Math.pow(2, (k - temp.level - 1)) / 2;
			if (x < temp.x && y >= temp.y) { // NW
				if (temp.NW == null) {
					
					int newX = temp.x - nextPointDifference;
					int newY = temp.y + nextPointDifference;
					temp.NW = new MXQuadNode(newX, newY, k, temp.level+1);
					//System.out.println("NW created " + newX + "," + newY + " at level " + temp.level);
				}
				temp = temp.NW;

			} else if (x < temp.x && y < temp.y) { // SW
				if (temp.SW == null) {

					int newX = temp.x - nextPointDifference;
					int newY = temp.y - nextPointDifference;
					temp.SW = new MXQuadNode(newX, newY, k, temp.level+1);
					//System.out.println("SW created " + newX + "," + newY + " at level " + temp.level);
				}
				temp = temp.SW;
				
			} else if (x >= temp.x && y >= temp.y) { // NE
				if (temp.NE == null) {

					int newX = temp.x + nextPointDifference;
					int newY = temp.y + nextPointDifference;
					temp.NE = new MXQuadNode(newX, newY, k, temp.level+1);
					
					//System.out.println("NE created " + newX + "," + newY + " at level " + temp.level);
				}
				temp = temp.NE;
				
			} else if (x >= temp.x && y < temp.y) { // SE
				if (temp.SE == null) {

					int newX = temp.x + nextPointDifference;
					int newY = temp.y - nextPointDifference;
					temp.SE =  new MXQuadNode(newX, newY, k, temp.level + 1);
					//System.out.println("SE created " + newX + "," + newY + " at level " + temp.level);
				}
				temp = temp.SE;
			}
		}
		
	    // At this point we reached the level just above leaf node. So we shall insert data.

		if (x == temp.x && y == temp.y) {
			temp.NE = new MXQuadNode(x, y, k, k); // NE
			System.out.println("Inserted " + "("+ temp.NE.x + ", "+ temp.NE.y + ")");
		} else if (x == temp.x -1 && y == temp.y) {
			temp.NW = new MXQuadNode(x, y, k, k); // NW
			System.out.println("Inserted " + "("+ temp.NW.x + ", "+ temp.NW.y + ")");

		} else if (x == temp.x && y == temp.y - 1)  {
			temp.SE = new MXQuadNode(x, y, k , k); // SE
			System.out.println("Inserted " + "("+ temp.SE.x + ", "+ temp.SE.y + ")");

		} else if (x == temp.x-1 && y == temp.y - 1) {
			temp.SW = new MXQuadNode(x, y, k, k); // SW
			System.out.println("Inserted " + "("+ temp.SW.x + ", "+ temp.SW.y + ")");
		} else {
			System.out.println("Error in Inserting.");
		}
		
	}
}
