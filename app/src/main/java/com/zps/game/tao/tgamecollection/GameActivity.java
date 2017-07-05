package com.zps.game.tao.tgamecollection;

import android.os.Bundle;

import com.zps.game.tao.fragmentation.base.MySupportActivity;
import com.zps.game.tao.fragmentation.base.MySupportFragment;

public class GameActivity extends MySupportActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        MySupportFragment fragment = findFragment(HomeFragment.class);
        if (fragment == null) {
            fragment = new HomeFragment();
        }
        loadRootFragment(R.id.root_id, fragment);
    }


}
