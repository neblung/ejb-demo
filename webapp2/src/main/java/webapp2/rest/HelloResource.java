package webapp2.rest;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import api.MyBean;

@Path("hello")
public class HelloResource {
    @EJB(lookup = "java:global/webapp1/MyBean!api.MyBean")
    private MyBean bean;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return bean.info();
    }
}
