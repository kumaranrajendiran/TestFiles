package BinaryTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Queue;
/**
 * 
 * @author kumaran, gautam, guna, monish 
 *
 */
public class BinaryTreeOperations {

	public int maxLength = 0;
	
	public void clean() {
		maxLength = 0;
	}
/**
 * Method the compute the length of the maximum BST in the given Binary Tree
 * @param root : Root node of the Binary Tree
 * @param min : minimum integer value possible in the binary tree
 * @param max : maximum integer value possible in the binary tree
 */
	public void getMaxBinarySearchTreeLength(Node root, int min, int max) {

		if (root != null) {
			if (min <= root.data - 1) {
				getMaxBinarySearchTreeLength(root.left, min, root.data - 1);
			} else {
				getMaxBinarySearchTreeLength(root.left, Integer.MIN_VALUE,
						root.data - 1);
			}
			if (max >= root.data + 1) {
				getMaxBinarySearchTreeLength(root.right, root.data + 1, max);
			} else {
				getMaxBinarySearchTreeLength(root.right, root.data + 1,
						Integer.MAX_VALUE);
			}
			int leftNodeContribution = 0;
			int rightNodeContribution = 0;
			if (root.left != null && root.left.data < root.data) {
				leftNodeContribution = root.left.contribution;
			}
			if (root.right != null && root.right.data > root.data) {
				rightNodeContribution = root.right.contribution;
			}
			root.levelContribution = 1 + leftNodeContribution
					+ rightNodeContribution;

			leftNodeContribution = 0;
			rightNodeContribution = 0;
			if (root.left != null && root.left.data >= min
					&& root.left.data < root.data) {
				leftNodeContribution = root.left.contribution;
			}
			if (root.right != null && root.right.data > root.data
					&& root.right.data <= max) {
				rightNodeContribution = root.right.contribution;
			}
			root.contribution = 1 + leftNodeContribution
					+ rightNodeContribution;
			if (root.levelContribution > maxLength) {
				maxLength = root.levelContribution;
			}
		}
	}
/**
 * Method to construct a binary tree from the given post-order and in-order traversals
 * @param inorder : Integer array representing in-order traversal
 * @param postorder : Integer array representing post-order traversal
 * @return
 */
	public Node buildTree(int[] inorder, int[] postorder) {
		if (inorder.length == 0) {
			return null;
		}
		Node root = new Node(postorder[postorder.length - 1]);
		int index = 0;
		for (int i = 0; i < inorder.length; i++) {
			if (inorder[i] == root.data) {
				index = i;
				break;
			}
		}
		int[] leftInOrder = new int[index];
		int[] leftPostOrder = new int[index];
		int[] rightInOrder = new int[inorder.length - index - 1];
		int[] rightPostOrder = new int[inorder.length - index - 1];
		for (int i = 0; i < inorder.length; i++) {
			if (i < index) {
				leftInOrder[i] = inorder[i];
			}
			if (i > index) {
				rightInOrder[i - index - 1] = inorder[i];
			}
		}
		for (int i = 0; i < postorder.length - 1; i++) {
			if (i < index) {
				leftPostOrder[i] = postorder[i];
			} else {
				rightPostOrder[i - index] = postorder[i];
			}
		}

		root.left = buildTree(leftInOrder, leftPostOrder);
		root.right = buildTree(rightInOrder, rightPostOrder);
		return root;
	}
/**
 * Displays the level order traversal of the given Binary Tree
 * @param root: Root node of the Binary Tree
 */
	public void levelorder(Node root) {
		Queue<Node> nodequeue = new LinkedList<Node>();
		if (root != null)
			nodequeue.add(root);
		while (!nodequeue.isEmpty()) {
			Node next = nodequeue.remove();
			System.out.print(next.data + " ");
			if (next.left != null) {
				nodequeue.add(next.left);
			}
			if (next.right != null) {
				nodequeue.add(next.right);
			}
		}
	}
/**
 * Displays the in-order traversal of the given Binary Tree
 * @param root: Root node of the Binary Tree
 */
	public void inOrderTraversal(Node root) {
		if (root == null) {
			return;
		} else {
			inOrderTraversal(root.left);
			System.out.print(root.data + " ");
			inOrderTraversal(root.right);
		}
	}
/**
 * Displays the post-order traversal of the given Binary Tree
 * @param root : Root Node of the Binary Tree
 */
	public void postOrderTraversal(Node root) {
		if (root != null) {
			postOrderTraversal(root.left);
			postOrderTraversal(root.right);
			System.out.print(root.data + " ");
		}
	}
/**
 * Wrapper method to parse the input file 
 * Calls the buildTree method to construct the tree based on the in-order and post-order traversal
 * Calls the getMaxBinaryTreeLength to compute the maximum length of the BST in the given binary tree
 * @param filePath : Input file path which is of type String
 */
	public void parseFileAndComputeLength(String filePath) {
		try {
			FileReader filereader = new FileReader(filePath);
			BufferedReader bufferedreader = new BufferedReader(filereader);
			int numTreeSets = Integer.parseInt(bufferedreader.readLine());
			for (int i = 0; i < numTreeSets; i++) {
				int numNodes = 0;
				numNodes = Integer.parseInt(bufferedreader.readLine());
				String[] postOrder = (bufferedreader.readLine()).split(" ");
				String[] inOrder = (bufferedreader.readLine()).split(" ");

				int[] postOrderTraversal = new int[numNodes];
				int[] inOrderTraversal = new int[numNodes];
				for (int j = 0; j < numNodes; j++) {
					postOrderTraversal[j] = Integer.parseInt(postOrder[j]);
					inOrderTraversal[j] = Integer.parseInt(inOrder[j]);
				}

				Node root = null;
				root = buildTree(inOrderTraversal, postOrderTraversal);

				/*levelorder(root);
				System.out.println("\n");
				postOrderTraversal(root);
				System.out.println("\n");
				inOrderTraversal(root);
				System.out.println("\n");*/
				getMaxBinarySearchTreeLength(root, Integer.MIN_VALUE,
						Integer.MAX_VALUE);
				System.out.println("Maximum Length Binary Tree for set "
						+ (i + 1) + " is " + maxLength);
				clean();
			}
			bufferedreader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		if(args.length ==0){
			System.out.println("Usage : Provide an input file");
			return;
		}
		BinaryTreeOperations operations = new BinaryTreeOperations();
		operations
				.parseFileAndComputeLength(args[0]);
	}
}
