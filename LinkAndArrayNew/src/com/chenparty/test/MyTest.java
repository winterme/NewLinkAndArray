package com.chenparty.test;

import com.chenparty.util.Array;
import com.chenparty.util.Link;

public class MyTest {
	
	public static void main(String[] args) {
		Array<String> array = new Array<>();
		array.add("I");
		array.add("love");
		array.add("java");
		
		for (int i = 0; i < array.size(); i++) {
			String string = array.get(i);
			System.out.println(string);
		}
		
		Link<String> link = new Link<>();
		link.addEle("zzq");
		link.addEle("love");
		link.addEle("Îâ¼ÑÀû");
		link.addEle("1314");
		
		for (int i = 0; i < link.getSize(); i++) {
			String string = link.get(i);
			System.out.println(string);
		}
		
	}
	
}
