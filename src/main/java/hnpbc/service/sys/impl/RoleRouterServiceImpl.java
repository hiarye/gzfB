package hnpbc.service.sys.impl;

import hnpbc.dao.sys.RoleRouterMapper;
import hnpbc.entity.sys.RoleRouter;
import hnpbc.entity.sys.RoleRouterExample;
import hnpbc.service.sys.RoleRouterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RoleRouterServiceImpl implements RoleRouterService {
    @Autowired
    private RoleRouterMapper roleRouterMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RoleRouter> selectRoleRouterByRole(String roleId) {
        RoleRouterExample example = new RoleRouterExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        return roleRouterMapper.selectByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveBatch(String roleId, List<String> routerIds) {
        List<RoleRouter> list = new ArrayList<RoleRouter>();
        for(String routerId: routerIds){
            RoleRouter roleRouter = new RoleRouter();
            roleRouter.setId(UUID.randomUUID().toString());
            roleRouter.setRoleId(roleId);
            roleRouter.setRouterId(routerId);
            list.add(roleRouter);
        }
        if (list.size()>0) {
            roleRouterMapper.insertListIncludeId(list);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteBatch(String roleId) {
        RoleRouterExample example = new RoleRouterExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleRouterMapper.deleteByExample(example);
    }
}
