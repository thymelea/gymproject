package com.example.gymproject.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "fcourse", schema = "")
public class Course {
    private String fid;
    private String adminid;
    private String collegeId;
    private String name;
    private String startDate;
    private String endDate;
    private String state;

    private String uuId;
    private Object dateCollege;
    private Object dateUser;
    private String collid;
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
    public String getAdminid() {
        return adminid;
    }

    public void setAdminid(String adminid) {
        this.adminid = adminid;
    }

    @Basic
    @Column(name = "collegeid", nullable = false, insertable = true, updatable = true, length = 128)
    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    @Basic
    @Column(name = "cname", nullable = false, insertable = true, updatable = true, length = 128)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "startdate", nullable = true, insertable = true, updatable = true, length = 32)
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "enddate", nullable = true, insertable = true, updatable = true, length = 32)
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "state", nullable = false, insertable = true, updatable = true, length = 2)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Transient
    public String getUuId() {
        return uuId;
    }

    public void setUuId(String uuId) {
        this.uuId = uuId;
    }

    @Transient
    public Object getDateCollege() {
        return dateCollege;
    }

    public void setDateCollege(Object dateCollege) {
        this.dateCollege = dateCollege;
    }

    @Transient
    public Object getDateUser() {
        return dateUser;
    }

    public void setDateUser(Object dateUser) {
        this.dateUser = dateUser;
    }


    @Transient
    public String getCollid() {
        return collid;
    }

    public void setCollid(String collid) {
        this.collid = collid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;

        Course course = (Course) o;

        if (fid != null ? !fid.equals(course.fid) : course.fid != null) return false;
        if (adminid != null ? !adminid.equals(course.adminid) : course.adminid != null) return false;
        if (collegeId != null ? !collegeId.equals(course.collegeId) : course.collegeId != null) return false;
        if (name != null ? !name.equals(course.name) : course.name != null) return false;
        if (startDate != null ? !startDate.equals(course.startDate) : course.startDate != null) return false;
        if (endDate != null ? !endDate.equals(course.endDate) : course.endDate != null) return false;
        return state != null ? state.equals(course.state) : course.state == null;
    }

    @Override
    public int hashCode() {
        int result = fid != null ? fid.hashCode() : 0;
        result = 31 * result + (adminid != null ? adminid.hashCode() : 0);
        result = 31 * result + (collegeId != null ? collegeId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
