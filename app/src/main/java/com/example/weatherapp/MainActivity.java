package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.weatherapp.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public CurrentWeather currWeather;
    private ImageView imgIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getForecast("30.6744","-96.3700");

    }

    private void getForecast(String lat, String lon) {
        final ActivityMainBinding binding = DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);

        imgIcon = findViewById(R.id.iconImageView);

        String API_KEY = "33f27127c54bc6b1358436def6d17031";
        String latitude = lat;
        String longitude = lon;
        String weatherURL = "https://api.darksky.net/forecast/" + API_KEY + "/" + latitude + "," + longitude;

        if(isNetworkAvailable()){
            OkHttpClient client = new OkHttpClient();

            Request req = new Request.Builder()
                    .url(weatherURL)
                    .build();

            Call call = client.newCall(req);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    try {
                        String JSONData = response.body().string();
                        Log.v("success", JSONData);
                        if(response.isSuccessful()){
                            currWeather = getJSONDetails(JSONData);

                            CurrentWeather displayWeather = new CurrentWeather(
                                    currWeather.getLocationLabel(),
                                    currWeather.getIcon(),
                                    currWeather.getSummary(),
                                    currWeather.getTime(),
                                    currWeather.getTemperature(),
                                    currWeather.getHumidity(),
                                    currWeather.getPrepChance(),
                                    currWeather.getTimezone()
                            );

                            binding.setWeather(displayWeather);
                            Drawable drawable = getResources().getDrawable(displayWeather.getIconID());
                            imgIcon.setImageDrawable(drawable);
                        }else{
                            alertUserAboutError();
                        }
                    } catch (IOException e) {
                        Log.e("IOException", String.valueOf(e));

                    }catch (JSONException e){
                        Log.e("JSONException", String.valueOf(e));
                    }
                }
            });
        }
    }

    private CurrentWeather getJSONDetails(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        JSONObject currently = forecast.getJSONObject("currently");
        CurrentWeather currWeather = new CurrentWeather();

        currWeather.setHumidity(currently.getDouble("humidity"));
        currWeather.setTime(currently.getLong("time"));
        currWeather.setIcon(currently.getString("icon"));
        currWeather.setLocationLabel("Alcatraz Island, CA");
        currWeather.setPrepChance(currently.getDouble("precipProbability"));
        currWeather.setSummary(currently.getString("summary"));
        currWeather.setTemperature(currently.getDouble("temperature"));
        currWeather.setTimezone(forecast.getString("timezone"));

        Log.v("success", currWeather.getFormattedTime());
        return currWeather;

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connect = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
             Network networkInfo = connect.getActiveNetwork();
            if (networkInfo != null ){
                return true;
            }else{
                Toast.makeText(this, "Sorry the network isn't available1",Toast.LENGTH_LONG).show();
            }
        }else{
            NetworkInfo networkInfo1 = connect.getActiveNetworkInfo();
            if (networkInfo1 != null && networkInfo1.isConnected()){
                return true;
            }else{
                Toast.makeText(this, "Sorry the network isn't available2",Toast.LENGTH_LONG).show();
            }

        }

        return false;
    }

    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();

        dialog.show(getSupportFragmentManager(), "error_Dialog");
    }

    public void refreshOnClick(View view){

        getForecast("30.6744","-96.3700");
        Toast.makeText(this, "Data Refreshed",Toast.LENGTH_LONG);
    }
}
