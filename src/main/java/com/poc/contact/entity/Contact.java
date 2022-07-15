package com.poc.contact.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Contact {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cid;
	
	private String name;
	
	private String email;
	
	private long phno;
	
	private String activeStatus;

}
