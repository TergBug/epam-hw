package com.epam.springcloud.notification.model;

public class Notification {
    private String user;
    private Notifier notifyBy;

    public Notification() {
        this.user = "";
        this.notifyBy = Notifier.EMAIL;
    }

    public Notification(String user, Notifier notifyBy) {
        this.user = user;
        this.notifyBy = notifyBy;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Notifier getNotifyBy() {
        return notifyBy;
    }

    public void setNotifyBy(Notifier notifyBy) {
        this.notifyBy = notifyBy;
    }
}
