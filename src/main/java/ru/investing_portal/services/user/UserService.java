package ru.investing_portal.services.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.investing_portal.dto.UserDto;
import ru.investing_portal.errors.UserAlreadyExistException;
import ru.investing_portal.mappers.UserMapper;
import ru.investing_portal.models.domain.User;
import ru.investing_portal.repos.UserRepository;

import javax.persistence.EntityNotFoundException;

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

    private User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Пользователь с id " + id + " не был найден!"));
    }

    public UserDto findUserById(int id) {
        return userMapper.toDto(getUserById(id));
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

}
