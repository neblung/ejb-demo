package jpa.api;

import java.util.function.Function;

public interface WithPersistence {
    <T> T execute(Function<ItemRepository, T> function);
}
