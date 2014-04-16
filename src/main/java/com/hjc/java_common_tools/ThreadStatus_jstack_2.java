package com.hjc.java_common_tools;

/**
 * <pre>
 * jstack输出的结果是：
 * 
 * "Thread-0" prio=6 tid=0x0000000006640000 nid=0xdcc waiting on condition [0x0000000006ebf000]
 *    java.lang.Thread.State: TIMED_WAITING (sleeping)
 *         at java.lang.Thread.sleep(Native Method)
 *         at com.hjc.java_common_tools.ThreadB_2.run(ThreadStatus_jstack_2.java:40)
 *         - locked <0x00000000eaceffd8> (a com.hjc.java_common_tools.ThreadB_2)
 * 
 * "main" prio=6 tid=0x000000000032b800 nid=0x10ec in Object.wait() [0x000000000251f000]
 *    java.lang.Thread.State: WAITING (on object monitor)
 *         at java.lang.Object.wait(Native Method)
 *         - waiting on <0x00000000eaceffd8> (a com.hjc.java_common_tools.ThreadB_2)
 *         at java.lang.Object.wait(Object.java:485)
 *         at com.hjc.java_common_tools.ThreadStatus_jstack_2.main(ThreadStatus_jstack_2.java:24)
 *         - locked <0x00000000eaceffd8> (a com.hjc.java_common_tools.ThreadB_2)
 * </pre>
 * 
 * 可以看到：2个线程还是在同一个对象上同步，但这次主线程立刻执行了b.wait()方法，因此释放了对象b上的锁，自己进入了WAITING状态。
 * 接下来新线程得到了对象b上的锁，所以没有进入阻塞状态，紧接着执行Thread.sleep(60000)方法，进入了TIMED_WAITING状态 。
 * 
 * @author hejianchao
 * 
 */
public class ThreadStatus_jstack_2 {
	public static void main(String[] args) {
		ThreadB_2 b = new ThreadB_2(); // ThreadB status: new

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

class ThreadB_2 extends Thread {
	int total;

	public void run() {
		synchronized (this) {
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < 101; i++) {
				total += i;
			}

			notifyAll();
		}
	}
}
