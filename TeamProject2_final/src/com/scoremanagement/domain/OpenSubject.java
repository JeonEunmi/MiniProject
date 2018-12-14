package com.scoremanagement.domain;
 
import java.sql.Date;
 
public class OpenSubject {
    
    // Field
    private String open_subject_id;            // 개설 과목 아이디
    private String subject_id;                // 과목 아이디
    private String subject_name;            // 과목 이름
    private String subjectbook_id;            // 교재 아이디
    private String subjectbook_name;        // 교재 이름
    private String instructor_id;            // 강사 아이디
    private String instructor_name;            // 강사 이름
    private String student_id;                // 수강생 아이디
    private String open_course_id;            // 개설 과정 아이디
    private String course_name;                // 과정 이름
    private Date subject_start_date;        // 과목 시작일
    private Date subject_end_date;            // 과목 종료일
    private Date open_course_start_date;    // 개설 과정 시작일
    private Date open_course_end_date;        // 개설 과정 종료일
    private String class_room_name;            // 강의실 이름
    private int student_count;                // 수강생 등록 인원
    private String instructor_status;        // 강사 진행 여부(강의진행전, 강의진행중, 강의종료)
    private int count_;                        // 삭제 가능 여부
    
    // Constructor
    public OpenSubject() {
 
    }
    
    // 검색시 사용
    // 개설과목아이디랑 학생아이디
    // 개설과정아이디랑 학생아이디
    // 개설과목아이디
    public OpenSubject(String open_subject_id, String subject_name, String open_course_id, String course_name, 
            String student_id, String instructor_id) {
        this.open_subject_id = open_subject_id;
        this.subject_name = subject_name;
        this.open_course_id = open_course_id;
        this.course_name = course_name;
        this.student_id = student_id;
        this.instructor_id = instructor_id;
    }
    
    // 개설과목번호 / 과목명 / 과목기간 / 비고
    public OpenSubject(String open_subject_id, String subject_name, Date subject_start_date, Date subject_end_date,
            String instructor_status) {
        this.open_subject_id = open_subject_id;
        this.subject_name = subject_name;
        this.subject_start_date = subject_start_date;
        this.subject_end_date = subject_end_date;
        this.instructor_status = instructor_status;
    }
    
    // 개설 과목 번호 / 과목명 / 개설 과목 기간 / 교재명 / 강사명
    // 1. 개설 과목 번호  2. 과목명  3. 개설 과정 번호  4. 과정명
    public OpenSubject(String open_subject_id, String subject_id, String subject_name, Date subject_start_date, Date subject_end_date,
            String subjectbook_id, String subjectbook_name, String instructor_id, String instructor_name) {
        this.open_subject_id = open_subject_id;
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.subject_start_date = subject_start_date;
        this.subject_end_date = subject_end_date;
        this.subjectbook_id = subjectbook_id;
        this.subjectbook_name = subjectbook_name;
        this.instructor_id = instructor_id;
        this.instructor_name = instructor_name;
    }
 
    // 개설 과목 추가할 때 사용
    // 개설 과목 삭제할 때 사용
    public OpenSubject(String open_subject_id, String open_course_id, String subject_id, 
            Date subject_start_date, Date subject_end_date, String subjectbook_id, String instructor_id) {
        this.open_subject_id = open_subject_id;
        this.open_course_id = open_course_id;
        this.subject_id = subject_id;
        this.subject_start_date = subject_start_date;
        this.subject_end_date = subject_end_date;
        this.subjectbook_id = subjectbook_id;
        this.instructor_id = instructor_id;
    }
 
    // 개설 과목 번호 / 개설과목명 / 개설 과목 기간 / 교재명 / 강사명 / 과정명 / 개설 과정 기간 / 강의실명
    public OpenSubject(String open_subject_id, String subject_name, Date subject_start_date, Date subject_end_date,
            String subjectbook_name, String instructor_name, String course_name, 
            Date open_course_start_date, Date open_course_end_date, String class_room_name) {
        this.open_subject_id = open_subject_id;
        this.subject_name = subject_name;
        this.subject_start_date = subject_start_date;
        this.subject_end_date = subject_end_date;
        this.subjectbook_name = subjectbook_name;
        this.instructor_name = instructor_name;
        this.course_name = course_name;
        this.open_course_start_date = open_course_start_date;
        this.open_course_end_date = open_course_end_date;
        this.class_room_name = class_room_name;
    }
    
    // 개설과목번호 / 개설과목명 / 개설과목기간 / 교재명 / 강사명 / 개설과정명 / 개설과정기간 / 강의실명 / 삭제가능여부
    public OpenSubject(String open_subject_id, String subject_name, Date subject_start_date, Date subject_end_date, 
            String subjectbook_name, String instructor_name, String course_name, 
            Date open_course_start_date, Date open_course_end_date, 
            String class_room_name, int count_) {
        this.open_subject_id = open_subject_id;
        this.subject_name = subject_name;
        this.subject_start_date = subject_start_date;
        this.subject_end_date = subject_end_date;
        this.subjectbook_name = subjectbook_name;
        this.instructor_name = instructor_name;
        this.course_name = course_name;
        this.open_course_start_date = open_course_start_date;
        this.open_course_end_date = open_course_end_date;
        this.class_room_name = class_room_name;
        this.count_ = count_;
    }
 
    // 개설과목번호 / 개설과정명 / 개설과정기간 / 강의실명 / 개설과목명 / 개설과목기간 / 강사명 / 교재명 / 수강생등록인원
    public OpenSubject(String open_subject_id, String course_name, Date open_course_start_date, Date open_course_end_date,
            String class_room_name, String subject_name, Date subject_start_date, Date subject_end_date, 
            String instructor_name, String subjectbook_name, int student_count) {
        this.open_subject_id = open_subject_id;
        this.course_name = course_name;
        this.open_course_start_date = open_course_start_date;
        this.open_course_end_date = open_course_end_date;
        this.class_room_name = class_room_name;
        this.subject_name = subject_name;
        this.subject_start_date = subject_start_date;
        this.subject_end_date = subject_end_date;
        this.instructor_name = instructor_name;
        this.subjectbook_name = subjectbook_name;
        this.student_count = student_count;
    }
 
    // 개설과목명 / 개설과목기간 / 개설과정명 / 개설과정기간 / 강의실명 / 강의진행여부
    public OpenSubject(String open_subject_id, String subject_name, Date subject_start_date, Date subject_end_date, 
            String course_name, Date open_course_start_date, Date open_course_end_date, 
            String class_room_name, String instructor_status) {
        this.open_subject_id = open_subject_id;
        this.subject_name = subject_name;
        this.subject_start_date = subject_start_date;
        this.subject_end_date = subject_end_date;
        this.course_name = course_name;
        this.open_course_start_date = open_course_start_date;
        this.open_course_end_date = open_course_end_date;
        this.class_room_name = class_room_name;
        this.instructor_status = instructor_status;
    }
 
    // Getter
    public String getOpen_subject_id() {
        return open_subject_id;
    }
 
    public String getSubject_id() {
        return subject_id;
    }
 
    public String getSubject_name() {
        return subject_name;
    }
 
    public String getSubjectbook_id() {
        return subjectbook_id;
    }
 
    public String getSubjectbook_name() {
        return subjectbook_name;
    }
 
    public String getInstructor_id() {
        return instructor_id;
    }
 
    public String getInstructor_name() {
        return instructor_name;
    }
 
    public String getStudent_id() {
        return student_id;
    }
 
    public String getOpen_course_id() {
        return open_course_id;
    }
 
    public String getCourse_name() {
        return course_name;
    }
 
    public Date getSubject_start_date() {
        return subject_start_date;
    }
 
    public Date getSubject_end_date() {
        return subject_end_date;
    }
 
    public Date getOpen_course_start_date() {
        return open_course_start_date;
    }
 
    public Date getOpen_course_end_date() {
        return open_course_end_date;
    }
 
    public String getClass_room_name() {
        return class_room_name;
    }
 
    public int getStudent_count() {
        return student_count;
    }
 
    public String getInstructor_status() {
        return instructor_status;
    }
 
    public int getCount_() {
        return count_;
    }
 
    // Setter
    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }
 
    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }
 
    public void setSubjectbook_id(String subjectbook_id) {
        this.subjectbook_id = subjectbook_id;
    }
 
    public void setSubjectbook_name(String subjectbook_name) {
        this.subjectbook_name = subjectbook_name;
    }
 
    public void setInstructor_id(String instructor_id) {
        this.instructor_id = instructor_id;
    }
 
    public void setInstructor_name(String instructor_name) {
        this.instructor_name = instructor_name;
    }
 
    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }
 
    public void setOpen_course_id(String open_course_id) {
        this.open_course_id = open_course_id;
    }
 
    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
 
    public void setSubject_start_date(Date subject_start_date) {
        this.subject_start_date = subject_start_date;
    }
 
    public void setSubject_end_date(Date subject_end_date) {
        this.subject_end_date = subject_end_date;
    }
 
    public void setOpen_course_start_date(Date open_course_start_date) {
        this.open_course_start_date = open_course_start_date;
    }
 
    public void setOpen_course_end_date(Date open_course_end_date) {
        this.open_course_end_date = open_course_end_date;
    }
 
    public void setClass_room_name(String class_room_name) {
        this.class_room_name = class_room_name;
    }
 
    public void setStudent_count(int student_count) {
        this.student_count = student_count;
    }
 
    public void setInstructor_status(String instructor_status) {
        this.instructor_status = instructor_status;
    }
    
    // print method
    public String print1() {
          String result = "";
          result = String.format("%s / %s / %s ~ %s / %s / %s", 
                this.getOpen_subject_id(), this.getSubject_name(), this.getSubject_start_date(), this.getSubject_end_date()
                , this.getSubjectbook_name(), this.getInstructor_name());
          return result;
    }
    
    public String print2() {
          String result = "";
          result = String.format("%s / %s / %s ~ %s / %s / %s / %s / %s ~ %s / %s", 
                this.getOpen_subject_id(), this.getSubject_name(), this.getSubject_start_date(), this.getSubject_end_date()
                , this.getSubjectbook_name(), this.getInstructor_name(), this.getCourse_name(), 
                this.getOpen_course_start_date(), this.getOpen_course_end_date(), this.getClass_room_name());
          return result;
    }
    
    public String print3() {
        String result = "";
        result = String.format("%s / %s / %s ~ %s / %s / %s / %s / %s ~ %s / %s / %s", 
                this.getOpen_subject_id(), this.getSubject_name(), this.getSubject_start_date(), this.getSubject_end_date(),
                this.getSubjectbook_name(), this.getInstructor_name(), this.getCourse_name(),
                this.getOpen_course_start_date(), this.getOpen_course_end_date(), this.getClass_room_name(),
                (this.getCount_() > 0 ? 'X' : 'O'));
        return result;
    }
    
    public String print4() {
        String result = "";
        result = String.format("%s / %s / %s ~ %s / %s", 
                this.getOpen_subject_id(), this.getSubject_name(), this.getSubject_start_date(), this.getSubject_end_date(),
                this.getInstructor_status());
        return result;
    }
    
    public String print5() {
        String result = "";
        result = String.format("%s / %s / %s ~ %s / %s / %s / %s ~ %s / %s / %d", 
                this.getOpen_subject_id(), this.getCourse_name(), 
                this.getOpen_course_start_date(), this.getOpen_course_end_date(), this.getClass_room_name(),
                this.getSubject_name(), this.getSubject_start_date(), this.getSubject_end_date(),
                this.getStudent_count());
        return result;
    }
    
    public String print6() {
        String result = "";
        result = String.format("%s / %s / %s ~ %s / %s / %s ~ %s / %s / %s", 
                this.getOpen_subject_id(), this.getSubject_name(), 
                this.getSubject_start_date(), this.getSubject_end_date(), 
                this.getCourse_name(), this.getOpen_course_start_date(), this.getOpen_course_end_date(),
                this.getClass_room_name(), this.getInstructor_status());
        return result;
    }
}
 