package com.samu.samuchat;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.samu.samuchat.databinding.ActivityMainBinding;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

private ActivityMainBinding binding;

    private EditText editemail,editsifre,editisim,editdtarihi,edittel;
    private String txtemail,txtsifre,txtisim,txtdtarihi,txttel;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private DatabaseReference mReferans;
    private HashMap <String,Object> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        editemail=(EditText)findViewById(R.id.email);
        editsifre=(EditText)findViewById(R.id.sifre);
        editisim=(EditText)findViewById(R.id.isim);
        editdtarihi=(EditText)findViewById(R.id.dtarihi);
        edittel=(EditText)findViewById(R.id.tel);


        mAuth=FirebaseAuth.getInstance();
        mReferans= FirebaseDatabase.getInstance().getReference();

     binding = ActivityMainBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public void Kay??tol(View v)
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
                        mData.put("kullan??c?? ad??",txtisim);
                        mData.put("kullan??c?? email",txtemail);
                        mData.put("kullan??c?? sifresi",txtsifre);
                        mData.put("kullan??c?? Uid",mUser.getUid());
                        mData.put("kullan??c?? dogum tarihi",txtdtarihi);
                        mData.put("kullan??c?? tel",txttel);

                        mReferans.child("kullan??c??lar").child(mUser.getUid())
                                .setValue(mData)
                                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful())
                                            Toast.makeText( MainActivity.this,"kay??t ??slem?? basar??l??", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, (CharSequence) task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        else{
            Toast.makeText(this,"email ve ??ifre bo?? olamaz",Toast.LENGTH_SHORT).show();
        }
    }

}