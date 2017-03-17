package view.rest;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jpa.api.WithPersistence;

@Path("item")
public class ItemResource {
    @Inject
    WithPersistence withPersitence;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public List<String> getAll() {
        return withPersitence.execute(repo -> repo.getAll());
    }

    @Transactional
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String createItem(@FormParam("text") String text) {
        boolean created = withPersitence.execute(repo -> repo.create(text));
        String feedback = created ? "OK" : String.format("item '%s' already exists", text);
        return feedback + ". use browser's <back> button";
    }
}
