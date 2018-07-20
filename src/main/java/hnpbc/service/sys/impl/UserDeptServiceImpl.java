package hnpbc.service.sys.impl;

import hnpbc.dao.sys.UserDeptMapper;
import hnpbc.entity.sys.UserDept;
import hnpbc.entity.sys.UserDeptExample;
import hnpbc.service.sys.UserDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserDeptServiceImpl implements UserDeptService {

    @Autowired
    private UserDeptMapper userDeptMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertOne(UserDept userDept) {
        userDept.setId(UUID.randomUUID().toString());
        userDeptMapper.insert(userDept);
    }

    @Override
    public UserDept selectOneByUserName(String username) {
        UserDeptExample example = new UserDeptExample();
        example.createCriteria().andUsernameEqualTo(username);
        return userDeptMapper.selectOneByExample(example);
    }
}
