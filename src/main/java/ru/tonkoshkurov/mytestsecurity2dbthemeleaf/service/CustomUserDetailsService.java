package ru.tonkoshkurov.mytestsecurity2dbthemeleaf.service;


import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.tonkoshkurov.mytestsecurity2dbthemeleaf.entity.User;
import ru.tonkoshkurov.mytestsecurity2dbthemeleaf.repository.UserRepository;

import java.util.stream.Collectors;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(usernameOrEmail);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(), user.getRoles().stream()
                    .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
        } else {
            throw new UsernameNotFoundException("Invalid username or email");
        }
    }
}
