package com.rohan.androidjoketeller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeTellerActivity extends AppCompatActivity {

    public static final String JOKE_EXTRA_STRING = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_teller);

        TextView jokeTextView = (TextView) findViewById(R.id.joke_text_view);

        Intent i = getIntent();

        if (i.getExtras() != null && i.getStringExtra(JOKE_EXTRA_STRING) != null) {
            jokeTextView.setText(i.getStringExtra(JOKE_EXTRA_STRING));
        } else {
            jokeTextView.setText("Error!");
        }
    }
}
