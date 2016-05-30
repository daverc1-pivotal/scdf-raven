package com.fetme.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Created by cdaver on 3/7/16.
 */
@Entity
public class Names {

    @Id
    private String id;
    private String name;

    public Names(){
    }

    public Names(String name) {
        super();
        this.id= UUID.randomUUID().toString();
        this.name = name;
    }

    public Names(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = name;
    }
}
