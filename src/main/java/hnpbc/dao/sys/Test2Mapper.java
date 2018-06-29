package hnpbc.dao.sys;

import hnpbc.common.BaseDao;
import hnpbc.entity.sys.Test2;
import hnpbc.entity.sys.Test2Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface Test2Mapper extends BaseDao<Test2> {
    long countByExample(Test2Example example);

    int deleteByExample(Test2Example example);

    List<Test2> selectByExample(Test2Example example);

    int updateByExampleSelective(@Param("record") Test2 record, @Param("example") Test2Example example);

    int updateByExample(@Param("record") Test2 record, @Param("example") Test2Example example);

    List<Test2> selectByName(String name);
}