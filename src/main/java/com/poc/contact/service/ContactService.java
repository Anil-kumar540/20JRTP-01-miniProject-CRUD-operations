package com.poc.contact.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poc.contact.entity.Contact;

@Service
public interface ContactService {
	
	public String upsert(Contact contact);
	
	public Contact getContact(int cid);
	
	public List<Contact> getAllContacts();
	
	public String deleteContact(int cid);

}
