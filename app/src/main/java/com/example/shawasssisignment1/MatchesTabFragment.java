package com.example.shawasssisignment1;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MatchesTabFragment extends Fragment {

    RecyclerView recyclerView;
    ContentAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
//    Parcelable listState;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycleviewer, container, false);

            adapter = new ContentAdapter(recyclerView.getContext());
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);

            //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);

        return recyclerView;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView picture;
        public TextView name;
        public TextView description;
        public ImageButton likeButton;
        public boolean liked = false;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.matches_fragment, parent, false));

            picture =  itemView.findViewById(R.id.card_image);
            name =  itemView.findViewById(R.id.card_title);
            description = itemView.findViewById(R.id.card_text);
            likeButton = itemView.findViewById(R.id.like_button);
        }
    }

    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        private final String[] mNames;
        private final String[] mDesc;
        private final Drawable[] mPictures;
        private final Context mContext;
        private final Boolean[] mLikeStatus;

//        public ContentAdapter(Context context, String[] names, String[] desc, Drawable[] pictures, Boolean[] likeStatus) {
//            mContext = context;
//            Resources resources = context.getResources();
//            names = resources.getStringArray(R.array.matches_names);
//            desc = resources.getStringArray(R.array.matches_desc);
//            TypedArray a = resources.obtainTypedArray(R.array.matches_picture);
//            pictures = new Drawable[a.length()];
//            TypedArray b = resources.obtainTypedArray(R.array.likes);
//            likeStatus = new Boolean[b.length()];
//            for (int i = 0; i < pictures.length; i++) {
//                pictures[i] = a.getDrawable(i);
//                likeStatus[i] = b.getBoolean(i, false);
//            }
//            a.recycle();
//            b.recycle();
//        }

        public ContentAdapter(Context context) {
            mContext = context;
            Resources resources = context.getResources();
            mNames = resources.getStringArray(R.array.matches_names);
            mDesc = resources.getStringArray(R.array.matches_desc);
            TypedArray a = resources.obtainTypedArray(R.array.matches_picture);
            mPictures = new Drawable[a.length()];
            TypedArray b = resources.obtainTypedArray(R.array.likes);
            mLikeStatus = new Boolean[b.length()];
            for (int i = 0; i < mPictures.length; i++) {
                mPictures[i] = a.getDrawable(i);
                mLikeStatus[i] = b.getBoolean(i, false);
            }
            a.recycle();
            b.recycle();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.picture.setImageDrawable(mPictures[position % mPictures.length]);
            holder.name.setText(mNames[position % mNames.length]);
            holder.description.setText(mDesc[position % mDesc.length]);
            //Pass the on restore bundle here
            holder.likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!holder.liked) {
                        Toast.makeText(mContext, "You liked " + holder.name.getText().toString(), Toast.LENGTH_LONG).show();
                        holder.liked = true;
                        holder.likeButton.setColorFilter(Color.RED);
                        mLikeStatus[position] = holder.liked;
                    } else {
                        holder.liked = false;
                        holder.likeButton.setColorFilter(Color.GRAY);
                        mLikeStatus[position] = holder.liked;
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mNames.length;
        }
    }

//    @Override
//        public void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        listState = layoutManager.onSaveInstanceState();
//        outState.putParcelable(Constants.KEY_RV_STATE, listState);
//    }
}


