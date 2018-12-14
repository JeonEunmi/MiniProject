package com.scoremanagement.domain;
 
import java.sql.Date;
 
public class OpenCourse {
    
    private String open_course_id;            // 개설 과정 아이디
    private String class_room_id;            // 강의실 아이디
    private String class_room_name;            // 강의실 이름
    private String course_id;                // 과정 아이디
    private String course_name;                // 과정 이름
    private Date open_course_start_date;    // 개설 과정 시작일
    private Date open_course_end_date;        // 개설 과정 종료일
    private String subject_list;            // 개설 과목명
    private int open_subject_count;            // 개설 과목 등록 갯수
    private String student_id;                // 수강생 아이디
    private String student_name;            // 수강생 이름
    private int student_count;                // 수강생 등록 인원
    private int count_;                        // 삭제 가능 여부
    private String completion_status;        // 수료 여부
    private Date dropout_date;                // 수료or탈락 날짜
 
 
    // Constructor
    public OpenCourse() {
 
    }
 
    // 개설 과정과 학생 함께 검색시 사용
    // 학생 아이디나 이름이 필요
    public OpenCourse(String open_course_id, String course_name, String student_id, String student_name) {
        this.open_course_id = open_course_id;
        this.course_name = course_name;
        this.student_id = student_id;
        this.student_name = student_name;
    }
 
    // 개설 과정 번호 / 과정명 / 개설 과정 기간 / 강의실명 / 삭제 가능 여부
    public OpenCourse(String open_course_id, String course_name, Date open_course_start_date,
            Date open_course_end_date, String class_room_name, int count_) {
        this.open_course_id = open_course_id;
        this.course_name = course_name;
        this.open_course_start_date = open_course_start_date;
        this.open_course_end_date = open_course_end_date;
        this.class_room_name = class_room_name;
        this.count_ = count_;
    }
 
    // 개설 과정 번호 / 과정명 / 개설 과정 기간 / 강의실명 / 개설 과목명
    public OpenCourse(String open_course_id, String course_name, Date open_course_start_date,
            Date open_course_end_date, String class_room_name, String subject_list) {
        this.open_course_id = open_course_id;
        this.course_name = course_name;
        this.open_course_start_date = open_course_start_date;
        this.open_course_end_date = open_course_end_date;
        this.class_room_name = class_room_name;
        this.subject_list = subject_list;
    }
 
    // 개설 과정 추가할 때 사용
    // 개설 과정 삭제할 때 사용
    // 개설 과정 검색할 때 사용
    // 개설 과정 번호 / 과정명 / 개설 과정 기간 / 강의실명
    public OpenCourse(String open_course_id, String course_id, String course_name, Date open_course_start_date,
            Date open_course_end_date, String class_room_id, String class_room_name) {
        this.open_course_id = open_course_id;
        this.course_id = course_id;
        this.course_name = course_name;
        this.open_course_start_date = open_course_start_date;
        this.open_course_end_date = open_course_end_date;
        this.class_room_id = class_room_id;
        this.class_room_name = class_room_name;
    }
    
    // 개설 과정 번호 / 과정명 / 개설 과정 기간 / 강의실명 / 개설 과목 등록 갯수 / 수강생 등록 인원
    public OpenCourse(String open_course_id, String course_name, Date open_course_start_date,
            Date open_course_end_date, String class_room_name, int open_subject_count, int student_count) {
        this.open_course_id = open_course_id;
        this.course_name = course_name;
        this.open_course_start_date = open_course_start_date;
        this.open_course_end_date = open_course_end_date;
        this.class_room_name = class_room_name;
        this.open_subject_count = open_subject_count;
        this.student_count = student_count;
    }
 
    // 개설 과정 번호 / 과정명 / 개설 과정 기간 / 강의실명 / 수료여부 / 날짜
    public OpenCourse(String open_course_id, String course_name, Date open_course_start_date,
            Date open_course_end_date, String class_room_name, String completion_status, Date dropout_date) {
        this.open_course_id = open_course_id;
        this.course_name = course_name;
        this.open_course_start_date = open_course_start_date;
        this.open_course_end_date = open_course_end_date;
        this.class_room_name = class_room_name;
        this.completion_status = completion_status;
        this.dropout_date = dropout_date;
    }
    
    // Getter
    public String getOpen_course_id() {
        return open_course_id;
    }
 
    public String getClass_room_id() {
        return class_room_id;
    }
 
    public String getClass_room_name() {
        return class_room_name;
    }
 
    public String getCourse_id() {
        return course_id;
    }
 
    public String getCourse_name() {
        return course_name;
    }
 
    public Date getOpen_course_start_date() {
        return open_course_start_date;
    }
 
    public Date getOpen_course_end_date() {
        return open_course_end_date;
    }
 
    public String getSubject_list() {
        return subject_list;
    }
 
    public int getOpen_subject_count() {
        return open_subject_count;
    }
 
    public String getStudent_id() {
        return student_id;
    }
 
    public String getStudent_name() {
        return student_name;
    }
 
    public int getStudent_count() {
        return student_count;
    }
 
    public int getCount_() {
        return count_;
    }
 
    public String getCompletion_status() {
        return completion_status;
    }
 
    public Date getDropout_date() {
        return dropout_date;
    }
 
    // Setter
    public void setClass_room_id(String class_room_id) {
        this.class_room_id = class_room_id;
    }
 
    public void setClass_room_name(String class_room_name) {
        this.class_room_name = class_room_name;
    }
 
    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }
 
    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
 
    public void setOpen_course_start_date(Date open_course_start_date) {
        this.open_course_start_date = open_course_start_date;
    }
 
    public void setOpen_course_end_date(Date open_course_end_date) {
        this.open_course_end_date = open_course_end_date;
    }
 
    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }
 
    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }
 
    public void setCompletion_status(String completion_status) {
        this.completion_status = completion_status;
    }
 
    public void setDropout_date(Date dropout_date) {
        this.dropout_date = dropout_date;
    }
    
    // print method
    public String print1() {
        String result = "";
        result = String.format("%s / %s / %s ~ %s / %s", 
                this.getOpen_course_id(), this.getCourse_name(), 
                this.getOpen_course_start_date(), this.getOpen_course_end_date(), this.getClass_room_name());
        return result;
    }
 
    public String print2() {
        String result = "";
        result = String.format("%s / %s / %s ~ %s / %s / %d개 / %d명", 
                this.getOpen_course_id(), this.getCourse_name(),
                this.getOpen_course_start_date(), this.getOpen_course_end_date(), this.getClass_room_name(),
                this.getOpen_subject_count(), this.getStudent_count());
        return result;
    }
    
    public String print3() {
        String result = String.format("%s / %s / %s / %s ~ %s / %s", 
                this.getOpen_course_id(), this.getClass_room_name(), this.getCourse_name(), 
                this.getOpen_course_start_date(), this.getOpen_course_end_date(),
                (this.getCount_() > 0 ? 'X' : 'O'));
        return result;
    }
    
    public String print4() {
        String result = String.format("%s / %s / %s ~ %s / %s / %s",
                this.getOpen_course_id(), this.getCourse_name(),
                this.getOpen_course_start_date(), this.getOpen_course_end_date(),
                this.getClass_room_name(), this.getSubject_list());
        return result;
    }
    
    public String print5() {
        String result = "";
        result = String.format("%s / %s / %s ~ %s / %s / %s", 
                this.getOpen_course_id(), this.getCourse_name(), this.getOpen_course_start_date(), this.getOpen_course_end_date(),
                this.getCompletion_status(), this.getDropout_date());
        return result;
    }
}
 