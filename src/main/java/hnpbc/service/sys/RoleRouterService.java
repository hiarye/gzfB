package hnpbc.service.sys;

import hnpbc.entity.sys.RoleRouter;

import java.util.List;

public interface RoleRouterService {

    public List<RoleRouter> selectRoleRouterByRole(String roleId);

    public void saveBatch(String roleId, List<String> routerIds);

    public void deleteBatch(String roleId);
}
