package com.example.jasmn.metroproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class viewdata extends AppCompatActivity {
    TextView STATnum, priceview, timeview;
    ListView viewsub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdata);
        STATnum = findViewById(R.id.textView9numst);
        priceview = findViewById(R.id.textView11price);
        timeview = findViewById(R.id.textView10time);
        viewsub = findViewById(R.id.list);

        Intent intent=getIntent();
        STATnum.setText(""+intent.getIntExtra("num",0)+"    stations");
        priceview.setText(""+intent.getIntExtra("price",0) +"     pound");
        timeview.setText(""+intent.getIntExtra("time",0)+"     minuts   ");
        ArrayList<String> sublist=new ArrayList<>();
        sublist=intent.getStringArrayListExtra("sub");
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, sublist);
        viewsub.setAdapter(adapter2);



    }
}
