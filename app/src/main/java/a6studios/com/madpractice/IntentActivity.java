package a6studios.com.madpractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class IntentActivity extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        text = findViewById(R.id.text);

        Intent i = getIntent();
        String msg = i.getStringExtra("name");
        text.setText(msg);
    }
}
