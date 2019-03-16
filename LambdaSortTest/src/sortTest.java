import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class sortTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		
		list.add("hello");
		list.add("world");
		list.add("apple");
		list.add("want");
		list.add("whatsup");
		list.add("kilo");
		list.add("billboard");
		
		Collections.sort(list, (a, b) -> (a.length() - b.length()));
		
		for (String s : list) {
			System.out.println(s);
		}
		
		List<Integer> list2 = new ArrayList<Integer>();
		
		list2.add(5);
		list2.add(3);
		list2.add(99);
		list2.add(1010);
		list2.add(23);
		list2.add(781);
		list2.add(254);
		
		Collections.sort(list2, (a, b) -> (a - b));
		
		for (int s : list2) {
			System.out.println(s);
		}
		
		int[] array = {5,3,99,25,1010,523,23,781,354};
		Arrays.sort(array, (int a, int b) -> (b-a));
	}

}
