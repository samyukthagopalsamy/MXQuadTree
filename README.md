# MXQuadTree
Implementation of MX quad tree in java.

public class MXQuadNode {

      int k; // K value that is chosen for granularity
      int level;  // level same as height of the node in the tree.
	  int x, y; // (x, y) coordinates
	  MXQuadNode NW, NE, SW, SE; // Pointers to 4 directions
	
}<br>
Value of k decides the height of the MX-Quad tree. It remains fixed for all nodes.<br>
Attribute level represents the current height of particular node. <br>
<br>
Two constructors<br>
1. Only for the root node, which sets middle point of the grid as x and y for the root.<br>
2. For all the other non-root nodes which assigns the specified x, y value. <br>
Initially, the attributes NW, NE, SW, SE will be assigned to null and k is the same for all nodes but level differs.
<br>
public void print(MXQuadNode head, String direction);
// recursive function for printing out the elements
<br>
public void insert(MXQuadNode head, int x, int y);
// non- recursive insert function
<br>
public void search(MXQuadNode head, int x, int y);
// function for searching if (x, y) is in the tree
<br>
public MXQuadNode delete(MXQuadNode head, int x, int y);
// recursive function for deleting if (x, y) is in the tree
<br>
public MXQuadNode deleteOptimize(MXQuadNode head);
// recursive function for deleting unused non-leaf nodes in the tree.<br>
