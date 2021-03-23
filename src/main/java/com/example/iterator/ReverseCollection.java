package com.example.iterator;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ReverseCollection<T> {

	private transient T[] arr;

	private transient T[] list;

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
		this.cursor = this.size - 1;
		this.clazz = clazz;
		arr = (T[]) Array.newInstance(clazz, this.size);
	}

	@SuppressWarnings("unchecked")
	protected ReverseCollection(Class<T> clazz, int size) {
		if (size < 1) {
			size = INIT_SIZE;
		}
		this.size = size;
		this.cursor = this.size - 1;
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
			arr[this.cursor] = t;
			this.cursor = this.cursor - 1;
		} else {
			if (Integer.MAX_VALUE / this.size < 2) {
				this.size = Integer.MAX_VALUE;
			} else {
				T[] n = (T[]) Array.newInstance(clazz, this.size * 2);
				int nCursor = this.size;
				System.arraycopy(arr, 0, n, nCursor, this.size);
				arr = n;
				this.size = n.length;
				arr[nCursor - 1] = t;
				this.cursor = nCursor - 2;
			}
		}
		this.length = this.length + 1;
		this.list = Arrays.copyOfRange(this.arr, this.cursor + 1, this.size);
		return true;
	}

	public T get(int index) {
		if (index < 0 || index >= this.list.length) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return this.list[index];
	}

	public T remove() {
		if (isEmpty()) {
			throw new NullPointerException();
		}
		T a = Arrays.copyOfRange(arr, this.cursor + 1, this.cursor + 2)[0];
		this.length = this.length - 1;
		arr[this.cursor + 1] = null;
		this.cursor = this.cursor + 1;
		this.list = Arrays.copyOfRange(this.arr, this.cursor + 1, this.size);
		return a;
	}

	public boolean isEmpty() {
		return this.length == 0;
	}

	public int getLength() {
		return length;
	}

	public Reverse<T> reverse() {
		return new Re();
	}

	private class Re implements Reverse<T> {

		private int cur;

		public Re() {
			this.cur = 0;
		}

		@Override
		public T next() {
			T a = ReverseCollection.this.list[this.cur];
			this.cur = this.cur + 1;
			return a;
		}

		@Override
		public boolean hasNext() {
			return cur < ReverseCollection.this.list.length;
		}

		@Override
		public T remove() {
			this.cur = this.cur - 1;
			return ReverseCollection.this.remove();
		}
	}

	@Override
	public String toString() {
		return "ReverseCollection{" + "arr=" + Arrays.toString(this.list) + ", size=" + size + ", cursor=" + cursor
				+ ", length=" + length + '}';
	}
}
