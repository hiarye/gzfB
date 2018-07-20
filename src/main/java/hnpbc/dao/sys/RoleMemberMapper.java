package hnpbc.dao.sys;

import hnpbc.common.BaseDao;
import hnpbc.entity.sys.RoleMember;
import hnpbc.entity.sys.RoleMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMemberMapper extends BaseDao<RoleMember> {
    long countByExample(RoleMemberExample example);

    int deleteByExample(RoleMemberExample example);

    List<RoleMember> selectByExample(RoleMemberExample example);

    int updateByExampleSelective(@Param("record") RoleMember record, @Param("example") RoleMemberExample example);

    int updateByExample(@Param("record") RoleMember record, @Param("example") RoleMemberExample example);
}