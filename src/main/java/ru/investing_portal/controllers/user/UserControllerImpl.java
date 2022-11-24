package ru.investing_portal.controllers.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.investing_portal.dto.UserDto;
import ru.investing_portal.services.user.UserDetailServiceImpl;
import ru.investing_portal.services.user.UserService;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    private final UserDetailServiceImpl userDetailService;

    @Override
    public void create(UserDto userDto) {
        userService.createUser(userDto);
    }

}
