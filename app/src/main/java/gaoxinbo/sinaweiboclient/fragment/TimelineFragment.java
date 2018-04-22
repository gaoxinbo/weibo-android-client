package gaoxinbo.sinaweiboclient.fragment;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import gaoxinbo.sinaweiboclient.R;
import gaoxinbo.sinaweiboclient.adapter.TimelineAdapter;
import gaoxinbo.sinaweiboclient.application.WeiboApplication;
import gaoxinbo.sinaweiboclient.service.retrofit.model.Timeline;
import gaoxinbo.sinaweiboclient.service.retrofit.RetrofitTimelineService;
import gaoxinbo.sinaweiboclient.storage.internal.TimelineCache;
import retrofit2.Callback;

import static gaoxinbo.sinaweiboclient.Constants.ACCESS_TOKEN;

public class TimelineFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private TimelineAdapter adapter;

    @Inject
    RetrofitTimelineService timelineService;

    @Inject
    TimelineCache timelineCache;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((WeiboApplication)getActivity().getApplication()).getTimelineFragmentComponent().inject(this);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_timeline, container, false);
        recyclerView = view.findViewById(R.id.timeline_list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TimelineAdapter(container.getContext(), timelineCache);
        recyclerView.setAdapter(adapter);

        String access_token = this.getArguments().getString(ACCESS_TOKEN);
        Log.v("TimelineFragment", this.toString());

        final retrofit2.Call<Timeline> timeline = timelineService.getTimeline(access_token);
        timeline.enqueue(new Callback<Timeline>() {
            @Override
            public void onResponse(retrofit2.Call<Timeline> call, retrofit2.Response<Timeline> response) {
                Timeline timeline = response.body();
                adapter.setList(timeline.getStatuses());
                timelineCache.saveCachedTweet(timeline);
            }

            @Override
            public void onFailure(retrofit2.Call<Timeline> call, Throwable t) {

            }
        });
        return view;
    }

}
