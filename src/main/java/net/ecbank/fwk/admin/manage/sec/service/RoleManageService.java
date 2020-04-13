package net.ecbank.fwk.admin.manage.sec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ecbank.fwk.admin.manage.sec.dto.AuthInfoDto;
import net.ecbank.fwk.admin.manage.sec.dto.RoleInfoDto;
import net.ecbank.fwk.admin.manage.sec.entity.AuthRoleRel;
import net.ecbank.fwk.admin.manage.sec.entity.RoleInfo;
import net.ecbank.fwk.admin.manage.sec.repository.AuthRoleRelRepository;
import net.ecbank.fwk.admin.manage.sec.repository.RoleInfoRepository;
import net.ecbank.fwk.admin.manage.sec.repository.RoleInfoRepositoryImpl;
import net.ecbank.fwk.admin.util.EcStringUtil;

@Service
public class RoleManageService {
	
	@Autowired
	private RoleInfoRepository roleInfoRep;
	
	@Autowired
	private RoleInfoRepositoryImpl roleInfoRepImpl;
	
	@Autowired
	private AuthRoleRelRepository authRoleRelRep;
	
	public List<RoleInfo> searchRoleInfoList(RoleInfoDto roleInfoDto){
		
		return roleInfoRepImpl.searchRoleInfoList(roleInfoDto);
	}
	
	public List<RoleInfo> searchAuthRoleList(AuthInfoDto authInfoDto){
		
		return roleInfoRepImpl.searchAuthRoleList(authInfoDto);
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
	
	@Transactional
	public void saveAuthRelation(List<RoleInfoDto> saveList, String authCode) {
		
		for(int i=0;i<saveList.size();i++) {
			
			RoleInfoDto roleInfoDto = saveList.get(i);
			
			AuthRoleRel authRoleRel = new AuthRoleRel(authCode,roleInfoDto.getRoleCode());
			
			if(roleInfoDto.getRoleRegYn().equals("Y")) {
				authRoleRelRep.save(authRoleRel);
			}else {
				authRoleRelRep.delete(authRoleRel);
			}
			
		}
		
	}
}
