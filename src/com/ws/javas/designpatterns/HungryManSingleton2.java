package com.ws.javas.designpatterns;
/**
 * 饿汉式（枚举方式）
 * @author wangshan
 * 实现单例模式的最佳方法。 
 * 它更简洁，自动支持序列化机制，绝对防止多次实例化 
 */
public enum HungryManSingleton2 {
	 //定义一个枚举的元素，它就是 Singleton 的一个实例
    INSTANCE;  
    
    public void doSomeThing() {  
	     System.out.println("枚举方法实现单例");
    }  
}
