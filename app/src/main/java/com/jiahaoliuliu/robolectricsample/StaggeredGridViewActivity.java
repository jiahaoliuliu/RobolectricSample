package com.jiahaoliuliu.robolectricsample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.etsy.android.grid.StaggeredGridView;


public class StaggeredGridViewActivity extends ActionBarActivity {

    private StaggeredGridView mStaggeredGridView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_grid_view);
        mStaggeredGridView = (StaggeredGridView) findViewById(R.id.staggered_grid_view);
    }
}
