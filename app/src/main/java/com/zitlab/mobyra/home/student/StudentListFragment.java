package com.zitlab.mobyra.home.student;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.zitlab.mobyra.MobyraInstance;
import com.zitlab.mobyra.R;
import com.zitlab.mobyra.library.MobyraClient;
import com.zitlab.palmyra.builder.PaginatedQueryFilter;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class StudentListFragment extends Fragment {

    private static final String ARG_ROW_INDEX = "column-count";
    private int mRowIndex = 1;

    private List<Student> items;
    private StudentListRecyclerViewAdapter adapter = null;
    private LinearLayoutManager layoutManager = null;
    private RecyclerView recyclerView = null;
    private ProgressDialog pd;
    private int offsetSize = 4;
    private final int limit = 4;

    private MobyraInstance mobyraInstance;

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

        if (getArguments() != null) {
            mRowIndex = getArguments().getInt(ARG_ROW_INDEX);
        }
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
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setLayoutManager(new GridLayoutManager(context, mRowIndex));

        items = MobyraInstance.getInstance().getStudents().getResult();
        adapter = new StudentListRecyclerViewAdapter(items);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager, 4) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadMoreItems(totalItemsCount);
            }
        });

        return view;
    }

    private EndlessRecyclerViewScrollListener listener = new EndlessRecyclerViewScrollListener(layoutManager, 0) {
        @Override
        public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
            loadMoreItems(totalItemsCount);
        }
    };

    private void loadMoreItems(int offsetSize) {
        MobyraClient client = mobyraInstance.client();
        PaginatedQueryFilter queryFilter = new PaginatedQueryFilter();
        queryFilter.setLimit(offsetSize);
        client.query(queryFilter, Student.class, (status, response, exception) -> {
            if (status) {
                List<Student> newList = response.getResult();
                if (newList != null && newList.size() > 0) {
                    this.offsetSize = this.offsetSize + newList.size();
                    items.addAll(newList);
                    adapter = new StudentListRecyclerViewAdapter(items);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.d(">>>>>>", ">>>>>>> Load finished.........");
                    //recyclerView.removeOnScrollListener(listener);
                }
            } else {
                Log.d(">>>>>>", ">>>>>>> Load more error.........");
            }
        });
    }
}