package com.zitlab.mobyra.home.marks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zitlab.mobyra.MobyraInstance;
import com.zitlab.mobyra.R;
import com.zitlab.mobyra.home.OnItemClickListener;
import com.zitlab.mobyra.home.detail.marks.pojo.Marks;
import com.zitlab.mobyra.home.marks.dummy.DummyContent;
import com.zitlab.mobyra.home.student.Student;
import com.zitlab.mobyra.library.MobyraClient;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class MarksItemFragment extends Fragment {
    private static final String ARG_STUDENT_CODE = "studentCode";
    private final RecyclerView recyclerView = null;
    private String studentCode;
    private MobyraInstance mobyraInstance;
    private int total = 0, offsetSize = 0;
    private List<Marks> items;
    private ProgressDialog pd;
    private MyMarksItemRecyclerViewAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MarksItemFragment() {
    }

    public static MarksItemFragment newInstance(String studentCode) {
        MarksItemFragment fragment = new MarksItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_STUDENT_CODE, studentCode);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            studentCode = getArguments().getString(ARG_STUDENT_CODE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_marks_item_list, container, false);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("Marks");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        items = new ArrayList<>();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            adapter = new MyMarksItemRecyclerViewAdapter(items, item -> {

            });
            recyclerView.setAdapter(adapter);
        }

        mobyraInstance = MobyraInstance.getInstance();

        pd = new ProgressDialog(getContext());
        pd.show();
        // Set the adapter
        loadItems();

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                break;
        }
        return true;

    }


    private void loadItems() {
        MobyraClient client = mobyraInstance.client();
        Marks marks = new Marks();
        marks.setStudentCode(studentCode);
        client.query(marks, Marks.class, (status, response, exception) -> {
            pd.dismiss();
            if (status.isStatus()) {
                total = response.getTotal();
                List<Marks> newList = response.getResult();
                if (newList != null && newList.size() > 0) {
                    this.offsetSize = this.offsetSize + newList.size();
                    items.addAll(newList);
                    offsetSize = items.size();
                    adapter.notifyDataSetChanged();
                } else {
                    Log.d(">>>>>>", ">>>>>>> All records loaded successfully : " + items.size());
                    //recyclerView.removeOnScrollListener(listener);
                }
            } else {
                Log.d(">>>>>>", ">>>>>>> Load more error.........");
            }
        });
    }
}