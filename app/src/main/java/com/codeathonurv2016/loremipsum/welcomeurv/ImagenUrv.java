package com.codeathonurv2016.loremipsum.welcomeurv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImagenUrv extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen_urv);
        Bundle grupo=getIntent().getExtras();
        ImageView im=(ImageView)findViewById(R.id.imagen);
        im.setImageResource(grupo.getInt("im"));

    }
}
