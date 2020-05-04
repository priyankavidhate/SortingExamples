
/*Given K sorted arrays arr, of size N each, merge them into a new array res, such that res is a sorted array.*/

import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class MergeKSortedArrays {
	static class Node {
		int value;
		int arrayIndex;
		int elementIndex;

		public Node(int value, int arrayIndex, int elementIndex) {
			this.value = value;
			this.arrayIndex = arrayIndex;
			this.elementIndex = elementIndex;
		}
	}

	static int[] mergeArrays(int[][] arr) {
		Comparator<Node> minComparator = new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {
				return n1.value - n2.value;
			}
		};

		Comparator<Node> maxComparator = new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {
				return n2.value - n1.value;
			}
		};
		/*
		 * Write your code here.
		 */
		int k = arr.length;
		int n = arr[0].length;
		int totalNumbers = k * n;
		int[] resultant = new int[k * n];
		int resultantIndex = 0;

		boolean ascending = true;
		// Check if elements are in increasing or decreasing order
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < n - 1; j++) {
				if (arr[i][j] > arr[i][j + 1])
					ascending = false;
			}
		}

		// System.out.println(ascending);
		PriorityQueue<Node> minHeap;
		if (ascending) {
			minHeap = new PriorityQueue<Node>(k, minComparator);
		} else {
			minHeap = new PriorityQueue<Node>(k, maxComparator);
		}

		// Add initial elements to the heap
		for (int i = 0; i < k; i++) {
			Node node = new Node(arr[i][0], i, 0);
			minHeap.add(node);
		}
		// 1. For every min element from heap
		// 2. Extract it out and dump it into resultant
		// 3. But also get the next element from the array it came from
		while (resultantIndex < totalNumbers) {
			Node minNode = minHeap.remove();
			resultant[resultantIndex] = minNode.value;
			if (minNode.elementIndex < arr[minNode.arrayIndex].length - 1) {
				int elementIndex = minNode.elementIndex + 1;
				int value = arr[minNode.arrayIndex][elementIndex];
				Node node = new Node(value, minNode.arrayIndex, elementIndex);
				minHeap.add(node);
			}
			resultantIndex++;
		}
		return resultant;
	}
}