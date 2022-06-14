package com.samu.samuchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GirisEkrani extends AppCompatActivity {

    Button btn_giris;
    Button btn_kayit;
    private EditText giriş_yap_email,Giriş_yap_parola;
    private String txtemail,txtsifre;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_ekrani);

        giriş_yap_email=(EditText)findViewById(R.id.giriş_yap_email);
        Giriş_yap_parola=(EditText)findViewById(R.id.Giriş_yap_parola);

        mAuth=FirebaseAuth.getInstance();

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
    public void GirisEkranı(View view)
    {
        txtemail=giriş_yap_email.getText().toString();
        txtsifre=Giriş_yap_parola.getText().toString();

        if(!TextUtils.isEmpty(txtemail) &&!TextUtils.isEmpty(txtsifre))
        {
            mAuth.signInWithEmailAndPassword(txtemail,txtsifre).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    mUser=mAuth.getCurrentUser();

                    System.out.println("Kullanıcı Adı :"+mUser.getDisplayName());
                    System.out.println("Kullanıcı Email :"+mUser.getEmail());

                }
            }).addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(GirisEkrani.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
            Toast.makeText(this,"email ve şifre boş olamaz",Toast.LENGTH_SHORT).show();
    }
    


}