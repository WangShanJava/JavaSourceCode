package com.ws.javas.thread;

public class FirstThread extends Thread {
	private int i;
	
	@Override
	public void run() {
		for(i=0; i<100; i++) {
			System.out.println(getName() + " " +i);
		}
	}
	
	public static void main(String[] args){
        FirstThread xx = new FirstThread();
        xx.setName("xx");
        xx.start();
        for(int i=0;i<100;i++){
            System.out.println(Thread.currentThread().getName()+"------ " +i);
            if(i==20){
                new FirstThread().start();
                new FirstThread().start();
                
            }
        }
    }
}
