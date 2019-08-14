package com.larten.sep_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button btnClick;
    TextView txtName , txtDiachi;
    ImageView imgHinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnClick = (Button)findViewById(R.id.btnClick);
        txtName = (TextView)findViewById(R.id.txtName);
        txtDiachi =(TextView)findViewById(R.id.txtDiachi);
        imgHinh =(ImageView)findViewById(R.id.imageHinh);


        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.initiateScan();


            }
        });
        txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, classActivity.class);
                startActivity(intent);
            }
        });



    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Picasso.with(this).load(result.getContents()).into(imgHinh);
                try {
                    JSONObject JsonObject = new JSONObject(result.getContents());
                    txtDiachi.setText(JsonObject.getString("DiaChi"));
                    txtName.setText(JsonObject.getString("name"));
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}
