/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.rohan.builditbigger.joketellerbackend;

import com.example.JokeTeller;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myJokeApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "joketellerbackend.builditbigger.rohan.com",
                ownerName = "joketellerbackend.builditbigger.rohan.com",
                packagePath = ""
        )
)
public class JokeEndpoint {

    @ApiMethod(name = "tellJoke")
    public Joke tellJoke() {

        JokeTeller jokeTeller = new JokeTeller();

        Joke response = new Joke();
        response.setJoke(jokeTeller.getJoke());

        return response;
    }

}
