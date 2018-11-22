package com.ws.javas.thread;

public class RunnableThread implements Runnable {
	private int i;

	@Override
	public void run() {
		for (; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + "wwwwwwww " + i);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName()+" "+i);
			if (i == 20) {
				RunnableThread st = new RunnableThread();
				new Thread(st, "新线程1").start();
				new Thread(st, "新线程2").start();
			}
		}
	}
}
