package com.example.iterator;

/**
 * 翻转迭代器
 * 
 * @param <T>
 * 
 */
public interface Reverse<T> {

	T next();

	boolean hasNext();

	T remove();
}
