package gaoxinbo.sinaweiboclient.storage.internal;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Optional;

import javax.inject.Inject;

import gaoxinbo.sinaweiboclient.application.WeiboApplication;
import gaoxinbo.sinaweiboclient.service.retrofit.model.Timeline;
import lombok.Cleanup;

import static android.content.Context.MODE_PRIVATE;

public class TimelineCache {
    static final String filename = "cached_tweets";
    static final String LOG_TAG = TimelineCache.class.getSimpleName();

    Gson gson;

    public TimelineCache(Gson gson) {
        this.gson = gson;
    }

    public Optional<Timeline> getCachedTweet() {
        try {
            @Cleanup
            BufferedReader  bufferedInputStream = new BufferedReader(
                    new InputStreamReader(
                            WeiboApplication.getInstance().openFileInput(filename)
                    )
            );

            String context = readAll(bufferedInputStream);
            return Optional.ofNullable(gson.fromJson(context, Timeline.class));

        } catch (FileNotFoundException e) {
            Log.v(LOG_TAG, e.getMessage(), e);
            return Optional.empty();
        } catch (IOException e) {
            Log.v(LOG_TAG, e.getMessage(), e);
            return Optional.empty();
        }

    }

    public void saveCachedTweet(Timeline timeline) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(
                            WeiboApplication.getInstance().openFileOutput(filename, MODE_PRIVATE)
                    )
            );
            bufferedWriter.write(gson.toJson(timeline));
            Log.v(LOG_TAG, "save timeline successfully");
        } catch (FileNotFoundException e) {
            Log.v(LOG_TAG, e.getMessage(), e);
            return;
        } catch (IOException e) {
            Log.v(LOG_TAG, e.getMessage(), e);
            return;
        }
    }

    private String readAll(BufferedReader bufferedReader) throws IOException {
        StringBuilder sb = new StringBuilder(2048);
        String line;
        while((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }

        return sb.toString();
    }
}
