package net.ecbank.fwk.admin.manage.sys.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.ecbank.fwk.admin.manage.sys.dto.MenuDto;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="EF_MENU")
//@ToString(exclude = {"codeClass", "codeDetails"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
/*@SqlResultSetMapping(
					name = "TreeMapping",
					classes = @ConstructorResult(
							targetClass = MenuTreeDto.class,
							columns = {
									@ColumnResult(name="menuNm", type= String.class),
									@ColumnResult(name="programFileNm", type= String.class),
									@ColumnResult(name="menuNo", type= Long.class),
									@ColumnResult(name="upperMenuNo", type= Long.class),
									@ColumnResult(name="menuOrder", type= Integer.class),
									@ColumnResult(name="menuDesc", type= String.class),
									@ColumnResult(name="relateImagePath", type= String.class),
									@ColumnResult(name="relateImageNm", type= String.class),
									@ColumnResult(name="lvl", type= Integer.class)
							}
							)
					)
@NamedNativeQuery(name = "Menu.searchMenuListByTree", 
				  query =   "SELECT  \r\n" + 
							"        MENU_NM AS menuNm, \r\n" + 
							"        PROGRM_FILE_NM AS programFileNm, \r\n" +
							"        MENU_NO AS menuNo, \r\n" + 
							"        UPPER_MENU_NO AS upperMenuNo,\r\n" +
							"        MENU_ORDR AS menuOrder,\r\n" +
							"        MENU_DC AS menuDesc,\r\n" +
							"        RELATE_IMAGE_PATH AS relateImagePath,\r\n" +
							"        RELATE_IMAGE_NM AS relateImageNm,\r\n" +
							"        LEVEL AS lvl \r\n" + 
							"FROM \r\n" + 
							"    (SELECT\r\n" + 
							"         MENU_NM\r\n" + 
							"        ,PROGRM_FILE_NM\r\n" + 
							"        ,MENU_NO\r\n" + 
							"        ,CASE WHEN UPPER_MENU_NO = MENU_NO THEN 1 ELSE UPPER_MENU_NO END AS UPPER_MENU_NO\r\n" + 
							"        ,MENU_ORDR\r\n" + 
							"        ,MENU_DC\r\n" + 
							"        ,RELATE_IMAGE_PATH\r\n" + 
							"        ,RELATE_IMAGE_NM\r\n" + 
							"    FROM  EF_MENU)\r\n" + 
							"START WITH MENU_NO = 0 \r\n" + 
							"CONNECT BY PRIOR MENU_NO = UPPER_MENU_NO",resultSetMapping="TreeMapping")*/
public class Menu {
	
	@Column(name="MENU_NM")
	private String menuNm;
	
	@Column(name="MENU_EN_NM")
	private String menuEnNm;
	
	@Column(name="PROGRM_FILE_NM")
	private String programFileNm;
	
	@Id
	@Column(name="MENU_NO")
	private String menuNo;
	
	@Column(name="UPPER_MENU_NO")
	private String upperMenuNo;
	
	@Column(name="MENU_ORDR")
	private int menuOrder;
	
	@Column(name="MENU_DC")
	private String menuDesc;
	
	@Column(name="RELATE_IMAGE_PATH")
	private String relateImagePath;
	
	@Column(name="RELATE_IMAGE_NM")
	private String relateImageNm;
	
	@Column(name="URL")
	private String url;
	
	@Column(name="USE_YN", columnDefinition="char")
	private String useYn;
	
	@Column(name="VIEW_CO_CD",columnDefinition="char")
	private String viewCoCd;
	
	@Transient
	private int lvl;
	
	@JsonIgnore
	@OneToMany(mappedBy="menuInfo",fetch=FetchType.LAZY)
	private List<MenuAuthRel> menuAuthList = new ArrayList<MenuAuthRel>();
	
	public Menu(String menuNo) {
		this.menuNo = menuNo;
	}
	
	public Menu(MenuDto menuDto) {
		this.menuNo = menuDto.getModMenuNo();
		this.menuNm = menuDto.getMenuNm();
		this.menuEnNm = menuDto.getMenuEnNm();
		this.upperMenuNo = menuDto.getUpperMenuNo();
		this.menuOrder = menuDto.getMenuOrder();
		this.menuDesc = menuDto.getMenuDesc();
		this.relateImageNm = menuDto.getRelateImageNm();
		this.relateImagePath = menuDto.getRelateImagePath();
		this.url = menuDto.getUrl();
		this.programFileNm = menuDto.getProgramFileNm();
		this.useYn = menuDto.getUseYn();
		this.viewCoCd = menuDto.getViewCoCd();
	}
	
}
