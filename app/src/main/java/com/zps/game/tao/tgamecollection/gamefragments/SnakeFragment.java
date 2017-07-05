package com.zps.game.tao.tgamecollection.gamefragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zps.game.tao.fragmentation.base.MySupportFragment;
import com.zps.game.tao.tgamecollection.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SnakeFragment extends MySupportFragment {


    public SnakeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_snake, container, false);
    }

}
