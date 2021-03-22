package com.example.iterator;

public class Test {

	public static void main(String[] args) {
		ReverseCollection<String> coll1 = new ReverseCollection<>(String.class, 30);
		coll1.add("1");
		coll1.add("2");
		coll1.add("3");
		System.out.println(coll1);
	}
}
