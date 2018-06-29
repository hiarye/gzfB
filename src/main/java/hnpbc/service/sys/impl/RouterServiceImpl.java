package hnpbc.service.sys.impl;

import hnpbc.dao.sys.RouterMapper;
import hnpbc.entity.sys.Router;
import hnpbc.entity.sys.RouterExample;
import hnpbc.service.sys.RouterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class RouterServiceImpl implements RouterService {

    @Autowired
    private RouterMapper routerMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertOne(Router router) {
        router.setId(UUID.randomUUID().toString());
        routerMapper.insert(router);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteOne(String id) {
        routerMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateOne(Router router) {
        routerMapper.updateByPrimaryKey(router);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Router selectByPrimaryKey(String id) {
        return routerMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Router> selectChildrenByParentId(String parentId) {
        RouterExample example = new RouterExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        return routerMapper.selectByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Router selectRoot() {
        RouterExample example = new RouterExample();
        example.createCriteria().andParentIdIsNull();
        return routerMapper.selectOneByExample(example);
    }

//    @Override
//    public List<Router> selectChildrenAndR(String roleId, String parentId) {
//        return routerMapper.selectChildrenAndAuth(roleId,parentId);
//    }

//    @Override
//    public List<Router> selectAllAndAuthRecursion(String roleId) {
//        return routerMapper.selectAllAndAuthRecursion(roleId);
//    }

    @Override
    public List<Router> selectAllRecursion() {
        return routerMapper.selectAllRecursion();
    }
}
