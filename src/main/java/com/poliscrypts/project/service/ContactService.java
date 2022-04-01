package com.poliscrypts.project.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poliscrypts.project.exceptions.ContactNotFoundException;
import com.poliscrypts.project.exceptions.JobNotFoundException;
import com.poliscrypts.project.model.Company;
import com.poliscrypts.project.model.Contact;
import com.poliscrypts.project.model.Job;
import com.poliscrypts.project.repo.ContactRepository;
import com.poliscrypts.project.repo.JobRepository;

@Service
@Transactional
public class ContactService {

	private final ContactRepository contactRepository;
	private final JobRepository jobRepository;

	@Autowired
	public ContactService(ContactRepository ContactRepository, JobRepository jobRepository) {
		this.contactRepository = ContactRepository;
		this.jobRepository = jobRepository;
	}

	public Contact addContact(Contact contact) {

		return contactRepository.save(contact);

	}

	public Contact findContactById(Long id) {
		return contactRepository.findContactById(id)
				.orElseThrow(() -> new ContactNotFoundException("Contact id :" + id + " was not found "));
	}
	public Page<Contact> findContactByName(String name, Pageable paging) {
		return contactRepository.findContactByNom(name, paging);
	}
	public Page<Contact> findAllContacts(Pageable paging) {
		return contactRepository.findAll(paging);
	}

	public Contact updateContact(Contact contact) {
		return contactRepository.save(contact);
	}

	public Optional<Contact> updateContact(Long id, Contact contact) {
		return contactRepository.findContactById(id).map(old -> {
			Contact contactUpdate = new Contact(old.getId(), contact.getNom(), contact.getPrenom(),
					contact.getAdresse());
			return contactRepository.save(contactUpdate);
		});
	}

	public void deleteContact(Long id) {
		contactRepository.deleteContactById(id);
	}

	public Set<Job> findAllJobs(Long id) {
		Optional<Contact> contact = contactRepository.findContactById(id);
		return contact.get().getJobs();
	}

	public Job addJobToContact(Long id, Job job) {
		Optional<Contact> contact = contactRepository.findContactById(id);
		if (contact.isPresent()) {
			contact.get().getJobs().add(job);
		}
		return job;
	}

	public Optional<Contact> deleteJobfromContact(Long id, Long jobId) {
		Optional<Contact> contact = contactRepository.findContactById(id);
		Job job = jobRepository.getById(jobId);
		if (contact.isPresent()) {
			contact.get().getJobs().remove(job);
		}
		return contact;
	}

	public Job addJob(Job job) {

		return jobRepository.save(job);

	}

	public Job updateJob(Job job) {

		return jobRepository.save(job);

	}

	public Job findJobById(Long id) {
		return jobRepository.findById(id)
				.orElseThrow(() -> new JobNotFoundException("Job id :" + id + " was not found "));

	}

	public void deleteJob(Long id) {

		jobRepository.deleteJobById(id);

	}
}
