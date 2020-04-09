package net.ecbank.fwk.admin.log.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.ecbank.fwk.admin.cmm.dto.Response;
import net.ecbank.fwk.admin.log.dto.LogExplorerDto;
import net.ecbank.fwk.admin.log.service.LogExplorerService;

@RestController
@RequestMapping("/log")
public class LogExplorerRestController {
	
	@Autowired
	private LogExplorerService logExplorerService;
	
	@Autowired
	private Environment environment;
	
	@PostMapping("/getLogPathList")
	@ResponseBody
	public List<LogExplorerDto> getLogPathList(){
		
		return logExplorerService.getLogPathList(environment.getProperty("ecbank.fwk.admin.log.explr.path"));
	}
	
	@PostMapping("/getLogView")
	public LogExplorerDto getLogView(@RequestBody LogExplorerDto dto){
		
		Response res = new Response();
		
		System.out.println("From Line : " + dto.getFromLine());
		
		try {
			logExplorerService.getLogString(dto);
			
			res.setResponseCd("S");
		}catch (Exception e) {
			e.printStackTrace();
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		dto.setResponse(res);
		
		return dto;
	}
	
	@PostMapping("/downloadlogFile")
	public void downloadlogFile(@Valid LogExplorerDto dto, HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		System.out.println("로그 파일 다운로드 : " + dto.getFullPath());
		
		OutputStream out = null;
		
		FileInputStream fis = null;

		try {
			
			File file = new File(dto.getFullPath());
			
			response.setContentType("application/octet-stream; charset=utf-8");
			response.setContentLength((int) file.length());
			String browser = getBrowser(request);
			String disposition = getDisposition(dto.getFullPath().substring(dto.getFullPath().lastIndexOf("\\")), browser);
			response.setHeader("Content-Disposition", disposition);
			response.setHeader("Content-Transfer-Encoding", "binary");
			out = response.getOutputStream();
			fis = new FileInputStream(file);
			
			FileCopyUtils.copy(fis, out);
			
			if (fis != null) {
				fis.close();
			}
			if( out != null) {
				out.flush();
				out.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (fis != null) {
				fis.close();
			}
			if( out != null) {
				out.flush();
				out.close();
			}
		}
		
		
	}
	
	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1 || header.indexOf("Trident") > -1)
			return "MSIE";
		else if (header.indexOf("Chrome") > -1)
			return "Chrome";
		else if (header.indexOf("Opera") > -1)
			return "Opera";
		return "Firefox";
	}

	private String getDisposition(String filename, String browser)
			throws UnsupportedEncodingException {
		String dispositionPrefix = "attachment;filename=";
		String encodedFilename = null;
		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll(
					"\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\""
					+ new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\""
					+ new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		}
		return dispositionPrefix + encodedFilename;
	}
	
}
