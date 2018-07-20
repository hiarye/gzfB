package hnpbc.service.sys;

import hnpbc.entity.sys.Router;

import java.util.List;

public interface RouterService {
    void insertOne(Router router);

    void deleteOne(String id);

    void updateOne(Router router);

    Router selectByPrimaryKey(String id);

    List<Router> selectChildrenByParentId(String parentId);

    List<Router> selectRouterByUsername(String username);

    List<Router> selectAllRouter();

    Router selectRoot();

    List<Router> selectAllRecursion();
}
