package hnpbc.service.sys;

import hnpbc.entity.sys.Dept;

import java.util.List;

public interface DeptService {

    public Dept t();

    public List<Dept> selectByPage();

    public Dept selectByPrimaryKey(String id);

    public List<Dept> selectChildenByParentId(String parentId);

    public void insertOne(Dept dept,String parentCode);

    public void updateOne(Dept dept);

    public void deleteOne(Dept dept);
}
