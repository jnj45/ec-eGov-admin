package net.ecbank.fwk.admin.manage.sys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.ecbank.fwk.admin.manage.sys.dto.MenuTreeDto;
import net.ecbank.fwk.admin.manage.sys.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {

	/*@Query(value="SELECT  \r\n" + 
			"        MENU_NM AS menuNm, \r\n" + 
			"        MENU_NO AS menuNo, \r\n" + 
			"        UPPER_MENU_NO AS upperMenuNo, \r\n" + 
			"        LEVEL AS lvl \r\n" + 
			"FROM \r\n" + 
			"    (SELECT\r\n" + 
			"         MENU_NM\r\n" + 
			"        ,PROGRM_FILE_NM\r\n" + 
			"        ,MENU_NO\r\n" + 
			"        ,CASE WHEN UPPER_MENU_NO = MENU_NO THEN NULL ELSE UPPER_MENU_NO END AS UPPER_MENU_NO\r\n" + 
			"        ,MENU_ORDR\r\n" + 
			"        ,MENU_DC\r\n" + 
			"        ,RELATE_IMAGE_PATH\r\n" + 
			"        ,RELATE_IMAGE_NM\r\n" + 
			"    FROM  EF_MENU)\r\n" + 
			"START WITH MENU_NO = 0 \r\n" + 
			"CONNECT BY PRIOR MENU_NO = UPPER_MENU_NO",nativeQuery=true)*/
	/*@Query(nativeQuery = true)
	List<MenuTreeDto> searchMenuListByTree();*/
	
	Menu findOneByMenuNo(String menuNo);
	
}
