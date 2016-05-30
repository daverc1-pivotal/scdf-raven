package com.pivotal.demo.scdf;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pivotal.demo.scdf.MessageStore;


interface MyRepository extends JpaRepository<MessageStore, Long> {
}
