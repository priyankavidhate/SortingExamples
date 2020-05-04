
/*You are given a string array named arr, of size N, containing KEYS and VALUES 
 separated by a space.Your task is to find, for each unique KEY, the number of 
 VALUES with that key and the VALUE with the highest lexicographical order 
 (also called alphabetical order OR dictionary order).
 If N = 5 and arr = [“key1 abcd”, “key2 zzz”, “key1 hello”,
“key3 world”, "key1 hello"]
Output Format:

There will be N lines of output, where ith line contains a string res[i], 
denoting value at index i of res.
Here, res is the result array returned by solution function.

For input N = 5 and arr = [“key1 abcd”, “key2 zzz”, “key1 hello”,

“key3 world”, "key1 hello"]

One possible output (you can return strings in any order):
[
    "key1:3,hello",
    "key2:1,zzz",
	"key3:1,world"
]

Sample Input 2:

arr = [
   “mark zuckerberg”,
   “tim cook”,
   “mark twain”
]

Sample Output 2:
One possible output (you can return strings in any order):
[
   "mark:2,zuckerberg",
   "tim:1,cook"
]
*/
import java.util.*;

public class LexicalOrder {
	static String[] solve(String[] arr) {
		Map<String, ArrayList> t = null;
		t = new TreeMap<String, ArrayList>();
		for (int i = 0; i < arr.length; i++) {
			ArrayList<String> l1 = new ArrayList<String>();
			ArrayList<String> l2 = new ArrayList<String>();
			String[] arrRes = arr[i].split(" ");
			if (t.containsKey(arrRes[0])) {
				l2 = t.get(arrRes[0]);
				l2.add(arrRes[1]);
				t.replace(arrRes[0], l2);
			} else {
				l1.add(arrRes[1]);
				t.put(arrRes[0], l1);
			}

		}
		int k = 0;
		String[] outputArr = new String[t.size()];

		for (Map.Entry<String, ArrayList> x : t.entrySet()) {
			ArrayList l = x.getValue();
			Collections.sort(l);
			String element = x.getKey() + ":" + l.size() + "," + l.get(l.size() - 1);
			outputArr[k] = element;
			k++;
		}
		return outputArr;

	}
}
