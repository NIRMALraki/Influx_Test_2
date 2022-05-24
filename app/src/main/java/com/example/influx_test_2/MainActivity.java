package com.example.influx_test_2;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            InfluxDB influxDB = InfluxDBFactory.connect("http://172.16.34.47:8086", "admin", "admin");
                            influxDB.setLogLevel(InfluxDB.LogLevel.NONE);
                            influxDB.setDatabase("DEMO");
                            if (influxDB != null) {
                                String sql = "SELECT value FROM \"random_number\" ORDER BY time desc LIMIT 1";

                                QueryResult queryResult = influxDB.query(new Query(sql), TimeUnit.MILLISECONDS);
                                List<QueryResult.Result> res = queryResult.getResults();
                                System.out.println(res.get(0).getSeries().get(0).getValues().get(0));
                                //System.out.println(res.get(0).getSeries().get(0));
                                //System.out.println(res.get(0).getSeries().get(1));
                                //System.out.println(res.get(0).getSeries());

                                //Result [series=[Series [name=random_numbers, tags=null, columns=[time, value], values=[[1.650133058582E12, 75.0]]]], error=null]
                            } else {
                                Log.d(TAG, "not fetched ");
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                });
                thread.start();
            }
        });


    }
}