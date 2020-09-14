package com.diusframi.android.telemetriaapp.model;

import org.json.JSONArray;

public class LogInfo {
    private static LogInfo instance;


    public void parseLogLevel(JSONArray logs){

    }
    
    private LogInfo(){}  
    
    public static LogInfo getInstance(){
        if (instance == null){
            instance = new LogInfo();
        }
        return instance; 
    }
}
