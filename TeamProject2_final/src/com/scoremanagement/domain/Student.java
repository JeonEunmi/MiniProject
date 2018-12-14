package com.scoremanagement.domain;
 
import java.sql.Date;
 
public class Student {
 
    // Field
    private String student_id;            // 수강생 아이디
    private String student_name;        // 수강생 이름
    private String student_phone;        // 수강생 휴대폰번호
    private Date student_regDate;        // 수강생 등록일
    private String student_pw;            // 수강생 비밀번호
    private String student_new_pw;        // 수강생 새 비밀번호
    private String completion_status;    // 수료 여부(수료 예정, 수료 완료, 중도 탈락)
    private Date completeion_date;        // 날짜(중도탈락, 수료)
    private int count_;                    // 삭제 가능 여부
    
    // Constructor
    public Student() {
        
    }
 
    // 수강생 비밀번호 초기화시 사용
    public Student(String student_id, String student_pw) {
        this.student_id = student_id;
        this.student_pw = student_pw;
    }
 
    // 수강생 로그인시 사용
    // 비밀번호 수정시 사용
    public Student(String student_name, String student_pw, String student_new_pw) {
        this.student_name = student_name;
        this.student_pw = student_pw;
        this.student_new_pw = student_new_pw;
    }
 
    // 수강생 추가시 사용
    // 수강생 삭제시 사용
    // 수강생 검색시 사용
    // 수강생번호 / 수강생이름 / 수강생전화번호 / 수강생등록일
    public Student(String student_id, String student_name, String student_phone, Date student_regDate,
            String student_pw) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.student_phone = student_phone;
        this.student_regDate = student_regDate;
        this.student_pw = student_pw;
    }
 
    // 수강생번호 / 수강생이름 / 수강생전화번호 / 수강생등록일 / 수강횟수
    public Student(String student_id, String student_name, String student_phone, Date student_regDate, int count_) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.student_phone = student_phone;
        this.student_regDate = student_regDate;
        this.count_ = count_;
    }
 
    // 수강생번호 / 수강생이름 / 수강생전화번호 / 수강생등록일 / 수료여부 / 날짜
    public Student(String student_id, String student_name, String student_phone, Date student_regDate,
            String completion_status, Date completeion_date) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.student_phone = student_phone;
        this.student_regDate = student_regDate;
        this.completion_status = completion_status;
        this.completeion_date = completeion_date;
    }
 
    // Getter
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
 
    public String getStudent_pw() {
        return student_pw;
    }
 
    public String getStudent_new_pw() {
        return student_new_pw;
    }
    
    public String getCompletion_status() {
        return completion_status;
    }
 
    public Date getCompleteion_date() {
        return completeion_date;
    }
 
    public int getCount_() {
        return count_;
    }
 
    // Setter
    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }
 
    public void setStudent_phone(String student_phone) {
        this.student_phone = student_phone;
    }
 
    public void setStudent_regDate(Date student_regDate) {
        this.student_regDate = student_regDate;
    }
 
    public void setStudent_pw(String student_pw) {
        this.student_pw = student_pw;
    }
 
    public void setStudent_new_pw(String student_new_pw) {
        this.student_new_pw = student_new_pw;
    }
    
    public void setCompletion_status(String completion_status) {
        this.completion_status = completion_status;
    }
 
    public void setCompleteion_date(Date completeion_date) {
        this.completeion_date = completeion_date;
    }
    
    // print method
    public String print() {
        String result = "";
        result = String.format("%s / %s / %s / %s", this.getStudent_id(), this.getStudent_name(),
                this.getStudent_phone(), this.getStudent_regDate());
        return result;
    }
 
    public String print1() {
        String result = "";
        result = String.format("%s / %s / %s / %s / %d회", this.getStudent_id(), this.getStudent_name(),
                this.getStudent_phone(), this.getStudent_regDate(), this.getCount_());
        return result;
    }
    
    public String print2() {
        String result = "";
        result = String.format("%s / %s / %s / %s / %s / %s", 
                getStudent_id(), getStudent_name(), getStudent_phone(), getStudent_regDate(),
                getCompletion_status(), getCompleteion_date());
        return result;
    }
}
 
