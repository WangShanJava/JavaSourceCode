package com.ws.javas.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class JavaWSThread{
	
	/**
	 * 16.6 线程通信
     * 
     * 包括
     * 
     * 16.6.1传统的线程通信,用this或者是被锁的对象来调用.
     * 
     * wait(),notify(), notifyAll()
     * 
     * 16.6.2使用Condition控制线程通信,调用Lock对象的newCondition()方法即可.
     * 
     * await(),signal(),signalAll()
     * 
     * 16.6.3 使用阻塞队列(BlockingQueue)控制线程通信
     * 
     * BlockingQueue是Queue的子接口,特征为:当生产者线程试图向BlockingQueue中放入元素时,如果该对垒已满,
     * 
     * 则该线程被阻塞;当消费者线程试图从BlockingQueue中取出元素时,如果该队列已空,则该线程被阻塞.
     * 
     * put(E e)
     * 
     * take(E e)
	 */
	
	
	//线程池
	//线程池的创建可以通过创建 ThreadPoolExecutor 对象或者调用 Executors 的工厂方法来创建线程池。
//	ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
//			corePoolSize, 核心线程池大小， 当新的任务到线程池后，线程池会创建新的线程（即使有空闲线程），直到核心线程池已满。
//			maximumPoolSize, 最大线程池大小，顾名思义，线程池能创建的线程的最大数目
//			keepAliveTime, 程池的工作线程空闲后，保持存活的时间
//			unit,  时间单位
//			workQueue, 用来储存等待执行任务的队列
//			threadFactory, 线程工厂
//			handler);当队列和线程池都满了时拒绝任务的策略
	/**
	 * 重要参数的说明：
     * corePoolSize 和 maximumPoolSize 
     * 默认情况下线程中的线程初始时为 0， 当有新的任务到来时才会创建新线程，当线程数目到达 corePoolSize 的数量时，新的任务会被缓存到 workQueue 队列中。如果不断有新的任务到来，队列也满了的话，线程池会再新建线程直到总的线程数目达到 maximumPoolSize。如果还有新的任务到来，则要根据 handler 对新的任务进行相应拒绝处理。
     * 
     * BlockingQueue<Runnable> 
     * 一个阻塞队列，用来存储等待执行的任务，常用的有如下几种：
     * ArrayBlockingQueue：是一个基于数组结构的有界阻塞队列，此队列按 FIFO（先进先出）原则对元素进行排序。
     * LinkedBlockingQueue：一个基于链表结构的阻塞队列，此队列按FIFO （先进先出） 排序元素，吞吐量通常要高于ArrayBlockingQueue。静态工厂方法Executors.newFixedThreadPool()使用了这个队列。
     * SynchronousQueue：一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue，静态工厂方法Executors.newCachedThreadPool使用了这个队列。
     * PriorityBlockingQueue：一个具有优先级得无限阻塞队列。
     * RejectedExecutionHandler 
     * 当队列和线程池都满了，说明线程池处于饱和状态，那么必须采取一种策略处理提交的新任务。有下面四种JDK提供的策略：
     * AbortPolicy，表示无法处理新任务时抛出异常, 默认策略
     * CallerRunsPolicy：用调用者所在线程来运行任务。
     * DiscardOldestPolicy： 该策略将丢弃最老的一个请求，也就是即将被执行的任务，并尝试再次提交当前任务。
     * DiscardPolicy：不处理，丢弃掉 
     * 除了这些JDK提供的策略外，还可以自己实现 RejectedExecutionHandler 接口定义策略。
	 */
	public static void main(String[] args) {
		createThreadPools();
	}
	
	public static void createThreadPools() {
		//不建议的做法
		/*一个固定大小的线程池，可以用于已知并发压力的情况下，对线程数做限制。*/
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);
		/*一个单线程的线程池，可以用于需要保证顺序执行的场景，并且只有一个线程在执行。*/
		ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
		/*一个可以无限扩大的线程池，比较适合处理执行时间比较小的任务。*/
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		/*可以延时启动，定时启动的线程池，适用于需要多个后台线程执行周期任务的场景。*/
		ExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(3);
		/*一个拥有多个任务队列的线程池，可以减少连接数，创建当前可用cpu数量的线程来并行执行。*/
		/* newWorkStealingPool 是jdk1.8才有的，会根据所需的并行层次来动态创建和关闭线程，通过使用多个队列减少竞争，底层用的 ForkJoinPool来实现的。ForkJoinPool 的优势在于，
		 * 可以充分利用多cpu，多核cpu的优势，把一个任务拆分成多个“小任务”，把多个“小任务”放到多个处理器核心上并行执行；当多个“小任务”执行完成之后，再将这些执行结果合并起来即可。*/
		ExecutorService newWorkStealingPool = Executors.newWorkStealingPool();
		//使用 guava 开源框架的 ThreadFactoryBuilder 给线程池的线程设置名字
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-thread-ws-%d").build();
        
        ExecutorService pool = new ThreadPoolExecutor(4, 10, 0L, 
        		TimeUnit.MILLISECONDS,
        		new LinkedBlockingDeque<Runnable>(256), 
        		namedThreadFactory,
        		new ThreadPoolExecutor.AbortPolicy());
        
        pool.execute(() -> System.out.println(Thread.currentThread().getName()));
        pool.execute(() -> System.out.println(Thread.currentThread().getName()));
        pool.execute(() -> System.out.println(Thread.currentThread().getName()));
        pool.shutdown();

	}
	
	

}
