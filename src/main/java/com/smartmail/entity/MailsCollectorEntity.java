package com.smartmail.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Mail_Collecotr")
public class MailsCollectorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer mailId;
	
	private String mail;

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
