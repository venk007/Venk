package src.main.java.com.venk.core.util;

import java.util.concurrent.CountDownLatch;

public class StringVenk {

	private static final String SAYHELLO_COMMAND = "SayHello";
	private static final String SAYHELLO_USAGE = SAYHELLO_COMMAND;

	public static void main(String[] args) {

		String name = "Hello Venk";
		StringBuffer buf = new StringBuffer("StringBuffer");
		StringBuilder bld = new StringBuilder("StringBuilder");

		long startTime = System.currentTimeMillis();
		int threadNum = 1000;

		// 定义正在运行的线程数
		CountDownLatch runningThreadNum = new CountDownLatch(threadNum);
		System.out.println(Thread.currentThread().getName() + "-start");

		// 创建多个子线程
		for (int i = 0; i < threadNum; i++) {

			new ThreadTest(buf, runningThreadNum).start();
		}
		
		try{
			runningThreadNum.await();
			System.out.println(buf.toString());
			System.out.println(Thread.currentThread().getName()+"-end");
			long endTime = System.currentTimeMillis();
			System.out.println("runningTime: "+(endTime-startTime));
		} catch(InterruptedException e){
			e.printStackTrace();
		}

	}

}

class ThreadTest extends Thread{
	private StringBuffer bld;
	//子线程计数器
	private CountDownLatch runningThreadNum;
	public ThreadTest(StringBuffer bld, CountDownLatch runningThreadNum){
		super();
		this.bld = bld;
		this.runningThreadNum = runningThreadNum;
	}
	
	public void run(){
		System.out.println(Thread.currentThread().getName()+"-start");
		for(int i = 0; i < 10; i++){
			if(i == 5){
				try{
					Thread.sleep(200);
				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}
			bld.append(i);
		}
		System.out.println(Thread.currentThread().getName()+"-end");
		
		runningThreadNum.countDown();
	
	}
}
