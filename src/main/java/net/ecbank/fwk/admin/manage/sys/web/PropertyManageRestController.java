package net.ecbank.fwk.admin.manage.sys.web;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ecbank.fwk.admin.common.dto.CommonDto;
import net.ecbank.fwk.admin.common.dto.Response;
import net.ecbank.fwk.admin.console.jmx.ServerConfigService;
import net.ecbank.fwk.admin.manage.sys.dto.PropertyDto;
import net.ecbank.fwk.admin.manage.sys.entity.Property;
import net.ecbank.fwk.admin.manage.sys.service.PropertyManageService;
import net.ecbank.fwk.admin.util.ModelMapperUtils;

@RestController
@RequestMapping("/sys")
public class PropertyManageRestController {
	
	private final Logger log = LoggerFactory.getLogger(PropertyManageRestController.class);
	
	@Autowired
	private PropertyManageService propertyManageService;
	
	@Autowired
	private ServerConfigService serverConfigService;
	
	@PostMapping("/propertyList")
	public List<PropertyDto> codeGrpList(@RequestBody PropertyDto propertyDto) {
		
		List<Property> list = propertyManageService.searchPropertyList(propertyDto);
		
		return convertToDtoList(list);
	}
	
	@PostMapping("/saveProperty")
	public PropertyDto saveProperty(@RequestBody PropertyDto propertyDto) {
		
		System.out.println("save size : " + propertyDto.getSaveList().size());
		
		Response res = new Response();
		
		try {
			propertyManageService.saveProperty(propertyDto.getSaveList());
			
			res.setResponseCd("S");
			res.setResponseMsg("처리가 완료되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		propertyDto.setResponse(res);
		
		return propertyDto;
	}
	
	@PostMapping("/deleteProperty")
	public PropertyDto deleteProperty(@RequestBody PropertyDto propertyDto) {
		
		Response res = new Response();
		
		try {
			propertyManageService.deleteProperty(propertyDto.getDeleteList());
			
			res.setResponseCd("S");
			res.setResponseMsg("처리가 완료되었습니다.");
		}catch (Exception e) {
			e.printStackTrace();
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		propertyDto.setResponse(res);
		
		return propertyDto;
	}
	
	@PostMapping("/reflectRuntime")
	public CommonDto reflectRuntime() {
		
		
		CommonDto commDto = new CommonDto();
		
		Response res = new Response();
		
		try {
			log.debug("::::::JMX CALL(reloadProperty) START");
			serverConfigService.reloadProperties();
			
			res.setResponseCd("S");
			res.setResponseMsg("처리가 완료되었습니다.");
			log.debug("::::::JMX CALL(reloadProperty) END::::::");
		}catch (Exception e) {
			log.error("::::::JMX CALL(reloadProperty) FAIL::::::");
			e.printStackTrace();
			res.setResponseCd("E");
			res.setResponseErrMsg(e.getMessage());
		}
		
		commDto.setResponse(res);
		
		return commDto;
	}
	
	private List<PropertyDto> convertToDtoList(List<Property> propertyList) {
	    return propertyList.stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	private PropertyDto convertToDto(Property property) {
		PropertyDto propertyDto = ModelMapperUtils.getModelMapper().map(property, PropertyDto.class);
		
	    return propertyDto;
	}
	
}
