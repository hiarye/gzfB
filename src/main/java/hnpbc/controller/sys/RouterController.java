package hnpbc.controller.sys;

import hnpbc.bean.FeedBack;
import hnpbc.entity.sys.Router;
import hnpbc.service.sys.RouterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping(value = "/router")
public class RouterController {
    @Autowired
    private RouterService routerService;

    // 保存信息
    @RequestMapping(value = "/saveOne",method = {RequestMethod.POST,RequestMethod.GET})
    public Router saveOne(@RequestBody Map<String,Object> reqMap, HttpServletRequest request) {
        Router router = mapToEntity(reqMap);
        if (router.getId() == null || "".equals(router.getId().trim())) {
            routerService.insertOne(router);
        }else {
            routerService.updateOne(router);
        }
//        System.out.println("调试");
        return router;
    }

    @RequestMapping(value = "/deleteOne",method = {RequestMethod.POST,RequestMethod.GET})
    public FeedBack deleteOne(@RequestBody Map<String,Object> reqMap, HttpServletRequest request) {
        FeedBack fb = new FeedBack();
        String id = (String)reqMap.get("id");
        if (id != null && !"".equals(id.trim())) {
            routerService.deleteOne(id);
            fb.setType(FeedBack.TYPE_SUCC);
        } else {
            fb.setType(FeedBack.TYPE_FAIL);
            fb.setData("没有找到删除的对象");
        }
        return fb;
    }

    // 获取子节点
    @RequestMapping(value = "/getChildren",method = {RequestMethod.POST,RequestMethod.GET})
    public FeedBack getChildrenByParentId(@RequestBody Map<String,Object> reqMap, HttpServletRequest request) {
        FeedBack fb = new FeedBack();
        String id = (String)reqMap.get("id");
        List<Router> list = null;
        if (id != null && !"".equals(id.trim())) {
            list = routerService.selectChildrenByParentId(id);
            fb.setType(FeedBack.TYPE_SUCC);
            fb.setData(list);
        } else {
            fb.setType(FeedBack.TYPE_FAIL);
        }
        return fb;
    }

    // 获取指定节点
    @RequestMapping(value = "/getOne",method = {RequestMethod.POST,RequestMethod.GET})
    public Router getOne(@RequestBody Map<String,Object> reqMap, HttpServletRequest request) {
        String id = (String)reqMap.get("id");
        Router router = routerService.selectByPrimaryKey(id);
        if(router == null || router.getId() == null){
            router = new Router();
        }else {
            Router parent = routerService.selectByPrimaryKey(router.getParentId());
            router.setParent(parent);
        }
        return router;
    }

    // 获取根节点
    @RequestMapping(value = "/getRoot",method = {RequestMethod.POST,RequestMethod.GET})
    public FeedBack getRoot() {
        FeedBack fb = new FeedBack();
        Router router = routerService.selectRoot();
        if(router != null && !"".equals(router.getId().trim())) {
            List<Router> list = routerService.selectChildrenByParentId(router.getId());
            if (list == null) {
                list = new ArrayList<Router>();
            }
            router.setChildren(list);
            router.setParent(null);
            fb.setType(FeedBack.TYPE_SUCC);
            fb.setData(router);
        }else {
            fb.setType(FeedBack.TYPE_FAIL);
        }
        return fb;
    }

    @RequestMapping(value = "/getAllTree",method = {RequestMethod.POST,RequestMethod.GET})
    public FeedBack getAllTree(HttpServletRequest request) {
        FeedBack fb = new FeedBack();
        List<Router> list = routerService.selectAllRecursion();
        fb.setType(FeedBack.TYPE_SUCC);
        fb.setData(list);  //去掉根节点
        return fb;
    }

    private Router mapToEntity(Map<String,Object> map){
        Router obj = new Router();
        obj.setId((String)map.get("id"));
        obj.setName((String)map.get("name"));
        obj.setTitle((String)map.get("title"));
        obj.setDescribe((String)map.get("describe"));
        obj.setSortOrder((Integer)map.get("sortOrder"));
        obj.setParentId((String)map.get("parentId"));
        return obj;
    }
}
