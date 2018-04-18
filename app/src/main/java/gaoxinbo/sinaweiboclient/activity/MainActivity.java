package gaoxinbo.sinaweiboclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;




import gaoxinbo.sinaweiboclient.R;
import gaoxinbo.sinaweiboclient.fragment.MainFragmentsManager;
import gaoxinbo.sinaweiboclient.fragment.model.FragmentNode;

import static gaoxinbo.sinaweiboclient.Constants.ACCESS_TOKEN;

public class MainActivity extends AppCompatActivity {
    TabLayout tableLayout;
    MainFragmentsManager mainFragmentsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mainFragmentsManager = new MainFragmentsManager();

        Intent intent = getIntent();
        Bundle bundle = new Bundle();

        bundle.putString(ACCESS_TOKEN, intent.getStringExtra(ACCESS_TOKEN));
        mainFragmentsManager.setBundle(bundle);

        init();
    }

    private void init() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for(FragmentNode fragmentNode: mainFragmentsManager.getFragments()) {
            fragmentTransaction.add(R.id.home_container, fragmentNode.getFragment(), fragmentNode.getText());
        }
        fragmentTransaction.commit();
        
        tableLayout = findViewById(R.id.bottom_tab_layout);
        tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                mainFragmentsManager.showFragment(fragmentTransaction, tab.getPosition());
                
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        for(int i=0; i<mainFragmentsManager.getFragmentCount(); i++) {
            tableLayout.addTab(tableLayout.newTab().setText(mainFragmentsManager.getText(i)));

        }
    }
}
