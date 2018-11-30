package com.ws.javas.designpatterns;
/**
 * 懒汉式
 * @author wangshan
 *
 */
public class SlackerSingleton {
	/**
	 * 懒汉式（非线程安全 ）
	 */
//	private static SlackerSingleton uniqueInstance;  
//    private SlackerSingleton (){
//    }   
//    //没有加入synchronized关键字的版本是线程不安全的
//    public static SlackerSingleton getInstance() {
//        //判断当前单例是否已经存在，若存在则返回，不存在则再建立单例
//	      if (uniqueInstance == null) {  
//	          uniqueInstance = new SlackerSingleton();  
//	      }  
//	      return uniqueInstance;  
//    }
	/**
	 * 懒汉式（synchronized关键字线程安全版本 ）
	 */
//    public static synchronized SlackerSingleton getInstance() {  
//	      if (instance == null) {  
//	          uniqueInstance = new SlackerSingleton();  
//	      }  
//	      return uniqueInstance;  
//    }
    /**
     * 懒汉式(双重检查加锁版本)
     */
  //volatile保证，当uniqueInstance变量被初始化成Singleton实例时，多个线程可以正确处理uniqueInstance变量
//    private volatile static SlackerSingleton uniqueInstance;
//    private SlackerSingleton() {
//    }
//    public static SlackerSingleton getInstance() {
//       //检查实例，如果不存在，就进入同步代码块
//        if (uniqueInstance == null) {
//            //只有第一次才彻底执行这里的代码
//            synchronized(SlackerSingleton.class) {
//               //进入同步代码块后，再检查一次，如果仍是null，才创建实例
//                if (uniqueInstance == null) {
//                    uniqueInstance = new SlackerSingleton();
//                }
//            }
//        }
//        return uniqueInstance;
//    }
    /**
     * 懒汉式（登记式/静态内部类方式）
     * @author wangshan
     *
     */
	private static class SingletonHolder {
		private static final SlackerSingleton INSTANCE = new SlackerSingleton();
	}
	private SlackerSingleton (){}
	public static final SlackerSingleton getInstance() {
		return SingletonHolder.INSTANCE;
	}
    
}
