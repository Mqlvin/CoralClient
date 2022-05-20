package net.coral.client.event.bus;

import net.coral.client.event.Priority;

import java.lang.reflect.Method;

public class EventListener {
    private Object listener;
    private Method method;
    private Priority priority;

    public EventListener(Object listener, Method method, Priority priority) {
        this.listener = listener;
        this.method = method;
        this.priority = priority;
    }

    public Object getListener() {
        return listener;
    }

    public Method getMethod() {
        return method;
    }

    public Priority getPriority() {
        return priority;
    }
}