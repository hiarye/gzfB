package hnpbc.entity.sys;

import javax.persistence.*;

@Table(name = "s_role_router")
public class RoleRouter {
    @Id
    @Column(name = "ID_")
    private String id;

    @Column(name = "ROLE_ID_")
    private String roleId;

    @Column(name = "ROUTER_ID_")
    private String routerId;

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
     * @return ROUTER_ID_
     */
    public String getRouterId() {
        return routerId;
    }

    /**
     * @param routerId
     */
    public void setRouterId(String routerId) {
        this.routerId = routerId == null ? null : routerId.trim();
    }
}