package hnpbc.service.sys.impl;

import com.github.pagehelper.PageHelper;
import hnpbc.dao.sys.RoleMapper;
import hnpbc.entity.sys.Role;
import hnpbc.service.sys.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Role> selectRolesPaged(Integer currentPage) {
        int offset = (currentPage-1)*10;
        PageHelper.offsetPage(offset,10);
        List<Role> roles = roleMapper.select(null);
        return roles;
    }

    @Override
    public List<Role> selectAllWithOneUser(String username) {
        return roleMapper.selectAllWithOneUser(username);
    }

    @Override
    public List<Role> selectAllWithOneRole(String roleid) {
        return roleMapper.selectAllWithOneRole(roleid);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int selectRolesCount() {
        return roleMapper.selectCount(null);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertOne(Role role) {
        role.setId(UUID.randomUUID().toString());
        roleMapper.insert(role);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteOne(String id) {
        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateOne(Role role) {
        roleMapper.updateByPrimaryKey(role);
    }
}
