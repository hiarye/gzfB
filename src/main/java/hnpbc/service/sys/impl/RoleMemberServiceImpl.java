package hnpbc.service.sys.impl;

import hnpbc.dao.sys.RoleMemberMapper;
import hnpbc.entity.sys.RoleMember;
import hnpbc.entity.sys.RoleMemberExample;
import hnpbc.service.sys.RoleMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RoleMemberServiceImpl implements RoleMemberService {
    @Autowired
    private RoleMemberMapper roleMemberMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveBatchByUser(String username, List<String> roleIds) {
        List<RoleMember> list = new ArrayList<RoleMember>();
        for(String roleId: roleIds){
            RoleMember RoleMember = new RoleMember();
            RoleMember.setId(UUID.randomUUID().toString());
            RoleMember.setRoleId(roleId);
            RoleMember.setUsername(username);
            list.add(RoleMember);
        }
        if (list.size()>0) {
            roleMemberMapper.insertListIncludeId(list);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteBathByUser(String username) {
        RoleMemberExample example = new RoleMemberExample();
        example.createCriteria().andUsernameEqualTo(username);
        roleMemberMapper.deleteByExample(example);
    }
}
