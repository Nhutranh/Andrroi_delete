package com.phanngocnhutranh.Giangvien;

public class Giangvien {
    String code, name, classroom;

    public Giangvien(String code, String name, String classroom) {
        this.code = code;
        this.name = name;
        this.classroom = classroom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
}
