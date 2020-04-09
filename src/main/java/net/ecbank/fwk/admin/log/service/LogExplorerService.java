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
	
	public List<LogExplorerDto> getLogPathList(String rootPath){
		
		List<LogExplorerDto> pathList = new ArrayList<LogExplorerDto>();
		
		File logRoot = new File(rootPath);
		
		int lvl = 1;
		LogExplorerDto logExplorerDto = new LogExplorerDto(logRoot,"",lvl);
		pathList.add(logExplorerDto);
		setChildPath(pathList,logRoot.listFiles(),lvl,logRoot.getAbsolutePath());
		
		return pathList;
		
	}
	
	public String getLogString(String logPath,long from, long to) throws Exception{
		
		String logContents = "";
		
		StringBuffer log = new StringBuffer("");
		
		int lineNum = 1;
		
		try{
			File logFile = new File(logPath);
            //입력 스트림 생성
            FileReader filereader = new FileReader(logFile);
            //입력 버퍼 생성
            BufferedReader bufReader = new BufferedReader(filereader);
            
            String line = "";
            while((line = bufReader.readLine()) != null){
            	if(lineNum > to) {
            		break;
            	}else {
            		if(lineNum >= from) {
            			//로그 라인 보여주기
            			//log.append("<font color=\"red\">").append(lineNum).append(">></font>");
            			log.append(line).append("\n");
            		}
            	}
            	lineNum++;
            }
            bufReader.close();
            logContents = log.toString();
        }catch (FileNotFoundException e) {
        	e.printStackTrace();
        	throw new Exception(e);
        }catch(IOException e){
        	e.printStackTrace();
        	throw new Exception(e);
        }
		
		return logContents;
		
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
	
}
