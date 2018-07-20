package hnpbc.entity.sys;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_role_member")
public class RoleMember {
    @Id
    @Column(name = "ID_")
    private String id;

    @Column(name = "CREATE_DATE_")
    private Date createDate;

    @Column(name = "ROLE_ID_")
    private String roleId;

    @Column(name = "USERNAME_")
    private String username;

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
     * @return CREATE_DATE_
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
     * @return USERNAME_
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }
}