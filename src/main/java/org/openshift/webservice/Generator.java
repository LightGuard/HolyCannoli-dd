package org.openshift.webservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openshift.model.*;
import org.openshift.model.Character;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by spousty on 8/22/14.
 */

@Path("/char")
public class Generator {

    private org.openshift.model.Character character = new Character();

    @GET()
    @Produces("application/json")
    public HashMap MakeACharacter(){
        return character.getAllAttributes();
    }

    @GET()
    @Produces("application/json")
    @Path("{name}")
    public HashMap MakeACharacterWithAName(@PathParam("name") String name){

        character.setName(name);
        return character.getAllAttributes();

    }

    @GET()
    @Produces("application/json")
    @Path("dd")
    public String MakeACharacterForMongo(@Context HttpServletRequest request) throws Exception{

        ///All Characters get the best name in the world
        character.setName("Steve");

        //build the POST
        System.out.println("About to Post");

        HttpClient client = new DefaultHttpClient();
        try {
            HttpPost post = new HttpPost("http://mongoservice:8080/ws/players");
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            post.setEntity(new StringEntity(gson.toJson(character.getAllAttributes())));
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("request failed for some non-helpful reason");
            }

            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();
            try {
                return org.apache.commons.io.IOUtils.toString(is);
            } finally {
                is.close();
            }

        } finally {
            client.getConnectionManager().shutdown();
        }

    }

}
