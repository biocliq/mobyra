package com.zitlab.mobyra.home.detail;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zitlab.mobyra.R;
import com.zitlab.mobyra.home.detail.pojo.marks.Result;

import java.util.List;

public class ItemDetailsRecyclerViewAdapter extends RecyclerView.Adapter<ItemDetailsRecyclerViewAdapter.ViewHolder> {

    private final List<Result> mValues;

    public ItemDetailsRecyclerViewAdapter(List<Result> items) {
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
        public Result mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.contentTitle);
            mContentSubTitle = (TextView) view.findViewById(R.id.contentSubTitle);
            mContentObject = (TextView) view.findViewById(R.id.contentObject);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}