package com.diusframi.android.telemetriaapp.model;

public class LogItem {
    private String tag;
    private String log;
    private String timeStamp;

    public LogItem(String tag, String log, String timeStamp) {
        this.tag = tag;
        this.log = log;
        this.timeStamp = timeStamp;
    }

    public String getLog() {
        return log;
    }

    public String toString(){
        if (log.length() > 25){
            return timeStamp+"::"+log.substring(0,25);
        }
        return timeStamp+"::"+log;
    }

    public String getFullInfo(){
       return timeStamp+"::"+tag + "::" + log;
    }

}
