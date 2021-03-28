package com.zitlab.mobyra;

import android.os.Bundle;

import androidx.fragment.app.ListFragment;

import com.zitlab.mobyra.login.ui.login.LoginFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFragment(R.id.fragmentContainer, new LoginFragment(), ListFragment.class.getSimpleName());

    }
}