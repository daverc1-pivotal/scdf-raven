package com.pivotal.demo.scdf;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class MessageStore {

    @Id
    @GeneratedValue
    private Long id;
    private String message;

    MessageStore() {}

    public MessageStore(String message) { this.message = message; }
    public Long getId() { return id; }
    public String getMessage() { return message; }
    @Override
    public String toString() {
        return "MessageStore{" +"id=" + id +", message='" + message + '\'' +'}';
    }
}
