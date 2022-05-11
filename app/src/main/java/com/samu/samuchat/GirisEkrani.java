package com.samu.samuchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GirisEkrani extends AppCompatActivity {

    Button btn_giris;
    Button btn_kayit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_ekrani);


        btn_giris=findViewById(R.id.btn_girisyap);
        btn_giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iris =new Intent(GirisEkrani.this,MainActivity.class);
                startActivities(new Intent[]{iris});
            }
        });

        btn_kayit=findViewById(R.id.btn_kayitol);
        btn_kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kayit=new Intent(GirisEkrani.this,KayitEkrani.class);
                startActivities(new Intent[]{kayit});
            }


        });

    }

    


}