package hnpbc.service.sys;

import hnpbc.entity.sys.Dept;

import java.util.List;

public interface DeptService {

    Dept t();

    List<Dept> selectByPage();

    Dept selectByPrimaryKey(String id);

    List<Dept> selectChildenByParentId(String parentId);

    Dept selectOrg(String deptId);

    void insertOne(Dept dept,String parentCode);

    void updateOne(Dept dept);

    void deleteOne(Dept dept);
}
