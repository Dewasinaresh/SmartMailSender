package com.smartmail.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartmail.entity.MailsCollectorEntity;

@Repository
public interface MailCollectorRepo extends JpaRepository<MailsCollectorEntity, Integer>{

	Optional<List<MailsCollectorEntity>> findByMail(String mail);
	
}
