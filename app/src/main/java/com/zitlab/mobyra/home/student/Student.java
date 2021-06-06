package com.zitlab.mobyra.home.student;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zitlab.mobyra.home.detail.marks.pojo.Marks;
import com.zitlab.palmyra.annotation.MobyraType;

import java.util.List;

@MobyraType("student")
public class Student {
    @SerializedName("id")
    @Expose
    private Number id;
    @SerializedName("studentName")
    @Expose
    private String studentName;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("studentCode")
    @Expose
    private String studentCode;
    @SerializedName("studentClass")
    @Expose
    private String studentClass;
    @SerializedName("lastAttendedOn")
    @Expose
    private String lastAttendedOn;
    @SerializedName("marks")
    @Expose
    private List<Marks> marks;

    public List<Marks> getMarks() {
        return marks;
    }

    public void setMarks(List<Marks> marks) {
        this.marks = marks;
    }

    public Number getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getLastAttendedOn() {
        return lastAttendedOn;
    }

    public void setLastAttendedOn(String lastAttendedOn) {
        this.lastAttendedOn = lastAttendedOn;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", dob='" + dob + '\'' +
                ", studentCode='" + studentCode + '\'' +
                ", studentClass='" + studentClass + '\'' +
                ", lastAttendedOn='" + lastAttendedOn + '\'' +
                '}';
    }

}
