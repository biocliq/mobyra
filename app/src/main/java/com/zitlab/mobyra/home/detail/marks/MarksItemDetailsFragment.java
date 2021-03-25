package com.zitlab.mobyra.home.detail.marks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zitlab.mobyra.R;
import com.zitlab.mobyra.home.detail.marks.pojo.Marks;
import com.zitlab.mobyra.home.detail.marks.pojo.Result;
import com.zitlab.mobyra.library.MobyraClient;
import com.zitlab.mobyra.library.MobyraResponseCallback;
import com.zitlab.mobyra.library.builder.CriteriaBuilder;
import com.zitlab.mobyra.library.builder.MobyraClientBuilder;

import java.util.ArrayList;
import java.util.List;

import dev.shreyaspatil.MaterialDialog.MaterialDialog;

/**
 * A fragment representing a list of Items.
 */
public class MarksItemDetailsFragment extends Fragment {

    private static final String ARG_ROW_INDEX = "column-count";
    private int mRowIndex = 1;

    private List<Result> items = new ArrayList<>();
    private MarksItemDetailsRecyclerViewAdapter adapter = null;
    private RecyclerView recyclerView = null;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MarksItemDetailsFragment() {
    }

    @SuppressWarnings("unused")
    public static MarksItemDetailsFragment newInstance(int columnCount) {
        MarksItemDetailsFragment fragment = new MarksItemDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ROW_INDEX, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mRowIndex = getArguments().getInt(ARG_ROW_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_details_list, container, false);

        TextView criteriaText = view.findViewById(R.id.criteriaText);
        // Set the adapter

        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        //recyclerView.setLayoutManager(new GridLayoutManager(context, mRowIndex));

        adapter = new MarksItemDetailsRecyclerViewAdapter(items);
        recyclerView.setAdapter(adapter);


        //Creating Criteria Builder
        CriteriaBuilder criteriaBuilder;
        if (mRowIndex == 1) {
            criteriaBuilder = new CriteriaBuilder.Builder()
                    .keyValueGreaterThan("english", "70.00")
                    .keyValueLessThanOrEqual("maths", "90.00")
                    .keyValueBetween("science", "85", "92")
                    .build();
        } else if (mRowIndex == 2) {
            criteriaBuilder = new CriteriaBuilder.Builder()
                    .keyValueGreaterThan("english", "70.00")
                    .keyValueLessThanOrEqual("maths", "90.00")
                    .keyValueNot("science", "90")
                    .build();
        } else {
            criteriaBuilder = new CriteriaBuilder.Builder().build();
        }

        criteriaText.setText("Criteria: " + criteriaBuilder.toString());

        ProgressDialog pd = new ProgressDialog(getContext());
        pd.setMessage("loading...");
        pd.show();

        //Setting up Mobyra Client
        MobyraClientBuilder builder = new MobyraClientBuilder.Builder("api.fluwiz.com")
                .withUsernamePassword("admin", "ad")
                .withAppName("palmyra")
                .withContext("apidev")
                .withApiVersion("v1")
                .withLogLevel(MobyraClientBuilder.LogLevel.BASIC)
                .build();
        MobyraClient mobyraClient = new MobyraClient(builder);
        //----------------------------------------

        mobyraClient.query(criteriaBuilder, Marks.class, (MobyraResponseCallback<Marks>) (status, response, exception) -> {
            pd.dismiss();

            if (status) {
                items = response.getResult();
                if (adapter != null) {
                    adapter = new MarksItemDetailsRecyclerViewAdapter(items);
                    recyclerView.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();
                }
            } else {
                MaterialDialog mDialog = new MaterialDialog.Builder(getActivity())
                        .setTitle(getString(R.string.app_name))
                        .setMessage((exception != null && exception.getMessage() != null) ? exception.getMessage() : "Something unexpected happened. Please try again after sometime.")
                        .setCancelable(false)
                        .setPositiveButton("OK", (dialogInterface, which) -> {
                            // Operation
                            dialogInterface.dismiss();
                        })
                        .setNegativeButton("Cancel", (dialogInterface, which) -> {
                            dialogInterface.dismiss();
                            getActivity().onBackPressed();
                        })
                        .build();
                // Show Dialog
                mDialog.show();
            }
        });

        return view;
    }
}