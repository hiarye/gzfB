package hnpbc.service.sys.impl;

import hnpbc.dao.sys.TestMapper;
import hnpbc.entity.sys.Test;
import hnpbc.service.sys.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestMapper testMapper;

    @Override
    public Test t() {

        return testMapper.selectByPrimaryKey("f46d4a92-b15b-4948-8c36-a28f744952ad");
    }

    public List<Test> t2() {
        return testMapper.selectByName("test1");
    }
}
