package hnpbc.dao.sys;

import hnpbc.common.BaseDao;
import hnpbc.entity.sys.Test;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TestMapper extends BaseDao<Test> {
    @Select("select ID_ as id,NAME_ as name from test where name_ = #{name}")
    public List<Test> selectByName(@Param("name") String name);
}
