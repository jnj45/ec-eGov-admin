package net.ecbank.fwk.admin.console.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.ecbank.fwk.admin.console.repository.UserRepository;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRep;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		System.out.println("=================login Id : " + userId);
		
		net.ecbank.fwk.admin.console.entity.User user = userRep.findOneByUserIdAndUseYn(userId,"Y");
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if(user != null) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		
		return new User(user.getUserId(), user.getPassword(), authorities);
	}
	
}
