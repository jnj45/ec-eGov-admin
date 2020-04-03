package net.ecbank.fwk.admin.sec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ecbank.fwk.admin.sec.entity.UserAuthRel;

public interface UserAuthRelRepository extends JpaRepository<UserAuthRel, String> {

}
