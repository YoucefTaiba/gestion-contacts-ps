package com.poliscrypts.project.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poliscrypts.project.exceptions.ContactNotFoundException;
import com.poliscrypts.project.model.Contact;
import com.poliscrypts.project.model.Job; 
import com.poliscrypts.project.service.ContactService;

@RestController
@RequestMapping("api/contact")
public class ContactRessource {
	@Autowired
	private ContactService contactService; 

	@GetMapping("/all")
	public ResponseEntity<List<Contact>> findAllContacts() {
		return new ResponseEntity<>(contactService.findAllContacts(), HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Contact> findContact(@PathVariable Long id) {
		return new ResponseEntity<>(contactService.findContactById(id), HttpStatus.OK);
	}

	@ExceptionHandler(value = ContactNotFoundException.class)
	public ResponseEntity<String> handleContactNotFoundException(ContactNotFoundException contactNotFoundException) {
		return new ResponseEntity<String>("contact dose not existe", HttpStatus.NOT_FOUND);
	}

	@PostMapping("/add")
	public ResponseEntity<Contact> addContact(@ModelAttribute Contact contact) {
		return new ResponseEntity<>(contactService.addContact(contact), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Optional<Contact>> updateContact(@PathVariable Long id, @RequestBody Contact contact) {
		return new ResponseEntity<>(contactService.updateContact(id, contact), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Contact> deleteContact(@PathVariable Long id) {
		contactService.deleteContact(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/jobs/{id}")
	public ResponseEntity<Set<Job>> findAllJobs(@PathVariable Long id) {
		return new ResponseEntity<Set<Job>>(contactService.findAllJobs(id), HttpStatus.OK);
	}

	@PutMapping("/addjob/{id}")
	public ResponseEntity<Optional<Contact>> addJob(@PathVariable Long id, @RequestBody Job job) { 
		return new ResponseEntity<>(contactService.addJobToContact(id,contactService.addJob(job) ), HttpStatus.CREATED);
	}

	@DeleteMapping("/deletejob/{id1}/{id2}")
	public ResponseEntity<Optional<Contact>> addJob(@PathVariable Long id1, @PathVariable Long id2) {
		return new ResponseEntity<>(contactService.deleteJobfromContact(id1, id2), HttpStatus.OK);
	}

}
