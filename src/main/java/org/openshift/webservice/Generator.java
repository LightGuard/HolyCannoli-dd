package org.openshift.webservice;

import java.util.HashMap;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.openshift.model.Character;

/**
 * Created by spousty on 8/22/14.
 */

@Path("/char")
@RequestScoped
public class Generator {

    private org.openshift.model.Character character = new Character();

    @Inject
    private PersistCharacter characterSaver;

    @GET()
    @Produces("application/json")
    public HashMap MakeACharacter() {
        return character.getAllAttributes();
    }

    @GET()
    @Produces("application/json")
    @Path("{name}")
    public HashMap MakeACharacterWithAName(@PathParam("name") String name) {

        character.setName(name);
        return character.getAllAttributes();

    }

    @GET()
    @Produces("application/json")
    @Path("dd")
    public HashMap MakeACharacterForMongo(@Context HttpServletRequest request) throws Exception {

        ///All Characters get the best name in the world
        character.setName("Steve");

        //build the POST
        System.out.println("About to Post");

        String savedId = characterSaver.saveCharacter(character);
        character.getAllAttributes().put("id", savedId);

        return character.getAllAttributes();
    }

}
