package hnpbc.entity.sys;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "TEST")
public class Test {
    @Id
    @Column(name = "ID_")
    @GeneratedValue(generator="UUID")
    private String id;

    @Column(name = "NAME_")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
