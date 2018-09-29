package com.example.gymproject.domain;

import javax.persistence.*;

@Entity
@Table(name = "admin", schema = "")
public class Admin {
    private String fid;
    private String username;
    private String password;

    @Id
    @Column(name = "fid", nullable = false, insertable = true, updatable = true, length = 128)
    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    @Basic
    @Column(name = "username", nullable = false, insertable = true, updatable = true, length = 128)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, insertable = true, updatable = true, length = 128)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin)) return false;

        Admin admin = (Admin) o;

        if (username != null ? !username.equals(admin.username) : admin.username != null) return false;
        return password != null ? password.equals(admin.password) : admin.password == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
