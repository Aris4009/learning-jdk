package com.example.iterator;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ReverseCollection<T> implements Reversable<T> {

	private transient T[] arr;

	private int size;

	private int cursor;

	private int length;

	private static final int INIT_SIZE = 16;

	private static final double FACTOR = 0.75;

	private static final int _2X = 2;

	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	protected ReverseCollection(Class<T> clazz) {
		this.size = INIT_SIZE;
		this.cursor = this.size;
		this.clazz = clazz;
		arr = (T[]) Array.newInstance(clazz, this.size);
	}

	@SuppressWarnings("unchecked")
	protected ReverseCollection(Class<T> clazz, int size) {
		if (size < 1) {
			size = INIT_SIZE;
		}
		this.size = size;
		this.cursor = this.size;
		this.clazz = clazz;
		arr = (T[]) Array.newInstance(clazz, this.size);
	}

	public boolean add(T t) {
		if (t == null) {
			throw new NullPointerException();
		}
		if (this.size - Integer.MAX_VALUE > 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		if (this.cursor >= 0) {
			arr[this.cursor - 1] = t;
			this.cursor = this.cursor - 1;
		} else {
			if (Integer.MAX_VALUE / 2 < this.size) {
				this.size = Integer.MAX_VALUE;
			} else {
				this.size = this.size * 2;
			}
			System.arraycopy(this.arr, this.cursor - 1, Array.newInstance(this.clazz, this.size), this.cursor - 1,
					this.size);

		}
		this.length = this.length + 1;
		return true;
	}

	public T remove() {
		if (isEmpty()) {
			throw new NullPointerException();
		}
		T a = Arrays.copyOfRange(arr, this.cursor - 1, this.cursor)[0];
		this.length = this.length - 1;
		arr[this.cursor - 1] = null;
		this.cursor = this.cursor + 1;
		return a;
	}

	public boolean isEmpty() {
		return this.length == 0;
	}

	public int getLength() {
		return length;
	}

	@Override
	public Reverse<T> reverse() {
		return null;
	}

	public class Re implements Reverse<T> {

		@Override
		public T pre() {
			T a = arr[cursor - 1];
			cursor = cursor - 1;
			return a;
		}

		@Override
		public boolean hasPre() {
			return cursor > 0;
		}

		@Override
		public T remove() {
			return ReverseCollection.this.remove();
		}
	}

	@Override
	public String toString() {
		return "ReverseCollection{" + "arr=" + Arrays.toString(arr) + ", size=" + size + ", cursor=" + cursor
				+ ", length=" + length + '}';
	}
}
