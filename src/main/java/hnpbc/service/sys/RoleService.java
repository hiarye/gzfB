package hnpbc.service.sys;

import hnpbc.entity.sys.Role;

import java.util.List;

public interface RoleService {

    public List<Role> selectRolesPaged(Integer currentPage);

    public int selectRolesCount();

    public void insertOne(Role role);

    public void deleteOne(String id);

    public void updateOne(Role role);
}
