package jpa.wiring;

import jpa.api.WithPersistence;

public interface JpaInjector {
    WithPersistence withPersistence();
}
