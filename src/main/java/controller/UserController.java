package controller;

/**
 * @author Jingzhou Ou
 * Created on 17-11-3
 */

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import user.model.User;
import user.service.UserService;
import org.apache.log4j.Logger;

import javax.annotation.Resource;

@Controller
@RequestMapping("/")
public class UserController {

    final static Logger logger = Logger.getLogger(UserController.class);  //log4j logger

    @Resource
    private UserService userService;

    /**
     * Get user by id
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "user/id/{userId}", method = RequestMethod.GET)
    public JSONObject getUserById(@PathVariable Integer userId) {
        JSONObject jsonObject = new JSONObject();
        try {
            User user = userService.getUserById(userId);
            if (user != null) {
                jsonObject.put("code", 200);
                jsonObject.put("id", user.getUserId());
                jsonObject.put("name", user.getUserName());
                jsonObject.put("email", user.getUserEmail());
            }
            else {
                jsonObject.put("code", 400);
                jsonObject.put("desc", "user does NOT exist");
            }
        }
        catch (Exception ex) {
            jsonObject.put("code", 500);
            logger.error(ex);

        }
        return jsonObject;
    }

    /**
     * Get user by name
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "user/name/{userName}", method = RequestMethod.GET)
    public JSONObject getUserByName(@PathVariable String userName) {
        JSONObject jsonObject = new JSONObject();
        try {
            User user = userService.getUserByName(userName);
            if (user != null) {
                jsonObject.put("code", 200);
                jsonObject.put("id", user.getUserId());
                jsonObject.put("name", user.getUserName());
                jsonObject.put("email", user.getUserEmail());
            }
            else {
                jsonObject.put("code", 400);
                jsonObject.put("desc", "user does NOT exist");
            }
        }
        catch (Exception ex) {
            jsonObject.put("code", 500);
        }
        return jsonObject;
    }

    /**
     * Create user
     * @param name
     * @param password
     * @param email
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "user", method = RequestMethod.POST)
    public JSONObject createUser(@RequestParam String name,
                                 @RequestParam String password,
                                 @RequestParam String email,
                                 @RequestParam(value = "id", required = false) Integer id) {
        User user = new User();
        user.setUserId(id);
        user.setUserName(name);
        user.setUserPassword(password);
        user.setUserEmail(email);
        JSONObject jsonObject = new JSONObject();
        try {
            int result = userService.insertUser(user);
            if (result == 1) {
                jsonObject.put("code", 200);
            }
            else {
                jsonObject.put("code", 500);
            }
        }
        catch (Exception ex) {
            jsonObject.put("code", 500);
        }
        return jsonObject;
    }

    /**
     * Update user by Id
     * @param id
     * @param name
     * @param password
     * @param email
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "user", method = RequestMethod.PUT)
    public JSONObject updateUserById(@RequestParam Integer id,
                                     @RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "password", required = false) String password,
                                     @RequestParam(value = "email", required = false) String email) {
        User user = new User();
        user.setUserId(id);
        user.setUserName(name);
        user.setUserPassword(password);
        user.setUserEmail(email);
        JSONObject jsonObject = new JSONObject();
        try {
            if (userService.getUserById(id) != null) {
                int result = userService.updateUser(user);
                if (result > 0) {
                    jsonObject.put("code", 200);
                }
            }
            else {
                jsonObject.put("code", 400);
                jsonObject.put("desc", "user does NOT exist");
            }
        }
        catch (Exception ex) {
            jsonObject.put("code", 500);
        }
        return jsonObject;
    }

    /**
     * Delete user by id
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "user/{userId}", method = RequestMethod.DELETE)
    public JSONObject deleteUser(@PathVariable Integer userId) {
        JSONObject jsonObject = new JSONObject();
        try {
            int result = userService.deleteUserById(userId);
            if (result > 0) {
                jsonObject.put("code", "200");
            }
            else {
                jsonObject.put("code", "400");
                jsonObject.put("desc", "User does NOT exist");
            }
        }
        catch (Exception ex) {
            jsonObject.put("code", "500");

        }
        return jsonObject;

    }

}
