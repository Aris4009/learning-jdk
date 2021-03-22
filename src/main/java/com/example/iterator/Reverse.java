package com.example.iterator;

/**
 * 翻转迭代器
 * 
 * @param <T>
 * 
 */
public interface Reverse<T> {

	T pre();

	boolean hasPre();

	T remove();
}
