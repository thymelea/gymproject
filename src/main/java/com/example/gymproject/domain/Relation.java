package com.example.gymproject.domain;

import javax.persistence.*;

@Entity
@Table(name = "ucrelation", schema = "")
public class Relation {
    private String fid;
    private String userId;
    private String courseId;
    private String startDate;
    private String endDate;
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
    @Column(name = "fuserid", nullable = false, insertable = true, updatable = true, length = 128)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "fcourseid", nullable = false, insertable = true, updatable = true, length = 128)
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
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

    public String getCollid() {
        return collid;
    }

    public void setCollid(String collid) {
        this.collid = collid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Relation)) return false;

        Relation relation = (Relation) o;

        if (fid != null ? !fid.equals(relation.fid) : relation.fid != null) return false;
        if (userId != null ? !userId.equals(relation.userId) : relation.userId != null) return false;
        if (courseId != null ? !courseId.equals(relation.courseId) : relation.courseId != null) return false;
        if (startDate != null ? !startDate.equals(relation.startDate) : relation.startDate != null) return false;
        if (endDate != null ? !endDate.equals(relation.endDate) : relation.endDate != null) return false;
        return collid != null ? collid.equals(relation.collid) : relation.collid == null;
    }

    @Override
    public int hashCode() {
        int result = fid != null ? fid.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (courseId != null ? courseId.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (collid != null ? collid.hashCode() : 0);
        return result;
    }
}
