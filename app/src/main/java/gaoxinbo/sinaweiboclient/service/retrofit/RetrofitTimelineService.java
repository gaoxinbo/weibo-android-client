package gaoxinbo.sinaweiboclient.service.retrofit;


import gaoxinbo.sinaweiboclient.service.retrofit.model.Timeline;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitTimelineService {
    @GET("home_timeline.json")
    Call<Timeline> getTimeline(@Query("access_token") String token);
}
