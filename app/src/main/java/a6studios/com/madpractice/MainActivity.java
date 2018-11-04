package a6studios.com.madpractice;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Array;

import static android.content.Intent.ACTION_VIEW;

public class MainActivity extends AppCompatActivity {

    Button implicit_btn, explicit_btn;
    EditText text;
    ProgressDialog progressDialog;
    private static final String[] COUNTRIES = new String[] {
            "Belgium", "France", "Italy", "Germany", "Spain", "India", "Israel", "Indonesia", ""
    };
    AutoCompleteTextView mAutoCompleteTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, COUNTRIES);

        implicit_btn = findViewById(R.id.implicit);
        explicit_btn = findViewById(R.id.explicit);
        text = findViewById(R.id.text);
        mAutoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        mAutoCompleteTextView.setThreshold(1);
        mAutoCompleteTextView.setAdapter(adapter);

    }

    void implicitCall(View v){
        final Intent i = new Intent(this, SecondActivity.class);
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(i);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setTitle("Warning!");
        builder.setMessage("Do you want to leave this activity?");

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    void explicitCall(View v){
        Intent i = new Intent();
        i.setClass(this, SecondActivity.class);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Opening...");
        progressDialog.setMessage("This may take a while");
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    Log.e("Thread Interrupted", e.toString());
                }
                progressDialog.dismiss();
            }
        }).start();
        startActivity(i);
    }

    void callBrowser(View v){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        startActivity(i);
    }

    void callDail(View v){
        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:789789789333"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            return;
        }
        startActivity(i);
    }

    void callGallery(View v){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"));
        startActivity(i);
    }

    void callContacts(View v){
        Intent i = new Intent(Intent.ACTION_DEFAULT, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(i, 1);
    }

    void passWithIntent(View v){
        Intent i = new Intent(this, IntentActivity.class);
        String msg = text.getText().toString();
        i.putExtra("name", msg);
        startActivity(i);
    }

    void passWithBundle(View v){
        Intent i = new Intent(this, IntentActivity.class);
        Bundle b = new Bundle();
        b.putString("name", text.getText().toString());
        i.putExtras(b);
        startActivity(i);
    }

}
