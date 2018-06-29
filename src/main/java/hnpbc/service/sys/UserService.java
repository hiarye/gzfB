package hnpbc.service.sys;

import hnpbc.entity.sys.User;

import java.util.List;

public interface UserService {

    public User selectOneByPrimaryKey(String username);

    public List<User> selectUsersByDeptIdAndPaged(String deptId, Integer currentPage);

    public int selectUsersCountByDeptId(String deptId);

    public void insertOne(User user);

    public void updateOne(User user);

    public void resetPassword(String username,String password);

    public void deleteOne(String username);
}
