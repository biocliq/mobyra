
package com.zitlab.mobyra.home.detail.marks.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("exam")
    @Expose
    private String exam;
    @SerializedName("studentCode")
    @Expose
    private String studentCode;
    @SerializedName("english")
    @Expose
    private float english;
    @SerializedName("maths")
    @Expose
    private float maths;
    @SerializedName("science")
    @Expose
    private float science;
    @SerializedName("tamil")
    @Expose
    private float tamil;
    @SerializedName("history")
    @Expose
    private float history;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public float getEnglish() {
        return english;
    }

    public void setEnglish(float english) {
        this.english = english;
    }

    public float getMaths() {
        return maths;
    }

    public void setMaths(float maths) {
        this.maths = maths;
    }

    public float getScience() {
        return science;
    }

    public void setScience(float science) {
        this.science = science;
    }

    public float getTamil() {
        return tamil;
    }

    public void setTamil(float tamil) {
        this.tamil = tamil;
    }

    public float getHistory() {
        return history;
    }

    public void setHistory(float history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", exam='" + exam + '\'' +
                ", studentCode='" + studentCode + '\'' +
                ", english=" + english +
                ", maths=" + maths +
                ", science=" + science +
                ", tamil=" + tamil +
                ", history=" + history +
                '}';
    }
}
