package com.samu.samuchat.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.samu.samuchat.R;
import com.samu.samuchat.SohbetEkrani;
import com.samu.samuchat.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

private FragmentHomeBinding binding;

        public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

    binding = FragmentHomeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final TextView textView = binding.button;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;




    }
    public static class homeFragment extends AppCompatActivity {



        Button button;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_home);




            button=findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent kayit;
                    kayit = new Intent(homeFragment.this, SohbetEkrani.class);
                    startActivities(new Intent[]{kayit});
                }
            });


        }
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}




