package com.zitlab.mobyra.home.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.pixplicity.easyprefs.library.Prefs;
import com.zitlab.mobyra.Constants;
import com.zitlab.mobyra.R;

public class FilterDialogFragment extends DialogFragment implements View.OnClickListener {

    private EditText hostName, appName, contextName;
    private Button saveBtn, cancelBtn;


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
        hostName = view.findViewById(R.id.editTextHostName);
        appName = view.findViewById(R.id.editTextAppName);
        contextName = view.findViewById(R.id.editTextContextName);

        hostName.setText(Prefs.getString(Constants.KEY_HOST_NAME, ""));
        appName.setText(Prefs.getString(Constants.KEY_APP_NAME, ""));
        contextName.setText(Prefs.getString(Constants.KEY_CONTEXT_NAME, ""));

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
    public void onClick(View v) {

        if (v == saveBtn) {
            String hostNameTxt = hostName.getText().toString();
            String appNameTxt = appName.getText().toString();
            String contextTxt = contextName.getText().toString();

            Prefs.putString(Constants.KEY_HOST_NAME, hostNameTxt);
            Prefs.putString(Constants.KEY_APP_NAME, appNameTxt);
            Prefs.putString(Constants.KEY_CONTEXT_NAME, contextTxt);

            getDialog().dismiss();
        }

        if (v == cancelBtn) {
            getDialog().dismiss();
        }
    }
}
