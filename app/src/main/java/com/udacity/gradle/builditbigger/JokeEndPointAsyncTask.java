package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.JokeTeller;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.rohan.androidjoketeller.JokeTellerActivity;
import com.rohan.builditbigger.joketellerbackend.myJokeApi.MyJokeApi;

import java.io.IOException;

import static com.rohan.androidjoketeller.JokeTellerActivity.JOKE_EXTRA_STRING;

/**
 * Created by Rohan on 27-Dec-16.
 */

class JokeEndPointAsyncTask extends AsyncTask<Context, Void, String> {

    private MyJokeApi myJokeApi;
    private Context mContext;

    @Override
    protected String doInBackground(Context... params) {

        mContext = params[0];

        if (myJokeApi == null) {  // Only do this once
            MyJokeApi.Builder builder = new MyJokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myJokeApi = builder.build();
        }

        try {
            return myJokeApi.tellJoke().execute().getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String s) {
//        Toast.makeText(mContext, "Fetching joke...", Toast.LENGTH_SHORT).show();
        ((MainActivity) mContext).mProgressDialog.dismiss();

        Intent i = new Intent(mContext, JokeTellerActivity.class);
        i.putExtra(JOKE_EXTRA_STRING, s);
        mContext.startActivity(i);
    }
}
