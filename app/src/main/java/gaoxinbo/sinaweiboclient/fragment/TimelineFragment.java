package gaoxinbo.sinaweiboclient.fragment;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.util.Optional;

import javax.inject.Inject;

import gaoxinbo.sinaweiboclient.R;
import gaoxinbo.sinaweiboclient.adapter.TimelineAdapter;
import gaoxinbo.sinaweiboclient.application.WeiboApplication;
import gaoxinbo.sinaweiboclient.service.retrofit.model.Timeline;
import gaoxinbo.sinaweiboclient.service.retrofit.wrapper.RetrofitTimelineWrapper;
import gaoxinbo.sinaweiboclient.storage.internal.TimelineCache;
import gaoxinbo.sinaweiboclient.storage.sqlite.WeiboWrapper;
import retrofit2.Callback;

import static gaoxinbo.sinaweiboclient.Constants.ACCESS_TOKEN;

public class TimelineFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Inject
    TimelineAdapter adapter;

    @Inject
    RetrofitTimelineWrapper retrofitTimelineWrapper;

    @Inject
    TimelineCache timelineCache;

    @Inject
    WeiboWrapper weiboWrapper;


    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((WeiboApplication)getActivity().getApplication()).getTimelineFragmentComponent().inject(this);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final SwipeRefreshLayout view = (SwipeRefreshLayout) inflater.inflate(R.layout.activity_timeline, container, false);

        view.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                Toast.makeText(WeiboApplication.getInstance(), "Fetching...", Toast.LENGTH_SHORT).show();
                Optional<String> access_token = weiboWrapper.getAccessToken();


                final retrofit2.Call<Timeline> timeline = retrofitTimelineWrapper.getTimelineLaterThan(
                        access_token.get(),
                        adapter.getMaxId()
                );
                timeline.enqueue(new Callback<Timeline>() {
                    @Override
                    public void onResponse(retrofit2.Call<Timeline> call, retrofit2.Response<Timeline> response) {
                        Timeline timeline = response.body();
                        adapter.insertFront(timeline.getStatuses());
                        Toast.makeText(WeiboApplication.getInstance(), String.format("fetched %d tweet(s)", timeline.getStatuses().size()), Toast.LENGTH_SHORT).show();

                        view.setRefreshing(false);
                    }


                    @Override
                    public void onFailure(retrofit2.Call<Timeline> call, Throwable t) {
                        Toast.makeText(WeiboApplication.getInstance(), "Unable to fetch timeline", Toast.LENGTH_SHORT).show();
                        view.setRefreshing(false);
                    }
                });


            }
        });

        recyclerView = view.findViewById(R.id.timeline_list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        String access_token = this.getArguments().getString(ACCESS_TOKEN);

        final retrofit2.Call<Timeline> timeline = retrofitTimelineWrapper.getDefaultTimeline(access_token);
        timeline.enqueue(new Callback<Timeline>() {
            @Override
            public void onResponse(retrofit2.Call<Timeline> call, retrofit2.Response<Timeline> response) {
                Timeline timeline = response.body();
                adapter.setList(timeline.getStatuses());
            }


            @Override
            public void onFailure(retrofit2.Call<Timeline> call, Throwable t) {

            }
        });
        return view;
    }


}
