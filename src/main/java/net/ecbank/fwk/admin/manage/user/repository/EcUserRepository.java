package net.ecbank.fwk.admin.manage.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.ecbank.fwk.admin.manage.sec.dto.UserAuthDto;
import net.ecbank.fwk.admin.manage.user.entity.EcUser;

public interface EcUserRepository extends JpaRepository<EcUser, String>  {
	
	Page<UserAuthDto> findByUserIdContainingAndUserNmContainingAndUserClsAndCoCd(String userId,String userNm,String userCls,String coCd, Pageable pageable);
	
}
