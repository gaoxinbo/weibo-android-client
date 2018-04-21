package gaoxinbo.sinaweiboclient.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import gaoxinbo.sinaweiboclient.fragment.model.FragmentNode;


public class MainFragmentsManager {
    List<FragmentNode> fragmentList;


    public MainFragmentsManager() {
        fragmentList = new ArrayList<>(2);
        fragmentList.add(
                FragmentNode
                        .builder()
                        .fragment(new TimelineFragment())
                        .text("Timeline")

                        .build());
        fragmentList.add(
                FragmentNode
                        .builder()
                        .fragment(new SettingsFragment())
                        .text("Settings")
                        .build()
        );

    }

    public int getFragmentCount() {
        return fragmentList.size();
    }

    public void setBundle(Bundle bundle) {
        for(FragmentNode fragment: fragmentList) {
            fragment.getFragment().setArguments(bundle);
        }
    }

    public String getText(int index) {
        return fragmentList.get(index).getText();
    }

    public List<FragmentNode> getFragments() {
        return fragmentList;
    }

    public void showFragment(FragmentTransaction fragmentTransaction, int index) {
        for(int i=0; i<fragmentList.size(); i++) {
            if (index != i) {
                fragmentTransaction.hide(fragmentList.get(i).getFragment());
            } else {
                fragmentTransaction.show(fragmentList.get(i).getFragment());
            }
        }
        fragmentTransaction.commit();
    }

}
