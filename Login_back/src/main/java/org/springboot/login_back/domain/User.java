package org.springboot.login_back.domain;

/**
 * @Author:Zikun Zhang
 * @Student ID(NCHU):21224131
 * @Student ID(NCI):X21205833
 * @Date: 2024/5/16 下午9:07
 * @Description:
 **/

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Table(name = "user")
@Entity
public class User {
    // 注意属性名要与数据表中的字段名一致
    // 主键自增int(10)对应long

    // 用户名属性varchar对应String
    private String uname;

    // 密码属性varchar对应String
    private String password;

    public User(String uname, String password, String role, long uid) {
        this.uname = uname;
        this.password = password;
        this.role = role;
        this.uid = uid;
    }

    // role属性varchar对应String
    private String role;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    public User() {

    }

    public String getUname() {
        return uname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}