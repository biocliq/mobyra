package com.zitlab.mobyra.home.detail.marks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zitlab.mobyra.R;
import com.zitlab.mobyra.home.detail.marks.pojo.Marks;

import java.util.List;

public class MarksItemDetailsRecyclerViewAdapter extends RecyclerView.Adapter<MarksItemDetailsRecyclerViewAdapter.ViewHolder> {

    private final List<Marks> mValues;

    public MarksItemDetailsRecyclerViewAdapter(List<Marks> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText("" + mValues.get(position).getId());
        holder.mContentView.setText("Exam: " + mValues.get(position).getExam());
        holder.mContentSubTitle.setText("Student Code: " + mValues.get(position).getStudentCode());
        holder.mContentObject.setText(mValues.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView, mContentSubTitle, mContentObject;
        public Marks mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.item_number);
            mContentView = view.findViewById(R.id.contentTitle);
            mContentSubTitle = view.findViewById(R.id.contentSubTitle);
            mContentObject = view.findViewById(R.id.contentObject);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}