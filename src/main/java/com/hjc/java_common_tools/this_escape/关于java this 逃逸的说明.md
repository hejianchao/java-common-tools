## 关于 java this逃逸 的说明

#### 什么是 this逃逸？

this 引用逃逸（`this escape`）是指对象还没有构造完成，它的this引用就被发布出去了。

#### this逃逸 的危害？

对外发布了一个未完全构造完成的对象，那么在多线程环境下，其他线程就可能访问到的是一个"初始化了一部分"的对象(`partially-constructed object`):可能某些线程中看到该对象的状态是没初始化完的状态，而在另外一些线程看到的却是已经初始化完的状态，这种不一致性是不确定的，程序也会因此而产生一些无法预知的并发错误。

#### this逃逸 的典型出现环境？

this逃逸 常常发生在 **构造函数中启动线程或注册监听器时**，比如：

    public class ThisEscape {
	    public ThisEscape() {
		    new Thread(new EscapeRunnable()).start();
		    // ...
	    }

	    private class EscapeRunnable implements Runnable {
		    @Override
		    public void run() {
			    // 通过ThisEscape.this就可以引用外围类ThisEscape的对象, 但是此时外围类对象可能还没有构造完成, 即发生了外围类的this引用的逃逸
		    }
	    }
    }

另外注册监听器的情况通常是在类的构造函数中又构造了内部类，并且通过某些注册操作导致内部类裹挟着外部类的不完整实例结果逃逸出去。例子参考[这里](https://github.com/hejianchao/java-common-tools/tree/master/src/main/java/com/hjc/java_common_tools/this_escape)


#### this逃逸 如何避免？
1. 不要在构造函数中启动(start)线程
2. 如果要再构造方法中创建内部类，一定要等构造函数已经执行完成后（其初始化已经完成），再发布内部类。通常使用一个私有的构造方法进行初始化，然后用一个公共的工厂方法进行发布。