package net.ecbank.fwk.admin.manage.log.dto;

import java.io.File;

import lombok.Data;
import net.ecbank.fwk.admin.common.dto.CommonDto;

@Data
public class LogExplorerDto extends CommonDto {
	
	private String instanceId;
	
	private String folderYn;
	private String name;
	private String fullPath;
	private String parentPath;
	private int lvl;
	private boolean expanded;
	private boolean leaf;
	private boolean loaded = true;
	
	private long fromLine;
	private long toLine;
	private String logContents;
	private String viewLineNumYn;
	private String searchText;
	private long currentLine;
	private long nextLine;
	private long searchTextFromLine;
	private long searchTextToLine;
	private int searchTextSize;
	private int nextSeq;
	private boolean searchNext;
	private boolean searchTextYn;
	private boolean logAddYn;
	private long lastLineNum;
	
	public LogExplorerDto() {
		
	}
	
	public LogExplorerDto(File f,String parentPath, int lvl) {
		
		this.name = f.getName();
		this.parentPath = parentPath;
		this.fullPath = f.getAbsolutePath();
		this.lvl = lvl;
		if(f.isDirectory()) {
			this.folderYn = "Y";
			this.leaf = false;
			this.expanded = true;
		}else {
			this.folderYn = "N";
			this.leaf = true;
			this.expanded = false;
		}
		
	}
	
}
