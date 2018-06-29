package hnpbc.service.sys;

import hnpbc.entity.sys.Router;

import java.util.List;

public interface RouterService {
    public void insertOne(Router router);

    public void deleteOne(String id);

    public void updateOne(Router router);

    public Router selectByPrimaryKey(String id);

    public List<Router> selectChildrenByParentId(String parentId);

    public Router selectRoot();

//    public List<Router> selectChildrenAndR(String roleId, String parentId);

//    public List<Router> selectAllAndAuthRecursion(String roleId);

    public List<Router> selectAllRecursion();
}
