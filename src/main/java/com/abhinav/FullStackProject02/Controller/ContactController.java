package com.abhinav.FullStackProject02.Controller;

import com.abhinav.FullStackProject02.Entity.Contact;
import com.abhinav.FullStackProject02.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5500/contact.html")
@RestController
@RequestMapping("/api/contact")

public class ContactController {

    @Autowired
    private ContactService contactService;


    @GetMapping("/id/{id}")
    public Contact getById(@PathVariable Long id)
    {

        return contactService.getById(id);
    }

    @GetMapping
    public List<Contact> getAll()
    {
        return contactService.getAll();
    }


    @GetMapping("/username/{username}")
    public ResponseEntity<Contact> getByName(@PathVariable String username) {
        return contactService.getbyUserName(username)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/save")
    public Contact addDetails(@RequestBody Contact contact)
    {
        return contactService.saveDetails(contact);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDetail(@PathVariable Long id) {
        if (!contactService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact with ID " + id + " not found");
        }
        contactService.deleteDetails(id);
        return ResponseEntity.ok("Contact deleted successfully!");
    }


}


