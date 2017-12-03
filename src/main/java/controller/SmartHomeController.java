package controller;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import user.model.User;
import user.service.UserService;

import javax.annotation.Resource;

/**
 * @author Jingzhou Ou
 * Created on 17-12-3
 */
@Controller
@RequestMapping("/")
public class SmartHomeController {

    @Resource
    private UserService userService;

    /**
     * Update led status
     * @param id
     * @param name
     * @param password
     * @param email
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "led/v1", method = RequestMethod.GET)
    public JSONObject updateLed(@RequestParam Integer id,
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
                    jsonObject.put("LED", user.getUserId());
                    jsonObject.put("Status", user.getUserName());
                }
            }
            else {
                jsonObject.put("code", 400);
                jsonObject.put("desc", "led info does not exist");
            }
        }
        catch (Exception ex) {
            jsonObject.put("code", 500);
        }
        return jsonObject;
    }
}
