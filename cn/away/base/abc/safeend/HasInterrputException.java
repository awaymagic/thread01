package cn.away.base.abc.safeend;

/**
 * 类说明：阻塞方法中抛出InterruptedException异常后，如果需要继续中断，需要手动再中断一次
 *
 * 线程通过方法 isInterrupted()来进行判断是否被中断，
 * 也可以调用静态方法 Thread.interrupted()来进行判断当前线程是否被中断，不过 Thread.interrupted() 会同时将中断标识位改写为 false
 */
public class HasInterrputException {
	
	private static class UseThread extends Thread{
		
		public UseThread(String name) {
			super(name);
		}
		
		@Override
		public void run() {
			// isInterrupted 进行判断当前线程是否被中断，同时将中断标识位改写为 false
			while(!isInterrupted()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName()
							+ " in InterruptedException interrupt flag is "
							+ isInterrupted());
					// 手动再中断一次
					interrupt();
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()
						+ " I am extends Thread.");
			}
			//while 循环结束后才会打印本句
			System.out.println(Thread.currentThread().getName()
					+ " interrupt flag is " + isInterrupted());
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread endThread = new UseThread("HasInterrputEx");
		endThread.start();
		Thread.sleep(500);
		endThread.interrupt();
	}

}
