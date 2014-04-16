package com.hjc.java_common_tools;

/**
 * <pre>
 * jstack输出的结果是： 
 * 
 * "Thread-0" prio=6 tid=0x0000000006562800 nid=0x1fb0 waiting for monitor entry [0x0000000006ebf000]
 *    java.lang.Thread.State: BLOCKED (on object monitor)
 *         at com.hjc.java_common_tools.ThreadB.run(ThreadA.java:28)
 *         - waiting to lock <0x00000000eacefcf0> (a com.hjc.java_common_tools.ThreadB)
 * 
 * "main" prio=6 tid=0x000000000033b800 nid=0x1c08 waiting on condition [0x000000000251f000]
 *    java.lang.Thread.State: TIMED_WAITING (sleeping)
 *         at java.lang.Thread.sleep(Native Method)
 *         at com.hjc.java_common_tools.ThreadA.main(ThreadA.java:12)
 *         - locked <0x00000000eacefcf0> (a com.hjc.java_common_tools.ThreadB)
 * 
 * </pre>
 * 
 * 可以看到：主线程和新线程在同一个对象上锁定，主线程的方法里执行了Thread.sleep(60000)，因此进入了TIMED_WAITING状态，
 * 而新线程则进入BLOCKED状态。
 * 
 * @author hejianchao
 * 
 */
public class ThreadStatus_jstack_1 {
	public static void main(String[] args) {
		ThreadB b = new ThreadB(); // ThreadB status: new

		b.start(); // ThreadB status: runnable

		synchronized (b) {
			try {
				System.out.println("wait b to cal 。。。");
				Thread.sleep(60000);
				b.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("b result is：" + b.total);
		}
	}
}

class ThreadB extends Thread {
	int total;

	public void run() {
		synchronized (this) {
			for (int i = 0; i < 101; i++) {
				total += i;
			}

			notifyAll();
		}
	}
}
