package com.zps.game.tao.tgamecollection.gamefragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zps.game.tao.fragmentation.base.MySupportFragment;
import com.zps.game.tao.taogamelib.games.snake.ui.SnakeView;
import com.zps.game.tao.tgamecollection.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SnakeFragment extends MySupportFragment {

    SnakeView sv;

    public SnakeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_snake, container, false);
        sv = (SnakeView) v.findViewById(R.id.snakeview);
        sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(v.getContext(),"~~~~~~~~~~~~~~~~~~~~~~click",Toast.LENGTH_SHORT).show();
            }
        });
        sv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                Toast.makeText(v.getContext(),"~~~~~~~~~~~~~~~~~~~~~~long click",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        return v;
    }

}
