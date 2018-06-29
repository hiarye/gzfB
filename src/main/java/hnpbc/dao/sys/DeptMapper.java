package hnpbc.dao.sys;

import hnpbc.common.BaseDao;
import hnpbc.entity.sys.Dept;
import hnpbc.entity.sys.DeptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeptMapper extends BaseDao<Dept> {
    long countByExample(DeptExample example);

    int deleteByExample(DeptExample example);

    List<Dept> selectByExample(DeptExample example);

    int updateByExampleSelective(@Param("record") Dept record, @Param("example") DeptExample example);

    int updateByExample(@Param("record") Dept record, @Param("example") DeptExample example);
}