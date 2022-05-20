package net.coral.client.event.impl.input;

import net.coral.client.event.Event;

public class KeyReleaseEvent extends Event {
    private final Integer key;
    private final Integer scancode;

    public KeyReleaseEvent(Integer key, Integer scancode) {
        this.key = key;
        this.scancode = scancode;
    }

    public Integer getKey() {
        return key;
    }

    public Integer getScancode() {
        return scancode;
    }
}
