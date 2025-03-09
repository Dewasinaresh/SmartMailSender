package com.smartmail.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.smartmail.entity.MailsCollectorEntity;
import com.smartmail.repo.MailCollectorRepo;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;

@Service
public class MailServiceImpl implements MailService{

	Logger log =LoggerFactory.getLogger(MailServiceImpl.class);

	private MailCollectorRepo mailCollectorRepo;
	
	@Value("${fileName}")
	private String fileName;

	@Value("${Path}")
	private String path;
	
	@Value("${From_Mail}")
	private String fromMail;
	
	private final String  subject ="Application for Java Development Position";
	
	private final String body = "Dear,\n" + 
		    "I hope this message finds you well. My name is Bhomaram Dewasi, and I am actively seeking a job opportunity in Java Development. I have over 2 years of relevant experience in full-stack development, including expertise in Java, Spring, Spring Boot, and React.js.\n" +
		    "I would greatly appreciate it if you could consider me for any suitable job openings that align with my skills and experience. Please find my resume attached for your reference.\n" +
		    "Thank you for your time and consideration. I look forward to the opportunity to discuss how my background and skills can contribute to your team.\n\n" +
		    "Best regards,\n" +
		    "Bhomaram Dewasi\n" +
		    "Mob No- +91 7249 429690";
	
	 @Autowired
	 private JavaMailSender mailSender;

	 
	public MailServiceImpl(MailCollectorRepo mailCollectorRepo) {
		super();
		this.mailCollectorRepo = mailCollectorRepo;
	}


	@Override
	public String sendtoAll() {
		List<MailsCollectorEntity> list = mailCollectorRepo.findAll();
	
		if (!list.isEmpty()) {
			try {
				list.stream().forEach(mail->sendEmail(mail.getMail(),subject,body,fileName));
				//-- > CALL sendEmail
				return "All Mails are sended successfully";
			}
			catch (Exception e) {
				return "Unable to send mail.. Try Again";
			}
		}
		else
			return "Mail is not present ";
		
		
	}

	    @Override
	    public String addMail(String mail) {
	    List<MailsCollectorEntity> presentMail=mailCollectorRepo.findByMail(mail).orElse(null);
	    if(presentMail.isEmpty()) {
	    	MailsCollectorEntity mailsCollectorEntity =new MailsCollectorEntity();
	    	mailsCollectorEntity.setMail(mail);
	    	mailCollectorRepo.save(mailsCollectorEntity);
	    	return "mail is successfully added";
	    }
	    else
	    {
	    	return "Mail is already available with "+mail;
	    }
	
	   }

		@Override
		public String sendSingleMail(String mailto) {
			sendEmail(mailto,subject,body,fileName);
			return "Mail is send to "+mailto;
		}
		
		// METHOD FOR SEND MAIL 
		 public void sendEmail(String to, String subject, String body, String fileName) {
		        log.info("Send Email of MailServiceImpl service is called");

		        MimeMessagePreparator mimeMessagePreparator = mime -> {
		            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mime, true);
		            mimeMessageHelper.setTo(to);
		            mimeMessageHelper.setFrom(fromMail);
		            mimeMessageHelper.setSubject(subject);
		            mimeMessageHelper.setText(body, false); // Set to true for HTML content
		            Path path = Paths.get("C:\\Users\\Naresh\\Downloads\\Resume_SPR.pdf");
		            System.out.println("mail :"+ this.path.concat(this.fileName));
		           // Path path = Paths.get(this.path.concat(this.fileName));
		            log.info("File having absolute path or not checking {}", path.isAbsolute());
		            FileSystemResource file = new FileSystemResource(path.toFile());
		            if (!file.exists()) {
		                log.error("File does not exist at the provided path: {}", path.toString());
		                throw new FileNotFoundException("File not found at path: " + path.toString());
		            }
		            log.info("File exists on provided path {}", file.exists());

		            mimeMessageHelper.addAttachment("Resume.pdf", file);
		            log.info("File is attached successfully");
		        };

		        try {
		            mailSender.send(mimeMessagePreparator);
		        } catch (Exception e) {
		            log.error("Error sending email: ", e);
		        }
		    }


	    
	    
	    
	
	
}
