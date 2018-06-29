package hnpbc.dao.sys;

import hnpbc.common.BaseDao;
import hnpbc.entity.sys.User;
import hnpbc.entity.sys.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseDao<User> {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    List<User> selectByExample(UserExample example);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    List<User> selectByDept(String deptId);

    int selectCountByDept(String deptId);

    int updatePassword(@Param("username") String username,@Param("password") String password);

    int updateOneExceptPassword(@Param("user")User user);
}