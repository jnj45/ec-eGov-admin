package net.ecbank.fwk.admin.manage.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ecbank.fwk.admin.manage.sys.dto.PropertyDto;
import net.ecbank.fwk.admin.manage.sys.entity.Property;
import net.ecbank.fwk.admin.manage.sys.repository.PropertyRepository;

@Service
public class PropertyManageService {
	
	@Autowired
	private PropertyRepository propertyRep;
	
	public List<Property> searchPropertyList(PropertyDto propertyDto){
		
		List<Property> list = propertyRep.findByKeyContaining(propertyDto.getKey());
		
		return list;
	}
	
	@Transactional
	public void saveProperty(List<PropertyDto> saveList) {
		
		for(int i=0;i<saveList.size();i++) {
			PropertyDto propertyDto = (PropertyDto) saveList.get(i);
			
			Property property = new Property(propertyDto);
			propertyRep.save(property);
		}
	}
	
	@Transactional
	public void deleteProperty(List<PropertyDto> deleteList) {
		
		for(int i=0;i<deleteList.size();i++) {
			PropertyDto propertyDto = (PropertyDto) deleteList.get(i);
			
			propertyRep.delete(new Property(propertyDto.getPropId()));
			
		}
	}
	
}
