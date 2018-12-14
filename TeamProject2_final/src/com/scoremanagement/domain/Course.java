package com.scoremanagement.domain;
 
public class Course {
    // Field
    private String course_id;        // 과정 아이디
    private String course_name;        // 과정 이름
    private int count_;                // 삭제 가능 여부
    
    // Constructor
    // 기본 생성자
    public Course() {
    
    }
 
    // 과정번호 / 과정이름
    public Course(String course_id, String course_name) {
        this.course_id = course_id;
        this.course_name = course_name;
    }
    
    // 과정번호 / 과정이름 / 삭제가능여부
    public Course(String course_id, String course_name, int count_) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.count_ = count_;
    }
 
    // Getter
    public String getCourse_id() {
        return course_id;
    }
 
    public String getCourse_name() {
        return course_name;
    }
 
    public int getCount_() {
        return count_;
    }
 
    // Setter
    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
 
    // print method
    // Return 과정번호 / 과정이름
    public String print1() {
        String result = "";
        result = String.format("%s / %s", 
                this.getCourse_id(), this.getCourse_name());
        return result;
    }
 
    // Return 과정번호 / 과정이름 / 삭제가능여부
    public String print2() {
        String result = "";
        result = String.format("%s / %s / %s", 
                this.getCourse_id(), this.getCourse_name(), (getCount_() > 0 ? 'X' : 'O'));
        return result;
    }
}
 
