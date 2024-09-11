package sae.planning.security;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import sae.planning.pojo.Principal;
import sae.planning.pojo.User;
import sae.planning.repository.UserRepository;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	HttpSession session;

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(usernameOrEmail)
				.orElseThrow(() -> new UsernameNotFoundException("User not exists by Username or Email"));

		Collection<GrantedAuthority> role = new ArrayList<GrantedAuthority>();
		role.add(new SimpleGrantedAuthority(user.getRole()));
		session.setAttribute("user", new Principal(user.getUno(), user.getEmail(), user.getRole()));
		return new org.springframework.security.core.userdetails.User(usernameOrEmail, user.getPwd(), role);
	}
}
