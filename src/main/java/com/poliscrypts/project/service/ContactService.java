package com.poliscrypts.project.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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

	public Contact addContact(Contact Contact) {

		return contactRepository.save(Contact);

	}

	public Contact findContactById(Long id) {
		return contactRepository.findContactById(id)
				.orElseThrow(() -> new ContactNotFoundException("Contact id :" + id + " was not found "));
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
	public Set<Job> findAllJobs(Long id) {
		Optional<Contact> contact = contactRepository.findContactById(id);
		Set<Job> jobs = new HashSet<Job>();
		if (contact.isPresent()) {
			jobs=contact.get().getJobs();
		}
		return jobs;
	}
	public Optional<Contact> addJobToContact(Long id, Job job) {
		Optional<Contact> contact = contactRepository.findContactById(id);
		if (contact.isPresent()) {
			contact.get().getJobs().add(job);
		}
		return contact;
	}

	public Optional<Contact> deleteJobfromContact(Long id, Long jobId) {
		Optional<Contact> contact = contactRepository.findContactById(id);
		Job job=jobRepository.getById(jobId);
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
		return jobRepository.findJobById(id)
				.orElseThrow(() -> new JobNotFoundException("Job id :" + id + " was not found "));

	}

	public void deleteJob(Long id) {

		jobRepository.deleteJobById(id);

	}
}
