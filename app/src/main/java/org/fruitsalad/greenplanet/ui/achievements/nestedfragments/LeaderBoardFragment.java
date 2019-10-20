package org.fruitsalad.greenplanet.ui.achievements.nestedfragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import org.fruitsalad.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeaderBoardFragment extends Fragment {


    public LeaderBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leader_board, container, false);
    }

}
