package com.hjc.java_common_tools;

/**
 * <pre>
 * jstack输出的结果是：
 * 
 * "Thread-0" prio=6 tid=0x0000000006735800 nid=0x1be0 waiting on condition [0x0000000006faf000]
 *    java.lang.Thread.State: TIMED_WAITING (sleeping)
 *         at java.lang.Thread.sleep(Native Method)
 *         at com.hjc.java_common_tools.ThreadB_3.run(ThreadStatus_jstack_3.java:58)
 *         - locked <0x00000000eaceffd0> (a com.hjc.java_common_tools.ThreadB_3)
 *         
 * "main" prio=6 tid=0x00000000004db800 nid=0xed0 in Object.wait() [0x00000000026cf000]
 *    java.lang.Thread.State: BLOCKED (on object monitor)
 *         at java.lang.Object.wait(Native Method)
 *         - waiting on <0x00000000eaceffd0> (a com.hjc.java_common_tools.ThreadB_3)
 *         at java.lang.Object.wait(Object.java:485)
 *         at com.hjc.java_common_tools.ThreadStatus_jstack_3.main(ThreadStatus_jstack_3.java:37)
 *         - locked <0x00000000eaceffd0> (a com.hjc.java_common_tools.ThreadB_3)
 * </pre>
 * 
 * 可以看到：当主线程执行b.wait()之后，就进入了WAITING状态，但是新线程执行notifyAll()之后，
 * 有一个瞬间主线程回到了RUNNABLE状态，但是好景不长，由于这个时候新线程还没有释放锁，所以主线程立刻进入了BLOCKED状态 。
 * 
 * <h2>当在对象上调用wait()方法时，执行该代码的线程立即放弃它在对象上的锁。然而调用notify()时，并不意味着这时线程会放弃其锁。
 * 如果线程仍然在完成同步代码，则线程在移出之前不会放弃锁。因此，只要调用notify()并不意味着这时该锁被释放</h2>
 * 
 * @author hejianchao
 * 
 */
public class ThreadStatus_jstack_3 {
	public static void main(String[] args) {
		ThreadB_3 b = new ThreadB_3(); // ThreadB status: new

		b.start(); // ThreadB status: runnable

		synchronized (b) {
			try {
				System.out.println("wait b to cal 。。。");
				b.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("b result is：" + b.total);
		}
	}
}

class ThreadB_3 extends Thread {
	int total;

	public void run() {
		synchronized (this) {
			for (int i = 0; i < 101; i++) {
				total += i;
			}

			notifyAll();
			try {
				System.out.println("子线程休息下。。");
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
