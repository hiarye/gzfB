package hnpbc.controller.login;

import hnpbc.bean.FeedBack;
import hnpbc.bean.MyPasswordEncoder;
import hnpbc.entity.sys.User;
import hnpbc.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private MyPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = {RequestMethod.POST,RequestMethod.GET})
    public FeedBack authentication(@RequestBody Map<String,Object> reqMap,HttpServletRequest request){
        System.out.println("This is loginService");
        String username = reqMap.get("userName").toString();
        String password = reqMap.get("password").toString();

        // 查询数据库，实现用户身份验证
        FeedBack fb = new FeedBack();
        if(username!=null ) {
            if (password != null ) {
                User user = userService.selectOneByPrimaryKey(username);
                System.out.println(passwordEncoder.encode(password));
                if (user != null && passwordEncoder.matches(password,user.getPassword())) {
                    fb.setType(FeedBack.TYPE_SUCC);
                    fb.setData("登陆成功");
                    return fb;
                }
            }
        }
        fb.setType(FeedBack.TYPE_FAIL);
        fb.setData("登陆错误，用户名或密码不正确");
        return fb;
    }
}
