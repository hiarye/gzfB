package hnpbc.service.sys;

import hnpbc.entity.sys.Test;
import hnpbc.entity.sys.Test2;

import java.util.List;

public interface Test2Service {
    public Test2 t1();

    public List<Test2> t2();

    public List<Test2> t3(String name);
}
