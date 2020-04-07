package net.ecbank.fwk.admin.sys.dto;

import lombok.Data;

@Data
public class MenuTreeDto{
	
	private String menuNm;
	private String programFileNm;
	private long menuNo;
	private long upperMenuNo;
	private int menuOrder;
	private String menuDesc;
	private String relateImagePath;
	private String relateImageNm;
	private int lvl;
	private boolean expanded;
	private boolean leaf;
	private boolean loaded = true;
	private String newYn;
	
	public MenuTreeDto() {
		
	}
	
	public String getNewYn() {
		this.newYn = "N";
		return this.newYn;
	}
	
	public boolean isExpanded() {
		
		if(this.lvl == 3) {
		  this.expanded = false;
		}else {
		  this.expanded = true;
		}
		
		return this.expanded;
	}
	
	public boolean isLeaf() {
		
		if(this.lvl == 3) {
		  this.leaf = true;
		}else {
		  this.leaf = false;
		}
		
		return this.leaf;
	}
	
	public MenuTreeDto(String menuNm, String programFileNm,
					long menuNo, long upperMenuNo,
					int menuOrder, String menuDesc,
					String relateImagePath, String relateImageNm,
					int lvl) {
		  this.menuNm = menuNm;
		  this.programFileNm = programFileNm;
		  this.menuNo = menuNo;
		  this.upperMenuNo = upperMenuNo;
		  this.menuOrder = menuOrder;
		  this.menuDesc = menuDesc;
		  this.relateImagePath = relateImagePath;
		  this.relateImageNm = relateImageNm;
		  this.lvl = lvl;
		  this.expanded = false;
		  
		  if(lvl == 3) {
			  this.leaf = true;
			  this.expanded = false;
		  }else {
			  this.leaf = false;
			  this.expanded = true;
		  }
		  this.loaded = true;
	}
	
}
