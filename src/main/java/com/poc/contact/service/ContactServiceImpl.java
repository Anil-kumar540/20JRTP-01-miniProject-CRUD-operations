package com.poc.contact.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.contact.entity.Contact;
import com.poc.contact.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepo;

	@Override
	public String upsert(Contact contact) {

		Contact savedContact = contactRepo.saveAndFlush(contact);
		if (savedContact != null) {
			return "Contact saved successfully";
		}
		return null;
	}

	@Override
	public Contact getContact(int cid) {

		Optional<Contact> optionalContact = contactRepo.findById(cid);
		if (optionalContact.isPresent()) {
			return optionalContact.get();
		}
		return null;
	}

	@Override
	public List<Contact> getAllContacts() {

		List<Contact> contacts = contactRepo.findAll();
		return contacts;
	}

	@Override
	public String deleteContact(int cid) {
		if (null != this.getContact(cid)) {
			contactRepo.deleteById(cid);
			return "deleted successfully";
		} else {
			return null;

		}

	}

}
