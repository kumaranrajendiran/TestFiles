package BinaryTree;
/**
 * Class To represent the node of a binary tree
 * @author kumaran, gautam, guna, monish 
 *
 */
public class Node {
	public Node  left = null;
	public Node  right = null;
	public int data;
	/**
	 * Maximum binary search tree length with the current node as the root node
	 */
	public int levelContribution;
	/**
	 * contribution of the current node to its parent in order to form a BST
	 */
	public int contribution;
	
	public Node(int data){
		this.data = data;
		this.levelContribution = 1;
		this.contribution = 1;
	}
}
