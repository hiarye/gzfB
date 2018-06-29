package hnpbc.entity.sys;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_user")
public class User {
    @Id
    @Column(name = "USERNAME_")
    private String username;

    /**
     * 是否系统管理员
     */
    @Column(name = "ADMINISTRATOR_")
    private Boolean administrator;

    /**
     * 中文名称
     */
    @Column(name = "CNAME_")
    private String cname;

    @Column(name = "CREATE_DATE_")
    private Date createDate;

    /**
     * 修改时间
     */
    @Column(name = "MODIFY_DATE_")
    private Date modifyDate;

    /**
     * 身份证号码
     */
    @Column(name = "IDENTITY_CARD_")
    private String identityCard;

    @Column(name = "EMAIL_")
    private String email;

    @Column(name = "ENABLED_")
    private Boolean enabled;

    @Column(name = "ENAME_")
    private String ename;

    @Column(name = "MOBILE_")
    private String mobile;

    @Column(name = "PASSWORD_")
    private String password;

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

    /**
     * 获取是否系统管理员
     *
     * @return ADMINISTRATOR_ - 是否系统管理员
     */
    public Boolean getAdministrator() {
        return administrator;
    }

    /**
     * 设置是否系统管理员
     *
     * @param administrator 是否系统管理员
     */
    public void setAdministrator(Boolean administrator) {
        this.administrator = administrator;
    }

    /**
     * 获取中文名称
     *
     * @return CNAME_ - 中文名称
     */
    public String getCname() {
        return cname;
    }

    /**
     * 设置中文名称
     *
     * @param cname 中文名称
     */
    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
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
     * 获取修改时间
     *
     * @return MODIFY_DATE_ - 修改时间
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * 设置修改时间
     *
     * @param modifyDate 修改时间
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * 获取身份证号码
     *
     * @return IDENTITY_CARD_ - 身份证号码
     */
    public String getIdentityCard() {
        return identityCard;
    }

    /**
     * 设置身份证号码
     *
     * @param identityCard 身份证号码
     */
    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard == null ? null : identityCard.trim();
    }

    /**
     * @return EMAIL_
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * @return ENABLED_
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return ENAME_
     */
    public String getEname() {
        return ename;
    }

    /**
     * @param ename
     */
    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    /**
     * @return MOBILE_
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * @return PASSWORD_
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}