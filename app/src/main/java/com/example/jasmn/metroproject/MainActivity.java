package com.example.jasmn.metroproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationListener {

    EditText location1;
    Spinner loc, placeAnswer;

    Button ok;
    String place1, place2;
    int nub_of_stat = 0;
    int price = 0;
    int num1 = 0, num2 = 0;
    int time;
    LocationManager location;
    ArrayList<String> stations = new ArrayList<>();
    ArrayList<String> stationsline2 = new ArrayList<>();
    ArrayList<String> stationsline3 = new ArrayList<>();
    ArrayList<String> allstation = new ArrayList<>();
    ArrayList<String> substation = new ArrayList<>();
    ArrayList<Float>distancelist=new ArrayList<>();
    ArrayList<Double>lon=new ArrayList<>();
    ArrayList<Double>lat=new ArrayList<>();
    TextToSpeech speech;
     flightsclass flightsclass=new flightsclass();
        stations station=new stations();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Backendless.initApp(this,"C1598D6E-F1E4-1BCF-FF6E-987A93BF0600","99618700-7679-7D06-FF13-0C955FED8400");

        location1 = findViewById(R.id.editTextloc);
        ok = findViewById(R.id.buttonok);
        loc = findViewById(R.id.locans);
        placeAnswer = findViewById(R.id.placeans);
        lines.insert();
        stations=lines.stations;
        stationsline2=lines.stationsline2;
        stationsline3=lines.stationsline3;
        allstation=lines.allstation;
        lon=lines.lonall;
        lat=lines.latall;

        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, allstation);

        loc.setAdapter(adapter1);

        placeAnswer.setAdapter(adapter1);

        speech=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                speech.setSpeechRate(0.6f);
                speech.setPitch(0.8f);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.metrolist,menu);
        return true;
    }

    public void placeloc(View view) {
        location = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] perm = {Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this, perm, 1);
        } else
            location.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
            try {
                location.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
        else finish();
    }

    public void onestat (int p1, int p2, ArrayList list){

    substation.addAll(list.subList(p1 + 1, p2 + 1));
    System.out.println("you wil go throw   (" + substation + ")");


}
    public void line12 (int p1,int p2,ArrayList list1,ArrayList list2){

        int p1sub = list1.indexOf("السادات");
        int p2sub = list2.indexOf("السادات");
        int p1sub2=list1.indexOf("الشهداء");
        int p2sub2=list2.indexOf("الشهداء");
        if (p1 < p1sub) {
            num1 = p1sub - p1;
            System.out.println("line 1" + list1.subList(p1 + 1, p1sub + 1));
            substation.addAll(list1.subList(p1 + 1, p1sub + 1));
            speech.speak("you will change in sadat station from first line to scond line",TextToSpeech.QUEUE_FLUSH,null);}
            if (p2 < p2sub) {

                num2 = p2sub - p2;
                substation.addAll(list2.subList(p2, p2sub));
            } else if (p2 > p2sub) {
                System.out.println("line 2" + list2.subList(p2sub + 1, p2 + 1));

                num2 = p2 - p2sub;
                substation.addAll(list2.subList(p2sub + 1, p2 + 1));
            }
//الشهدا

          else if (p1 > p1sub2) {

            num1 = p1 - p1sub2;
            System.out.println("line 1" + list1.subList(p1sub2, p1));
            substation.addAll(list1.subList(p1sub2, p1));
            if (p2 < p2sub2) {

                num2 = p2sub2 - p2;
                substation.addAll(list2.subList(p2, p2sub2));
            } else if (p2 > p2sub2) {
                System.out.println("line 2" + list2.subList(p2sub2 + 1, p2 + 1));

                num2 = p2 - p2sub2;
                substation.addAll(list2.subList(p2sub2 + 1, p2 + 1));

            }

        }

        else if ((p1sub<p1)&&(p1<p1sub2)){
            if((p1-p1sub)<=(p1sub2-p1)){
                num1 = p1 - p1sub;
                System.out.println("line 1" + list1.subList(p1sub, p1));
                substation.addAll(list1.subList(p1sub, p1));


                if (p2 < p2sub) {

                    num2 = p2sub - p2;
                    substation.addAll(list2.subList(p2, p2sub));
                    Collections.reverse(substation);

                } else if (p2 > p2sub) {
                    System.out.println("line 2" + list2.subList(p2sub + 1, p2 + 1));

                    num2 = p2 - p2sub;
                    substation.addAll(list2.subList(p2sub + 1, p2 + 1));
                }}


            else {
                num1 = p1sub2 - p1;
                System.out.println("line 1" + list1.subList(p1 + 1, p1sub2 + 1));
                substation.addAll(list1.subList(p1 + 1, p1sub2 + 1));
                if (p2 < p2sub2) {

                    num2 = p2sub2 - p2;
                    substation.addAll(list2.subList(p2, p2sub2));
                    Collections.reverse(substation);

                } else if (p2 > p2sub2) {
                    System.out.println("line 2" + list2.subList(p2sub2 + 1, p2 + 1));

                    num2 = p2 - p2sub2;
                    substation.addAll(list2.subList(p2sub2 + 1, p2 + 1));
                }

            }
        }



        System.out.println("you wil go throw   (" + substation + ")");
        nub_of_stat = num1 + num2;
    }

    public void line23(int p1,int p2,ArrayList list2,ArrayList list3){
        int p1sub = list2.indexOf("العتبة");
        int p2sub = list3.indexOf("العتبة");

        if (p1 <= p1sub) {
            num1 = p1sub - p1;
            System.out.println("line 1" + list2.subList(p1 + 1, p1sub + 1));
            substation.addAll(list2.subList(p1 + 1, p1sub + 1));

        } else if (p1 > p1sub) {
            num1 = p1 - p1sub;
            System.out.println("line 1" + list2.subList(p1sub, p1));
            substation.addAll(list2.subList(p1sub, p1));
            Collections.reverse(substation);
        }
        if (p2 <= p2sub) {
            System.out.println("line 3" + list3.subList(p2, p2sub));
            num2 = p2sub - p2;
            substation.addAll(list3.subList(p2, p2sub));
            Collections.reverse(substation);
            } else if (p2 > p2sub) {
            System.out.println("line 3" + list3.subList(p2sub + 1, p2 + 1));

            num2 = p2 - p2sub;
            substation.addAll(list3.subList(p2sub + 1, p2 + 1));

        }


        System.out.println("you wil go throw   (" + substation + ")");
        nub_of_stat = num1 + num2;
    }


    public void ok(View view) {
        place1 = loc.getSelectedItem().toString();
        place2 = placeAnswer.getSelectedItem().toString();
        //search in all station**********************************************************
        if (place1.endsWith("--") || place2.endsWith("--")) {
            Toast.makeText(this, "choose place", Toast.LENGTH_LONG).show();
        } else if (allstation.contains(place1) && allstation.contains(place2)) {


            int p1 = allstation.indexOf(place1);
            int p2 = allstation.indexOf(place2);
            // place1 first---------------------------------------------------
            if (p1 < p2) {
                ///////////line1
                if (stations.contains(place1) && stations.contains(place2)) {
                    substation.clear();
                    p1 = stations.indexOf(place1);
                    p2 = stations.indexOf(place2);
                    onestat(p1, p2, stations);
                    nub_of_stat = p2 - p1;
                }
                ///////////line2
                else if (stationsline2.contains(place1) && stationsline2.contains(place2)) {
                    substation.clear();
                    p1 = stationsline2.indexOf(place1);
                    p2 = stationsline2.indexOf(place2);
                    onestat(p1, p2, stationsline2);
                    nub_of_stat = p2 - p1;
                }
                /////////line3
                else if (stationsline3.contains(place1) && stationsline3.contains(place2)) {
                    substation.clear();
                    p1 = stationsline3.indexOf(place1);
                    p2 = stationsline3.indexOf(place2);
                    onestat(p1, p2, stationsline3);
                    nub_of_stat = p2 - p1;
                }
                /////////line1 and line2 السادات محطة تبادلية
                else if (stations.contains(place1) && stationsline2.contains(place2)) {
                    substation.clear();
                    p1 = stations.indexOf(place1);
                    p2 = stationsline2.indexOf(place2);
                    line12(p1, p2, stations, stationsline2);
                }
                /////////line1 and line جمال عبدالناصر محطة تبادل3 ----------------------------------------------------------
                else if (stations.contains(place1) && stationsline3.contains(place2)) {
                    substation.clear();
                    p1 = stations.indexOf(place1);
                    p2 = stationsline3.indexOf(place2);
                    int p1sub = stationsline2.indexOf("العتبة");
                    line12(p1, p1sub, stations, stationsline2);
                    int num=nub_of_stat;
                    line23(p1sub, p2, stationsline2, stationsline3);
                    nub_of_stat=nub_of_stat+num;
                }
                /////////line2 and line3 جامعة القاهرة محطة تبادلية---------------------------------------------------------------
                else if (stationsline2.contains(place1) && stationsline3.contains(place2)) {
                    substation.clear();
                    p1 = stationsline2.indexOf(place1);
                    p2 = stationsline3.indexOf(place2);
                    line23(p1, p2, stationsline2, stationsline3);

                }
            }
//////p2 first--------------------------------------------------------------------------------------------------------------------
            else if (p1 > p2) {
                /////line1
                if (stations.contains(place1) && stations.contains(place2)) {
                    substation.clear();
                    p1 = stations.indexOf(place1);
                    p2 = stations.indexOf(place2);
                    onestat(p2 - 1, p1 - 1, stations);
                    nub_of_stat = p1 - p2;

                }
                /////line2
                else if (stationsline2.contains(place1) && stationsline2.contains(place2)) {
                    substation.clear();
                    p1 = stationsline2.indexOf(place1);
                    p2 = stationsline2.indexOf(place2);
                    onestat(p2 - 1, p1 - 1, stationsline2);
                    nub_of_stat = p1 - p2;
                }
                //////line3
                else if (stationsline3.contains(place1) && stationsline3.contains(place2)) {
                    substation.clear();
                    p1 = stationsline3.indexOf(place1);
                    p2 = stationsline3.indexOf(place2);
                    onestat(p2 - 1, p1 - 1, stationsline3);

                    nub_of_stat = p1 - p2;
                }
                //////line 2 and line 3---------***********************************************************************************************
                else if (stationsline2.contains(place1) && stations.contains(place2)) {
                    substation.clear();
                    p1 = stationsline2.indexOf(place1);
                    p2 = stations.indexOf(place2);
                    line12(p1, p2, stationsline2, stations);


                }
                //////line3 and line 1------------------------------------------------------------------
                else if (stationsline3.contains(place1) && stations.contains(place2)) {
                    substation.clear();
                    p1 = stationsline3.indexOf(place1);
                    p2 = stations.indexOf(place2);
                    int p1sub = stationsline2.indexOf("العتبة");
                    line23(p1, p1sub, stationsline3, stationsline2);
                    int num=nub_of_stat;
                    line12(p1sub, p2, stationsline2, stations);
                     nub_of_stat=nub_of_stat+num-1;
                }
                //////line3 and line 2------------------------------------------------------------------
                else if (stationsline3.contains(place1) && stationsline2.contains(place2)) {
                    substation.clear();
                    p1 = stationsline3.indexOf(place1);
                    p2 = stationsline2.indexOf(place2);
                    line23(p1, p2, stationsline3, stationsline2);

                }
            }
            //////price-------------------------------------------------------------------------------------
            if (nub_of_stat <= 9 && nub_of_stat > 0)
                price = 3;
            else if (nub_of_stat <= 16 && nub_of_stat > 9) price = 5;
            else if (nub_of_stat > 16) price = 7;

            time = nub_of_stat*2;

            Intent intent=new Intent(this,viewdata.class);
            intent.putExtra("num",nub_of_stat);
            intent.putExtra("time",time);
            intent.putExtra("price",price);
            intent.putStringArrayListExtra("sub",substation);
            startActivity(intent);


                flightsclass.setTrip_name(place1+"&&"+place2);
                flightsclass.setStationn_num(nub_of_stat);
                flightsclass.setTime(time);
                flightsclass.setPrice(price);
            Backendless.Persistence.save(flightsclass, new AsyncCallback<com.example.jasmn.metroproject.flightsclass>() {
                @Override
                public void handleResponse(com.example.jasmn.metroproject.flightsclass response) {
                    Toast.makeText(MainActivity.this, "saved", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void handleFault(BackendlessFault fault) {

                }
            });
            for (int i = 0; i < substation.size(); i++) {
                station.setStations(substation.get(i));
                Backendless.Persistence.save(station, new AsyncCallback<com.example.jasmn.metroproject.stations>() {
                    @Override
                    public void handleResponse(com.example.jasmn.metroproject.stations response) {
                        Toast.makeText(MainActivity.this, "saved", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {

                    }
                });

            }
            }}





    String s;
    ArrayList<Float> d;
    @Override
    public void onLocationChanged(Location location) {
        Geocoder geocoder = new Geocoder(this);
        float dic1 = 0, distance;

        d = new ArrayList<>();

        try {



                List<Address> name = geocoder.getFromLocationName("محطة مترو" + stations.get(5), 1);
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            for (int i = 0; i <lat.size() ; i++) {

                Location location2 = new Location(LocationManager.GPS_PROVIDER);
                location2.setLatitude(lat.get(i));
                location2.setLongitude(lon.get(i));
                distance = location.distanceTo(location2);
                d.add(distance);
                }
            Float min = Collections.min(d);
            int index = d.indexOf(min);
            if (index>(stations.size()-2))
                s = allstation.get(index + 3);
            else
                s = allstation.get(index + 2);

            Toast.makeText(this, "least distance"+min+s, Toast.LENGTH_SHORT).show();
            speech.speak("the least disdance is"+s,TextToSpeech.QUEUE_FLUSH,null);
             loc.setSelection(index+2);
            } catch(IOException e){
                e.printStackTrace();
            } }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public void login(MenuItem item) {
        Intent intent=new Intent(this,login.class);
        startActivity(intent);
    }

    public void flights(final MenuItem item) {
Intent intent=new Intent(this,com.example.jasmn.metroproject.trips.class);
startActivity(intent);


    }


}


