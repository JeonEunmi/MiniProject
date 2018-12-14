package com.scoremanagement.domain;
 
import java.sql.Date;
 
public class StudentHistory {
 
    // Field
    private String student_id;            // 수강생 아이디
    private String open_course_id;        // 개설 과정 아이디
    private Date dropout_date;            // 중도 탈락일
    
    // Constructor
    public StudentHistory() {
        
    }
 
    // 수강생 히스토리 추가시 사용
    public StudentHistory(String student_id, String open_course_id) {
        this.student_id = student_id;
        this.open_course_id = open_course_id;
    }
 
    // 수강생 중도탈락 추가시 사용
    public StudentHistory(String student_id, String open_course_id, Date dropout_date) {
        this.student_id = student_id;
        this.open_course_id = open_course_id;
        this.dropout_date = dropout_date;
    }
 
    // Getter
    public String getStudent_id() {
        return student_id;
    }
 
    public String getOpen_course_id() {
        return open_course_id;
    }
 
    public Date getDropout_date() {
        return dropout_date;
    }
 
    // Setter
    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }
 
    public void setOpen_course_id(String open_course_id) {
        this.open_course_id = open_course_id;
    }
 
    public void setDropout_date(Date dropout_date) {
        this.dropout_date = dropout_date;
    }
    
    
}