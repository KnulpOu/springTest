package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import user.model.User;
import user.service.UserService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/")
public class UserController {
    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "user/getUserById/{userId}", method = RequestMethod.GET)
    public User getUserById(@PathVariable Integer userId) {
        return userService.getUserById(userId);
    }

    @ResponseBody
    @RequestMapping(value = "user/getUserByName/{userName}", method = RequestMethod.GET)
    public User getUserById(@PathVariable String userName) {
        return userService.getUserByName(userName);
    }

}
