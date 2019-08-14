package com.larten.sep_project;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class classActivity extends AppCompatActivity {
    ListView listviewmonhoc;
    ArrayList<MonHoc> arrayMonhoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        listviewmonhoc = (ListView)findViewById(R.id.listViewMonHoc);

        arrayMonhoc = new ArrayList<MonHoc>();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new readJSON().execute("http://cntttest.vanlanguni.edu.vn:18080/Cap21T4/AttendanceManagement/JSON/GetCourses?email=phamngocduy%40vanlanguni.edu.vn&hk=182&fbclid=IwAR24BCAdHeLNuut8s0yddRBT7NWtvnZ83ey_4RGB7xsnvS2cTSG6HDelutY");
            }
        });
    }

    class readJSON extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... strings) {
            return docNoiDung_Tu_URL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONArray jarray = new JSONArray(s);
                for (int i = 0; i < jarray.length(); i++){
                    JSONObject mhoc = jarray.getJSONObject(i);
                    arrayMonhoc.add(new MonHoc(
                            mhoc.getInt("id"),
                            mhoc.getString("Name"),
                            mhoc.getInt("Students"),
                            mhoc.getString("Day"),
                            mhoc.getString("Time"),
                            mhoc.getString("Room")
                    ));
                }
                ListAdapterAct adapterAct = new ListAdapterAct(
                        getApplicationContext(),
                        R.layout.activity_item_mon_hoc,
                        arrayMonhoc
                );
                listviewmonhoc.setAdapter(adapterAct);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }
    }

    // out
    private String docNoiDung_Tu_URL(String theUrl){
        StringBuilder content = new StringBuilder();
        try    {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)    {
            e.printStackTrace();
        }
        return content.toString();
    }
}
