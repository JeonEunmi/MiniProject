package com.scoremanagement.domain;
 
public class Admin {
    // Field
    private String admin_id;        // 관리자 아이디
    private String admin_pw;        // 관리자 비밀번호
    private String admin_new_pw;    // 관리자 새 비밀번호
 
    // Constructor
    // 기본 생성자
    public Admin() {
 
    }
 
    // 로그인
    public Admin(String admin_id, String admin_pw) {
        this.admin_id = admin_id;
        this.admin_pw = admin_pw;
    }
    
    // 비밀번호 변경
    public Admin(String admin_id, String admin_pw, String admin_new_pw) {
        this.admin_id = admin_id;
        this.admin_pw = admin_pw;
        this.admin_new_pw = admin_new_pw;
    }
 
    // Getter
    public String getAdmin_id() {
        return admin_id;
    }
 
    public String getAdmin_pw() {
        return admin_pw;
    }
    
    public String getAdmin_new_pw() {
        return admin_new_pw;
    }
 
    // Setter
    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }
 
    public void setAdmin_pw(String admin_pw) {
        this.admin_pw = admin_pw;
    }
    
    public void setAdmin_new_pw(String admin_new_pw) {
        this.admin_new_pw = admin_new_pw;
    }
}