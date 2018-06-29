package hnpbc.entity.sys;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "s_dept")
public class Dept {
    @Id
    @Column(name = "ID_")
    private String id;

    /**
     * 体现上下级关系，减少递归查询
     */
    @Column(name = "CODE_")
    private String code;

    @Column(name = "CREATE_DATE_")
    private Date createDate;

    @Column(name = "DESCRIBE_")
    private String describe;

    @Column(name = "NAME_")
    private String name;

    /**
     * 父节点ID
     */
    @Column(name = "PARENT_ID_")
    private String parentId;

    /**
     * 金融机构编码
     */
    @Column(name = "FINANCIAL_CODE_")
    private String financialCode;

    /**
     * 地区编码,GB/T2260
     */
    @Column(name = "AREA_CODE_")
    private String areaCode;

    /**
     * 是否机构
     */
    @Column(name = "IS_ORG_")
    private Boolean isOrg;

    @Transient
    private Dept parent;

    @Transient
    private List<Dept> children;

    public List<Dept> getChildren() {
        return children;
    }

    public void setChildren(List<Dept> children) {
        this.children = children;
    }

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
     * 获取体现上下级关系，减少递归查询
     *
     * @return CODE_ - 体现上下级关系，减少递归查询
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置体现上下级关系，减少递归查询
     *
     * @param code 体现上下级关系，减少递归查询
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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

    public Dept getParent() {
        return parent;
    }

    public void setParent(Dept parent) {
        this.parent = parent;
    }

    /**
     * @return DESCRIBE_
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * @param describe
     */
    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    /**
     * @return NAME_
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取父节点ID
     *
     * @return PARENT_ID_ - 父节点ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置父节点ID
     *
     * @param parentId 父节点ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * 获取金融机构编码
     *
     * @return FINANCIAL_CODE_ - 金融机构编码
     */
    public String getFinancialCode() {
        return financialCode;
    }

    /**
     * 设置金融机构编码
     *
     * @param financialCode 金融机构编码
     */
    public void setFinancialCode(String financialCode) {
        this.financialCode = financialCode == null ? null : financialCode.trim();
    }

    /**
     * 获取地区编码,GB/T2260
     *
     * @return AREA_CODE_ - 地区编码,GB/T2260
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 设置地区编码,GB/T2260
     *
     * @param areaCode 地区编码,GB/T2260
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * 获取是否机构
     *
     * @return IS_ORG_ - 是否机构
     */
    public Boolean getIsOrg() {
        return isOrg;
    }

    /**
     * 设置是否机构
     *
     * @param isOrg 是否机构
     */
    public void setIsOrg(Boolean isOrg) {
        this.isOrg = isOrg;
    }
}