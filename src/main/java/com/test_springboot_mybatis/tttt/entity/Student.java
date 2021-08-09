package com.test_springboot_mybatis.tttt.entity;

import java.util.Date;

public class Student {
    private Integer sid;

    private String sname;

    private String sex;

    private Date birthday;

    private Integer cno;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getCno() {
        return cno;
    }

    public void setCno(Integer cno) {
        this.cno = cno;
    }

    public Student(Integer sid, String sname, String sex) {
        this.sid = sid;
        this.sname = sname;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", cno=" + cno +
                '}';
    }
}