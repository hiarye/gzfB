package hnpbc.service.sys.impl;

import com.github.pagehelper.PageHelper;
import hnpbc.dao.sys.UserMapper;
import hnpbc.entity.sys.User;
import hnpbc.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User selectOneByPrimaryKey(String username) {
        return userMapper.selectByPrimaryKey(username);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> selectUsersByDeptIdAndPaged(String deptId, Integer currentPage) {
        int offset = (currentPage-1)*10;
        PageHelper.offsetPage(offset,10);
        List<User> users = null;
        if (deptId != null && !"".equals(deptId.trim())){
            users = userMapper.selectByDept(deptId);
        }
        return users;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int selectUsersCountByDeptId(String deptId) {
        return userMapper.selectCountByDept(deptId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertOne(User user) {
        userMapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateOne(User user) {
        userMapper.updateOneExceptPassword(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void resetPassword(String username,String password) {
        userMapper.updatePassword(username,password);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteOne(String username) {
        userMapper.deleteByPrimaryKey(username);
    }
}
