package a6studios.com.madpractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BundleActivity extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bundle);

        text = findViewById(R.id.text);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        if(b != null){
            String msg = b.getString("name");
            text.setText(msg);
        }
    }
}
