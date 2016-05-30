package com.fetme.repository;

import com.fetme.domain.Greeting;
import com.fetme.domain.Names;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by cdaver on 3/7/16.,
 */
public interface NamesRepository extends CrudRepository<Names, String> {

    /**
     * Find a greeting with the specified text.
     *
     * @param name
     * @return The text if found, null otherwise.
     */
    public Greeting findByName(String name);

    /**
     * Find greeting whose name contains the specified string
     *
     * @param partialName
     *            Any alphabetic string.
     * @return The list of matching greetings - always non-null, but may be
     *         empty.
     */
    public List<Greeting> findByNameContainingIgnoreCase(String partialName);
}

