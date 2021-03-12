package com.zitlab.mobyra.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zitlab.mobyra.BaseActivity;
import com.zitlab.mobyra.R;
import com.zitlab.mobyra.home.detail.marks.MarksItemFragment;
import com.zitlab.mobyra.home.detail.student.StudentItemFragment;

public class HomeScrollingFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_scrolling, container, false);

        Button student = view.findViewById(R.id.studentApisButton);
        student.setOnClickListener(v -> {
            ((BaseActivity)getActivity()).replaceFragment(R.id.fragmentContainer, StudentItemFragment.newInstance(1), StudentItemFragment.class.getSimpleName(), StudentItemFragment.class.getSimpleName());
        });

        Button marks = view.findViewById(R.id.marksApisButton);
        marks.setOnClickListener( v -> {
            ((BaseActivity)getActivity()).replaceFragment(R.id.fragmentContainer, MarksItemFragment.newInstance(1), MarksItemFragment.class.getSimpleName(), MarksItemFragment.class.getSimpleName());
        });
        getActivity().setTitle(getString(R.string.app_name));
        ((BaseActivity) getActivity()).hideBackButton();

        return view;
    }
}