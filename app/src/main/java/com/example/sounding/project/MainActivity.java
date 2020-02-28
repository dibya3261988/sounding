package com.example.sounding.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    TextView tombol;
    Intent pindah;

    TextView tombol2;
    Intent pindah2;

    TextView tombol3;
    Intent pindah3;

    TextView tombol4;
    Intent pindah4;

    TextView tombol5;
    Intent pindah5;

    TextView tombol6;
    Intent pindah6;

    TextView tombol7;
    Intent pindah7;

    TextView tombol8;
    Intent pindah8;

    TextView tombol9;
    Intent pindah9;

    TextView tombol10;
    Intent pindah10;

    TextView tombol11;
    Intent pindah11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tombol = (TextView) findViewById(R.id.ke_layout_2);
        tombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pindah = new Intent(MainActivity.this, Layout_2.class);
                startActivity(pindah);
                finish();
            }
        });

        tombol2 = (TextView) findViewById(R.id.ke_layout_3);
        tombol2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pindah2 = new Intent(MainActivity.this, Layout_3.class);
                startActivity(pindah2);
                finish();
            }
        });

        tombol3 = (TextView) findViewById(R.id.ke_layout_4);
        tombol3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pindah3 = new Intent(MainActivity.this, Layout_4.class);
                startActivity(pindah3);
                finish();
            }
        });

        tombol4 = (TextView) findViewById(R.id.ke_layout_5);
        tombol4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pindah4 = new Intent(MainActivity.this, Layout_5.class);
                startActivity(pindah4);
                finish();
            }
        });

        tombol5 = (TextView) findViewById(R.id.ke_layout_6);
        tombol5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pindah5 = new Intent(MainActivity.this, Layout_6.class);
                startActivity(pindah5);
                finish();
            }
        });

        tombol6 = (TextView) findViewById(R.id.ke_layout_7);
        tombol6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pindah6 = new Intent(MainActivity.this, Layout_7.class);
                startActivity(pindah6);
                finish();
            }
        });

        tombol7 = (TextView) findViewById(R.id.ke_layout_8);
        tombol7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pindah7 = new Intent(MainActivity.this, Layout_8.class);
                startActivity(pindah7);
                finish();
            }
        });

        tombol8 = (TextView) findViewById(R.id.ke_layout_9);
        tombol8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pindah8 = new Intent(MainActivity.this, Layout_9.class);
                startActivity(pindah8);
                finish();
            }
        });

        //Untuk Tangki UF9
        tombol10 = (TextView) findViewById(R.id.ke_layout_11);
        tombol10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pindah10 = new Intent(MainActivity.this, Layout_11.class);
                startActivity(pindah10);
                finish();
            }
        });

        //Untuk Tangki CD2
        tombol9 = (TextView) findViewById(R.id.ke_layout_10);
        tombol9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pindah9 = new Intent(MainActivity.this, Layout_10.class);
                startActivity(pindah9);
                finish();
            }
        });



        //Untuk CD3
        tombol11 = (TextView) findViewById(R.id.ke_layout_12);
        tombol11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pindah11 = new Intent(MainActivity.this, Layout_12.class);
                startActivity(pindah11);
                finish();
            }
        });
    }
}