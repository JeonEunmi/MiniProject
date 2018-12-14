package com.scoremanagement.domain;
 
import java.sql.Date;
 
public class Exam {
    
    // Field
    private String exam_id;                // 시험 아이디
    private String subject_project_id;    // 과목별 배점 정보 아이디
    private String student_score_id;    // 수강생 성적 정보 아이디
    private String open_subject_id;        // 개설 과목 아이디
    private String subject_name;        // 과목 이름
    private String subjectbook_name;    // 교재 이름
    private Date subject_start_date;    // 과목 시작일
    private Date subject_end_date;        // 과목 종료일
    private String instructor_id;        // 강사 아이디
    private String instructor_name;        // 강사 이름
    private int attendance_point;        // 출결 배점
    private int write_point;            // 필기 배점
    private int skill_point;            // 실기 배점
    private Date exam_date;                // 시험 날짜
    private String exam_file;            // 시험 문제 파일
    private String student_id;            // 수강생 아이디
    private String student_name;        // 수강생 이름
    private String student_phone;        // 수강생 휴대폰번호
    private Date student_regDate;        // 수강생 등록일
    private int attendance_score;        // 출결 점수
    private int write_score;            // 필기 점수
    private int skill_score;            // 실기 점수
    private int total_score;            // 총점
    private int class_count;            // 수강 횟수
    private String completion_status;    // 수료 여부
    private Date completion_date;        // 날짜(수료 or 중도탈락)
    private String score_status;        // 성적 입력 여부(관리자 20번 참고)
 
 
    // Constructor
    public Exam() {
        
    }
    
    // 검색 시 사용
    public Exam(String exam_id, String open_subject_id, String student_id, String instructor_id) {
        this.exam_id = exam_id;
        this.open_subject_id = open_subject_id;
        this.student_id = student_id;
        this.instructor_id = instructor_id;
    }
    
    // 배점 등록시 사용
    public Exam(String exam_id, String open_subject_id, int attendance_point, int write_point, int skill_point,
            Date exam_date, String exam_file) {
        this.exam_id = exam_id;
        this.open_subject_id = open_subject_id;
        this.attendance_point = attendance_point;
        this.write_point = write_point;
        this.skill_point = skill_point;
        this.exam_date = exam_date;
        this.exam_file = exam_file;
    }
 
    // 성적 등록시 사용
    public Exam(String exam_id, String student_id, int attendance_score, int write_score, int skill_score) {
        this.exam_id = exam_id;
        this.student_id = student_id;
        this.attendance_score = attendance_score;
        this.write_score = write_score;
        this.skill_score = skill_score;
    }
 
    // 시험번호 / 출결배점 / 필기배점 / 실기배점 / 시험날짜 / 시험문제 / 성적등록인원수 / 성적등록여부
    public Exam(String exam_id, int attendance_point, int write_point, int skill_point, Date exam_date,
            String exam_file, int class_count, String score_status) {
        this.exam_id = exam_id;
        this.attendance_point = attendance_point;
        this.write_point = write_point;
        this.skill_point = skill_point;
        this.exam_date = exam_date;
        this.exam_file = exam_file;
        this.class_count = class_count;
        this.score_status = score_status;
    }
 
    // 수강생번호 / 수강생이름 / 수강생휴대폰번호 / 등록일 / 수료여부 / 날짜 / 출결점수 / 필기점수 / 실기점수 / 총점
    public Exam(String student_id, String student_name, String student_phone, Date student_regDate,
            String completion_status, Date completion_date, 
            int attendance_score, int write_score, int skill_score, int total_score) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.student_phone = student_phone;
        this.student_regDate = student_regDate;
        this.attendance_score = attendance_score;
        this.write_score = write_score;
        this.skill_score = skill_score;
        this.total_score = total_score;
        this.completion_status = completion_status;
        this.completion_date = completion_date;
    }
 
    // 시험번호 / 출결점수 / 출결배점 / 필기점수 / 필기배점 / 실기점수 / 실기배점 / 시험날짜 / 시험파일
    public Exam(String exam_id, int attendance_point, int write_point, int skill_point,
            int attendance_score, int write_score, int skill_score, Date exam_date, String exam_file) {
        this.exam_id = exam_id;
        this.attendance_point = attendance_point;
        this.write_point = write_point;
        this.skill_point = skill_point;
        this.attendance_score = attendance_score;
        this.write_score = write_score;
        this.skill_score = skill_score;
        this.exam_date = exam_date;
        this.exam_file = exam_file;
    }
    
    // 시험번호 / 과목명 / 과목개설기간 / 강사명 / 출결점수 / 출결배점 / 필기점수 / 필기배점 / 실기점수 / 실기배점 / 시험날짜 / 시험파일
    public Exam(String exam_id, String subject_name, Date subject_start_date, Date subject_end_date, 
            String instructor_name, int attendance_point, int write_point, int skill_point,
            int attendance_score, int write_score, int skill_score, Date exam_date, String exam_file) {
        this.exam_id = exam_id;
        this.subject_name = subject_name;
        this.subject_start_date = subject_start_date;
        this.subject_end_date = subject_end_date;
        this.instructor_name = instructor_name;
        this.attendance_point = attendance_point;
        this.write_point = write_point;
        this.skill_point = skill_point;
        this.attendance_score = attendance_score;
        this.write_score = write_score;
        this.skill_score = skill_score;
        this.exam_date = exam_date;
        this.exam_file = exam_file;
    }
 
    // Getter
    public String getExam_id() {
        return exam_id;
    }
 
    public String getSubject_project_id() {
        return subject_project_id;
    }
 
    public String getStudent_score_id() {
        return student_score_id;
    }
 
    public String getOpen_subject_id() {
        return open_subject_id;
    }
 
    public String getSubject_name() {
        return subject_name;
    }
 
    public String getSubjectbook_name() {
        return subjectbook_name;
    }
 
    public Date getSubject_start_date() {
        return subject_start_date;
    }
 
    public Date getSubject_end_date() {
        return subject_end_date;
    }
 
    public String getInstructor_id() {
        return instructor_id;
    }
    
    public String getInstructor_name() {
        return instructor_name;
    }
 
    public int getAttendance_point() {
        return attendance_point;
    }
 
    public int getWrite_point() {
        return write_point;
    }
 
    public int getSkill_point() {
        return skill_point;
    }
 
    public Date getExam_date() {
        return exam_date;
    }
 
    public String getExam_file() {
        return exam_file;
    }
 
    public String getStudent_id() {
        return student_id;
    }
 
    public String getStudent_name() {
        return student_name;
    }
 
    public String getStudent_phone() {
        return student_phone;
    }
 
    public Date getStudent_regDate() {
        return student_regDate;
    }
 
    public int getAttendance_score() {
        return attendance_score;
    }
 
    public int getWrite_score() {
        return write_score;
    }
 
    public int getSkill_score() {
        return skill_score;
    }
 
    public int getTotal_score() {
        return total_score;
    }
 
    public int getClass_count() {
        return class_count;
    }
 
    public String getCompletion_status() {
        return completion_status;
    }
 
    public Date getCompletion_date() {
        return completion_date;
    }
 
    public String getScore_status() {
        return score_status;
    }
 
    // Setter
    public void setOpen_subject_id(String open_subject_id) {
        this.open_subject_id = open_subject_id;
    }
 
    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }
 
    public void setSubjectbook_name(String subjectbook_name) {
        this.subjectbook_name = subjectbook_name;
    }
 
    public void setSubject_start_date(Date subject_start_date) {
        this.subject_start_date = subject_start_date;
    }
 
    public void setSubject_end_date(Date subject_end_Date) {
        this.subject_end_date = subject_end_Date;
    }
 
    public void getInstructor_id(String instructor_id) {
        this.instructor_id = instructor_id;
    }
    
    public void setInstructor_name(String instructor_name) {
        this.instructor_name = instructor_name;
    }
 
    public void setAttendance_point(int attendance_point) {
        this.attendance_point = attendance_point;
    }
 
    public void setWrite_point(int write_point) {
        this.write_point = write_point;
    }
 
    public void setSkill_point(int skill_point) {
        this.skill_point = skill_point;
    }
 
    public void setExam_date(Date exam_date) {
        this.exam_date = exam_date;
    }
 
    public void setExam_file(String exam_file) {
        this.exam_file = exam_file;
    }
 
    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }
 
    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }
 
    public void setStudent_phone(String student_phone) {
        this.student_phone = student_phone;
    }
 
    public void setStudent_regDate(Date student_regDate) {
        this.student_regDate = student_regDate;
    }
 
    public void setAttendance_score(int attendance_score) {
        this.attendance_score = attendance_score;
    }
 
    public void setWrite_score(int write_score) {
        this.write_score = write_score;
    }
 
    public void setSkill_score(int skill_score) {
        this.skill_score = skill_score;
    }
 
    public void setTotal_score(int total_score) {
        this.total_score = total_score;
    }
 
    public void setClass_count(int class_count) {
        this.class_count = class_count;
    }
 
    public void setCompletion_status(String completion_status) {
        this.completion_status = completion_status;
    }
 
    public void setCompletion_date(Date completion_date) {
        this.completion_date = completion_date;
    }
 
    public void setScore_status(String score_status) {
        this.score_status = score_status;
    }
    
    // print method
    public String print1() {
          String result = "";
          result = String.format("%s / %d / %d / %d / %s / %s", 
                this.getExam_id(), this.getAttendance_point(), this.getWrite_point(), this.getSkill_point(), 
                this.getExam_date(), this.getExam_file());
          return result;
    }
    
    public String print2() {
          String result = "";
          result = String.format("%s / %d / %d / %d / %d / %d / %d / %s / %s", 
                this.getExam_id(), this.getAttendance_point(), this.getAttendance_score(), 
                this.getWrite_point(), this.getWrite_score(), this.getSkill_point(), this.getSkill_score(),
                this.getExam_date(), this.getExam_file());
          return result;
    }
    
    public String print3() {
        String result = "";
        result = String.format("%s / %d / %d / %d / %s / %s / %d명 / %s", 
                this.getExam_id(), this.getAttendance_point(), this.getWrite_point(), this.getSkill_point(), 
                this.getExam_date(), this.getExam_file(), this.getClass_count(), this.getScore_status());
        return result;
    }
    
 
    public String print4() {
        String result = "";
        result = String.format("%s / %s / %s / %s / %s / %s / %d / %d / %d / %d",
                this.getStudent_id(), this.getStudent_name(), this.getStudent_phone(), this.getStudent_regDate(),
                this.getCompletion_status(), this.getCompletion_date(),
                this.getAttendance_score(), this.getWrite_score(), this.getSkill_score(), this.getTotal_score());
        return result;
    }
    
    public String print5() {
        String result = "";
        result = String.format("%s / %s / %s ~ %s / %s / %d(%d) / %d(%d) / %d(%d) / %s / %s",
                this.getExam_id(), this.getSubject_name(), 
                this.getSubject_start_date(), this.getSubject_end_date(), this.getInstructor_name(), 
                this.getAttendance_score(), this.getAttendance_point(),
                this.getWrite_score(), this.getWrite_point(), 
                this.getSkill_score(), this.getSkill_point(),
                this.getExam_date(), this.getExam_file());
        return result;
    }
}
 
