package com.example.sims;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText NAME, NIM;
    Button SIMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NAME = findViewById(R.id.name);
        NIM = findViewById(R.id.nim);
        SIMS = findViewById(R.id.sims);

        SIMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity. this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
                }else{

                    try {
                        String name1= NAME.getText().toString();
                        String nim1= NIM.getText().toString();
                        File file = new File("/sdcard/sims/");
                        file.mkdirs();

                        String csv ="/sdcard/sims/sims.csv";

                        CSVWriter csvWriter = new CSVWriter(new FileWriter(csv,true));

                        String row[]=new String[]{name1,nim1};
                        csvWriter.writeNext(row);
                        csvWriter.close();
                        Toast.makeText(MainActivity.this,"File Succesfully Created",Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }
        });

    }
}
