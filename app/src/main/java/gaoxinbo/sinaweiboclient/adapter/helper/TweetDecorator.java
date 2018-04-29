package gaoxinbo.sinaweiboclient.adapter.helper;

import android.content.Context;

import android.text.SpannableStringBuilder;
import android.text.Spanned;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gaoxinbo.sinaweiboclient.service.retrofit.model.Tweet;
import gaoxinbo.sinaweiboclient.adapter.span.TweetClickableSpan;

public class TweetDecorator {
    static Pattern pattern = Pattern.compile("(@[^: ：]+)");


    public static SpannableStringBuilder getTweetSpannableStringBuilder(final Context context, final Tweet tweet) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(tweet.getText());
        Matcher matcher = pattern.matcher(tweet.getText());


        while (matcher.find()) {
            String userName = tweet.getText().substring(matcher.start() + 1, matcher.end());
            spannableStringBuilder.setSpan(
                    new TweetClickableSpan(userName, context),
                    matcher.start(),
                    matcher.end(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );

        }

        return spannableStringBuilder;
    }
}
