package com.zitlab.mobyra;

import androidx.fragment.app.ListFragment;

import android.os.Bundle;

import com.zitlab.mobyra.home.HomeScrollingFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFragment(R.id.fragmentContainer, new HomeScrollingFragment(), ListFragment.class.getSimpleName());

    }
}