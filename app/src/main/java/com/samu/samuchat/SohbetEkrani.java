package com.samu.samuchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.samu.samuchat.ui.home.HomeFragment;

public class SohbetEkrani extends AppCompatActivity {



    Button btn_geri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sohbet_ekrani);




        btn_geri=findViewById(R.id.btn_geri);
        btn_geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kayit=new Intent(SohbetEkrani.this, HomeFragment.class);
                startActivities(new Intent[]{kayit});
            }
        });


    }
}