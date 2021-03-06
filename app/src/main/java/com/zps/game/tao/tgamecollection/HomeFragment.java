package com.zps.game.tao.tgamecollection;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zps.game.tao.fragmentation.base.MySupportFragment;
import com.zps.game.tao.taogamelib.utils.HissViewAction;
import com.zps.game.tao.tgamecollection.gamefragments.RussiaCubeFragment;
import com.zps.game.tao.tgamecollection.gamefragments.SnakeFragment;
import com.zps.game.tao.tgamecollection.gamefragments.SnakeFragment2;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends MySupportFragment {
    Button btnSnake;
    Button btnSnake2;
    Button btnRussiaCube;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        btnSnake = (Button) view.findViewById(R.id.snake);
        btnSnake.setOnClickListener(new HissViewAction().new OnClickListener() {
            @Override
            public void action(View v) {
                start(new SnakeFragment());
            }
        });

        btnSnake2 = (Button) view.findViewById(R.id.snake2);
        btnSnake2.setOnClickListener(new HissViewAction().new OnClickListener() {
            @Override
            public void action(View v) {
                start(new SnakeFragment2());
            }
        });

        btnRussiaCube = (Button) view.findViewById(R.id.cube);
        btnRussiaCube.setOnClickListener(new HissViewAction().new OnClickListener() {
            @Override
            public void action(View v) {
                start(new RussiaCubeFragment());
            }
        });
    }

}
