package gaoxinbo.sinaweiboclient.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import gaoxinbo.sinaweiboclient.R;
import gaoxinbo.sinaweiboclient.fragment.TimelineFragment;

import static gaoxinbo.sinaweiboclient.Constants.ACCESS_TOKEN;

public class MainActivity extends AppCompatActivity {
    TabLayout tableLayout;
    Fragment timelineFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Intent intent = getIntent();

        Bundle bundle = new Bundle();
        bundle.putString(ACCESS_TOKEN, intent.getStringExtra(ACCESS_TOKEN));
        timelineFragment = new TimelineFragment();
        timelineFragment.setArguments(bundle);

        init();
    }

    private void init() {
        tableLayout = findViewById(R.id.bottom_tab_layout);
        tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //onTabItemSelected(tab.getPosition());
                getSupportFragmentManager().beginTransaction().replace(R.id.home_container,timelineFragment).commit();
                Log.v("MainActivity", "onTabSelected");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tableLayout.addTab(tableLayout.newTab().setText("Timeline"));
        tableLayout.addTab(tableLayout.newTab().setText("settings"));
    }
}
