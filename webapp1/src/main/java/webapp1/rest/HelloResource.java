package webapp1.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import api.MyBean;

@Path("hello")
public class HelloResource {
    @Inject
    private MyBean bean;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return bean.info();
    }
}
