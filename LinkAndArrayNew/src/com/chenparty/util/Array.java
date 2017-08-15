package com.chenparty.util;

public class Array<T extends Object> {

	// �����ʼ���飬��ʼ��
	private T[] arr;

	// ���ýű�
	private int floot = 0;

	// ���췽��
	public Array() {
		arr = (T[]) new Object[10];
	}

	// ���췽��,ָ����ʼ������
	public Array(int len) {
		if (len > 0) {
			arr = (T[]) new Object[len];
		} else {
			arr = (T[]) new Object[10];
		}
	}

	// add������ݽ�ȥ,���Ҷ�̬����
	public boolean add(T num) {
		if (arr.length <= floot) {
			T[] newArr = (T[]) new Object[(int) (arr.length * 1.5)];
			System.arraycopy(arr, 0, newArr, 0, arr.length);
			arr = newArr;
		}
		arr[floot++] = num;
		return true;
	}

	// ��������
	public Object[] getArr() {
		// ȥ��nullֵ
		this.deleteNull();
		return this.arr;
	}

	// ��������
	public T[] sort(boolean b) {
		// ȥ��nullֵ
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

	// ɾ��null����
	private void deleteNull() {
		for (int i = 0; i < this.arr.length; i++) {
			if (arr[i] == null) {
				this.remove(i);
				i--;
			}
		}
	}

	// ɾ������
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

	// ���鷴ת
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

	// ���������޸�����
	public void set(int index, T val) {
		this.arr[index] = val;
	}

	// ����������ȡ����
	public T get(int index) {
		return this.arr[index];
	}

	// ��ȡ����
	public int size() {
		// TODO Auto-generated method stub
		this.deleteNull();
		return this.arr.length;
	}

}
