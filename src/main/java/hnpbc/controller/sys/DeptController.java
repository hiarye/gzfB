package hnpbc.controller.sys;

import hnpbc.bean.FeedBack;
import hnpbc.entity.sys.Dept;
import hnpbc.entity.sys.Test;
import hnpbc.entity.sys.Test2;
import hnpbc.service.sys.DeptService;
import hnpbc.service.sys.Test2Service;
import hnpbc.service.sys.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping(value = "/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @Autowired
    private TestService testService;

    @Autowired
    private Test2Service test2Service;

    @RequestMapping(value = "/testtest",method = {RequestMethod.POST,RequestMethod.GET})
    public String test(HttpServletRequest request){

        Test t= testService.t();
        List<Test> t2 = testService.t2();
        Iterator<Test> it = t2.iterator();
        Test test = null;
        while(it.hasNext()){
            test = it.next();
            System.out.println("--------------"+test.getName());
        }

        return "aa"+t.getName();
    }

    @RequestMapping(value = "/test2",method = {RequestMethod.POST,RequestMethod.GET})
    public String test2(HttpServletRequest request){
        List<Test2> list= test2Service.t2();
        StringBuffer sb = new StringBuffer();
        for(Test2 test2:list){
            System.out.println(test2.getId() + ":::" + test2.getName());
            sb.append(test2.getName()+";;;");
        }
        return sb.toString();
    }

    @RequestMapping(value = "/test22",method = {RequestMethod.POST,RequestMethod.GET})
    public String test22(HttpServletRequest request){
        List<Test2> list= test2Service.t3("test123");
        StringBuffer sb = new StringBuffer();
        for(Test2 test2:list){
            System.out.println(test2.getId() + ":::" + test2.getName());
            sb.append(test2.getName()+";;;");
        }
        return sb.toString();
    }

    // 保存部门信息
    @RequestMapping(value = "/saveOne",method = {RequestMethod.POST,RequestMethod.GET})
    public Dept saveOne(@RequestBody Map<String,Object> reqMap,HttpServletRequest request) {
        Dept dept = mapToEntity(reqMap);
        if (dept.getId() == null || "".equals(dept.getId().trim())) {
            deptService.insertOne(dept,(String)reqMap.get("parentCode"));
        }else {
            deptService.updateOne(dept);
        }
//        System.out.println("调试");
        return dept;
    }

    @RequestMapping(value = "/deleteOne",method = {RequestMethod.POST,RequestMethod.GET})
    public FeedBack deleteOne(@RequestBody Map<String,Object> reqMap, HttpServletRequest request) {
        FeedBack fb = new FeedBack();
        String deptId = (String)reqMap.get("deptId");
        if (deptId != null && !"".equals(deptId.trim())) {
            Dept dept = new Dept();
            dept.setId(deptId);
            deptService.deleteOne(dept);
            fb.setType(FeedBack.TYPE_SUCC);
        }
        return fb;
    }

    @RequestMapping(value = "/updateDept",method = {RequestMethod.POST,RequestMethod.GET})
    public String updateOne(HttpServletRequest request) {
        deptService.updateOne(null);
        return "update dept success";
    }

    @RequestMapping(value = "/selectByPage",method = {RequestMethod.POST,RequestMethod.GET})
    public String selectByPage(HttpServletRequest request) {
        List<Dept> listDept =  deptService.selectByPage();
        StringBuffer sb = new StringBuffer();
        for(Dept dept : listDept) {
            System.out.println("分页部门" + dept.getName());
            sb.append(dept.getName() + ";;;;");

        }
        return sb.toString();
    }

    // 获取子部门
    @RequestMapping(value = "/getChildrenDepts",method = {RequestMethod.POST,RequestMethod.GET})
    public List<Dept> getChildrenByParentId(@RequestBody Map<String,Object> reqMap, HttpServletRequest request) {
        String deptId = (String)reqMap.get("deptId");
        List<Dept> listDept = null;
        if (deptId != null && !"".equals(deptId.trim())) {
            listDept = deptService.selectChildenByParentId(deptId);
        }
        return listDept;
    }

    // 获取指定部门
    @RequestMapping(value = "/getOne",method = {RequestMethod.POST,RequestMethod.GET})
    public Dept getOneById(@RequestBody Map<String,Object> reqMap, HttpServletRequest request) {
        String deptId = reqMap.get("deptId").toString();
        System.out.println(deptId);
        Dept dept = deptService.selectByPrimaryKey(deptId);
        if(dept == null || dept.getId() == null){
            dept = new Dept();
        }else {
            Dept parentDept = deptService.selectByPrimaryKey(dept.getParentId());
            dept.setParent(parentDept);
        }
        System.out.println(dept.getId());
        return dept;
    }

    // 根据当前用户获得其所在部门
    @RequestMapping(value = "/getRootNodeByUser",method = {RequestMethod.POST,RequestMethod.GET})
    public Dept getDeptByUser() {
        //从jwt中即可获得当前用户所在部门的基本信息
        String deptId = "f46d4a92-b15b-4948-8c36-a28f744952ad";
        String deptName = "cszz";

        Dept dept = deptService.selectByPrimaryKey(deptId);

        List<Dept> listDept = deptService.selectChildenByParentId(deptId);
        dept.setChildren(listDept);
        dept.setParent(null);
        return dept;
    }

    private Dept mapToEntity(Map<String,Object> map){
        Dept dept = new Dept();
        dept.setId((String)map.get("id"));
        dept.setCode((String)map.get("code"));
        dept.setName((String)map.get("name"));
        dept.setDescribe((String)map.get("describe"));
        dept.setParentId((String)map.get("parentId"));
        dept.setFinancialCode((String)map.get("financialCode"));
        dept.setAreaCode((String)map.get("areaCode"));
        dept.setIsOrg((Boolean)map.get("isOrg"));
        return dept;
    }
}
