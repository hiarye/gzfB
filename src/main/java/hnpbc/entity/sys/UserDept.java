package hnpbc.entity.sys;

import javax.persistence.*;

@Table(name = "s_user_dept")
public class UserDept {
    @Id
    @Column(name = "ID_")
    private String id;

    @Column(name = "DEPT_ID_")
    private String deptId;

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
     * @return DEPT_ID_
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * @param deptId
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
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