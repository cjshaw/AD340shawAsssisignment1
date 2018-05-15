package com.example.shawasssisignment1;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shawasssisignment1.model.Matches;
import com.squareup.picasso.Picasso;

import java.util.List;



public class MatchesRecyclerViewAdapter extends RecyclerView.Adapter<MatchesRecyclerViewAdapter.ViewHolder> {
    private final List<Matches> mValues;
    private final MatchesTabFragment.OnListFragmentInteractionListener mListener;

    public MatchesRecyclerViewAdapter(List<Matches> items, MatchesTabFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.matches_fragment, parent, false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        //holder.mLikeStatus.set(String.format("%s", mValues.get(position).liked));
        holder.mName.setText(mValues.get(position).name);
        //holder.mImgUrl.get(mValues.get(position).imageUrl);

        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onListFragmentInteraction(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mValues != null) {
            return mValues.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public final TextView mName;
        //public final ImageView mImgUrl;
        public Matches mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mName = view.findViewById(R.id.card_title);
            //mImgUrl = view.findViewById(R.id.card_image);

        }
    }
}
