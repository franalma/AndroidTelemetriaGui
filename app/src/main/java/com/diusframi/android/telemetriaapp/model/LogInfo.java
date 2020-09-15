package com.diusframi.android.telemetriaapp.model;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class LogInfo {
    private static LogInfo instance;
    private ArrayList<LogItem> batteryLogs;
    private ArrayList<LogItem> wifiLogs;

    public void parseLogLevel(String input){
        try{
            JSONArray logs = new JSONArray(input);
            for (int i = 0; i < logs.length(); i++){
                JSONObject joc = logs.getJSONObject(i);
                LogItem item = (new LogItem(joc.getString("tag"),
                        joc.getString("log"),
                        joc.getString("time")));
                System.out.println("----item: "+item);
                if (item.getLog().toLowerCase().contains("battery")){
                    batteryLogs.add(item);
                    System.out.println("---battery");
                }else if (item.getLog().toLowerCase().contains("wiifstats")){
                    wifiLogs.add(item);
                    System.out.println("---wifi");
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<LogItem> getBatteryLogs() {
        return batteryLogs;
    }

    public ArrayList<LogItem> getWifiLogs() {
        return wifiLogs;
    }

    private LogInfo(){
        batteryLogs = new ArrayList<>();
        wifiLogs = new ArrayList<>();
    }
    
    public static LogInfo getInstance(){
        if (instance == null){
            instance = new LogInfo();
        }
        return instance; 
    }
}
