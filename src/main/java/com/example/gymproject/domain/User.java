package com.example.gymproject.domain;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "fuser", schema = "")
public class User {
    private String fid;
    private String adminId;
    private String name;
    private String sex;
    private String yearold;
    private String weight;
    private String height;
    private String state;

    @Id
    @Column(name = "fid", nullable = false, insertable = true, updatable = true, length = 128)
    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }
    @Basic
    @Column(name = "adminid", nullable = false, insertable = true, updatable = true, length = 128)
    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 128)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "sex", nullable = true, insertable = true, updatable = true, length = 2)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "yearold", nullable = true, insertable = true, updatable = true, length = 4)
    public String getYearold() {
        return yearold;
    }

    public void setYearold(String yearold) {
        this.yearold = yearold;
    }

    @Basic
    @Column(name = "weight", nullable = true, insertable = true, updatable = true, length = 4)
    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "height", nullable = true, insertable = true, updatable = true, length = 4)
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Basic
    @Column(name = "state", nullable = false, insertable = true, updatable = true, length = 2)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (fid != null ? !fid.equals(user.fid) : user.fid != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (sex != null ? !sex.equals(user.sex) : user.sex != null) return false;
        if (yearold != null ? !yearold.equals(user.yearold) : user.yearold != null) return false;
        if (weight != null ? !weight.equals(user.weight) : user.weight != null) return false;
        if (height != null ? !height.equals(user.height) : user.height != null) return false;
        return state != null ? state.equals(user.state) : user.state == null;
    }

    @Override
    public int hashCode() {
        int result = fid != null ? fid.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (yearold != null ? yearold.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (height != null ? height.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
