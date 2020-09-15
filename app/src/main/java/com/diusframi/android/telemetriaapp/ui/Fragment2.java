package com.diusframi.android.telemetriaapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.diusframi.android.telemetriaapp.R;
import com.diusframi.android.telemetriaapp.model.LogInfo;
import com.diusframi.android.telemetriaapp.model.LogItem;

import java.util.ArrayList;

public class Fragment2 extends Fragment {

    private void loadBattery(View view){
        ArrayList<LogItem> list = LogInfo.getInstance().getBatteryLogs();
        ListView listView = view.findViewById(R.id.id_list_battery_logs);
        ArrayList<String> items = new ArrayList<>();
        for (LogItem item:list){
            items.add(item.toString());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String value = LogInfo.getInstance().getBatteryLogs().get(i).getFullInfo();
                Utils.createDialogSingleButton(getContext(), value, "OK").show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadBattery(view);
    }
}