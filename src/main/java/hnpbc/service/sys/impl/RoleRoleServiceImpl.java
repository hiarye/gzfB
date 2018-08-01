package hnpbc.service.sys.impl;

import hnpbc.dao.sys.RoleRoleMapper;
import hnpbc.entity.sys.RoleRole;
import hnpbc.entity.sys.RoleRoleExample;
import hnpbc.service.sys.RoleRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RoleRoleServiceImpl implements RoleRoleService {
    @Autowired
    private RoleRoleMapper roleRoleMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RoleRole> selectRoleRole(String roleId) {
        RoleRoleExample example = new RoleRoleExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        return roleRoleMapper.selectByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveBatch(String roleId, List<String> roleIds) {
        List<RoleRole> list = new ArrayList<RoleRole>();
        for(String id2: roleIds){
            RoleRole RoleRole = new RoleRole();
            RoleRole.setId(UUID.randomUUID().toString());
            RoleRole.setRoleId(roleId);
            RoleRole.setRoleId2(id2);
            list.add(RoleRole);
        }
        if (list.size()>0) {
            roleRoleMapper.insertListIncludeId(list);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteBatch(String roleId) {
        RoleRoleExample example = new RoleRoleExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleRoleMapper.deleteByExample(example);
    }
}
