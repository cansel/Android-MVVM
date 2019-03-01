package com.demo.example.view.userinterface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mvvm.restapi.example.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            GamesListFragment fragment = new GamesListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragment, GamesListFragment.TAG).commit();
        }
    }
}
