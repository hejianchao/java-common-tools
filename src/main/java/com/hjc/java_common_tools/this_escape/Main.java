package com.hjc.java_common_tools.this_escape;

public class Main {
    public static void main(String[] args) {
        EventSource<AEventListener> eventSource = new EventSource<AEventListener>();

        ListenerRunnable listenerRunnable = new ListenerRunnable(eventSource);
        new Thread(listenerRunnable).start();

        ThisEscape escape = new ThisEscape(eventSource);
    }
}
