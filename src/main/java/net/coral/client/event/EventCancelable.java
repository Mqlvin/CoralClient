package net.coral.client.event;

public class EventCancelable {
    private Boolean canceled = false;

    public void cancel() {
        canceled = true;
    }

    public Boolean isCanceled() {
        return canceled;
    }
}
