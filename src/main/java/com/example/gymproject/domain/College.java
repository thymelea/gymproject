package com.example.gymproject.domain;

import javax.persistence.*;

@Entity
@Table(name = "college", schema = "")
public class College {
    private String fid;
    private String adminid;
    private String collegename;
    private String address;

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
    @Column(name = "collegename", nullable = false, insertable = true, updatable = true, length = 128)
    public String getCollegename() {
        return collegename;
    }

    public void setCollegename(String collegename) {
        this.collegename = collegename;
    }

    @Basic
    @Column(name = "address", nullable = true, insertable = true, updatable = true, length = 256)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof College)) return false;

        College college = (College) o;

        if (fid != null ? !fid.equals(college.fid) : college.fid != null) return false;
        if (adminid != null ? !adminid.equals(college.adminid) : college.adminid != null) return false;
        if (collegename != null ? !collegename.equals(college.collegename) : college.collegename != null) return false;
        return address != null ? address.equals(college.address) : college.address == null;
    }

    @Override
    public int hashCode() {
        int result = fid != null ? fid.hashCode() : 0;
        result = 31 * result + (adminid != null ? adminid.hashCode() : 0);
        result = 31 * result + (collegename != null ? collegename.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
