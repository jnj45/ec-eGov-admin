package net.ecbank.fwk.admin.sys.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ecbank.fwk.admin.sys.dto.CodeGroupDto;
import net.ecbank.fwk.admin.sys.dto.RoleInfoDto;
import net.ecbank.fwk.admin.sys.entity.CodeDetail;
import net.ecbank.fwk.admin.sys.entity.CodeGroup;
import net.ecbank.fwk.admin.sys.entity.RoleInfo;
import net.ecbank.fwk.admin.sys.repository.RoleInfoRepository;
import net.ecbank.fwk.admin.sys.repository.RoleInfoRepositoryImpl;
import net.ecbank.fwk.admin.util.EcStringUtil;

@Service
public class RoleManageService {
	
	@Autowired
	private RoleInfoRepository roleInfoRep;
	
	@Autowired
	private RoleInfoRepositoryImpl roleInfoRepImpl;
	
	public List<RoleInfo> searchRoleInfoList(RoleInfoDto roleInfoDto){
		
		return roleInfoRepImpl.searchRoleInfoList(roleInfoDto);
	}
	
	@Transactional
	public void saveRoleInfo(List<RoleInfoDto> saveList) {
		
		for(int i=0;i<saveList.size();i++) {
			RoleInfoDto roleInfoDto = (RoleInfoDto) saveList.get(i);
			
			RoleInfo roleInfo = new RoleInfo(roleInfoDto);
			
			if(roleInfoDto.getRoleCode().equals(roleInfoDto.getModRoleCode())) {
				roleInfo.setCreateDate(roleInfoDto.getCreateDate());
				roleInfoRep.save(roleInfo);
			}else {
				roleInfo.setCreateDate(EcStringUtil.getDateFormat("yy/MM/dd"));
				if(roleInfoDto.getRoleCode() == null || roleInfoDto.getRoleCode().equals("")) {
					roleInfoRep.save(roleInfo);
				}else {
					roleInfoRep.save(roleInfo);
					roleInfoRep.delete(new RoleInfo(roleInfoDto.getRoleCode()));
				}
			}
			
		}
	}
	
	@Transactional
	public void deleteRoleInfo(List<RoleInfoDto> deleteList) {
		
		for(int i=0;i<deleteList.size();i++) {
			RoleInfoDto roleInfoDto = (RoleInfoDto) deleteList.get(i);
			
			roleInfoRep.delete(new RoleInfo(roleInfoDto.getRoleCode()));
			
		}
	}
}
