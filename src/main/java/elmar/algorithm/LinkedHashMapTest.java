package elmar.algorithm;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class LinkedHashMapTest {
	static class A{
		@Override
		public int hashCode() {
			return 1;
		}
	}
	public static void main(String[] args) {
		HashMap<A, String> a = new LinkedHashMap<A, String>();
		a.put(new A(), "2");
		a.put(new A(), "2"); 
	}
}
