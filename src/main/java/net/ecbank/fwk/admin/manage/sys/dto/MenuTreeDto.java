package net.ecbank.fwk.admin.manage.sys.dto;

import lombok.Data;

@Data
public class MenuTreeDto{
	
	private String menuNm;
	private String menuEnNm;
	private String programFileNm;
	private String menuNo;
	private String upperMenuNo;
	private int menuOrder;
	private String menuDesc;
	private String relateImagePath;
	private String relateImageNm;
	private int lvl;
	private boolean expanded;
	private boolean leaf;
	private boolean loaded = true;
	private String newYn;
	private String regYn;
	private String orgRegYn;
	private String authCode;
	
	public MenuTreeDto() {
		
	}
	
	public String getNewYn() {
		this.newYn = "N";
		return this.newYn;
	}
	
	public String getOrgRegYn() {
		return this.regYn;
	}
	
	public boolean isExpanded() {
		
		if(this.programFileNm != null) {
			if(!this.programFileNm.equals("dir")) {
			  this.expanded = false;
			}else {
			  this.expanded = true;
			}
		}else {
			this.expanded = false;
		}
		
		
		return this.expanded;
	}
	
	public boolean isLeaf() {
		
		if(this.programFileNm != null) {
			if(!this.programFileNm.equals("dir")) {
			  this.leaf = true;
			}else {
			  this.leaf = false;
			}
		}else {
			this.leaf = true;
		}
		
		return this.leaf;
	}
	
	public MenuTreeDto(String menuNm, String menuEnNm, String programFileNm,
			String menuNo, String upperMenuNo,
					int menuOrder, String menuDesc,
					String relateImagePath, String relateImageNm,
					int lvl) {
		  this.menuNm = menuNm;
		  this.menuEnNm = menuEnNm;
		  this.programFileNm = programFileNm;
		  this.menuNo = menuNo;
		  this.upperMenuNo = upperMenuNo;
		  this.menuOrder = menuOrder;
		  this.menuDesc = menuDesc;
		  this.relateImagePath = relateImagePath;
		  this.relateImageNm = relateImageNm;
		  this.lvl = lvl;
		  this.expanded = false;
		  
		  if(this.programFileNm != null) {
			  if(!this.programFileNm.equals("dir")) {
				  this.leaf = true;
				  this.expanded = false;
			  }else {
				  this.leaf = false;
				  this.expanded = true;
			  }
		  }else {
			  this.leaf = true;
			  this.expanded = false;
		  }
		  this.loaded = true;
	}
	
}
