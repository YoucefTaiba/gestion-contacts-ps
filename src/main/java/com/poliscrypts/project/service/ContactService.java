package com.poliscrypts.project.service;
 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poliscrypts.project.exceptions.ContactNotFoundException;
import com.poliscrypts.project.model.Company;
import com.poliscrypts.project.model.Contact;
import com.poliscrypts.project.model.Job;
import com.poliscrypts.project.repo.ContactRepository;
@Service
@Transactional
public class ContactService {

	private final ContactRepository contactRepository;

	@Autowired
	public ContactService(ContactRepository ContactRepository) {
		this.contactRepository = ContactRepository;
	}

	public Contact addContact(Contact Contact) {

		return contactRepository.save(Contact);

	}

	public Contact findContactById(Long id)    {
		return contactRepository.findContactById(id).orElseThrow(()->new ContactNotFoundException("Contact id :"+id+" was not found "));
	}

	public List<Contact> findAllContacts() {
		return contactRepository.findAll();
	}

	public Contact updateContact(Contact Contact) {
		return contactRepository.save(Contact);
	}

	public void deleteContact(Long id) {
		contactRepository.deleteContactById(id);
	}
	
	public void addJob(Long id, Job job) {
		Optional<Contact> contact = contactRepository.findContactById(id);
		if (contact.isPresent()) {
			contact.get().getJobs().add(job);
		}
	}
	public void deleteJob(Long id, Job job) {
		Optional<Contact> contact = contactRepository.findContactById(id);
		if (contact.isPresent()) {
			contact.get().getJobs().remove(job);
		}
	}
}
