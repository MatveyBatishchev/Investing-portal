package ru.investing_portal.services.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.investing_portal.dto.UserDto;
import ru.investing_portal.errors.UserAlreadyExistException;
import ru.investing_portal.mappers.UserMapper;
import ru.investing_portal.repos.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService  {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public void createUser(UserDto userDto) {
        if (userRepository.existsUserByEmail(userDto.getEmail()))
            throw new UserAlreadyExistException("There is already an account with that email address: " + userDto.getEmail());
        userRepository.save(userMapper.toUser(userDto));
    }

}
