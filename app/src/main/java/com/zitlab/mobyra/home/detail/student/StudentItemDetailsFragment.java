package com.zitlab.mobyra.home.detail.student;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zitlab.mobyra.R;
import com.zitlab.mobyra.home.detail.marks.pojo.Marks;
import com.zitlab.mobyra.home.detail.student.pojo.Student;
import com.zitlab.mobyra.library.MobyraClient;
import com.zitlab.palmyra.ResponseCallback;
import com.zitlab.palmyra.builder.CriteriaBuilder;
import com.zitlab.palmyra.builder.MobyraClientBuilder;
import com.zitlab.palmyra.exception.PalmyraException;
import com.zitlab.palmyra.pojo.FieldCriteriaQueryFilter;

import java.util.ArrayList;
import java.util.List;

import dev.shreyaspatil.MaterialDialog.MaterialDialog;

/**
 * A fragment representing a list of Items.
 */
public class StudentItemDetailsFragment extends Fragment {

    private static final String ARG_ROW_INDEX = "column-count";
    private int mRowIndex = 1;

    private List<Student> items = new ArrayList<>();
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
        recyclerView = view.findViewById(R.id.list);
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
            mobyraClient.query(criteriaBuilder, Student.class, (status, response, exception) -> {
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
            FieldCriteriaQueryFilter queryFilter = new FieldCriteriaQueryFilter();
            queryFilter.setCriteria(criteriaBuilder);
            criteriaText.setText("Criteria: " + criteriaBuilder.toString());
            mobyraClient.findUnique(queryFilter, Student.class, (ResponseCallback<Student>) (status, response, exception) -> {
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
            mobyraClient.query(criteriaBuilder, Student.class, (status, response, exception) -> {
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
            mobyraClient.query(criteriaBuilder, Student.class, (status, response, exception) -> {
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
            mobyraClient.query(criteriaBuilder, Student.class, (status, response, exception) -> {
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
            mobyraClient.query(criteriaBuilder, Student.class, (status, response, exception) -> {
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

            Student result = new Student();
            result.setStudentName("JOHN");
            result.setStudentCode("SD9000");
            result.setStudentClass("5");
            result.setDob("2010-01-02");
            criteriaText.setText("Request object: " + result.toString());

            mobyraClient.save(result, Student.class, (ResponseCallback<Student>) (status, response, exception) -> {
                pd.dismiss();

                if (status) {
                    items = new ArrayList<>();
                    adapter = new StudentItemDetailsRecyclerViewAdapter(items);
                    recyclerView.setAdapter(adapter);
                    Toast.makeText(getContext(), "Data successfully updated.", Toast.LENGTH_SHORT).show();
                } else {
                    showErrorDialog(exception);
                }
            });
        } else if (mRowIndex == 8) {
            Marks marks = new Marks();
            marks.setExam("EX03");
            marks.setEnglish(90);
            marks.setMaths(85);
            marks.setScience(90);
            marks.setTamil(86);
            marks.setHistory(75);
            List<Marks> marksList = new ArrayList<>();
            marksList.add(marks);

            Student result = new Student();
            result.setStudentName("JOHN");
            result.setStudentCode("SD9000");
            result.setStudentClass("5");
            result.setDob("2010-01-02");
            result.setMarks(marksList);

            criteriaText.setText("Request object: " + marksList.toString());
            mobyraClient.save(result, Student.class, (ResponseCallback<Student>) (status, response, exception) -> {
                pd.dismiss();

                if (status) {
                    items = new ArrayList<>();
                    adapter = new StudentItemDetailsRecyclerViewAdapter(items);
                    recyclerView.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Data successfully updated.", Toast.LENGTH_SHORT).show();
                } else {
                    showErrorDialog(exception);
                }
            });
        } else if (mRowIndex == 9) {
            Student result = new Student();
            result.setStudentName("JOHN");
            result.setStudentCode("SD9000");
            result.setStudentClass("5");
            result.setDob("2010-01-02");
            criteriaText.setText("Request object: " + result.toString());

            mobyraClient.save(result, Student.class, (ResponseCallback<Student>) (status, response, exception) -> {
                pd.dismiss();

                if (status) {
                    items = new ArrayList<>();
                    adapter = new StudentItemDetailsRecyclerViewAdapter(items);
                    recyclerView.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Data successfully updated.", Toast.LENGTH_SHORT).show();
                } else {
                    showErrorDialog(exception);
                }
            });
        } else if (mRowIndex == 10) {
            Student result = new Student();
            result.setStudentName("JOHN");
            result.setStudentCode("SD9000");
            result.setStudentClass("5");
            result.setDob("2010-01-02");
            criteriaText.setText("Request object: " + result.toString());

            mobyraClient.save(result, Student.class, (ResponseCallback<Student>) (status, response, exception) -> {
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
            mobyraClient.query(criteriaBuilder, Student.class, (status, response, exception) -> {
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


    private void showErrorDialog(PalmyraException exception) {
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