package com.zitlab.mobyra.home.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.zitlab.mobyra.R;
import com.zitlab.palmyra.builder.CriteriaBuilder;

import java.util.Map;

public class FilterDialogFragment extends DialogFragment implements View.OnClickListener {

    private EditText editTextFieldName, editTextFieldValue;
    private Spinner spinnerOperation;
    private Button saveBtn, cancelBtn;
    private OnDialogButtonClick listener;

    public FilterDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static FilterDialogFragment newInstance(String title) {
        FilterDialogFragment frag = new FilterDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_MaterialComponents_DayNight_Dialog_Alert);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_filter, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        editTextFieldName = view.findViewById(R.id.editTextFieldName);
        spinnerOperation = view.findViewById(R.id.spinnerOperation);
        editTextFieldValue = view.findViewById(R.id.editTextFieldValue);

        saveBtn = view.findViewById(R.id.saveButton);
        saveBtn.setOnClickListener(this);
        cancelBtn = view.findViewById(R.id.cancelButton);
        cancelBtn.setOnClickListener(this);


        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", getString(R.string.app_name));
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field
        //hostName.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public int show(@NonNull FragmentTransaction transaction, @Nullable String tag) {
        return super.show(transaction, tag);
    }

    public void show(@NonNull FragmentManager manager, @NonNull String tag,  OnDialogButtonClick listener) {
        super.show(manager, tag);
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {

        if (v == saveBtn) {
            CriteriaBuilder builder = null;
            if(spinnerOperation.getSelectedItemPosition() == 0){
                builder = new CriteriaBuilder.Builder()
                        .keyValue(editTextFieldName.getText().toString(), editTextFieldValue.getText().toString())
                        .build();
            }else  if(spinnerOperation.getSelectedItemPosition() == 1){
                builder = new CriteriaBuilder.Builder()
                        .keyValueNot(editTextFieldName.getText().toString(), editTextFieldValue.getText().toString())
                        .build();
            }else  if(spinnerOperation.getSelectedItemPosition() == 2){
                builder = new CriteriaBuilder.Builder()
                        .keyValueGreaterThan(editTextFieldName.getText().toString(), editTextFieldValue.getText().toString())
                        .build();
            }else  if(spinnerOperation.getSelectedItemPosition() == 3){
                builder = new CriteriaBuilder.Builder()
                        .keyValueGreaterThanOrEqual(editTextFieldName.getText().toString(), editTextFieldValue.getText().toString())
                        .build();
            }else  if(spinnerOperation.getSelectedItemPosition() == 4){
                builder = new CriteriaBuilder.Builder()
                        .keyValueLessThan(editTextFieldName.getText().toString(), editTextFieldValue.getText().toString())
                        .build();
            }else  if(spinnerOperation.getSelectedItemPosition() == 5){
                builder = new CriteriaBuilder.Builder()
                        .keyValueLessThanOrEqual(editTextFieldName.getText().toString(), editTextFieldValue.getText().toString())
                        .build();
            }

            listener.onPositiveClick(builder);
            getDialog().dismiss();
        }

        if (v == cancelBtn) {
            listener.onNegativeClick();
            getDialog().dismiss();
        }
    }

    public interface  OnDialogButtonClick {

        void onPositiveClick(CriteriaBuilder filter);

        void onNegativeClick();
    }
}
