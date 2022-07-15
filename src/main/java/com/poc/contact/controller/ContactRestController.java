package com.poc.contact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.contact.entity.Contact;
import com.poc.contact.service.ContactService;

@RestController
public class ContactRestController {

	@Autowired(required = true)
	private ContactService contactService;

	@PostMapping("/contact")
	public ResponseEntity<String> createContact(@RequestBody Contact contact) {

		if (null != contactService.upsert(contact)) {
			return new ResponseEntity<>(contactService.upsert(contact), HttpStatus.CREATED);
		}

		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/contact/{cid}")
	public ResponseEntity<Contact> getContact(@PathVariable("cid") int cid) {

		if (null != contactService.getContact(cid)) {
			return new ResponseEntity<>(contactService.getContact(cid), HttpStatus.OK);

		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/contacts")
	public ResponseEntity<List<Contact>> getAllContacts() {

		List<Contact> contacts = contactService.getAllContacts();
		if (!contacts.isEmpty())
			return new ResponseEntity<>(contacts, HttpStatus.OK);
		else
			return new ResponseEntity<>(contacts, HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/contact/{cid}")
	public ResponseEntity<String> deleteContact(@PathVariable("cid") int cid) {

		if (null != contactService.getContact(cid))
			return new ResponseEntity<>(contactService.deleteContact(cid), HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

}
