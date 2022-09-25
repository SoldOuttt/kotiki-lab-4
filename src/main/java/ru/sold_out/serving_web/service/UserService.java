package ru.sold_out.serving_web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sold_out.serving_web.entity.User;
import ru.sold_out.serving_web.entity.additional_info.Role;
import ru.sold_out.serving_web.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
	private final UserRepository userRepository;

	public void save(String username, String password, Boolean active, Set<Role> roles) {
		if (username.isBlank()
				|| password.isBlank()
				|| active == null
				|| roles.isEmpty()
				|| findByUserName(username).isPresent()
		) {
			return;
		}
		User user = new User(username, password, active, roles);
		userRepository.save(user);
	}

	public void deleteById(Long id) {
		if (id == null)
			return;
		try {
			userRepository.deleteById(id);
		} catch (Exception exception) {
			System.out.println(exception.getStackTrace());
			System.out.println(exception.getMessage());
		}
	}

	public Optional<User> findByUserName(String userName) {
		return userRepository.findByUsername(userName);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return findByUserName(username).orElse(null);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}
}
