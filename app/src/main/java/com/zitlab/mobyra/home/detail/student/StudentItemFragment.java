package com.zitlab.mobyra.home.detail.student;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zitlab.mobyra.BaseActivity;
import com.zitlab.mobyra.R;
import com.zitlab.mobyra.home.apis.APIContent;
import com.zitlab.mobyra.home.detail.marks.MarksItemDetailsFragment;
import com.zitlab.mobyra.home.detail.marks.MarksItemRecyclerViewAdapter;
import com.zitlab.mobyra.home.dialog.SettingsDialogFragment;

/**
 * A fragment representing a list of Items.
 */
public class StudentItemFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StudentItemFragment() {
    }

    public static StudentItemFragment newInstance(int columnCount) {
        StudentItemFragment fragment = new StudentItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            //recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));

            StudentItemRecyclerViewAdapter adapter = new StudentItemRecyclerViewAdapter(APIContent.ITEMS_STUDENTS);
            recyclerView.setAdapter(adapter);
            adapter.setCardClickListener((view1, position) -> {
                StudentItemDetailsFragment fragment = StudentItemDetailsFragment.newInstance(position);
                ((BaseActivity) getActivity()).replaceFragment(R.id.fragmentContainer, fragment,
                        StudentItemDetailsFragment.class.getSimpleName(), StudentItemDetailsFragment.class.getSimpleName());
            });
        }

        getActivity().setTitle(getString(R.string.txt_students_api_list));
        ((BaseActivity) getActivity()).showBackButton();

        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.settings_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_action_settings:
                showSettingPopUp(getContext());
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showSettingPopUp(Context context) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        SettingsDialogFragment editNameDialogFragment = SettingsDialogFragment.newInstance("Settings");
        editNameDialogFragment.show(fm, "SettingsDialogFragment");
    }

    public interface OnCardClickListner {
        void OnCardClicked(View view, int position);
    }

}