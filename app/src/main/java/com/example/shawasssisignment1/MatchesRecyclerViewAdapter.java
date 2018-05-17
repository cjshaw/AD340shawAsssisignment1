package com.example.shawasssisignment1;

import android.graphics.Color;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        holder.mName.setText(mValues.get(position).name);
        holder.mImgUrl = mValues.get(position).imageUrl; // get image URL from object
        Picasso.get().load(holder.mImgUrl).into(holder.mImage); // set image url into ImageView
        holder.liked = mValues.get(position).liked;

        if (!holder.liked) {
            holder.likeBtn.setColorFilter(Color.GRAY);
        } else {
            holder.likeBtn.setColorFilter(Color.RED);
        }

        holder.likeBtn.setOnClickListener(v -> {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                if (!holder.liked) {
                    Toast.makeText(holder.mView.getContext(), "You liked " + mValues.get(position).name, Toast.LENGTH_LONG).show();
                    holder.likeBtn.setColorFilter(Color.RED);
                } else {
                    holder.likeBtn.setColorFilter(Color.GRAY);
                }
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
        public final ImageView mImage;
        public final ImageButton likeBtn;
        public String mImgUrl;
        public Matches mItem;
        public Boolean liked;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mName = view.findViewById(R.id.card_title);
            mImage = view.findViewById(R.id.card_image);
            likeBtn = view.findViewById(R.id.like_button);
        }
    }
}
