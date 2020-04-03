package net.ecbank.fwk.admin.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ecbank.fwk.admin.sys.dto.AuthInfoDto;
import net.ecbank.fwk.admin.sys.dto.RoleInfoDto;
import net.ecbank.fwk.admin.sys.dto.UserAuthDto;
import net.ecbank.fwk.admin.sys.entity.AuthInfo;
import net.ecbank.fwk.admin.sys.entity.AuthRoleRel;
import net.ecbank.fwk.admin.sys.entity.RoleInfo;
import net.ecbank.fwk.admin.sys.entity.UserAuthRel;
import net.ecbank.fwk.admin.sys.repository.AuthInfoRepository;
import net.ecbank.fwk.admin.sys.repository.AuthInfoRepositoryImpl;
import net.ecbank.fwk.admin.sys.repository.UserAuthRelRepository;
import net.ecbank.fwk.admin.util.EcStringUtil;

@Service
public class AuthManageService {
	
	@Autowired
	private AuthInfoRepository authInfoRep;
	
	@Autowired
	private AuthInfoRepositoryImpl authInfoRepImpl;
	
	@Autowired
	private UserAuthRelRepository userAuthRelRepository;
	
	public List<AuthInfo> searchAuthInfoList(AuthInfoDto authInfoDto){
		
		return authInfoRepImpl.searchAuthInfoList(authInfoDto);
	}
	
	public List<AuthInfo> searchUserAuthList(AuthInfoDto authInfoDto){
		
		return authInfoRepImpl.searchUserAuthList(authInfoDto);
	}
	
	
	@Transactional
	public void saveAuthInfo(List<AuthInfoDto> saveList) {
		
		for(int i=0;i<saveList.size();i++) {
			AuthInfoDto authInfoDto = (AuthInfoDto) saveList.get(i);
			
			AuthInfo authInfo = new AuthInfo(authInfoDto);
			
			if(authInfoDto.getAuthCode().equals(authInfoDto.getModAuthCode())) {
				authInfo.setCreateDate(authInfoDto.getCreateDate());
				authInfoRep.save(authInfo);
			}else {
				authInfo.setCreateDate(EcStringUtil.getDateFormat("yy/MM/dd"));
				if(authInfoDto.getAuthCode() == null || authInfoDto.getAuthCode().equals("")) {
					authInfoRep.save(authInfo);
				}else {
					authInfoRep.save(authInfo);
					authInfoRep.delete(new AuthInfo(authInfoDto.getAuthCode()));
				}
			}
			
		}
	}
	
	@Transactional
	public void deleteAuthInfo(List<AuthInfoDto> deleteList) {
		
		for(int i=0;i<deleteList.size();i++) {
			AuthInfoDto authInfoDto = (AuthInfoDto) deleteList.get(i);
			
			authInfoRep.delete(new AuthInfo(authInfoDto.getAuthCode()));
			
		}
	}
	
	@Transactional
	public void saveUserAuthRelation(AuthInfoDto reqAuthInfoDto) {
		
		for(int i=0;i<reqAuthInfoDto.getSaveList().size();i++) {
			
			AuthInfoDto authInfoDto = reqAuthInfoDto.getSaveList().get(i);
			
			UserAuthRel userAuthRel = new UserAuthRel(reqAuthInfoDto.getUserId(),reqAuthInfoDto.getUserType(),authInfoDto.getAuthCode());
			
			if(authInfoDto.getRegYn().equals("Y")) {
				userAuthRelRepository.save(userAuthRel);
			}else {
				userAuthRelRepository.delete(userAuthRel);
			}
			
		}
		
	}
}
