package com.telecomuc.teleqr;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    
    @BindView(R.id.last_seen_rv)
    RecyclerView lastSeenRv;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.empty_view)
    LinearLayout emptyView;
    @BindView(R.id.camera_fab)
    FloatingActionButton cameraFab;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    
        //TODO: Set empty view
    
        setupFab();
    }
    
    private void setupFab() {
        cameraFab.setOnClickListener(v -> {
        
        });
    }
}
