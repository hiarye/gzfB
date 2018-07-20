package hnpbc.service.sys;

import java.util.List;

public interface RoleMemberService {

    void saveBatchByUser(String username, List<String> roleIds);

    void deleteBathByUser(String username);
}
