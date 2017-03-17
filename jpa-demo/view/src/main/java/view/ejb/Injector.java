package view.ejb;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;

import jpa.api.WithPersistence;
import jpa.wiring.JpaInjector;

@Startup
@Singleton
public class Injector {
    @EJB(lookup = "java:global/jpa/JpaInjector!jpa.wiring.JpaInjector")
    private JpaInjector jpaInjector;

    @Produces
    public WithPersistence withPersistence() {
        return jpaInjector.withPersistence();
    }
}
