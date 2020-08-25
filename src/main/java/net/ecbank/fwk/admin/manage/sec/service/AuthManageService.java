package net.ecbank.fwk.admin.manage.sec.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ecbank.fwk.admin.intface.service.TotalLogApiService;
import net.ecbank.fwk.admin.manage.sec.dto.AuthInfoDto;
import net.ecbank.fwk.admin.manage.sec.entity.AuthInfo;
import net.ecbank.fwk.admin.manage.sec.entity.DeptAuthRel;
import net.ecbank.fwk.admin.manage.sec.entity.UserAuthRel;
import net.ecbank.fwk.admin.manage.sec.repository.AuthInfoRepository;
import net.ecbank.fwk.admin.manage.sec.repository.AuthInfoRepositoryImpl;
import net.ecbank.fwk.admin.manage.sec.repository.DeptAuthRelRepository;
import net.ecbank.fwk.admin.manage.sec.repository.UserAuthRelRepository;
import net.ecbank.fwk.admin.util.EcStringUtil;
import net.ecbank.fwk.admin.util.UserDetailHelper;
import net.ecbank.fwk.admin.util.WebUtil;

@Service
public class AuthManageService {
	
	@Autowired
	private AuthInfoRepository authInfoRep;
	
	@Autowired
	private AuthInfoRepositoryImpl authInfoRepImpl;
	
	@Autowired
	private UserAuthRelRepository userAuthRelRepository;
	
	@Autowired
	private DeptAuthRelRepository deptAuthRelRepository;
	
	@Autowired
	private TotalLogApiService totalLogApiService;
	
	public List<AuthInfo> searchAuthInfoAllList(){
		
		return authInfoRep.findAll();
	}
	
	public List<AuthInfo> searchAuthInfoList(AuthInfoDto authInfoDto){
		
		return authInfoRepImpl.searchAuthInfoList(authInfoDto);
	}
	
	public List<AuthInfo> searchUserAuthList(AuthInfoDto authInfoDto){
		
		return authInfoRepImpl.searchUserAuthList(authInfoDto);
	}
	
	public List<AuthInfo> searchDeptAuthList(AuthInfoDto authInfoDto){
		
		return authInfoRepImpl.searchDeptAuthList(authInfoDto);
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
		
		System.out.println(reqAuthInfoDto.getOrgRoleList());
		System.out.println(reqAuthInfoDto.getChangeRoleList());
		
		for(int i=0;i<reqAuthInfoDto.getSaveList().size();i++) {
			
			AuthInfoDto authInfoDto = reqAuthInfoDto.getSaveList().get(i);
			
			UserAuthRel userAuthRel = new UserAuthRel(reqAuthInfoDto.getUserId(),reqAuthInfoDto.getUserType(),authInfoDto.getAuthCode(),authInfoDto.getSeq(),reqAuthInfoDto.getCoCd());
			
			if(authInfoDto.getRegYn().equals("Y")) {
				userAuthRel.setUseYn("Y");
				userAuthRelRepository.save(userAuthRel);
			}else {
				userAuthRel.setUseYn("N");
				userAuthRelRepository.save(userAuthRel);
			}
			
		}
		
		try {
		
			String profile = System.getProperty("spring.profiles.active");
			
			//통합로그 전송
			//api 파라미터. 전송처리는 async로 처리되므로 세션과 관련된 정보는 호출 시 넘겨줘야 됨.
			Map<String,Object> apiParam = new HashMap<String,Object>();
			//로그인 처리 URL
			apiParam.put("uri",	WebUtil.getRequestBaseURL() + "/sec/userAuthManage");
			//권한변경 목적
			apiParam.put("obj",	"업무변경");
			//권한변경자
			apiParam.put("actor", UserDetailHelper.getUserId());
			//권한변경 목적
			apiParam.put("ip",	WebUtil.getClientIP());
			//권한변경 대상자
			apiParam.put("custIdx", new String[] {reqAuthInfoDto.getUserId()});
			//변경전 권한
			apiParam.put("bfRole", String.join(",",reqAuthInfoDto.getOrgRoleList()));
			//변경후 권한
			apiParam.put("afRole", String.join(",",reqAuthInfoDto.getChangeRoleList()));
			
			if(profile.equals("prod")) {
				totalLogApiService.sendAuthLog(apiParam);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void saveDeptAuthRelation(AuthInfoDto reqAuthInfoDto) {
		
		System.out.println(reqAuthInfoDto.getOrgRoleList());
		System.out.println(reqAuthInfoDto.getChangeRoleList());
		
		for(int i=0;i<reqAuthInfoDto.getSaveList().size();i++) {
			
			AuthInfoDto authInfoDto = reqAuthInfoDto.getSaveList().get(i);
			
			DeptAuthRel userAuthRel = new DeptAuthRel(reqAuthInfoDto.getDeptCd(),authInfoDto.getAuthCode(),authInfoDto.getSeq(),reqAuthInfoDto.getCoCd());
			
			if(authInfoDto.getRegYn().equals("Y")) {
				userAuthRel.setUseYn("Y");
				deptAuthRelRepository.save(userAuthRel);
			}else {
				userAuthRel.setUseYn("N");
				deptAuthRelRepository.delete(userAuthRel);
			}
			
		}
		
	}
}
