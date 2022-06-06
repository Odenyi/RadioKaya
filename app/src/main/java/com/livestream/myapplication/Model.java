package com.livestream.myapplication;

public class Model {
    private String presenters;
    private String show;
    private String id;
    private String date;
    private String timestart;
    private String timestop;
    private String day;

    public Model() {
    }

    public Model(String presenters, String show, String id, String date, String timestart, String timestop, String day) {
        this.presenters = presenters;
        this.show = show;
        this.id = id;
        this.date = date;
        this.timestart = timestart;
        this.timestop = timestop;
        this.day = day;
    }

    public String getPresenters() {
        return presenters;
    }

    public void setPresenters(String presenters) {
        this.presenters = presenters;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimestart() {
        return timestart;
    }

    public void setTimestart(String timestart) {
        this.timestart = timestart;
    }

    public String getTimestop() {
        return timestop;
    }

    public void setTimestop(String timestop) {
        this.timestop = timestop;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }


}
