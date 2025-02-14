package com.abhinav.FullStackProject02.Repository;

import com.abhinav.FullStackProject02.Entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact,Long> {

    Optional<Contact> getByUsername(String username);
}
