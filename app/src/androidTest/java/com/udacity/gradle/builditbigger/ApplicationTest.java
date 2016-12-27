package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.content.Intent;
import android.test.ApplicationTestCase;

import org.junit.Test;

/**
 * Created by Rohan on 27-Dec-16.
 */

public class ApplicationTest extends ApplicationTestCase<Application> {


    public ApplicationTest() {
        super(Application.class);
    }

    @Test
    public void EndpointTest(){

        String resultString = null;
        JokeEndPointAsyncTask task = new JokeEndPointAsyncTask();
        task.execute(getContext());

        try {
            resultString = task.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(resultString);

    }
}
