package ru.myproject.tz_googlemap;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

public class JsonParser extends AsyncTask<String, String, String> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(String... params) {
        HTTP_Handler http_handler=new HTTP_Handler();
        String url = "https://dev.skif.me/coordinates.json";
        String jsonStr = http_handler.makeServiceCall(url);

        try {
            JSONArray jsonArray =new JSONArray(http_handler.makeServiceCall(url));
            System.out.println();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.e("TAG", "Response from url: " + jsonStr);
        return jsonStr;
    }

    @Override
    protected void onPostExecute(String s) {

        System.out.println("////////////////// " + s);
        super.onPostExecute(s);
    }
}
