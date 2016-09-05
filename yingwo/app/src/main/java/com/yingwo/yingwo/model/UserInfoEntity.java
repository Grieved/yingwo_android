package com.yingwo.yingwo.model;

/**
 * Created by wangyu on 9/2/16.
 */

public class UserInfoEntity {
    private String name = "";
    private String password = "";
    private String face_img = "";
    private String grade = "";
    private String signature = "";
    private String school_id = "";
    private String academy_id = "";
    private int sex = 0;

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public UserInfoEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFace_img() {
        return face_img;
    }

    public void setFace_img(String face_img) {
        this.face_img = face_img;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public String getAcademy_id() {
        return academy_id;
    }

    public void setAcademy_id(String academy_id) {
        this.academy_id = academy_id;
    }

    public boolean rightFlag() {
        if (name.equals("") || grade.equals("") || signature.equals("") || school_id.equals(""))
            return false;
        return true;
    }

    @Override
    public String toString() {
        String content = "用户名:"+name+" 年级:"+grade+" 签名:"+signature+" 性别:"+sex+" 头像:"+face_img+" 学校id:"+school_id+" 专业id:"+academy_id;
        return content;
    }
}
