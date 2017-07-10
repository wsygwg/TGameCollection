package com.zps.game.tao.tgamecollection.gamefragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zps.game.tao.fragmentation.base.MySupportFragment;
import com.zps.game.tao.taogamelib.games.russiacube.ui.RussiaCubeView;
import com.zps.game.tao.tgamecollection.R;

/**
 * Created by tao on 2017/7/5.
 */

public class RussiaCubeFragment extends MySupportFragment{

    RussiaCubeView russiaCubeView;
    public RussiaCubeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_russia_cube, container, false);
        russiaCubeView = (RussiaCubeView) view.findViewById(R.id.russiacubeid);
        russiaCubeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("click");
            }
        });
        russiaCubeView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showToast("long click");
                return true;
            }
        });
        return view;
    }

    private void showToast(String s){
        Toast.makeText(RussiaCubeFragment.this.getContext(),s,Toast.LENGTH_SHORT).show();;
    }
}
