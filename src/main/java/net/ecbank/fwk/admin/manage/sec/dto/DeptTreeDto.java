package net.ecbank.fwk.admin.manage.sec.dto;

import lombok.Data;

@Data
public class DeptTreeDto {
	
	String coCd;
	String deptCd;
	String deptNm;
	String upperDeptCd;
	String isLeaf;
	private int deptOrder;
	
	private int lvl;
	private boolean expanded;
	private boolean leaf;
	private boolean loaded = true;
	
	
	public boolean isExpanded() {
		
		if(!this.isLeaf.equals("0")) {
		  this.expanded = false;
		}else {
		  this.expanded = true;
		}
		
		return this.expanded;
	}
	
	public boolean isLeaf() {
		
		if(!this.isLeaf.equals("0")) {
		  this.leaf = true;
		}else {
		  this.leaf = false;
		}
		
		return this.leaf;
	}
}
