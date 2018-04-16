package gaoxinbo.sinaweiboclient.fragment;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gaoxinbo.sinaweiboclient.R;
import gaoxinbo.sinaweiboclient.adapter.TimelineAdapter;
import gaoxinbo.sinaweiboclient.model.Timeline;
import gaoxinbo.sinaweiboclient.service.retrofit.ApiFactory;
import gaoxinbo.sinaweiboclient.service.retrofit.RetrofitTimelineService;
import retrofit2.Callback;

import static gaoxinbo.sinaweiboclient.Constants.ACCESS_TOKEN;

public class TimelineFragment extends Fragment {

    private RecyclerView recyclerView;
    private TimelineAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_timeline, container, false);
        recyclerView = view.findViewById(R.id.timeline_list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TimelineAdapter(container.getContext());
        recyclerView.setAdapter(adapter);

        String access_token = this.getArguments().getString(ACCESS_TOKEN);
        Log.v("TimelineFragment", access_token);

        final RetrofitTimelineService timelineService = ApiFactory.getTimelineService();
        final retrofit2.Call<Timeline> timeline = timelineService.getTimeline(access_token);
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
