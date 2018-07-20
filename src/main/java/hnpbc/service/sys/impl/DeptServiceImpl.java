package hnpbc.service.sys.impl;

import com.github.pagehelper.PageHelper;
import hnpbc.dao.sys.DeptMapper;
import hnpbc.entity.sys.Dept;
import hnpbc.entity.sys.DeptExample;
import hnpbc.service.sys.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    DeptMapper deptMapper;

    public Dept t(){
        Object o = deptMapper.selectByPrimaryKey("f46d4a92-b15b-4948-8c36-a28f744952ad");
        System.out.println(o.toString());
        Dept t = new Dept();
        t.setDescribe("test");
        t = o instanceof Dept ? ((Dept) o) : null;
        return t;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Dept selectByPrimaryKey(String id) {
        return deptMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Dept> selectChildenByParentId(String parentId){
        DeptExample example = new DeptExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        return deptMapper.selectByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Dept selectOrg(String deptId) {
        Dept dept = deptMapper.selectByPrimaryKey(deptId);
        if (dept != null ) {
            if (dept.getIsOrg()) {
                return dept;
            } else {
                return selectOrg(dept.getParentId());
            }
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Dept> selectByPage() {
        PageHelper.offsetPage(0,5);
        List<Dept> deptList = deptMapper.select(null);

        return deptList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertOne(Dept dept,String parentCode) {
        String code = generateCode(dept.getParentId(),parentCode);
        dept.setId(UUID.randomUUID().toString());
        dept.setCode(code);
        dept.setCreateDate(new Date());
        deptMapper.insert(dept);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateOne(Dept dept) {
        dept.setCreateDate(new Date());
        deptMapper.updateByPrimaryKey(dept);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteOne(Dept dept) {
        // TODO,首先检查当前机构下是否有子机构

        // TODO,其次检查当前机构及子机构下是否有用户

      deptMapper.deleteByPrimaryKey(dept.getId());
    }


    // 根据父机构的code，生成当前机构的code
    @Transactional(propagation = Propagation.SUPPORTS)
    private String generateCode(String parentId,String parentCode){
        List<Dept> children = selectChildenByParentId(parentId);
        Map map = new HashMap();
        if(children == null) {
            return "1000";
        }
        for( Dept child : children){
            map.put(child.getCode(), child);
        }
        for(int i=1000;i<=9999;i++){
            String currentCode = parentCode==null||"".equals(parentCode.trim())?""+i : parentCode + i;
            Object o = map.get(currentCode);
            if(o == null){
                map.clear();
                return currentCode;
            }
        }
        map.clear();
        return "9999";
    }
}
