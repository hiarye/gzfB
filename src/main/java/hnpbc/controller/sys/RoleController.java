package hnpbc.controller.sys;

import hnpbc.bean.FeedBack;
import hnpbc.bean.MyPasswordEncoder;
import hnpbc.bean.PageBean;
import hnpbc.common.Util;
import hnpbc.entity.sys.*;
import hnpbc.service.sys.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RouterService routerService;

    @Autowired
    private RoleRouterService roleRouterService;

    @RequestMapping(value = "/getRolesPaged",method = {RequestMethod.POST,RequestMethod.GET})
    public PageBean getRolesPaged(@RequestBody Map<String,Object> reqMap, HttpServletRequest request) {
        Integer currentPage = (Integer)reqMap.get("currentPage");
        List<Role> roles = null;
        PageBean page = new PageBean();
        roles = roleService.selectRolesPaged(currentPage);
        int totalCount = roleService.selectRolesCount();
        page.setTotalCount(totalCount);
        page.setPageSize(10);
        page.setCurrentPage(currentPage);
        page.setPageNum(totalCount/10+1);
        page.setData(roles);

        return page;
    }

    @RequestMapping(value = "/insertOne",method = {RequestMethod.POST,RequestMethod.GET})
    public FeedBack insertOne(@RequestBody Map<String,Object> reqMap, HttpServletRequest request) {
        Role role = mapToEntity(reqMap);
        FeedBack fb = new FeedBack();
        if (role.getName() != null && !"".equals(role.getName().trim())) {
            roleService.insertOne(role);
            fb.setType(FeedBack.TYPE_SUCC);
        } else {
            fb.setType(FeedBack.TYPE_FAIL);
        }
        return fb;
    }

    @RequestMapping(value = "/updateOne",method = {RequestMethod.POST,RequestMethod.GET})
    public FeedBack updateOne(@RequestBody Map<String,Object> reqMap, HttpServletRequest request) {
        Role role = mapToEntity(reqMap);
        FeedBack fb = new FeedBack();
        if (role.getId() != null && !"".equals(role.getId().trim()) ) {
            roleService.updateOne(role);
            fb.setType(FeedBack.TYPE_SUCC);
        } else {
            fb.setType(FeedBack.TYPE_FAIL);
        }
        return fb;
    }

    @RequestMapping(value = "/deleteOne",method = {RequestMethod.POST,RequestMethod.GET})
    public FeedBack deleteOne(@RequestBody Map<String,Object> reqMap, HttpServletRequest request) {
        FeedBack fb = new FeedBack();
        String id = (String)reqMap.get("id");
        if (id != null && !"".equals(id.trim())) {
            roleService.deleteOne(id);
            fb.setType(FeedBack.TYPE_SUCC);
        } else {
            fb.setType(FeedBack.TYPE_FAIL);
        }
        return fb;
    }

//    @RequestMapping(value = "/getAuthTree",method = {RequestMethod.POST,RequestMethod.GET})
//    public FeedBack getAuthTree(@RequestBody Map<String,Object> reqMap, HttpServletRequest request) {
//        FeedBack fb = new FeedBack();
//        String roleId = (String)reqMap.get("roleId");
//        List<Router> list = routerService.selectAllAndAuthRecursion(roleId);
//        fb.setType(FeedBack.TYPE_SUCC);
//        fb.setData(list);  //去掉根节点
//        return fb;
//    }

    @RequestMapping(value = "/getRoleRouter",method = {RequestMethod.POST,RequestMethod.GET})
    public FeedBack getRoleRouter(@RequestBody Map<String,Object> reqMap, HttpServletRequest request) {
        FeedBack fb = new FeedBack();
        String roleId = (String)reqMap.get("roleId");
        List<RoleRouter> list = null;
        if (roleId != null && !"".equals(roleId.trim())) {
            list = roleRouterService.selectRoleRouterByRole(roleId);
            fb.setType(FeedBack.TYPE_SUCC);
            fb.setData(list);
        } else {
            fb.setType(FeedBack.TYPE_FAIL);
        }
        return fb;
    }

    @RequestMapping(value = "/saveRoleRouter",method = {RequestMethod.POST,RequestMethod.GET})
    public FeedBack saveRoleRouter(@RequestBody Map<String,Object> reqMap, HttpServletRequest request) {
        FeedBack fb = new FeedBack();
        String roleId = (String)reqMap.get("roleId");
        List<String> checkedIds = (List<String>)reqMap.get("checkedIds");
        roleRouterService.deleteBatch(roleId);
        roleRouterService.saveBatch(roleId, checkedIds);
        fb.setType(FeedBack.TYPE_SUCC);
        return fb;
    }


    private Role mapToEntity(Map<String,Object> map){
        Role role = new Role();
        role.setId(map.get("id") == null? null:(String)map.get("id"));
        role.setName((String)map.get("name"));
        role.setDescribe((String)map.get("describe"));
        role.setEnabled((Boolean) map.get("enabled"));
        String strCreateDate = (String)map.get("createDate");
        role.setCreateDate(strCreateDate == null ? new Date():Util.str2date(strCreateDate));
        role.setModifyDate(new Date());
        return role;
    }
}
