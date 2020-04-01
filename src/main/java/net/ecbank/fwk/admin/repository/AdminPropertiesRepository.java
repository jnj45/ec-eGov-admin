package net.ecbank.fwk.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ecbank.fwk.admin.entity.AdminProperties;

/**
 * 관리콘솔 App 프로퍼티 repository
 * @author LYJ
 *
 */
public interface AdminPropertiesRepository extends JpaRepository<AdminProperties, Long> {

	public List<AdminProperties> findByPropKeyLike(String propKey);

	public List<AdminProperties> findByPropKeyStartingWith(String string);

	public AdminProperties findByPropKey(String string);
}
