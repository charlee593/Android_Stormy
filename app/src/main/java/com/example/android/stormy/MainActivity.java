package com.example.android.stormy;

import android.app.DownloadManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String apiKey = "8e4a6e62c5d7966c7f81be4e611fbe37";
        String latitude = "37.8267";
        String longitude = "-122.423";
        String forecastURL = "https://api.forcast.io/forecast/" + apiKey + "/" + latitude
                + "," + longitude;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(forecastURL).build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    if(response.isSuccessful()){
                        Log.v(TAG, response.body().string());
                    }
                    else{
                        alertUserAboutError();
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Exception catch: ", e);
                }
            }
        });
    }

    private void alertUserAboutError() {
        AlertDialogFrament dialog = new AlertDialogFrament();
        dialog.show(getFragmentManager(), "error_dialog");
    }

}
