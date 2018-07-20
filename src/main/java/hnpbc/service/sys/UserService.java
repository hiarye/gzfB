package hnpbc.service.sys;

import hnpbc.entity.sys.User;

import java.util.List;

public interface UserService {

    User selectOneByPrimaryKey(String username);

    List<User> selectUsersByDeptIdAndPaged(String deptId, Integer currentPage);

    int selectUsersCountByDeptId(String deptId);

    void insertOne(User user);

    void updateOne(User user);

    void resetPassword(String username,String password);

    void deleteOne(String username);
}
