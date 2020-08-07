package com.example.danhba;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.danhba.databinding.ActivityMainBinding;
import com.example.danhba.databinding.PhonebookBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        ActionBar ac = getSupportActionBar();
        ac.hide();
        //getFragment(InfoContact.newInstance("","",""));
        getFragment(PhoneBook.newInstance());
    }
    public void getFragment(Fragment fragment){
        try {
            getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment,fragment).commit();
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG,"getFragment"+e.getMessage());
        }
    }
}
