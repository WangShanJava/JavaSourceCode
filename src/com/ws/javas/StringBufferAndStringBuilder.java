package com.ws.javas;

public class StringBufferAndStringBuilder {
	public static void main(String[] args) {
		String string = new String();
		//线程安全
		StringBuffer stringBuffer = new StringBuffer();
		//非线程安全
		StringBuilder stringBuilder = new StringBuilder();
	}
}
