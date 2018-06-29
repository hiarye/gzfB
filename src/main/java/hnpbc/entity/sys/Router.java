package hnpbc.entity.sys;

import javax.persistence.*;
import java.util.List;

@Table(name = "s_router")
public class Router {
    @Id
    @Column(name = "ID_")
    private String id;

    /**
     * 权限标题
     */
    @Column(name = "TITLE_")
    private String title;

    @Column(name = "NAME_")
    private String name;

    @Column(name = "DESCRIBE_")
    private String describe;

    @Column(name = "SORT_ORDER_")
    private Integer sortOrder;

    @Column(name = "PARENT_ID_")
    private String parentId;

    @Transient
    private Router parent;

    @Transient
    private List<Router> children;

//    @Transient
//    private RoleRouter roleRouter; // 特殊用途，用于将显示某特定角色与路由的一对一关系

//    public RoleRouter getRoleRouter() {
//        return roleRouter;
//    }
//
//    public void setRoleRouter(RoleRouter roleRouter) {
//        this.roleRouter = roleRouter;
//    }

    public Router getParent() {
        return parent;
    }

    public void setParent(Router parent) {
        this.parent = parent;
    }

    public List<Router> getChildren() {
        return children;
    }

    public void setChildren(List<Router> children) {
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
     * 获取权限标题
     *
     * @return TITLE_ - 权限标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置权限标题
     *
     * @param title 权限标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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
     * @return SORT_ORDER_
     */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     * @param sortOrder
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * @return PARENT_ID_
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }
}