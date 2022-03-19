package com.poliscrypts.project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poliscrypts.project.model.Contact;
import com.poliscrypts.project.service.ContactService;

@RestController
@RequestMapping("/contact")
public class ContactRessource {
	@Autowired
	private ContactService contactService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Contact>> findAllContacts() {
		return new ResponseEntity<>(contactService.findAllContacts(), HttpStatus.OK);
	}

	@RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
	public ResponseEntity<Contact> findContact(@PathVariable Long id) {
		return new ResponseEntity<>(contactService.findContactById(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
		return new ResponseEntity<>(contactService.addContact(contact), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Contact> updateContact(@RequestBody Contact contact) {
		return new ResponseEntity<>(contactService.updateContact(contact), HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Contact> deleteContact(@PathVariable Long id) {
		contactService.deleteContact(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

//	@RequestMapping(value = "/find", method = RequestMethod.GET)
//	public Page<Contact> searshContact(@RequestParam(name = "mc", defaultValue = "") String mc,
//			@RequestParam(name = "page", defaultValue = "0") int page,
//			@RequestParam(name = "size", defaultValue = "5") int size) {
//		return contactRepository.searche("%"+mc+"%",  PageRequest.of(page, size));
//	}
}
