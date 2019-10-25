package com.example.coursemanagement;

public class coursePojo {
    private int courseID;
    private String CourseName;
    private String CourseDesc;
    private String CourseCatagories;
    private String CoursePay;

    public coursePojo(int courseID, String courseName, String courseDesc, String courseCatagories, String coursePay) {
        this.courseID = courseID;
        CourseName = courseName;
        CourseDesc = courseDesc;
        CourseCatagories = courseCatagories;
        CoursePay = coursePay;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getCourseDesc() {
        return CourseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        CourseDesc = courseDesc;
    }

    public String getCourseCatagories() {
        return CourseCatagories;
    }

    public void setCourseCatagories(String courseCatagories) {
        CourseCatagories = courseCatagories;
    }

    public String getCoursePay() {
        return CoursePay;
    }

    public void setCoursePay(String coursePay) {
        CoursePay = coursePay;
    }
}
