package com.poc.contact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.contact.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
