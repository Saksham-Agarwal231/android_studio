package com.example.countdown_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://api.covid19api.com/summary")
                .method("GET", null)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    String myResponse = response.body().string();
                    try {
                        JSONObject json = new JSONObject(myResponse);
                        JSONObject jsss = json.getJSONObject("Global");
                        String cases = jsss.getString("TotalConfirmed");
                        String TotalRecovered = jsss.getString("TotalRecovered");
                        String TotalDeaths = jsss.getString("TotalDeaths");
                        TextView total = findViewById(R.id.total);
                        TextView recovered = findViewById(R.id.recoverd);
                        TextView deaths = findViewById(R.id.deaths);
                        total.setText(cases);
                        recovered.setText(TotalRecovered);
                        deaths.setText(TotalDeaths);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                }
            }
        });

    }
}