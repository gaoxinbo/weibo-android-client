package gaoxinbo.sinaweiboclient.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import gaoxinbo.sinaweiboclient.R;
import gaoxinbo.sinaweiboclient.activity.LoginActivity;
import gaoxinbo.sinaweiboclient.application.WeiboApplication;
import gaoxinbo.sinaweiboclient.storage.sqlite.WeiboWrapper;

public class SettingsFragment extends Fragment {
    @Inject
    WeiboWrapper weiboWrapper;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((WeiboApplication)getActivity().getApplication()).getSettingFragmentComponent().inject(this);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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
                Toast toast = Toast.makeText(getContext(), "Logging Out", Toast.LENGTH_SHORT);
                toast.show();

                weiboWrapper.deleteAccessToken();

                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }


}
