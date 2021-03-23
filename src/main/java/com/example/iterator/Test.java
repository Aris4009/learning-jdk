package com.example.iterator;

public class Test {

	public static void main(String[] args) {
		ReverseCollection<String> coll1 = new ReverseCollection<>(String.class, 100);
		coll1.add("1");
		coll1.add("2");
		coll1.add("3");
		coll1.add("4");
		coll1.add("5");
		coll1.add("6");
		coll1.add("7");

		System.out.println(coll1);
		coll1.remove();
		System.out.println(coll1);
		System.out.println(coll1.get(5));
		coll1.add("7");
		System.out.println(coll1);

		Reverse<String> reverse = coll1.reverse();
		while (reverse.hasNext()) {
			String a = reverse.next();
			System.out.println(reverse.remove());
		}
		System.out.println(coll1);

	}
}
