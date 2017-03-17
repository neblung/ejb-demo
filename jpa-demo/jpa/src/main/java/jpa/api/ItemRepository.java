package jpa.api;

import java.util.List;

public interface ItemRepository {
    List<String> getAll();

    boolean create(String itemText);
}
