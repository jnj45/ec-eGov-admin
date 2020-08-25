package net.ecbank.fwk.admin.manage.log.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ecbank.fwk.admin.manage.log.entity.MailLog;

public interface MailLogRepository  extends JpaRepository<MailLog, Integer>{
	
	MailLog findBySeq(String seq);
}
