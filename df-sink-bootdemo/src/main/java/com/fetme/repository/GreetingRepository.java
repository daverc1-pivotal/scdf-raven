package com.fetme.repository;

import com.fetme.domain.Greeting;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by cdaver on 3/7/16.,
 */
public interface GreetingRepository extends CrudRepository<Greeting, String> {

    /**
     * Find a greeting with the specified text.
     *
     * @param title
     * @return The text if found, null otherwise.
     */
    public Greeting findByTitle(String title);

    /**
     * Find greeting whose name contains the specified string
     *
     * @param partialTitle
     *            Any alphabetic string.
     * @return The list of matching greetings - always non-null, but may be
     *         empty.
     */
    public List<Greeting> findByTitleContainingIgnoreCase(String partialTitle);
}
