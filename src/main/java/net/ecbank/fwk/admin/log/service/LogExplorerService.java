package net.ecbank.fwk.admin.log.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import net.ecbank.fwk.admin.log.dto.LogExplorerDto;

@Service
public class LogExplorerService {
	
	@Autowired
	private Environment environment;
	
	public List<LogExplorerDto> getLogPathList(String rootPath){
		
		List<LogExplorerDto> pathList = new ArrayList<LogExplorerDto>();
		
		File logRoot = new File(rootPath);
		
		int lvl = 1;
		LogExplorerDto logExplorerDto = new LogExplorerDto(logRoot,"",lvl);
		pathList.add(logExplorerDto);
		setChildPath(pathList,logRoot.listFiles(),lvl,logRoot.getAbsolutePath());
		
		return pathList;
		
	}
	
	public LogExplorerDto getLogString(LogExplorerDto dto) throws Exception{
		
		List<Long> searchLineList = null;
		
		if(!dto.isSearchNext()) {
			//System.out.println("최초");
			dto.setSearchTextSize(1000);
			dto.setSearchTextFromLine(1);
			dto.setSearchTextToLine(10000);
		}
		
		if(dto.isSearchTextYn()) {
			
			//System.out.println(dto.getSearchTextSize()+"||||"+dto.getNextSeq());
			
			if(dto.getSearchTextSize() == dto.getNextSeq()+1) {
				//System.out.println("다음");
				dto.setSearchTextFromLine(dto.getSearchTextFromLine()+10000);
				dto.setSearchTextToLine(dto.getSearchTextToLine()+10000);
				dto.setNextSeq(0);
			}
			
			
			searchLineList = getSearchTextLogLine(dto);
			dto.setSearchTextSize(searchLineList.size());
			
			setSearchTextLineList(searchLineList, dto);
			
			if(dto.isSearchNext()) {
				//System.out.println("다음.........");
				if(searchLineList.size() == 0) {
					//System.out.println("사이즈가 0");
					dto.setSearchTextFromLine(1);
					dto.setSearchTextToLine(10000);
					
					searchLineList = getSearchTextLogLine(dto);
					dto.setSearchTextSize(searchLineList.size());
					
					setSearchTextLineList(searchLineList, dto);
					
				}
				
				if(searchLineList.size() == 1) {
					//System.out.println("사이즈가 1");
					dto.setNextSeq(0);
				}
			}
			
			if(searchLineList.size() == 0) {
				throw new Exception("검색된 내용이 없습니다.");
			}
			
			//System.out.println("최종 리스트 사이즈 :" + searchLineList.size());
			if(!dto.isLogAddYn()) {
				if(dto.isSearchNext()) {
					//System.out.println("다음 SEQ : "+dto.getNextSeq());
					dto.setFromLine(searchLineList.get(dto.getNextSeq()));
				}else {
					dto.setFromLine(searchLineList.get(0));
				}
			}
		}
		
		String logContents = "";
		
		StringBuffer log = new StringBuffer("");
		
		long lineNum = 1;
		
		try{
			File logFile = new File(dto.getFullPath());
            //입력 스트림 생성
            FileReader filereader = new FileReader(logFile);
            //입력 버퍼 생성
            BufferedReader bufReader = new BufferedReader(filereader);
            
            String line = "";
            dto.setToLine(dto.getFromLine() + Integer.parseInt(environment.getProperty("ecbank.fwk.admin.log.explr.add.line.cnt")));
            while((line = bufReader.readLine()) != null){
            	if(lineNum > dto.getToLine()) {
            		break;
            	}else {
            		if(lineNum >= dto.getFromLine()) {
            			//로그 라인 보여주기
            			if(dto.getViewLineNumYn().equals("Y")) {
            				log.append("<font color=\""+environment.getProperty("ecbank.fwk.admin.log.explr.line.num.col")+"\">").append(lineNum).append(">>></font>");
            			}
            			
            			if(dto.isSearchTextYn()) {
            				line = line.replaceAll(dto.getSearchText(), "<font color=\""+environment.getProperty("ecbank.fwk.admin.log.explr.srch.txt.col")+"\">"+dto.getSearchText()+"</font>");
            			}
            			
            			log.append(line).append("\n");
            		}
            	}
            	lineNum++;
            }
            bufReader.close();
            logContents = log.toString();
            dto.setLogContents(logContents);
            
            if(dto.isSearchTextYn()) {
            	if(!dto.isLogAddYn()) {
			        if(dto.isSearchNext()) {
						//dto.setNextLine(searchLineList.get(dto.getNextSeq()+1));
						dto.setNextSeq((int) (dto.getNextSeq() + 1));
					}else {
						//dto.setNextLine(searchLineList.get(1));
						dto.setNextSeq(1);
					}
            	}
            }
        }catch (FileNotFoundException e) {
        	e.printStackTrace();
        	throw new Exception(e);
        }catch(IOException e){
        	e.printStackTrace();
        	throw new Exception(e);
        }
		
		return dto;
		
	}
	
	public List<Long> getSearchTextLogLine(LogExplorerDto dto) throws Exception{
		
		List<Long> lineNumList = new ArrayList<Long>();
		
		long lineNum = 1;
		
		try{
			File logFile = new File(dto.getFullPath());
            //입력 스트림 생성
            FileReader filereader = new FileReader(logFile);
            //입력 버퍼 생성
            BufferedReader bufReader = new BufferedReader(filereader);
            //System.out.println("textfrom : " +dto.getSearchTextFromLine()+ " :: textTo : "+dto.getSearchTextToLine());
            String line = "";
            while((line = bufReader.readLine()) != null){
            	if(lineNum > dto.getSearchTextToLine()) {
            		break;
            	}else {
            		if(lineNum >= dto.getSearchTextFromLine()) {
	            		if(line.contains(dto.getSearchText())) {
	                		lineNumList.add(lineNum);
	                	}
            		}
            	}
            	lineNum++;
            }
            
            dto.setLastLineNum(lineNum-1);
            
            bufReader.close();
        }catch (FileNotFoundException e) {
        	e.printStackTrace();
        	throw new Exception(e);
        }catch(IOException e){
        	e.printStackTrace();
        	throw new Exception(e);
        }
		
		return lineNumList;
		
	}
	
	public String getLogString2(String logPath) throws Exception{
		
		String logContents = "";
		
		StringBuffer log = new StringBuffer("");
		
		try{
			File logFile = new File(logPath);
            
			RandomAccessFile raf = new RandomAccessFile(logFile, "rw");
			
			String line = null;
			raf.seek(1);
			while((line = raf.readLine())!=null) {
				log.append(line).append("\n");
			}
			
			raf.close();
			
        }catch (FileNotFoundException e) {
        	e.printStackTrace();
        	throw new Exception(e);
        }catch(IOException e){
        	e.printStackTrace();
        	throw new Exception(e);
        }
		
		return logContents;
		
	}
	
	public static void setChildPath(List<LogExplorerDto> pathList, File[] files,int lvl,String parentPath) {
		
		int newlvl = lvl + 1;
		
		for(int i=0;i<files.length;i++) {
			
			File f = files[i];
			
			if(f.isDirectory()) {
				//System.out.println(f.getAbsolutePath());
				LogExplorerDto logExplorerDto = new LogExplorerDto(f,parentPath,newlvl);
				pathList.add(logExplorerDto);
				setChildPath(pathList,f.listFiles(),newlvl,f.getAbsolutePath());
			}else {
				//System.out.println(f.getAbsolutePath());
				LogExplorerDto logExplorerDto = new LogExplorerDto(f,parentPath,newlvl);
				pathList.add(logExplorerDto);
			}
			
		}
		
	}
	
	public LogExplorerDto setSearchTextLineList(List searchLineList,LogExplorerDto dto) throws Exception {
		
		//System.out.println(dto.getLastLineNum()+"||||dddd| "+dto.getSearchTextToLine());
		if(dto.getLastLineNum() >= dto.getSearchTextToLine()) {
			if(searchLineList.size() == 0) {
				//System.out.println(": "+dto.getSearchTextFromLine()+" :" + dto.getSearchTextToLine());
				dto.setSearchTextFromLine(dto.getSearchTextFromLine()+10000);
				dto.setSearchTextToLine(dto.getSearchTextToLine()+10000);
				dto.setNextSeq(0);
				searchLineList = getSearchTextLogLine(dto);
				dto.setSearchTextSize(searchLineList.size());
				dto = setSearchTextLineList(searchLineList, dto);
			}
		}
		return dto;
		
	}
	
}
