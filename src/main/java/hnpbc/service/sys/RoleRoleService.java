package hnpbc.service.sys;

import hnpbc.entity.sys.RoleRole;

import java.util.List;

public interface RoleRoleService {
    List<RoleRole> selectRoleRole(String roleId);

    void saveBatch(String roleId, List<String> routerIds);

    void deleteBatch(String roleId);
}
