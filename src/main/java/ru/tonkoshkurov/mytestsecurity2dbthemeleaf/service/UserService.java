package ru.tonkoshkurov.mytestsecurity2dbthemeleaf.service;

import org.springframework.stereotype.Service;
import ru.tonkoshkurov.mytestsecurity2dbthemeleaf.dto.UserDto;
import ru.tonkoshkurov.mytestsecurity2dbthemeleaf.entity.User;

import java.util.List;
@Service
public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
}
