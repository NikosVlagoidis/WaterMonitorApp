package com.example.nikos.watermonitorapp;

import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class DataThread extends Thread {

    private float data[][];
    private int id;

    public DataThread(float array[][],int id){

        data = array;
        this.id = id;
    }

    public void run(){
        
        try
        {
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("http://api.thingspeak.com/channels/" +
                    id + "/feeds.json");
            HttpResponse response = client.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {
                String server_response = EntityUtils.toString(response.getEntity());
                try{
                    JSONObject jsonRootObject = new JSONObject(server_response);

                    JSONArray array = jsonRootObject.getJSONArray("feeds");
                    for(int i=array.length()-100; i<array.length(); i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        //float entryIds = Float.valueOf(jsonObject.optString("entry_id").toString());
                        /*float date_created = Float.valueOf(jsonObject.optString("created_at")
                                .toString().replace("-","").replace(":","").replace("T","").
                                        replace("Z","")); */

                        float field1 =(float) Float.valueOf(jsonObject.optString("field1").
                                toString());
                        data[0][i-(array.length()-100)] = field1;
                    }
                }catch(JSONException j){j.printStackTrace();}
            } else {
                Log.e("Server response", "Failed to get server response");
            }
        }

        catch (IOException e) {
        }
    }
}
