package com.chenparty.util;

public class Array<T extends Object> {

	// 设置最开始数组，初始化
	private T[] arr;

	// 设置脚标
	private int floot = 0;

	// 构造方法
	public Array() {
		arr = (T[]) new Object[10];
	}

	// 构造方法,指定初始化长度
	public Array(int len) {
		if (len > 0) {
			arr = (T[]) new Object[len];
		} else {
			arr = (T[]) new Object[10];
		}
	}

	// add添加数据进去,并且动态扩容
	public boolean add(T num) {
		if (arr.length <= floot) {
			T[] newArr = (T[]) new Object[(int) (arr.length * 1.5)];
			System.arraycopy(arr, 0, newArr, 0, arr.length);
			arr = newArr;
		}
		arr[floot++] = num;
		return true;
	}

	// 返回数组
	public Object[] getArr() {
		// 去除null值
		this.deleteNull();
		return this.arr;
	}

	// 数组排序
	public T[] sort(boolean b) {
		// 去除null值
		this.deleteNull();
		if (arr[0] instanceof Integer) {
			for (int i = 0; i < this.arr.length; i++) {
				for (int j = i + 1; j < this.arr.length; j++) {
					if (b ? (Integer) this.arr[i] > (Integer) this.arr[j]
							: (Integer) this.arr[i] < (Integer) this.arr[j]) {
						Integer temp = (Integer) this.arr[i];
						this.arr[i] = this.arr[j];
						this.arr[j] = (T) temp;
					}
				}
			}
		}
		return this.arr;
	}

	// 删除null控制
	private void deleteNull() {
		for (int i = 0; i < this.arr.length; i++) {
			if (arr[i] == null) {
				this.remove(i);
				i--;
			}
		}
	}

	// 删除数据
	public T[] remove(int index) {
		if (index > this.arr.length - 1) {
			return this.arr;
		}
		T arr1[] = (T[]) new Object[this.arr.length - 1];
		int arr1Index = 0;
		for (int i = 0; i < this.arr.length; i++) {
			if (index != i) {
				arr1[arr1Index++] = (T) this.arr[i];
			}
		}
		this.arr = arr1;
		return this.arr;
	}

	// 数组反转
	public T[] reverse() {
		this.deleteNull();
		int center = (this.arr.length) / 2;
		int head = 0;
		int tail = this.arr.length - 1;
		for (int i = 0; i < center; i++) {
			Object temp = this.arr[head];
			this.arr[head] = this.arr[tail];
			this.arr[tail] = (T) temp;
			head++;
			tail--;
		}
		return this.arr;
	}

	// 根据索引修改数据
	public void set(int index, T val) {
		this.arr[index] = val;
	}

	// 根据索引获取数据
	public T get(int index) {
		return this.arr[index];
	}

	// 获取长度
	public int size() {
		// TODO Auto-generated method stub
		this.deleteNull();
		return this.arr.length;
	}

}
