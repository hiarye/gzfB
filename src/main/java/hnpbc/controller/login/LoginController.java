package hnpbc.controller.login;

import hnpbc.bean.FeedBack;
import hnpbc.bean.WebToken;
import hnpbc.bean.MyPasswordEncoder;
import hnpbc.entity.sys.User;
import hnpbc.service.sys.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
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
                    HttpSession session = request.getSession(true);
                    session.setAttribute("username",user.getUsername());
                    WebToken token = buildWebToken(username);
                    fb.setType(FeedBack.TYPE_SUCC);
                    fb.setData(token);
                    return fb;
                }
            }
        }
        fb.setType(FeedBack.TYPE_FAIL);
        fb.setData("登陆错误，用户名或密码不正确");
        return fb;
    }

    @RequestMapping(value = "/nologin",method = {RequestMethod.POST,RequestMethod.GET})
    public FeedBack noLogin(@RequestBody Map<String,Object> reqMap,HttpServletRequest request){
        FeedBack fb = new FeedBack();
        fb.setType(FeedBack.TYPE_NO_LOGIN);
        fb.setData("用户未登录");
        return fb;
    }

    public WebToken buildWebToken(String username){
        WebToken token = new WebToken();

        byte[] keySecretBytes = DatatypeConverter.parseBase64Binary("gzfxt");
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        Key signingKey = new SecretKeySpec(keySecretBytes, signatureAlgorithm.getJcaName());

        String compactJws = Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512,signingKey)
                .compact();

        token.setExpire("");
        token.setData(compactJws);
        return token;
    }
}
