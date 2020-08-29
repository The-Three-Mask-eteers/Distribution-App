package com.example.loginapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {



    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen2);

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                openDonateAMask();

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCollectAMask();

            }
        });
    }
    public void openDonateAMask() {
//        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
//        Donatee.donateeString = Donatee.donatee1;
//        startActivity(intent);
    }
    public void openCollectAMask() {
//        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
//
//        startActivity(intent);
    }

}