package gaoxinbo.sinaweiboclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class UserHomepageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Toast.makeText(this, intent.getStringExtra("USER_NAME"), Toast.LENGTH_LONG).show();
    }
}