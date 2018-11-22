package com.ws.javas.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableThread {
	public static void main(String[] atgs) {
		// CallableThread rt = new CallableThread();
		FutureTask<Integer> task = new FutureTask<Integer>((Callable<Integer>) () -> {
			int i = 0;
			for (; i < 100; i++) {
				System.out.println(Thread.currentThread().getName() + " 1循环变量i的值：" + i);
			}
			return i;
		});
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + " 2循环变量i的值：" + i);
			if (i == 20) {
				new Thread(task, "有返回值的线程").start();
			}
		}
		try {
			System.out.println("子线程的返回值：" + task.get());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
