package hnpbc.service.sys;

import hnpbc.entity.sys.Role;

import java.util.List;

public interface RoleService {

    List<Role> selectRolesPaged(Integer currentPage);

    List<Role> selectAllWithOneUser(String username);

    List<Role> selectAllWithOneRole(String roleid);

    int selectRolesCount();

    void insertOne(Role role);

    void deleteOne(String id);

    void updateOne(Role role);
}
