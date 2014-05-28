package com.hjc.java_common_tools.guava;

import org.junit.Test;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * http://www.cnblogs.com/peida/p/3224706.html
 *
 */
public class EventBus_Study {

	@Test
	public void testSingleListener() throws Exception {
		EventBus eventBus = new EventBus("test");
		MyEventListener listener = new MyEventListener();

		eventBus.register(listener);
		eventBus.post(new TestEvent("你好，何健超"));
		eventBus.post(new TestEvent("你好，java"));
		eventBus.post(new TestEvent("你好，world"));

		System.out.println("LastMessage:" + listener.getLastMessage());
	}

	@Test
	public void testMultipleEvents() throws Exception {

		EventBus eventBus = new EventBus("test");
		MultipleListener multiListener = new MultipleListener();

		eventBus.register(multiListener);

		eventBus.post(new Integer(100));
		eventBus.post(new Integer(200));
		eventBus.post(new Integer(300));
		eventBus.post(new Long(800));
		eventBus.post(new Long(800990));
		eventBus.post(new Long(800882934));

		System.out.println("LastInteger:" + multiListener.getLastInteger());
		System.out.println("LastLong:" + multiListener.getLastLong());
	}
}

class TestEvent {
	private final String message;

	public TestEvent(String message) {
		this.message = message;
		System.out.println("event message:" + message);
	}

	public String getMessage() {
		return message;
	}
}

class MyEventListener {
	public String lastMessage;

	@Subscribe
	public void listen(TestEvent event) {
		lastMessage = event.getMessage();
		System.out.println("Message:" + lastMessage);
	}

	public String getLastMessage() {
		return lastMessage;
	}

}

class MultipleListener {
	public Integer lastInteger;
	public Long lastLong;

	@Subscribe
	public void listenInteger(Integer event) {
		lastInteger = event;
		System.out.println("event Integer:" + lastInteger);
	}

	@Subscribe
	public void listenLong(Long event) {
		lastLong = event;
		System.out.println("event Long:" + lastLong);
	}

	public Integer getLastInteger() {
		return lastInteger;
	}

	public Long getLastLong() {
		return lastLong;
	}
}