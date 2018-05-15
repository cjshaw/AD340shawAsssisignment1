package com.example.shawasssisignment1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shawasssisignment1.model.Matches;
import com.example.shawasssisignment1.viewmodels.MatchesViewModel;

import java.util.ArrayList;
import java.util.List;

public class MatchesTabFragment extends Fragment {

    public static final String ARG_COLUMN_COUNT = "column-count";
    public static final String ARG_DATA_SET = "matches";

    private int mColumnCount = 6;
    private List<Matches> mDataSet;
    private OnListFragmentInteractionListener mListener;
    private MatchesViewModel viewModel;

    public MatchesTabFragment(){

    }

    @SuppressWarnings("unused")
    public static MatchesTabFragment newInstance(int columnCount) {
        MatchesTabFragment fragment = new MatchesTabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            mDataSet = getArguments().getParcelableArrayList(ARG_DATA_SET);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        RecyclerView view = (RecyclerView) inflater.inflate(
                R.layout.recycleviewer, container, false);

        viewModel = new MatchesViewModel();
        viewModel.getMatchesItems(
                (ArrayList<Matches> matches) -> {

                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(ARG_DATA_SET, matches);

                    MatchesTabFragment matchesTabFragment = new MatchesTabFragment();
                    matchesTabFragment.setArguments(bundle);

                }
        );

        MatchesRecyclerViewAdapter adapter = new MatchesRecyclerViewAdapter(mDataSet, mListener);
        view.setAdapter(adapter);
        view.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        view.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnListFragmentInteractionListener){
            mListener = (OnListFragmentInteractionListener) context;
        } else{
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListner");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Matches item);
    }
}


