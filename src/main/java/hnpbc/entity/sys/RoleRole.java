package hnpbc.entity.sys;

import javax.persistence.*;

@Table(name = "s_role_role")
public class RoleRole {
    @Id
    @Column(name = "ID_")
    private String id;

    @Column(name = "ROLE_ID_")
    private String roleId;

    @Column(name = "ROLE_ID_2")
    private String roleId2;

    /**
     * @return ID_
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return ROLE_ID_
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    /**
     * @return ROLE_ID_2
     */
    public String getRoleId2() {
        return roleId2;
    }

    /**
     * @param roleId2
     */
    public void setRoleId2(String roleId2) {
        this.roleId2 = roleId2 == null ? null : roleId2.trim();
    }
}