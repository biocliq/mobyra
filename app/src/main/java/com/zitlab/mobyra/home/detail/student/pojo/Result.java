
package com.zitlab.mobyra.home.detail.student.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zitlab.mobyra.home.detail.marks.pojo.Marks;
import com.zitlab.mobyra.library.annotation.MobyraType;

import java.util.List;

@MobyraType("student")
public class Result {

    @SerializedName("id")
    @Expose
    private int id;
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
    private List<com.zitlab.mobyra.home.detail.marks.pojo.Result> marks;

    public int getId() {
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

    public List<com.zitlab.mobyra.home.detail.marks.pojo.Result> getMarks() {
        return marks;
    }

    public void setMarks(List<com.zitlab.mobyra.home.detail.marks.pojo.Result> marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", dob='" + dob + '\'' +
                ", studentCode='" + studentCode + '\'' +
                ", studentClass='" + studentClass + '\'' +
                ", lastAttendedOn='" + lastAttendedOn + '\'' +
                '}';
    }
}
