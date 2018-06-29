package hnpbc.dao.sys;

import hnpbc.common.BaseDao;
import hnpbc.entity.sys.UserDept;
import hnpbc.entity.sys.UserDeptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserDeptMapper extends BaseDao<UserDept> {
    long countByExample(UserDeptExample example);

    int deleteByExample(UserDeptExample example);

    List<UserDept> selectByExample(UserDeptExample example);

    int updateByExampleSelective(@Param("record") UserDept record, @Param("example") UserDeptExample example);

    int updateByExample(@Param("record") UserDept record, @Param("example") UserDeptExample example);
}