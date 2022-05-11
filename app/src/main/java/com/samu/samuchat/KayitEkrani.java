package com.samu.samuchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class KayitEkrani extends AppCompatActivity {

    Button button_kayit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ekrani);

        button_kayit=findViewById(R.id.button_kayit);
        button_kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kayit=new Intent(KayitEkrani.this,GirisEkrani.class);
                startActivities(new Intent[]{kayit});
            }
        });
    }
}