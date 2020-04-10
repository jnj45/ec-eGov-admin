package net.ecbank.fwk.admin.console.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ecbank.fwk.admin.console.dto.UserDto;
import net.ecbank.fwk.admin.console.entity.User;
import net.ecbank.fwk.admin.console.repository.UserRepository;

@Service
public class UserManageService {
	
	@Autowired
	private UserRepository userRep;
	
	public List<User> searchUserList(UserDto userDto){
		
		List<User> list = userRep.findByUserIdContainingAndUserNmContaining(userDto.getUserId(), userDto.getUserNm());
		
		return list;
	}
	
	@Transactional
	public void saveUser(List<UserDto> saveList) {
		
		for(int i=0;i<saveList.size();i++) {
			UserDto userDto = (UserDto) saveList.get(i);
			
			User user = new User(userDto);
			
			if(userDto.getUserId().equals(userDto.getModUserId())) {
				userRep.save(user);
			}else {
				if(userDto.getUserNm() == null || userDto.getUserNm().equals("")) {
					userRep.save(user);
				}else {
					userRep.save(user);
					userRep.delete(new User(userDto.getUserId()));
				}
			}
			
		}
	}
	
	@Transactional
	public void deleteUser(List<UserDto> deleteList) {
		
		for(int i=0;i<deleteList.size();i++) {
			UserDto userDto = (UserDto) deleteList.get(i);
			
			userRep.delete(new User(userDto.getUserNm()));
			
		}
	}
	
}
