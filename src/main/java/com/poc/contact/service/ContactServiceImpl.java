package com.poc.contact.service;

import java.util.ArrayList;
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

		contact.setActiveStatus("Y");
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
			Contact contact = optionalContact.get();
			if (contact.getActiveStatus().equals("Y"))
				return contact;
		}
		return null;
	}

	@Override
	public List<Contact> getAllContacts() {

		List<Contact> contacts = contactRepo.findAll();
		List<Contact> activeConatcts = new ArrayList<Contact>();
		for (Contact contact : contacts) {
			if (contact.getActiveStatus().equals("Y")) {
				activeConatcts.add(contact);
			}

		}
		return activeConatcts;
	}

	@Override
	public String deleteContact(int cid) {

		Contact contact = this.getContact(cid);
		if (null != contact) {
			// contactRepo.deleteById(cid);
			contact.setActiveStatus("N"); //soft delete
			Contact entity = contactRepo.saveAndFlush(contact);
			if (null != entity) {
				return "deleted successfully";
			}
		}
		return null;

	}

}
