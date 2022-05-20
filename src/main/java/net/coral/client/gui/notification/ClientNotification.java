package net.coral.client.gui.notification;

import net.coral.client.util.ScreenUtil;

public class ClientNotification {
    public static final int DEF_WIDTH = 120;
    public static final int DEF_HEIGHT = 60;

    public static final int DEF_X_OFFSET = -(DEF_WIDTH + 30);
    public static final int DEF_Y_OFFSET = -(DEF_HEIGHT + 30);

    private String content;
    private NotificationType type;

    private long stay;
    private long passed;

    private int height, width, x, y;

    public ClientNotification(String content, NotificationType type) {
        this(content, type, 3000, DEF_WIDTH, DEF_HEIGHT, ScreenUtil.getScreenWidth() + DEF_X_OFFSET, ScreenUtil.getScreenHeight() + DEF_Y_OFFSET);
    }

    public ClientNotification(String content, NotificationType type, long stay, int width, int height, int x, int y) {
        this.content = content;
        this.type = type;

        this.stay = stay;
        this.passed = 0;

        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    private void update() {

    }

    public String getContent() {
        return content;
    }

    public NotificationType getType() {
        return type;
    }

    public long getStay() {
        return stay;
    }

    public long getPassed() {
        return passed;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
