package com.ws.javas.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

public class JavaWSArray {
	public static void main(String[] args) {
		/**
		 * ArrayList和LinkedList的大致区别如下:
		 * 1.ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。 
		 * 2.对于随机访问get和set，ArrayList觉得优于LinkedList，因为LinkedList要移动指针。 
		 * 3.对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList要移动数据。 
		 */
		ArrayList array = new ArrayList<>();
		
		LinkedList linkedList = new LinkedList<>();
		/**
		 * Vector的方法都是同步的(Synchronized),是线程安全的(thread-safe)，而ArrayList的方法不是，
		 * 由于线程的同步必然要影响性能，因此,ArrayList的性能比Vector好。 
         * 当Vector或ArrayList中的元素超过它的初始大小时,Vector会将它的容量翻倍,
         * 而ArrayList只增加50%的大小，这样,ArrayList就有利于节约内存空间。
		 */
		Vector vector = new Vector<>();
		
	}
}
