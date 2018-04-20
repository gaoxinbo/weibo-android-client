package gaoxinbo.sinaweiboclient.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import gaoxinbo.sinaweiboclient.R;
import gaoxinbo.sinaweiboclient.activity.LoginActivity;
import gaoxinbo.sinaweiboclient.storage.sqlite.WeiboContract;
import gaoxinbo.sinaweiboclient.storage.sqlite.WeiboDbHelper;

import static gaoxinbo.sinaweiboclient.Constants.ACCESS_TOKEN;

public class SettingsFragment extends Fragment {
    WeiboDbHelper weiboDbHelper;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        weiboDbHelper = new WeiboDbHelper(getContext());
        View view = inflater.inflate(R.layout.settings, container, false);

        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        init(view);
    }
    private void init(@NonNull View view) {
        if (view == null) {
            return;
        }
        view.findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getContext(), "Log out", Toast.LENGTH_SHORT);
                toast.show();

                SQLiteDatabase writableDatabase = weiboDbHelper.getWritableDatabase();
                writableDatabase.delete(WeiboContract.WeiboEntry.TABLE_NAME,
                        WeiboContract.WeiboEntry.KEY + " =? ",
                        new String[]{ACCESS_TOKEN});
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }


}
