import java.util.*;
import java.io.*;

class Node {
    Node left;
    Node right;
    int data;
    
    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class Solution {
	/*
    class Node 
    	int data;
    	Node left;
    	Node right;
	*/
    static int treeHeight = 0;
	  public static int height(Node root) {
      	// Write your code here.
        Traverse(root, 0);
        return treeHeight;
    }
    
    public static void Traverse(Node node, int nodeHeight)
    {
        Node left = node.left;
        Node right = node.right;
        if(left != null)
        {
            Traverse(left, nodeHeight+1);
        }
        if(right != null)
        {
            Traverse(right, nodeHeight+1);
        }
        if(left == null && right == null)//leaf node
        {
            if(treeHeight < nodeHeight)
            {
                treeHeight = nodeHeight;
            }
        }
    }

	public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        int height = height(root);
        System.out.println(height);
    }	
}
