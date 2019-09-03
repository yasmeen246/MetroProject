package com.example.jasmn.metroproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class trips extends AppCompatActivity {
    ArrayList<String> trips = new ArrayList<>();
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);
        list=findViewById(R.id.trips);
       /* if((Backendless.UserService.CurrentUser()).equals(true)){

        Toast.makeText(this, "please log in first" ,Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,login.class);
            startActivity(intent);
        }else {*/
        Backendless.Data.of(com.example.jasmn.metroproject.flightsclass.class).find(new AsyncCallback<List<flightsclass>>() {
            @Override
            public void handleResponse(List<com.example.jasmn.metroproject.flightsclass> response) {
                for (com.example.jasmn.metroproject.flightsclass flightsclass1 : response) {
                    trips.add(flightsclass1.getTrip_name());
                }
                ArrayAdapter arrayAdapter=new ArrayAdapter(trips.this,android.R.layout.simple_list_item_1,trips);
                list.setAdapter(arrayAdapter);
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DataQueryBuilder queryBuilder=DataQueryBuilder.create();
                queryBuilder.setWhereClause("trip_name='"+trips.get(i)+"'");
                Backendless.Data.of(flightsclass.class).find(queryBuilder, new AsyncCallback<List<flightsclass>>() {
                    @Override
                    public void handleResponse(List<flightsclass> response) {
                        Intent intent=new Intent(com.example.jasmn.metroproject.trips.this,viewdata.class);
                        intent.putExtra("num",response.get(0).getStationn_num());
                        intent.putExtra("time",response.get(0).getTime());
                        intent.putExtra("price",response.get(0).getPrice());
                    //    intent.putStringArrayListExtra("sub",response.get(0).getList_of_stations());
                        startActivity(intent);

                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {

                    }
                });
            }
        });
    }


}


