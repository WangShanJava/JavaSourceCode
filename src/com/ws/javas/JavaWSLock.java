package com.ws.javas;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class JavaWSLock {
	private ArrayList<Integer> arrayList = new ArrayList<Integer>();
	private Lock lock = new ReentrantLock();    //注意这个地方

//	public static void main(String[] args) {
//		final JavaWSLock test = new JavaWSLock();
//
//		new Thread() {
//			public void run() {
//				test.insert(Thread.currentThread());
//			};
//		}.start();
//
//		new Thread() {
//			public void run() {
//				test.insert(Thread.currentThread());
//			};
//		}.start();
//	}

//	public void insert(Thread thread) {
//		//Lock lock = new ReentrantLock(); // 注意这个地方
//		lock.lock();
//		try {
//			System.out.println(thread.getName() + "得到了锁");
//			for (int i = 0; i < 5; i++) {
//				arrayList.add(i);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		} finally {
//			System.out.println(thread.getName() + "释放了锁");
//			lock.unlock();
//		}
//	}
	
	public void insert(Thread thread) {
		if (lock.tryLock()) {
			try {
				System.out.println(thread.getName() + "得到了锁");
				for (int i = 0; i < 5; i++) {
					arrayList.add(i);
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				System.out.println(thread.getName() + "释放了锁");
				lock.unlock();
			}
		} else {
			System.out.println(thread.getName() + "获取锁失败");
		}
	}
	
	//多个线程要同时进行读操作
	/**
	 * 1）Lock是一个接口，而synchronized是Java中的关键字，synchronized是内置的语言实现；
　　	 * 2）synchronized在发生异常时，会自动释放线程占有的锁，因此不会导致死锁现象发生；而Lock在发生异常时，如果没有主动通过unLock()去释放锁，则很可能造成死锁现象，因此使用Lock时需要在finally块中释放锁；
　　	 * 3）Lock可以让等待锁的线程响应中断，而synchronized却不行，使用synchronized时，等待的线程会一直等待下去，不能够响应中断；
　　	 * 4）通过Lock可以知道有没有成功获取锁，而synchronized却无法办到。
　　	 * 5）Lock可以提高多个线程进行读操作的效率。
　　	 * 在性能上来说，如果竞争资源不激烈，两者的性能是差不多的，而当竞争资源非常激烈时（即有大量线程同时竞争），此时Lock的性能要远远优于synchronized。所以说，在具体使用时要根据适当情况选择。
	 *
	 * 1.可重入锁
	 * 如果锁具备可重入性，则称作为可重入锁。像synchronized和ReentrantLock都是可重入锁，可重入性在我看来实际上表明了锁的分配机制：基于线程的分配，而不是基于方法调用的分配。
	 * 举个简单的例子，当一个线程执行到某个synchronized方法时，比如说method1，而在method1中会调用另外一个synchronized方法method2，此时线程不必重新去申请锁，而是可以直接执行方法method2。
	 *
	 * 2.可中断锁
	 * 可中断锁：顾名思义，就是可以相应中断的锁。
　　  * 在Java中，synchronized就不是可中断锁，而Lock是可中断锁。
　　  * 如果某一线程A正在执行锁中的代码，另一线程B正在等待获取该锁，可能由于等待时间过长，线程B不想等待了，想先处理其他事情，我们可以让它中断自己或者在别的线程中中断它，这种就是可中断锁。
　　  * 在前面演示lockInterruptibly()的用法时已经体现了Lock的可中断性。
	 *
	 * 3.公平锁
	 * 公平锁即尽量以请求锁的顺序来获取锁。比如同是有多个线程在等待一个锁，当这个锁被释放时，等待时间最久的线程（最先请求的线程）会获得该所，这种就是公平锁。
　　  * 非公平锁即无法保证锁的获取是按照请求锁的顺序进行的。这样就可能导致某个或者一些线程永远获取不到锁。
　　  * 在Java中，synchronized就是非公平锁，它无法保证等待的线程获取锁的顺序。
　　  * 而对于ReentrantLock和ReentrantReadWriteLock，它默认情况下是非公平锁，但是可以设置为公平锁。
	 *
	 * 4.读写锁
　　  *读写锁将对一个资源（比如文件）的访问分成了2个锁，一个读锁和一个写锁。
　 　 *正因为有了读写锁，才使得多个线程之间的读操作不会发生冲突。
　　  *ReadWriteLock就是读写锁，它是一个接口，ReentrantReadWriteLock实现了这个接口。
　　  *可以通过readLock()获取读锁，通过writeLock()获取写锁。
	 *
	 *
	 *
	 *
	 */
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

	public static void main(String[] args) {
		final JavaWSLock test = new JavaWSLock();

		new Thread() {
			public void run() {
				test.get(Thread.currentThread());
			};
		}.start();

		new Thread() {
			public void run() {
				test.get(Thread.currentThread());
			};
		}.start();

	}

//	public synchronized void get(Thread thread) {
//		long start = System.currentTimeMillis();
//		while (System.currentTimeMillis() - start <= 1) {
//			System.out.println(thread.getName() + "正在进行读操作");
//		}
//		System.out.println(thread.getName() + "读操作完毕");
//	}
	
	public void get(Thread thread) {
		rwl.readLock().lock();
		try {
			long start = System.currentTimeMillis();

			while (System.currentTimeMillis() - start <= 1) {
				System.out.println(thread.getName() + "正在进行读操作");
			}
			System.out.println(thread.getName() + "读操作完毕");
		} finally {
			rwl.readLock().unlock();
		}
	}
}
