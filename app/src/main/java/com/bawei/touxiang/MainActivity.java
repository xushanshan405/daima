package com.bawei.touxiang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.bawei.touxiang.adapter.VpAdapter;
import com.bawei.touxiang.fragment.SYFragment;
import com.bawei.touxiang.fragment.WDFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rg;
    private ViewPager vp;
    private ArrayList<Fragment> fragments;
    private VpAdapter vpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg = findViewById(R.id.rg);
        vp = findViewById(R.id.vp);
        fragments = new ArrayList<>();
        fragments.add(new SYFragment());
        fragments.add(new WDFragment());
        vpAdapter = new VpAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(vpAdapter);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.but_sy:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.but_wd:
                        vp.setCurrentItem(1);
                        break;
                }
            }
        });
    }
}
