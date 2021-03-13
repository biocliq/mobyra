package com.zitlab.mobyra.home.detail.student;

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
import com.zitlab.mobyra.home.detail.student.pojo.Result;
import com.zitlab.mobyra.home.detail.student.pojo.Student;
import com.zitlab.mobyra.library.MobyraClient;
import com.zitlab.mobyra.library.MobyraResponseCallback;
import com.zitlab.mobyra.library.builder.CriteriaBuilder;
import com.zitlab.mobyra.library.builder.MobyraClientBuilder;
import com.zitlab.mobyra.library.exception.MobyraException;

import java.util.ArrayList;
import java.util.List;

import dev.shreyaspatil.MaterialDialog.MaterialDialog;

/**
 * A fragment representing a list of Items.
 */
public class StudentItemDetailsFragment extends Fragment {

    private static final String ARG_ROW_INDEX = "column-count";
    private int mRowIndex = 1;

    private List<Result> items = new ArrayList<>();
    private StudentItemDetailsRecyclerViewAdapter adapter = null;
    private RecyclerView recyclerView = null;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StudentItemDetailsFragment() {
    }

    @SuppressWarnings("unused")
    public static StudentItemDetailsFragment newInstance(int columnCount) {
        StudentItemDetailsFragment fragment = new StudentItemDetailsFragment();
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

        adapter = new StudentItemDetailsRecyclerViewAdapter(items);
        recyclerView.setAdapter(adapter);

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

        ProgressDialog pd = new ProgressDialog(getContext());
        pd.setMessage("loading...");
        pd.show();


        //Creating Criteria Builder
        CriteriaBuilder criteriaBuilder;
        if (mRowIndex == 1) {
            criteriaBuilder = new CriteriaBuilder.Builder()
                    .build();
            criteriaText.setText("Criteria: " + criteriaBuilder.toString());
            mobyraClient.query(criteriaBuilder, Student.class, (MobyraResponseCallback<Student>) (status, response, exception) -> {
                pd.dismiss();

                if (status) {
                    items = response.getResult();


                    adapter = new StudentItemDetailsRecyclerViewAdapter(items);
                    recyclerView.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();

                } else {
                    showErrorDialog(exception);
                }
            });
        } else if (mRowIndex == 2) {
            criteriaBuilder = new CriteriaBuilder.Builder()
                    .keyValue("studentCode", "SD9000")
                    .build();
            criteriaText.setText("Criteria: " + criteriaBuilder.toString());
            mobyraClient.findUnique(criteriaBuilder, Result.class, (MobyraResponseCallback<Result>) (status, response, exception) -> {
                pd.dismiss();

                if (status) {
                    items = new ArrayList<>();
                    items.add(response);
                    adapter = new StudentItemDetailsRecyclerViewAdapter(items);
                    recyclerView.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();
                } else {
                    showErrorDialog(exception);
                }
            });
        } else if (mRowIndex == 3) {
            criteriaBuilder = new CriteriaBuilder.Builder()
                    .keyValueGreaterThan("dob", "2010-03-01")
                    .build();
            criteriaText.setText("Criteria: " + criteriaBuilder.toString());
            mobyraClient.query(criteriaBuilder, Student.class, (MobyraResponseCallback<Student>) (status, response, exception) -> {
                pd.dismiss();

                if (status) {
                    items = response.getResult();
                    adapter = new StudentItemDetailsRecyclerViewAdapter(items);
                    recyclerView.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();
                } else {
                    showErrorDialog(exception);
                }
            });
        } else if (mRowIndex == 4) {
            criteriaBuilder = new CriteriaBuilder.Builder()
                    .keyValueBetween("dob", "2010-02-01", "2013-02-01")
                    .build();
            criteriaText.setText("Criteria: " + criteriaBuilder.toString());
            mobyraClient.query(criteriaBuilder, Student.class, (MobyraResponseCallback<Student>) (status, response, exception) -> {
                pd.dismiss();

                if (status) {
                    items = response.getResult();
                    adapter = new StudentItemDetailsRecyclerViewAdapter(items);
                    recyclerView.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();
                } else {
                    showErrorDialog(exception);
                }
            });
        } else if (mRowIndex == 5) {
            criteriaBuilder = new CriteriaBuilder.Builder()
                    .keyValue("studentName", "JOHN")
                    .build();
            criteriaText.setText("Criteria: " + criteriaBuilder.toString());
            mobyraClient.query(criteriaBuilder, Student.class, (MobyraResponseCallback<Student>) (status, response, exception) -> {
                pd.dismiss();

                if (status) {
                    items = response.getResult();
                    adapter = new StudentItemDetailsRecyclerViewAdapter(items);
                    recyclerView.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();
                } else {
                    showErrorDialog(exception);
                }
            });
        } else if (mRowIndex == 6) {
            criteriaBuilder = new CriteriaBuilder.Builder()
                    .keyValueContains("studentName", "O")
                    .build();
            criteriaText.setText("Criteria: " + criteriaBuilder.toString());
            mobyraClient.query(criteriaBuilder, Student.class, (MobyraResponseCallback<Student>) (status, response, exception) -> {
                pd.dismiss();

                if (status) {
                    items = response.getResult();
                    adapter = new StudentItemDetailsRecyclerViewAdapter(items);
                    recyclerView.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();
                } else {
                    showErrorDialog(exception);
                }
            });
        } else if (mRowIndex == 7) {

            Result result = new Result();
            result.setStudentName("JOHN");
            result.setStudentCode("SD9000");
            result.setStudentClass("5");
            result.setDob("2010-01-02");
            criteriaText.setText("Request object: " + result.toString());

            mobyraClient.save(result, Result.class, (MobyraResponseCallback<Result>) (status, response, exception) -> {
                pd.dismiss();

                if (status) {
                    items = new ArrayList<>();
                    items.add(result);
                    adapter = new StudentItemDetailsRecyclerViewAdapter(items);
                    recyclerView.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();

                } else {
                    showErrorDialog(exception);
                }
            });
        } else if (mRowIndex == 8) {
            Result result = new Result();
            result.setStudentName("JOHN");
            result.setStudentCode("SD9000");
            result.setStudentClass("5");
            result.setDob("2010-01-02");

            com.zitlab.mobyra.home.detail.marks.pojo.Result marks = new com.zitlab.mobyra.home.detail.marks.pojo.Result();
            marks.setExam("EX03");
            marks.setEnglish(90);
            marks.setMaths(85);
            marks.setScience(90);
            marks.setTamil(86);
            marks.setHistory(75);

            List<com.zitlab.mobyra.home.detail.marks.pojo.Result> marksList = new ArrayList<>();
            marksList.add(marks);
            result.setMarks(marksList);
            criteriaText.setText("Request object: " + result.toString());

            mobyraClient.save(result, Result.class, (MobyraResponseCallback<Result>) (status, response, exception) -> {
                pd.dismiss();

                if (status) {
                    items.add(result);
                    adapter = new StudentItemDetailsRecyclerViewAdapter(items);
                    recyclerView.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();
                } else {
                    showErrorDialog(exception);
                }
            });
        } else if (mRowIndex == 9) {
            Result result = new Result();
            result.setStudentName("JOHN");
            result.setStudentCode("SD9000");
            result.setStudentClass("5");
            result.setDob("2010-01-02");
            criteriaText.setText("Request object: " + result.toString());

            mobyraClient.save(result, Result.class, (MobyraResponseCallback<Result>) (status, response, exception) -> {
                pd.dismiss();

                if (status) {
                    items = new ArrayList<>();
                    items.add(result);
                    adapter = new StudentItemDetailsRecyclerViewAdapter(items);
                    recyclerView.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();

                } else {
                    showErrorDialog(exception);
                }
            });
        } else if (mRowIndex == 10) {
            Result result = new Result();
            result.setStudentName("JOHN");
            result.setStudentCode("SD9000");
            result.setStudentClass("5");
            result.setDob("2010-01-02");
            criteriaText.setText("Request object: " + result.toString());

            mobyraClient.save(result, Result.class, (MobyraResponseCallback<Result>) (status, response, exception) -> {
                pd.dismiss();

                if (status) {
                    items = new ArrayList<>();
                    items.add(response);
                    adapter = new StudentItemDetailsRecyclerViewAdapter(items);
                    recyclerView.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();

                } else {
                    showErrorDialog(exception);
                }
            });
        } else {
            criteriaBuilder = new CriteriaBuilder.Builder().build();
            mobyraClient.query(criteriaBuilder, Student.class, (MobyraResponseCallback<Student>) (status, response, exception) -> {
                pd.dismiss();

                if (status) {
                    items = response.getResult();
                    adapter = new StudentItemDetailsRecyclerViewAdapter(items);
                    recyclerView.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();
                } else {
                    showErrorDialog(exception);
                }
            });
        }

        return view;
    }


    private void showErrorDialog(MobyraException exception) {
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
}