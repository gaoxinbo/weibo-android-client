package gaoxinbo.sinaweiboclient.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

import gaoxinbo.sinaweiboclient.R;
import gaoxinbo.sinaweiboclient.adapter.TimelineAdapter;
import gaoxinbo.sinaweiboclient.model.Timeline;
import gaoxinbo.sinaweiboclient.model.Tweet;
import gaoxinbo.sinaweiboclient.service.retrofit.ApiFactory;
import gaoxinbo.sinaweiboclient.service.retrofit.RetrofitTimelineService;

import retrofit2.Callback;

import static gaoxinbo.sinaweiboclient.Constants.ACCESS_TOKEN;

public class TimelineActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TimelineAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        recyclerView = findViewById(R.id.timeline_list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TimelineAdapter(this);
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        String access_token = intent.getStringExtra(ACCESS_TOKEN);
        Log.v("TimelineActivity", access_token);

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


    }
}
