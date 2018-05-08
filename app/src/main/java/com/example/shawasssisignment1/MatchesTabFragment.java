package com.example.shawasssisignment1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MatchesTabFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycleviewer, container, false);

        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView picture;
        public TextView name;
        public TextView description;
        public ImageButton likeButton;
        boolean flag = false;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.matches_fragment, parent, false));

            picture =  itemView.findViewById(R.id.card_image);
            name =  itemView.findViewById(R.id.card_title);
            description = itemView.findViewById(R.id.card_text);
            likeButton = itemView.findViewById(R.id.like_button);

            likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!flag) {
                        Toast.makeText(itemView.getContext(), "You liked " + name.getText().toString(), Toast.LENGTH_LONG).show();
                        flag = true;
                        likeButton.setColorFilter(Color.RED);
                    } else {
                        flag = false;
                        likeButton.setColorFilter(Color.GRAY);
                    }
                }
            });
        }
    }

    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.

        private final String[] mNames;
        private final String[] mDesc;
        private final Drawable[] mPictures;
        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            mNames = resources.getStringArray(R.array.matches_names);
            mDesc = resources.getStringArray(R.array.matches_desc);
            TypedArray a = resources.obtainTypedArray(R.array.matches_picture);
            mPictures = new Drawable[a.length()];
            for (int i = 0; i < mPictures.length; i++) {
                mPictures[i] = a.getDrawable(i);
            }
            a.recycle();
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.picture.setImageDrawable(mPictures[position % mPictures.length]);
            holder.name.setText(mNames[position % mNames.length]);
            holder.description.setText(mDesc[position % mDesc.length]);
        }

        @Override
        public int getItemCount() {
            return mNames.length;
        }
    }
}


