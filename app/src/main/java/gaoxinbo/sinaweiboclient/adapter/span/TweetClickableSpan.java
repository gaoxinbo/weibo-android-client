package gaoxinbo.sinaweiboclient.adapter.span;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import gaoxinbo.sinaweiboclient.R;
import gaoxinbo.sinaweiboclient.activity.UserHomepageActivity;

public class TweetClickableSpan extends ClickableSpan {
    final String userName;
    final Context context;

    public TweetClickableSpan(String userName, Context context) {
        this.userName = userName;
        this.context = context;
    }


    @Override
    public void onClick(View widget) {
        Intent intent = new Intent(context, UserHomepageActivity.class);
        intent.putExtra("USER_NAME", userName);
        context.startActivity(intent);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        int color = ContextCompat.getColor(context, R.color.colorPrimary);
        ds.setColor(color);
        ds.setUnderlineText(false);
    }
}
