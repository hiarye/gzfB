package hnpbc.service.sys;

import hnpbc.entity.sys.RoleRouter;

import java.util.List;

public interface RoleRouterService {

    List<RoleRouter> selectRoleRouterByRole(String roleId);

    void saveBatch(String roleId, List<String> routerIds);

    void deleteBatch(String roleId);
}
