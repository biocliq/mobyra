package com.zitlab.mobyra.home.student;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zitlab.mobyra.MobyraInstance;
import com.zitlab.mobyra.R;
import com.zitlab.mobyra.home.dialog.FilterDialogFragment;
import com.zitlab.mobyra.home.student.add.AddStudentActivity;
import com.zitlab.mobyra.library.MobyraClient;
import com.zitlab.mobyra.listview.EndlessScrollEventListener;
import com.zitlab.palmyra.builder.PaginatedQueryFilter;
import com.zitlab.palmyra.pojo.QueryResultSet;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class StudentListFragment extends Fragment {

    private static final String ARG_ROW_INDEX = "column-count";
    private final int limit = 4;
    private int total = 0, offsetSize = 0;
    private List<Student> items;
    private StudentListRecyclerViewAdapter adapter = null;
    private RecyclerView recyclerView = null;
    private ProgressDialog pd;
    private MobyraInstance mobyraInstance;

    private EndlessScrollEventListener endlessScrollEventListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StudentListFragment() {
    }

    @SuppressWarnings("unused")
    public static StudentListFragment newInstance(int columnCount) {
        StudentListFragment fragment = new StudentListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ROW_INDEX, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        mobyraInstance = MobyraInstance.getInstance();

        pd = new ProgressDialog(getContext());
        TextView criteriaText = view.findViewById(R.id.criteriaText);
        // Set the adapter

        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.list);
        QueryResultSet<Student> studentsQueryResult = MobyraInstance.getInstance().getStudents();
        items = studentsQueryResult.getResult();
        total = studentsQueryResult.getTotal();

        offsetSize = items.size();
        adapter = new StudentListRecyclerViewAdapter(items);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        endlessScrollEventListener = new EndlessScrollEventListener(layoutManager) {
            @Override
            public void onLoadMore(int pageNum, RecyclerView recyclerView) {
                if (offsetSize <= total) {
                    loadMoreItems();
                }
            }
        };
        recyclerView.addOnScrollListener(endlessScrollEventListener);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.student_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_action_add:
                Log.d(">>>>>>", ">>>>>>> Adding Student... ");
                Intent intent = new Intent(getActivity(), AddStudentActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.ic_action_filter:
                Log.d(">>>>>>", ">>>>>>> Adding filter to Student list... ");
                showFilterSettingsPopUp();
                break;
        }
        return true;

    }

    private void showFilterSettingsPopUp() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FilterDialogFragment dialogFragment = FilterDialogFragment.newInstance("Filter");
        dialogFragment.show(fm, "FilterDialogFragment");
    }

    private void loadMoreItems() {
        Log.d(">>>>>>", ">>>>>>> Loading offset index ........." + offsetSize);
        MobyraClient client = mobyraInstance.client();
        PaginatedQueryFilter queryFilter = new PaginatedQueryFilter();
        queryFilter.setLimit(limit);
        queryFilter.setOffset(offsetSize);
        queryFilter.setTotal(true);
        client.query(queryFilter, Student.class, (status, response, exception) -> {
            if (status.isStatus()) {
                total = response.getTotal();
                List<Student> newList = response.getResult();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            if (data.getBooleanExtra("status", false)) {
                offsetSize = 0;
                endlessScrollEventListener.reset();
                items.clear();
                loadMoreItems();
            } else {

            }
        }
    }
}