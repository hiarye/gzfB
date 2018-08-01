package hnpbc.dao.sys;

import hnpbc.common.BaseDao;
import hnpbc.entity.sys.RoleRole;
import hnpbc.entity.sys.RoleRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleRoleMapper extends BaseDao<RoleRole> {
    long countByExample(RoleRoleExample example);

    int deleteByExample(RoleRoleExample example);

    List<RoleRole> selectByExample(RoleRoleExample example);

    int updateByExampleSelective(@Param("record") RoleRole record, @Param("example") RoleRoleExample example);

    int updateByExample(@Param("record") RoleRole record, @Param("example") RoleRoleExample example);
}