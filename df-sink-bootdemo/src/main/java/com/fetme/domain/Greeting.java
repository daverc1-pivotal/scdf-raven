package com.fetme.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.UUID;

/**
 * Created by cdaver on 3/7/16.
 */
@Entity
public class Greeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;

    public Greeting(){
    }

    public Greeting(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
}
