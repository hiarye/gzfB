package hnpbc.service.sys.impl;

import hnpbc.dao.sys.Test2Mapper;
import hnpbc.dao.sys.TestMapper;
import hnpbc.entity.sys.Test2;
import hnpbc.entity.sys.Test2Example;
import hnpbc.service.sys.Test2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Test2ServiceImpl implements Test2Service {
    @Autowired
    Test2Mapper test2Mapper;

    @Override
    public Test2 t1() {
        return null;
    }

    @Override
    public List<Test2> t2() {
        Test2Example example = new Test2Example();
        example.createCriteria().andNameEqualTo("test123");
        return test2Mapper.selectByExample(example);
    }

    @Override
    public List<Test2> t3(String name) {
        return test2Mapper.selectByName(name);
    }
}
