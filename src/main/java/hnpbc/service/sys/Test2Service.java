package hnpbc.service.sys;

import hnpbc.entity.sys.Test;
import hnpbc.entity.sys.Test2;

import java.util.List;

public interface Test2Service {
    Test2 t1();

    List<Test2> t2();

    List<Test2> t3(String name);
}
