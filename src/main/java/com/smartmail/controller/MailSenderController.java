package com.smartmail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartmail.service.MailService;

@RestController
@RequestMapping("/sendMail")
public class MailSenderController {

	@Autowired
	private MailService mailService;
	
	@GetMapping("/SendMailToAll")
	public String sendMail()
	{
		return mailService.sendtoAll();
	}
	
	@PostMapping("/AddMail")
	public String addMail(@RequestParam String mail)
	{
		return mailService.addMail(mail);
	}
	@GetMapping("/sendSingleMail")
	public String sendMailToSingle(@RequestParam String mail ) {
		
		
		return mailService.sendSingleMail(mail);
	}
	
	
}
