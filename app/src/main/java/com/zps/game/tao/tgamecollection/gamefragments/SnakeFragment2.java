package com.zps.game.tao.tgamecollection.gamefragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zps.game.tao.fragmentation.base.MySupportFragment;
import com.zps.game.tao.taogamelib.games.snake.ui.SnakeView;
import com.zps.game.tao.taogamelib.games.snake.ui.SnakeView2;
import com.zps.game.tao.tgamecollection.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SnakeFragment2 extends MySupportFragment {

    private final String TAG = SnakeFragment2.class.getSimpleName();
    SnakeView2 sv;

    public SnakeFragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_snake2, container, false);
        sv = (SnakeView2) v.findViewById(R.id.snakeview2);
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
