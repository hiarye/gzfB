package hnpbc.controller.login;

import hnpbc.bean.FeedBack;
import hnpbc.bean.WebToken;
import hnpbc.bean.MyPasswordEncoder;
import hnpbc.common.Util;
import hnpbc.entity.sys.Dept;
import hnpbc.entity.sys.Router;
import hnpbc.entity.sys.User;
import hnpbc.entity.sys.UserDept;
import hnpbc.service.sys.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.sf.json.JSONObject;
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
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private MyPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDeptService userDeptService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRouterService roleRouterService;

    @Autowired
    private RouterService routerService;


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
//                System.out.println(passwordEncoder.encode(password));
                if (user != null && passwordEncoder.matches(password,user.getPassword())) {
                    // 验证通过后，设置用户信息、所在部门、角色信息、权限信息
                    UserDept userDept = userDeptService.selectOneByUserName(username);
                    Dept org = deptService.selectOrg(userDept.getDeptId());

                    HttpSession session = request.getSession(true);
                    session.setAttribute("username",user.getUsername());
                    session.setAttribute("orgid",org.getId());
                    WebToken token = buildWebToken(username,org.getId());
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

    @RequestMapping(value = "/getRouters",method = {RequestMethod.POST,RequestMethod.GET})
    public FeedBack getRouters(HttpServletRequest request){
        FeedBack fb = new FeedBack();
        String username = null;

        String token = request.getHeader("Authorization");
        if(token != null && !"".equals(token)) {
            String jsonStr = Util.decodeJwt(token);
            JSONObject json = JSONObject.fromObject(jsonStr);
            username = (String)json.get("username");
        }
        if (username != null && !"".equals(username.trim())) {
            List<Router> list = null;
            if (username.trim().equalsIgnoreCase("iview_admin")) {
                list = routerService.selectAllRouter();
            } else {
                list = routerService.selectRouterByUsername(username);
            }
            fb.setType(FeedBack.TYPE_SUCC);
            fb.setData(list);
        } else {
            fb.setType(FeedBack.TYPE_NO_LOGIN);
            fb.setData("用户未登录");
        }
        return fb;
    }

    public WebToken buildWebToken(String username,String OrgId){
        WebToken token = new WebToken();

        byte[] keySecretBytes = DatatypeConverter.parseBase64Binary("gzfxt");
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        Key signingKey = new SecretKeySpec(keySecretBytes, signatureAlgorithm.getJcaName());

        JSONObject json = new JSONObject();
        json.put("username",username);
        json.put("orgid",OrgId);

        String compactJws = Jwts.builder()
                .setSubject(json.toString())
                .signWith(SignatureAlgorithm.HS512,signingKey)
                .compact();

        token.setExpire("");
        token.setData(compactJws);
        return token;
    }
}
