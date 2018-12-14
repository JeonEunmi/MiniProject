package com.scoremanagement.domain;
 
import java.sql.Date;
 
public class Instructor {
    
    // Field
    private String instructor_id;        // 강사 아이디
    private String instructor_name;        // 강사 이름
    private String instructor_phone;    // 강사 휴대폰 번호
    private Date instructor_regDate;    // 강사 등록일
    private String instructor_pw;        // 강사 비밀번호
    private String instructor_new_pw;    // 강사 새 비밀번호
    private String instructor_possible;    // 강의 가능 과목 나열
    private int count_;                    // 삭제 가능 여부
    
    // Constructor
    public Instructor() {
 
    }
 
    // 강사 비밀번호 초기화시 사용
    public Instructor(String instructor_id, String instructor_pw) {
        this.instructor_id = instructor_id;
        this.instructor_pw = instructor_pw;
    }
 
    // 강사 로그인시 사용
    // 강사 비밀번호 수정시 사용
    public Instructor(String instructor_name, String instructor_pw, String instructor_new_pw) {
        this.instructor_name = instructor_name;
        this.instructor_pw = instructor_pw;
        this.instructor_new_pw = instructor_new_pw;
    }
 
    // 강사 등록시 사용
    // 강사 삭제시 사용
    // 강사 검색시 사용
    // 강사번호 / 강사이름 / 강사휴대폰번호 / 등록일
    public Instructor(String instructor_id, String instructor_name, String instructor_phone, Date instructor_regDate, String instructor_pw) {
        this.instructor_id = instructor_id;
        this.instructor_name = instructor_name;
        this.instructor_phone = instructor_phone;
        this.instructor_regDate = instructor_regDate;
        this.instructor_pw = instructor_pw;
    }
 
    // 강사번호 / 강사이름 / 강사휴대폰번호 / 등록일 / 강의가능과목
    // 강사번호 / 강사이름 / 강사휴대폰번호 / 등록일 / 삭제가능여부
    public Instructor(String instructor_id, String instructor_name, String instructor_phone, Date instructor_regDate,
            String instructor_possible, int count_) {
        this.instructor_id = instructor_id;
        this.instructor_name = instructor_name;
        this.instructor_phone = instructor_phone;
        this.instructor_regDate = instructor_regDate;
        this.instructor_possible = instructor_possible;
        this.count_ = count_;
    }
 
    // Getter
    public String getInstructor_id() {
        return instructor_id;
    }
 
    public String getInstructor_name() {
        return instructor_name;
    }
 
    public String getInstructor_phone() {
        return instructor_phone;
    }
 
    public Date getInstructor_regDate() {
        return instructor_regDate;
    }
 
    public String getInstructor_pw() {
        return instructor_pw;
    }
    
    public String getInstructor_new_pw() {
        return instructor_new_pw;
    }
    
    public String getInstructor_possible() {
        return instructor_possible;
    }
 
    public int getCount_() {
        return count_;
    }
 
    // Setter
    public void setInstructor_name(String instructor_name) {
        this.instructor_name = instructor_name;
    }
 
    public void setInstructor_phone(String instructor_phone) {
        this.instructor_phone = instructor_phone;
    }
 
    public void setInstructor_regDate(Date instructor_regDate) {
        this.instructor_regDate = instructor_regDate;
    }
 
    public void setInstructor_pw(String instructor_pw) {
        this.instructor_pw = instructor_pw;
    }    
    
    public void setInstructor_new_pw(String instructor_new_pw) {
        this.instructor_new_pw = instructor_new_pw;
    }    
    
    // print method
    public String print() {
        String result = "";
        result = String.format("%s / %s / %s / %s", this.getInstructor_id(), this.getInstructor_name(),
                this.getInstructor_phone(), this.getInstructor_regDate());
        return result;
    }
    
    public String print1() {
        String result = "";
        result = String.format("%s / %s / %s / %s / %s / %s", this.getInstructor_id(), this.getInstructor_name(),
                this.getInstructor_phone(), this.getInstructor_regDate(), this.getInstructor_possible(), 
                (this.getCount_() > 0 ? 'X' : 'O'));
        return result;
    }
    
    public String print2() {
        String result = "";
        result = String.format("%s / %s / %s / %s / %s", this.getInstructor_id(), this.getInstructor_name(),
                this.getInstructor_phone(), this.getInstructor_regDate(), this.getInstructor_possible());
        return result;
    }
}
 
