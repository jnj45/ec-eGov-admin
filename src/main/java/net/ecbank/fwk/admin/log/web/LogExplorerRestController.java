package net.ecbank.fwk.admin.log.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
		
		try {
			String logContents = logExplorerService.getLogString(dto.getFullPath());
			
			dto.setLogContents(logContents);
			
			res.setResponseCd("S");
		}catch (Exception e) {
			e.printStackTrace();
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		dto.setResponse(res);
		
		return dto;
	}
}
