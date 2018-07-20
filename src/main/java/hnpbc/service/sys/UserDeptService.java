package hnpbc.service.sys;

import hnpbc.entity.sys.User;
import hnpbc.entity.sys.UserDept;

public interface UserDeptService {

    void insertOne(UserDept userDept);

    UserDept selectOneByUserName(String username);
}
