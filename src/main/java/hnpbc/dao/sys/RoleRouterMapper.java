package hnpbc.dao.sys;

import hnpbc.common.BaseDao;
import hnpbc.entity.sys.RoleRouter;
import hnpbc.entity.sys.RoleRouterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleRouterMapper extends BaseDao<RoleRouter> {
    long countByExample(RoleRouterExample example);

    int deleteByExample(RoleRouterExample example);

    List<RoleRouter> selectByExample(RoleRouterExample example);

    int updateByExampleSelective(@Param("record") RoleRouter record, @Param("example") RoleRouterExample example);

    int updateByExample(@Param("record") RoleRouter record, @Param("example") RoleRouterExample example);
}