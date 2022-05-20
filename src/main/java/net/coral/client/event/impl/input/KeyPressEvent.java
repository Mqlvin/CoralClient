package net.coral.client.event.impl.input;

import net.coral.client.event.Event;

public class KeyPressEvent extends Event {
    private final Integer key;
    private final Integer scancode;

    private final Boolean isRepeatedEvent;

    public KeyPressEvent(Integer key, Integer scancode, Boolean repeated) {
        this.key = key;
        this.scancode = scancode;

        this.isRepeatedEvent = repeated;
    }

    public Integer getKey() {
        return key;
    }

    public Integer getScancode() {
        return scancode;
    }

    public Boolean getRepeatedEvent() {
        return isRepeatedEvent;
    }
}
