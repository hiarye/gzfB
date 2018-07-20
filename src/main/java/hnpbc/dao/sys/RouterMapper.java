package hnpbc.dao.sys;

import hnpbc.common.BaseDao;
import hnpbc.entity.sys.Router;
import hnpbc.entity.sys.RouterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RouterMapper extends BaseDao<Router> {
    long countByExample(RouterExample example);

    int deleteByExample(RouterExample example);

    List<Router> selectByExample(RouterExample example);

    int updateByExampleSelective(@Param("record") Router record, @Param("example") RouterExample example);

    int updateByExample(@Param("record") Router record, @Param("example") RouterExample example);

    List<Router> selectChildrenAndAuth(@Param("roleId") String roleId, @Param("parentId") String parentId);

    List<Router> selectAllAndAuthRecursion(@Param("roleId") String roleId);

    List<Router> selectAllRecursion();

    List<Router> selectAllByUsername(@Param("username") String username);
}