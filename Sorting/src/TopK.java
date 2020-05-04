/*You are given an array of integers, arr, of size n,
   which is analogous to a continuous stream of integers input. 
  Your task is to find K largest elements from a given stream of numbers.
  Example One
Input: arr = [1, 5, 4, 4, 2]; K = 2
Output: [4, 5]

Example Two
Input: arr = [1, 5, 1, 5, 1]; K = 3
Output: [5, 1] */
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class TopK {
	static class storeArrayInd implements Comparator<storeArrayInd> {
		int value;

		public storeArrayInd(int value) {
			this.value = value;
		}

		@Override
		public int compare(storeArrayInd n1, storeArrayInd n2) {
			return n1.value - n2.value;
		}

	}

	static int[] topK(int[] arr, int k) {
		int[] outputArr = new int[k];
		int j = 0;
		Set<Integer> set = new HashSet<Integer>();
		Comparator<storeArrayInd> maxComparator = new Comparator<storeArrayInd>() {
			@Override
			public int compare(storeArrayInd n1, storeArrayInd n2) {
				return n2.value - n1.value;
			}
		};

		PriorityQueue<storeArrayInd> queue = new PriorityQueue<storeArrayInd>(maxComparator);
		for (int i = 0; i < arr.length; i++) {
			storeArrayInd node = new storeArrayInd(arr[i]);
			if (!set.contains(arr[i])) {
				queue.add(node);
				set.add(arr[i]);
			}
		}

		if (set.size() < k) {
			int a = 0;

			int[] myArray = new int[set.size()];
			for (Integer x : set) {

				myArray[a] = x;
				a++;
			}
			return myArray;
		}

		while (!queue.isEmpty() && k > 0) {
			storeArrayInd s = queue.remove();
			outputArr[j] = s.value;
			k--;
			j++;

		}
		return outputArr;
	}
}
