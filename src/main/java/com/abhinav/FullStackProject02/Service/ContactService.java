package com.abhinav.FullStackProject02.Service;

import com.abhinav.FullStackProject02.Entity.Contact;
import com.abhinav.FullStackProject02.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact getById(Long id)
    {
        return contactRepository.findById(id).orElse(null);
    }

    public List<Contact> getAll()
    {
        List<Contact> list = contactRepository.findAll();
        return list;
    }

    public Optional<Contact> getbyUserName(String username) {
        return contactRepository.getByUsername(username);
    }

    public Contact saveDetails(Contact contact)
    {
        return contactRepository.save(contact);
    }


    public void deleteDetails(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact with ID " + id + " not found");
        }
        contactRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return contactRepository.existsById(id);
    }




}
