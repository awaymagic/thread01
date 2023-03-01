package cn.away.base.abc;

import java.util.concurrent.ExecutionException;

/**
 * 类说明：守护线程的使用
 */
public class DaemonThread {
	private static class UseThread extends Thread{
		@Override
		public void run() {
			try {
				while (true) {
					System.out.println(Thread.currentThread().getName() 
							+ " I am extends Thread.");
				}
				// System.out.println(Thread.currentThread().getName()
				// 		+ " interrupt flag is " + isInterrupted());
			} finally {
				// 守护线程中finally不一定起作用
				System.out.println(" .............finally");
			}
		}
	}

	static{
		UseThread useThread = new UseThread();
		useThread.setDaemon(true);
		useThread.start();
	}

	/**
	 * JVM 判断全部为守护线程时会终止
	 * 当有至少一个不是守护线程时 不会终止 变成守护进程
	 */
	public static void main(String[] args) 
			throws InterruptedException, ExecutionException {
		UseThread useThread = new UseThread();
		// 标识当前线程会变成守护线程
		useThread.setDaemon(true);
		useThread.start();
		Thread.sleep(2000);
		// useThread.interrupt();
	}
}
