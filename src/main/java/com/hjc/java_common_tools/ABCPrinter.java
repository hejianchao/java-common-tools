package com.hjc.java_common_tools;

/**
 * 实现ABC依序输出，各自输出十次。
 * 
 * <pre>
 * synchronized和wait()、notify()等的关系:
 * 1.有synchronized的地方不一定有wait,notify
 * 2.有wait,notify的地方必有synchronized.这是因为wait和notify不是属于线程类，而是每一个对象都具有的方法，而且，这两个方法都和对象锁有关，有锁的地方，必有synchronized。
 * 另外，注意一点：如果要把notify和wait方法放在一起用的话，必须先调用notify后调用wait，因为如果调用完wait，该线程就已经不是currentthread了。
 * </pre>
 * 
 * @author hejianchao
 * 
 */
public class ABCPrinter implements Runnable {

	private String name;
	private Object prev;
	private Object self;

	private ABCPrinter(String name, Object prev, Object self) {
		this.name = name;
		this.prev = prev;
		this.self = self;
	}

	@Override
	public void run() {
		int count = 10;
		while (count > 0) {
			synchronized (prev) {
				synchronized (self) {
					System.out.print(name);
					count--;
					// 打开注释段代码，会出现ACBACB...这样的输出
					// try {
					// TimeUnit.MILLISECONDS.sleep(1000);
					// } catch (InterruptedException e) {
					// e.printStackTrace();
					// }
					self.notify();
				}
				if (count > 0) {// 添加这个条件，是为了最后正常退出。否则会一直运行
					try {
						prev.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

	public static void main(String[] args) throws Exception {
		Object a = new Object();
		Object b = new Object();
		Object c = new Object();
		ABCPrinter pa = new ABCPrinter("A", c, a);
		ABCPrinter pb = new ABCPrinter("B", a, b);
		ABCPrinter pc = new ABCPrinter("C", b, c);

		new Thread(pa).start();
		new Thread(pb).start();
		new Thread(pc).start();
	}
}
