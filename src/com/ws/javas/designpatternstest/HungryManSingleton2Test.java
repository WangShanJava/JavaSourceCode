package com.ws.javas.designpatternstest;

import com.ws.javas.designpatterns.HungryManSingleton2;

public class HungryManSingleton2Test {
	public static void main(String[] args) {
		HungryManSingleton2 singleton = HungryManSingleton2.INSTANCE;
		singleton.doSomeThing();//output:枚举方法实现单例

	}
}
