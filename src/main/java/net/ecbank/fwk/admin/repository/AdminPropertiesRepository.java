package net.ecbank.fwk.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.ecbank.fwk.admin.entity.AdminProperties;

/**
 * 관리콘솔 App 프로퍼티 repository
 * @author LYJ
 *
 */
public interface AdminPropertiesRepository extends JpaRepository<AdminProperties, Long> {

	@Query("select p from AdminProperties p where p.propKey like :propKey||'%' and p.useYn='Y'")
	public List<AdminProperties> findProperties(@Param("propKey") String propKey);
	
	public AdminProperties findByPropKey(String string);
}
