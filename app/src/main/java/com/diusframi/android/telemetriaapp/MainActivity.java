package com.diusframi.android.telemetriaapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.diusframi.android.androidstatserviceapi.DandroidStats;
import com.diusframi.android.androidstatserviceapi.StatsRemoteApi;
import com.diusframi.android.telemetriaapp.ui.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private  ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    DandroidStats.DandroidStatsDelegate dandroidStatsDelegate = new DandroidStats.DandroidStatsDelegate() {
        @Override
        public void onInit(boolean status) {
            System.out.println("----on init:"+ status);
            if (status){
                dialog.dismiss();
                String result = DandroidStats.getInstance().getLogInfo();
                System.out.println("---result: "+result);
                try{
                    JSONObject joc = new JSONObject(result);
                    JSONArray jac = joc.getJSONObject("root").getJSONArray("tag");
                   for (int i =0; i<jac.length(); i++){
                      joc =  (JSONObject)jac.get(i);
                       System.out.println("----joc: "+joc.toString());
                   }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }

        @Override
        public void onConnectionLost() {
            Toast.makeText(getApplicationContext(),
                    "Connection to stats service lost", Toast.LENGTH_LONG).show();
        }
    };

    private void init(){
        dialog = Utils.showDialog(MainActivity.this,"Please wait... ");
        DandroidStats.getInstance().init(MainActivity.this,dandroidStatsDelegate);

    }



}