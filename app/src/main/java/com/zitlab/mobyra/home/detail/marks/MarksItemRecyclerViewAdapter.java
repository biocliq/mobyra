package com.zitlab.mobyra.home.detail.marks;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zitlab.mobyra.R;
import com.zitlab.mobyra.home.detail.student.StudentItemFragment;

import java.util.List;

public class MarksItemRecyclerViewAdapter extends RecyclerView.Adapter<MarksItemRecyclerViewAdapter.ViewHolder> {

    private final List<Pair<String, String>> mValues;
    StudentItemFragment.OnCardClickListner onCardClickListner;

    public MarksItemRecyclerViewAdapter(List<Pair<String, String>> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    public void setCardClickListener(StudentItemFragment.OnCardClickListner listener) {
        this.onCardClickListner = listener;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText("" + (position + 1));
        holder.mContentView.setText(mValues.get(position).first);
        holder.mSubContentView.setText(mValues.get(position).second);

        holder.mView.setOnClickListener(v -> onCardClickListner.OnCardClicked(v, position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mSubContentView;
        public Pair<String, String> mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.item_number);
            mContentView = view.findViewById(R.id.content);
            mSubContentView = view.findViewById(R.id.sub_content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}