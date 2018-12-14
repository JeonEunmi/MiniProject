package com.scoremanagement.domain;
 
public class InstructorPossible {
    
    // Field
    private String instructor_id;        // 강사 아이디
    private String subject_id;            // 과목 아이디
    private String subject_name;        // 과목 이름
 
    // Constructor
    public InstructorPossible() {
        
    }
 
    public InstructorPossible(String instructor_id, String subject_id) {
        this.instructor_id = instructor_id;
        this.subject_id = subject_id;
    }
    
    public InstructorPossible(String instructor_id, String subject_id, String subject_name) {
        this.instructor_id = instructor_id;
        this.subject_id = subject_id;
        this.subject_name = subject_name;
    }
 
    // Getter
    public String getInstructor_id() {
        return instructor_id;
    }
 
    public String getSubject_id() {
        return subject_id;
    }
 
    public String getSubject_name() {
        return subject_name;
    }
 
    // Setter
    public void setInstructor_id(String instructor_id) {
        this.instructor_id = instructor_id;
    }
 
    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }
 
    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }
    
    // print method
    public String print() {
        String result = "";
        result = String.format("%s - %s", this.getSubject_id(), this.getSubject_name());
        return result;
    }
}