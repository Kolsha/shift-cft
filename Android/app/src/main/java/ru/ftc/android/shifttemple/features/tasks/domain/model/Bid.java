package ru.ftc.android.shifttemple.features.tasks.domain.model;

public final class Bid {
    private String bid_id;
    private String task_id;
    private String user_id;
    private String user_name;
    private String text;
    private String date;

    private String phone;
    private String vk_link;
    private String tg_link;
    private String email;

    public String getPhone() {
        return phone;
    }

    public String getVkLink() {
        return vk_link;
    }

    public String getTgLink() {
        return tg_link;
    }

    public String getEmail() {
        return email;
    }

    public Bid(String task_id, String text) {
        this.task_id = task_id;
        this.text = text;
    }

    public String getId() {
        return bid_id;
    }

    public String getUserName() {
        return user_name;
    }

    public String getUserId() {
        return user_id;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    public String getTaskId() {
        return task_id;
    }

}
