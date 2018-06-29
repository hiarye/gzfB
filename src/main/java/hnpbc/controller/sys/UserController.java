package hnpbc.controller.sys;

import hnpbc.bean.FeedBack;
import hnpbc.bean.MyPasswordEncoder;
import hnpbc.bean.PageBean;
import hnpbc.common.Util;
import hnpbc.entity.sys.User;
import hnpbc.entity.sys.UserDept;
import hnpbc.service.sys.UserDeptService;
import hnpbc.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private MyPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDeptService userDeptService;

    @RequestMapping(value = "/getUsersByDeptIdAndPaged",method = {RequestMethod.POST,RequestMethod.GET})
    public PageBean getUsersByDeptIdAndPaged(@RequestBody Map<String,Object> reqMap, HttpServletRequest request) {
        String deptId = (String)reqMap.get("deptId");
        Integer currentPage = (Integer)reqMap.get("currentPage");
        List<User> users = null;
        PageBean page = new PageBean();
        if (deptId != null && !"".equals(deptId.trim())) {
            users = userService.selectUsersByDeptIdAndPaged(deptId, currentPage);
            int totalCount = userService.selectUsersCountByDeptId(deptId);
            page.setTotalCount(totalCount);
            page.setPageSize(10);
            page.setCurrentPage(currentPage);
            page.setPageNum(totalCount/10+1);
            page.setData(users);
        }else{
            page.setTotalCount(0);
            page.setPageSize(10);
            page.setCurrentPage(1);
            page.setPageNum(1);
        }
        return page;
    }

    @RequestMapping(value = "/insertOne",method = {RequestMethod.POST,RequestMethod.GET})
    public FeedBack insertOne(@RequestBody Map<String,Object> reqMap, HttpServletRequest request) {
        User user = mapToEntity((Map) reqMap.get("formItem"));
        String deptId = (String)reqMap.get("deptId");

        FeedBack fb = new FeedBack();
        if (user.getUsername() != null && !"".equals(user.getUsername().trim()) && deptId != null && !"".equals(deptId.trim())) {
            String encPassword = passwordEncoder.encode("password");
            user.setPassword(encPassword);
            userService.insertOne(user);
            UserDept userDept = new UserDept();
            userDept.setUsername(user.getUsername());
            userDept.setDeptId(deptId);
            userDeptService.insertOne(userDept);
            fb.setType(FeedBack.TYPE_SUCC);
        } else {
            fb.setType(FeedBack.TYPE_FAIL);
        }
        return fb;
    }

    @RequestMapping(value = "/updateOne",method = {RequestMethod.POST,RequestMethod.GET})
    public FeedBack updateOne(@RequestBody Map<String,Object> reqMap, HttpServletRequest request) {
        User user = mapToEntity((Map) reqMap.get("formItem"));
        FeedBack fb = new FeedBack();
        if (user.getUsername() != null && !"".equals(user.getUsername().trim()) ) {
            userService.updateOne(user);
            fb.setType(FeedBack.TYPE_SUCC);
        } else {
            fb.setType(FeedBack.TYPE_FAIL);
        }
        return fb;
    }

    @RequestMapping(value = "/resetPassword",method = {RequestMethod.POST,RequestMethod.GET})
    public FeedBack resetPassword(@RequestBody Map<String,Object> reqMap, HttpServletRequest request) {
        String username = (String)reqMap.get("username");
        FeedBack fb = new FeedBack();
        if (username != null && !"".equals(username.trim())) {
            String encPassword = passwordEncoder.encode("password");
            userService.resetPassword(username,encPassword);
            fb.setType(FeedBack.TYPE_SUCC);
        } else {
            fb.setType(FeedBack.TYPE_FAIL);
        }
        return fb;
    }

    @RequestMapping(value = "/deleteOne",method = {RequestMethod.POST,RequestMethod.GET})
    public FeedBack deleteOne(@RequestBody Map<String,Object> reqMap, HttpServletRequest request) {
        FeedBack fb = new FeedBack();
        String username = (String)reqMap.get("username");
        if (username != null && !"".equals(username.trim())) {
            userService.deleteOne(username);
            fb.setType(FeedBack.TYPE_SUCC);
        } else {
            fb.setType(FeedBack.TYPE_FAIL);
        }
        return fb;
    }

    private User mapToEntity(Map<String,Object> map){
        User user = new User();
        user.setUsername((String)map.get("username"));
        user.setCname((String)map.get("cname"));
        user.setEname((String)map.get("ename"));
        user.setEnabled((Boolean) map.get("enabled"));
        user.setIdentityCard((String) map.get("identityCard"));
        user.setEmail((String)map.get("email"));
        user.setMobile((String)map.get("mobile"));
        String strCreateDate = (String)map.get("createDate");
        user.setCreateDate(strCreateDate == null ? new Date():Util.str2date(strCreateDate));
        user.setModifyDate(new Date());
        return user;
    }
}
