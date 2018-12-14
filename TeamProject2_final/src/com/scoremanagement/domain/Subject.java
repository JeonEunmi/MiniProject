package com.scoremanagement.domain;
 
public class Subject {
    // Field
    private String subject_id;            // 과목 아이디
    private String subject_name;        // 과목 이름
    private int count_;                    // 삭제 가능 여부
    
    // Constructor
    // 기본 생성자
    public Subject() {
    
    }
 
    // 과목번호 / 과목이름
    public Subject(String subject_id, String subject_name) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
    }
    
    // 과목번호 / 과목이름 / 삭제가능여부
    public Subject(String subject_id, String subject_name, int count_) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.count_ = count_;
    }
 
    // Getter
    public String getSubject_id() {
        return subject_id;
    }
 
    public String getSubject_name() {
        return subject_name;
    }
 
    public int getCount_() {
        return count_;
    }
 
    // Setter
    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }
 
    // print method
    // Return 과목번호 / 과목이름
    public String print1() {
        String result = "";
        result = String.format("%s / %s", 
                this.getSubject_id(), this.getSubject_name());
        return result;
    }
 
    // Return 과목번호 / 과목이름 / 삭제가능여부
    public String print2() {
        String result = "";
        result = String.format("%s / %s / %s", 
                this.getSubject_id(), this.getSubject_name(), (getCount_() > 0 ? 'X' : 'O'));
        return result;
    }
}
 
