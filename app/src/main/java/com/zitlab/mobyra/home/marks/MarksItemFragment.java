package com.zitlab.mobyra.home.marks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zitlab.mobyra.MobyraInstance;
import com.zitlab.mobyra.R;
import com.zitlab.mobyra.home.detail.marks.pojo.Marks;
import com.zitlab.mobyra.home.marks.dummy.DummyContent;
import com.zitlab.mobyra.home.student.Student;
import com.zitlab.mobyra.library.MobyraClient;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class MarksItemFragment extends Fragment {
    private static final String ARG_COLUMN_COUNT = "column-count";
    private final int limit = 4;
    private int mColumnCount = 1;
    private MobyraInstance mobyraInstance;
    private int total = 0, offsetSize = 0;
    private List<Marks> items;
    private final RecyclerView recyclerView = null;
    private ProgressDialog pd;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MarksItemFragment() {
    }

    public static MarksItemFragment newInstance(int columnCount) {
        MarksItemFragment fragment = new MarksItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_marks_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyMarksItemRecyclerViewAdapter(DummyContent.ITEMS));
        }

        mobyraInstance = MobyraInstance.getInstance();

        pd = new ProgressDialog(getContext());
        // Set the adapter
        loadItems();

        return view;
    }

    private void loadItems() {
        Log.d(">>>>>>", ">>>>>>> Loading offset index ........." + offsetSize);
        MobyraClient client = mobyraInstance.client();
        Marks marks = new Marks();
        marks.setStudentCode("");
        client.query(marks, Student.class, (status, response, exception) -> {
            if (status.isStatus()) {
                total = response.getTotal();
                List<Student> newList = response.getResult();
                if (newList != null && newList.size() > 0) {
                    this.offsetSize = this.offsetSize + newList.size();
//                    items.addAll(newList);
//                    offsetSize = items.size();
//                    adapter.notifyDataSetChanged();
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