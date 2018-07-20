package hnpbc.service.sys;

import hnpbc.entity.sys.Role;

import java.util.List;

public interface RoleService {

    List<Role> selectRolesPaged(Integer currentPage);

    List<Role> selectAllWithOneUser(String username);

    int selectRolesCount();

    void insertOne(Role role);

    void deleteOne(String id);

    void updateOne(Role role);
}
