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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class KayitEkrani extends AppCompatActivity {

    Button button_kayit;
    private EditText editemail,editsifre,editisim,editdtarihi,edittel;
    private String txtemail,txtsifre,txtisim,txtdtarihi,txttel;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private DatabaseReference mReferans;
    private HashMap<String,Object> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ekrani);
        editemail=(EditText)findViewById(R.id.email);
        editsifre=(EditText)findViewById(R.id.sifre);
        editisim=(EditText)findViewById(R.id.isim);
        editdtarihi=(EditText)findViewById(R.id.dtarihi);
        edittel=(EditText)findViewById(R.id.tel);


        mAuth=FirebaseAuth.getInstance();
        mReferans= FirebaseDatabase.getInstance().getReference();

        button_kayit=findViewById(R.id.button_kayit);
        button_kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kayit=new Intent(KayitEkrani.this,GirisEkrani.class);
                startActivities(new Intent[]{kayit});
            }
        });
    }
    public void Kayıtol(View v)
    {
        txtemail=editemail.getText().toString();
        txtsifre=editsifre.getText().toString();
        txtisim=editisim.getText().toString();
        txtdtarihi=editdtarihi.getText().toString();
        txttel=edittel.getText().toString();

        if(!TextUtils.isEmpty(txtemail) &&!TextUtils.isEmpty(txtsifre))
        {
            mAuth.createUserWithEmailAndPassword(txtemail,txtsifre).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        mUser=mAuth.getCurrentUser();

                        mData=new HashMap<>();
                        mData.put("kullanıcı adı",txtisim);
                        mData.put("kullanıcı email",txtemail);
                        mData.put("kullanıcı sifresi",txtsifre);
                        mData.put("kullanıcı Uid",mUser.getUid());
                        mData.put("kullanıcı dogum tarihi",txtdtarihi);
                        mData.put("kullanıcı tel",txttel);

                        mReferans.child("kullanıcılar").child(mUser.getUid())
                                .setValue(mData)
                                .addOnCompleteListener(KayitEkrani.this, new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful())
                                            Toast.makeText( KayitEkrani.this,"kayıt ıslemı basarılı", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                    else
                    {
                        Toast.makeText(KayitEkrani.this, (CharSequence) task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        else{
            Toast.makeText(this,"email ve şifre boş olamaz",Toast.LENGTH_SHORT).show();
        }
    }
}