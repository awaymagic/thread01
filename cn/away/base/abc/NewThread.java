package cn.away.base.abc;

import cn.away.tools.SleepTools;

import java.util.concurrent.ExecutionException;

/**
 * 类说明：新启线程的方式
 */
public class NewThread {

	/** 扩展自 Thread 类 */
	private static class UseThread extends Thread{
		@Override
		public void run() {
			super.run();
			SleepTools.second(1);
			// do my work;
			System.out.println("I am extendec Thread");
		}
	}

	/** 实现Runnable接口 */
	private static class UseRunnable implements Runnable{
		@Override
		public void run() {
			// do my work;
			System.out.println("I am implements Runnable");
		}
	}

	public static void main(String[] args) 
			throws InterruptedException, ExecutionException {
		UseThread useThread = new UseThread();
		useThread.start();
		// start()方法不能重复调用，如果重复调用会抛出异常
		// useThread.start();

		UseRunnable useRunnable = new UseRunnable();
		new Thread(useRunnable).start();
		System.out.println("main end");
	}

}
