//$Id$
package hackerEarthSolutions.dataStructures.trees;

import java.util.Random;
import java.util.Scanner;

/**
 * 
 * <a href= "https://www.hackerearth.com/challenge/competitive/july-clash-15/algorithm/upgrade/">Problem Link</a>
 * @author gokul
 *
 */
public class Upgrade {
	static Random rand = new Random();
	private static class Node{
		int key,priority,sz;
		Node left,right;
		boolean reverse = false;
		Node(int key){
			this.key = key;
			this.priority = rand.nextInt();
			this.left = null;
			this.right = null;
			this.sz = 1;
		}
		public String toString() {
        	return String.valueOf(key);
        }
	}
	private static class DNode {
        Node left;
        Node right;
        
        DNode() {
            left = null;
            right = null;
        }
        
        public String toString() {
            return "L:" + left + " R:" + right;
        }
    }
	private Node root;
	public int sz(Node node){
		if(node == null) return 0;
		return node.sz;
	}
	public void updateSz(Node node){
		if(node != null)
			node.sz = 1 + sz(node.left) + sz(node.right);
	}
	public DNode split(Node node,int key){
		DNode pair = new DNode();
		if(node == null) {
			return pair;
		}
		if(sz(node.left) >= key){
			pair = split(node.left, key);
			node.left = pair.right;
			updateSz(node);
			pair.right = node;
		}else{
			pair = split(node.right, key - sz(node.left) - 1);
			node.right = pair.left;
			updateSz(node);
			pair.left = node;
		}
		return pair;
	}
	private void reverse(Node node){
		if(node.reverse){
			Node temp = node.left;
			node.left =  node.right;
			node.right = temp;
			node.reverse = false;
			if(node.left != null)
				node.left.reverse = !node.left.reverse;
			if(node.right != null)
				node.right.reverse = !node.right.reverse;
		}
	}
	public Node merge(Node leftTreap, Node rightTreap){
		if(leftTreap == null) return rightTreap;
		if(rightTreap == null) return leftTreap;
		//Reverse Operations
		reverse(leftTreap);
		reverse(rightTreap);
		if(leftTreap.priority > rightTreap.priority){
			leftTreap.right = merge(leftTreap.right, rightTreap);
			updateSz(leftTreap);
			return leftTreap;
		}else{
			rightTreap.left = merge(leftTreap, rightTreap.left);
			updateSz(rightTreap);
			return rightTreap;
		}
	}
	public Node reverseQuery(Node node, int from , int to){
		DNode pair1 = split(root, from);
		DNode pair2 = split(pair1.right, to - from + 1);
		pair2.left.reverse = true;
		return merge(pair1.left, merge(pair2.left, pair2.right));
	}
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int q = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		
	}
}
