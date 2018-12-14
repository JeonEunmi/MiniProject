package com.scoremanagement.service;
 
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
 
import com.scoremanagement.domain.Exam;
import com.scoremanagement.domain.Instructor;
import com.scoremanagement.domain.InstructorPossible;
import com.scoremanagement.domain.OpenSubject;
import com.scoremanagement.domain.Student;
import com.scoremanagement.persistance.ExamDAO;
import com.scoremanagement.persistance.InstructorPossibleDAO;
import com.scoremanagement.persistance.InstructorDAO;
import com.scoremanagement.persistance.OpenSubjectDAO;
import com.scoremanagement.persistance.StudentDAO;
 
public class ServiceInstructor {
 
    private InstructorDAO iDAO = new InstructorDAO();
    private OpenSubjectDAO osDAO = new OpenSubjectDAO();
    private StudentDAO stDAO = new StudentDAO();
    private ExamDAO eDAO = new ExamDAO();
    private InstructorPossibleDAO ipDAO = new InstructorPossibleDAO();
    
    private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
    
    private String instructor_id = null;
    private String instructor_name = null;
    
    // 성적 처리 시스템 v6.0 > 2. 강사 로그인
    public void login(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.println("성적 처리 시스템 v6.0 > 2. 강사 로그인");
        System.out.print("이름 > ");
        this.instructor_name = sc.nextLine();
        
        System.out.print("비밀번호 > ");
        String instructor_pw = sc.nextLine();
        
        Instructor i = new Instructor(this.instructor_name, instructor_pw, null);
        this.instructor_id = this.iDAO.login(i);
        
        if(this.instructor_id != null) {
            System.out.printf("강사 '%s'님이 로그인되었습니다.\n", this.instructor_name);
            this.main(sc);
        } else {
             System.out.println("아이디 또는 패스워드가 틀렸습니다.");
        }
    }
    
    // 성적 처리 시스템 v6.0 (강사 : OOO) 
    private void main(Scanner sc) {
        
        boolean run = true;
        while(run) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("성적 처리 시스템 v6.0 (강사 : %s)\n", this.instructor_name);
            System.out.println("1. 강의 스케줄 조회  2. 배점 관리  3. 성적 관리  4. 개인 정보");
            System.out.print("선택 > ");
            int selectNum = sc.nextInt();
            sc.nextLine();
            
            switch(selectNum) {
            
            case 1:
                this.m1(sc);
                break;
                
            case 2:
                this.m2(sc);
                break;
 
            case 3:
                this.m3(sc);
                break;
 
            case 4:
                this.m4(sc);
                break;
 
            case 0:
                run = false;
                System.out.printf("강사 '%s'님이 로그아웃 되었습니다.\n", this.instructor_name);
                break;
                
            default:
                System.out.println("없는 번호입니다.");
                break;
            }
        }
    }
    
    // 성적 처리 시스템 v6.0 (강사 : OOO) > 1. 강의 스케줄 조회
    private void m1(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (강사 : %s) > 1. 강의 스케줄 조회\n", this.instructor_name);
        int size = this.schedule_print();
        
        if(size > 0) {
            
            System.out.print("개설 과목 번호 > ");
            String open_subject_id = sc.nextLine();
            
            int size2 = this.open_subject_print(open_subject_id);
            
            if(size2 > 0) {
                List<Student> list3 = this.stDAO.list2("open_subject_id", open_subject_id, this.instructor_id);
                if(list3.size() > 0) {
                    System.out.println("** 수강생 정보 **");
                    System.out.println("수강생번호 / 이름 / 전화번호 / 등록일 / 수료여부 / 날짜");
                    for(Student s : list3) {
                        System.out.println(s.print2());
                    }
                    System.out.println();
                } else {
                    System.out.println("검색 결과가 없습니다.");
                }
            } else {
                System.out.println("검색 결과가 없습니다.");
            }
        } else {
            System.out.println("검색 결과가 없습니다.");
        }
    }
    
    // 성적 처리 시스템 v6.0 (강사 : OOO) > 2. 배점 관리
    private void m2(Scanner sc) {
        
        boolean run = true;
        while(run) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("성적 처리 시스템 v6.0 (강사 : %s) > 2. 배점 관리\n", this.instructor_name);
            System.out.println("1. 배점 입력  2. 배점 출력  3. 배점 삭제");
            System.out.print("선택 > ");
            int selectNum = sc.nextInt();
            sc.nextLine();
            
            switch(selectNum) {
            case 1:
                this.m2_s1(sc);
                break;
                
            case 2:
                this.m2_s2(sc);
                break;
 
            case 3:
                this.m2_s3(sc);
                break;
 
            case 0:
                run = false;
                break;
                
            default:
                System.out.println("없는 번호입니다.");
                break;
            }
        }
    }
    
    // 성적 처리 시스템 v6.0 (강사 : OOO) > 2. 배점 관리 > 1. 배점 입력
    private void m2_s1(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (강사 : %s) > 2. 배점 관리 > 1. 배점 입력\n", this.instructor_name);
 
        int size1 = this.schedule_print();
        
        if(size1 > 0) {
            System.out.print("개설 과목 번호 > ");
            String open_subject_id = sc.nextLine();
 
            int size2 = this.open_subject_print(open_subject_id);
            
            if(size2 > 0) {
                int size3 = this.exam_print(open_subject_id);
                
                int selectNum = 0;
                
                if (size3 > 0) {
                    System.out.println("이미 시험 정보가 등록되어 있습니다.");
                    System.out.print("추가로 진행하시겠습니까? (0/1) > ");
                    selectNum = sc.nextInt();
                    sc.nextLine();
                }
                
                if((selectNum == 1) || (size3 == 0)) {
                    System.out.print("출결 배점 > ");
                    int attendance_point = sc.nextInt();
                    sc.nextLine();
                    
                    System.out.print("필기 배점 > ");
                    int write_point = sc.nextInt();
                    sc.nextLine();
                    
                    System.out.print("실기 배점 > ");
                    int skill_point = sc.nextInt();
                    sc.nextLine();
                    
                    System.out.print("시험 날짜 > ");
                    Date exam_date = Date.valueOf(sc.nextLine());
                    
                    System.out.print("시험 문제 > ");
                    String exam_file = sc.nextLine();
                    
                    Exam e = new Exam("", open_subject_id, attendance_point, write_point, skill_point, exam_date,
                            exam_file);
                    int result = this.eDAO.insertPoint(e);
                    
                    if (result > 0) {
                        System.out.println("등록되었습니다.");
                    } else {
                        System.out.println("실패했습니다.");
                    }
                }
            }
        }
    }
    
    // 성적 처리 시스템 v6.0 (강사 : OOO) > 2. 배점 관리 > 2. 배점 출력
    private void m2_s2(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (강사 : %s) > 2. 배점 관리 > 2. 배점 출력\n", this.instructor_name);
        int size1 = this.schedule_print();
        
        if(size1 > 0) {
            System.out.print("개설 과목 번호 > ");
            String open_subject_id = sc.nextLine();
            
            int size2 = this.open_subject_print(open_subject_id);
            
            if(size2 > 0) {
                this.exam_print(open_subject_id);
            }
        }
    }
    
    // 성적 처리 시스템 v6.0 (강사 : OOO) > 2. 배점 관리 > 3. 배점 삭제
    private void m2_s3(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (강사 : %s) > 2. 배점 관리 > 3. 배점 삭제\n", this.instructor_name);
 
        int size1 = this.schedule_print();
        
        if(size1 > 0) {
            System.out.print("개설 과목 번호 > ");
            String open_subject_id = sc.nextLine();
            
            int size2 = this.open_subject_print(open_subject_id);
            if(size2 > 0) {
                int size3 = this.exam_print(open_subject_id);
                
                if(size3 > 0) {
                    System.out.print("시험 번호 > ");
                    String exam_id = sc.nextLine();
                    System.out.print("배점(시험정보 포함)을 삭제하시겠습니까? (0/1) > ");
                    int selectNum = sc.nextInt();
                    sc.nextLine();
                    
                    if(selectNum == 1) {
                        Exam e = new Exam(exam_id, null, null, this.instructor_id);
                        int result = this.eDAO.removePoint(e);
                        
                        if(result > 0) {
                            System.out.println("배점 정보가 삭제되었습니다.");
                        } else {
                            System.out.println("배점 정보 삭제를 실패했습니다.");
                        }
                    }
                }
            }
        }
    }
    
    // 성적 처리 시스템 v6.0 (강사 : OOO) > 3. 성적 관리
    private void m3(Scanner sc) {
        
        boolean run = true;
        while(run) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("성적 처리 시스템 v6.0 (강사 : %s) > 3. 성적 관리\n", this.instructor_name);
            System.out.println("1. 성적 입력  2. 성적 출력  3. 성적 삭제");
            System.out.print("선택 > ");
            int selectNum = sc.nextInt();
            sc.nextLine();
            
            switch(selectNum) {
            case 1:
                this.m3_s1(sc);
                break;
                
            case 2:
                this.m3_s2(sc);
                break;
 
            case 3:
                this.m3_s3(sc);
                break;
 
            case 0:
                run = false;
                break;
                
            default:
                System.out.println("없는 번호입니다.");
                break;
            }
        }
    }
    
    // 성적 처리 시스템 v6.0 (강사 : OOO) > 3. 성적 관리 > 1. 성적 입력
    private void m3_s1(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (강사 : %s) > 3. 성적 관리 > 1. 성적 입력\n", this.instructor_name);
 
        boolean run = true;
        int size1 = this.schedule_print();
        
        if(size1 > 0) {
            System.out.print("개설 과목 번호 > ");
            String open_subject_id = sc.nextLine();
            
            int size2 = this.open_subject_print(open_subject_id);
            
            if(size2 > 0) {
                int size3 = this.exam_list_print(open_subject_id);
                
                if(size3 > 0) {
                    System.out.print("시험 번호 > ");
                    String exam_id = sc.nextLine();
                    int size4 = this.exam_detail_print(open_subject_id, exam_id);
                    
                    if(size4 > 0) {
                        
                        this.student_print(open_subject_id, this.instructor_id, exam_id);
                        while(run) {
                            System.out.print("수강생 번호 > ");
                            String student_id = sc.nextLine();
                            if(student_id.equals("0")) {
                                break;
                            }
                            
                            List<Student> list = this.stDAO.search("student_id", 
                                    new Student(student_id, null, null, null, null));
                            
                            for(Student s : list) {                                
                                System.out.printf("수강생 번호 : %s\n", s.getStudent_id());
                                System.out.printf("수강생 이름 : %s\n", s.getStudent_name());
                                System.out.printf("수강생 휴대폰번호 : %s\n", s.getStudent_phone());
                                System.out.printf("수강생 등록일 : %s\n", s.getStudent_regDate());
                            }
                            
                            System.out.print("출결 점수 > ");
                            int attendance_score = sc.nextInt();
                            sc.nextLine();
                            System.out.print("필기 점수 > ");
                            int write_score = sc.nextInt();
                            sc.nextLine();
                            System.out.print("실기 점수 > ");
                            int skill_score = sc.nextInt();
                            sc.nextLine();
                            
                            System.out.print("등록하시겠습니까?(0/1) > ");
                            int selectNum = sc.nextInt();
                            sc.nextLine();
                            
                            if(selectNum == 1) {
                                Exam e = new Exam(exam_id, student_id, attendance_score, write_score, skill_score);
                                int result = this.eDAO.insertScore(e);
                                
                                if(result > 0) {
                                    System.out.println("등록되었습니다.");
                                } else {
                                    System.out.println("실패하였습니다.");
                                }
                            }
                        }
                    }
                }
            } else {
                System.out.println("개설 과목 정보가 없습니다.");
            }
        }
    }
    
    // 성적 처리 시스템 v6.0 (강사 : OOO) > 3. 성적 관리 > 2. 성적 출력
    private void m3_s2(Scanner sc) {
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (강사 : %s) > 3. 성적 관리 > 2. 성적 출력\n", this.instructor_name);
 
        int size1 = this.schedule_print();
        
        if(size1 > 0) {
            System.out.print("개설 과목 번호 > ");
            String open_subject_id = sc.nextLine();
            
            int size2 = this.open_subject_print(open_subject_id);
            
            if(size2 > 0) {
                int size3 = this.exam_list_print(open_subject_id);
                
                if(size3 > 0) {
                    System.out.print("시험 번호 > ");
                    String exam_id = sc.nextLine();
                    int size4 = this.exam_detail_print(open_subject_id, exam_id);
                    if(size4 > 0) {
                        this.student_print(open_subject_id, this.instructor_id, exam_id);
                    }
                }
            }
        }
    }
    
    // 성적 처리 시스템 v6.0 (강사 : OOO) > 3. 성적 관리 > 3. 성적 삭제
    private void m3_s3(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (강사 : %s) > 3. 성적 관리 > 3. 성적 삭제\n", this.instructor_name);
 
        int size1 = this.schedule_print();
        
        if(size1 > 0) {
            System.out.print("개설 과목 번호 > ");
            String open_subject_id = sc.nextLine();
            
            int size2 = this.open_subject_print(open_subject_id);
            
            if(size2 > 0) {
                int size3 = this.exam_list_print(open_subject_id);
                
                if(size3 > 0) {
                    System.out.print("시험 번호 > ");
                    String exam_id = sc.nextLine();
                    int size4 = this.exam_detail_print(open_subject_id, exam_id);
                    
                    if(size4 > 0) {
                        this.student_print(open_subject_id, this.instructor_id, exam_id);
                        System.out.print("수강생 번호 > ");
                        String student_id = sc.nextLine();
                        
                        List<Student> list = this.stDAO.search("student_id", 
                                new Student(student_id, null, null, null, null));
                        for(Student s : list) {                                
                            System.out.printf("수강생 번호 : %s\n", s.getStudent_id());
                            System.out.printf("수강생 이름 : %s\n", s.getStudent_name());
                            System.out.printf("수강생 휴대폰번호 : %s\n", s.getStudent_phone());
                            System.out.printf("수강생 등록일 : %s\n", s.getStudent_regDate());
                        }
                        
                        System.out.print("성적 정보를 삭제하시겠습니까? (0/1) > ");
                        int selectNum = sc.nextInt();
                        sc.nextLine();
                        
                        if(selectNum == 1) {
                            Exam e = new Exam(null, null, student_id, null);
                            int result = this.eDAO.removeScore(e);
                            
                            if(result > 0) {
                                System.out.println("삭제되었습니다.");
                            } else {
                                System.out.println("실패하였습니다.");
                            }
                        }
                    }
                }
            }
        }
    }
    
    // 성적 처리 시스템 v6.0 (강사 : OOO) > 4. 개인 정보
    private void m4(Scanner sc) {
        
        boolean run = true;
        while(run) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("성적 처리 시스템 v6.0 (강사 : %s) > 4. 개인 정보\n", this.instructor_name);
            System.out.println("1. 개인 정보 조회  2. 비밀 번호 변경");
            System.out.print("선택 > ");
            int selectNum = sc.nextInt();
            sc.nextLine();
            
            switch(selectNum) {
            case 1:
                this.m4_s1();
                break;
                
            case 2:
                this.m4_s2(sc);
                break;
 
            case 0:
                run = false;
                break;
                
            default:
                System.out.println("없는 번호입니다.");
                break;
            }
        }
    }
    
    // 성적 처리 시스템 v6.0 (강사 : OOO) > 4. 개인 정보 > 1. 개인 정보 조회
    private void m4_s1() {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (강사 : %s) > 4. 개인 정보 > 1. 개인 정보 조회\n", this.instructor_name);
        List<Instructor> list = this.iDAO.search("instructor_id", this.instructor_id);
        for(Instructor i : list) {
            System.out.printf("강사번호 : %s\n", i.getInstructor_id());
            System.out.printf("이름 : %s\n", i.getInstructor_name());
            System.out.printf("휴대폰번호 : %s\n", i.getInstructor_phone());
            System.out.printf("등록일 : %s\n", i.getInstructor_regDate());
            System.out.println();
        }
        
        List<InstructorPossible> list2 = this.ipDAO.list1(this.instructor_id);
        if(list2.size() > 0) {
            System.out.println("** 강의 가능 과목 **");
            System.out.println("-------------------------------");
            System.out.println("과목 번호 / 과목명");
            for(InstructorPossible ip : list2) {
                System.out.printf("%s - %s\n", ip.getSubject_id(), ip.getSubject_name());
            }
            System.out.println("-------------------------------");
            System.out.printf("총 %d건\n", list2.size());
        }
    }
    
    // 성적 처리 시스템 v6.0 (강사 : OOO) > 4. 개인 정보 > 2. 비밀 번호 변경
    private void m4_s2(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (강사 : %s) > 4. 개인 정보 > 2. 비밀 번호 변경\n", this.instructor_name);
        System.out.print("현재 비밀번호 > ");
        String instructor_pw = sc.nextLine();
        System.out.print("신규 비밀번호 > ");
        String instructor_new_pw = sc.nextLine();
        System.out.print("비밀번호 확인 > ");
        String intructor_new_pw2 = sc.nextLine();
        
        System.out.print("비밀번호를 변경하시겠습니까? (0/1) > ");
        int selectNum = sc.nextInt();
        sc.nextLine();
        
        if(instructor_new_pw.equals(intructor_new_pw2)) {
            if(selectNum == 1) {
                int result = this.iDAO.modify(new Instructor(this.instructor_name, instructor_pw, instructor_new_pw));
                
                if(result > 0) {
                    System.out.printf("강사 '%s'의 비밀번호가 변경되었습니다.\n", this.instructor_name);
                } else {
                    System.out.println("실패했습니다.");
                }
            }
        } else {
            System.out.println("비밀번호가 일치하지 않습니다.");
        }
    }
    
    private int schedule_print() {
        
        System.out.println("** 강의 과목 **");
        System.out.println("오늘 날짜 : " + LocalDate.now().format(this.formatter));
    
        List<OpenSubject> list = this.osDAO.list4(this.instructor_id);
        if(list.size() > 0) {
            System.out.println("개설과목번호 / 과목명 / 과목 기간 / 비고");
            for(OpenSubject os : list) {
                System.out.printf("%s / %s / %s ~ %s / %s\n", 
                        os.getOpen_subject_id(), os.getSubject_name(), 
                        os.getSubject_start_date(), os.getSubject_end_date(), os.getInstructor_status());
            }
            System.out.println();
        } else {
            System.out.println("강의 스케줄이 없습니다.");
        }
        return list.size();
    }
    
    private int open_subject_print(String open_subject_id) {
        int size = 0;
        
        List<OpenSubject> list = this.osDAO.list5("open_subject_idANDinstructor_id", 
                new OpenSubject(open_subject_id, null, null, null, null, this.instructor_id));
        size = list.size();
        if(size > 0) {
            for(OpenSubject os : list) {
                System.out.printf("개설 과목 번호 : %s\n", open_subject_id);
                System.out.printf("개설 과정명 : %s\n", os.getCourse_name());
                System.out.printf("개설 과정 기간 : %s ~ %s\n", os.getOpen_course_start_date(), os.getOpen_course_end_date());
                System.out.printf("강의실 : %s\n", os.getClass_room_name());
                System.out.printf("개설 과목명 : %s\n", os.getSubject_name());
                System.out.printf("개설 과목 기간 : %s ~ %s\n", os.getSubject_start_date(), os.getSubject_end_date());
                System.out.printf("교재명 : %s\n", os.getSubjectbook_name());    
                System.out.printf("수강생 등록 인원 : %d명\n", os.getStudent_count());
                System.out.println();
            }
        } else {
            System.out.println("개설 과목 정보가 없습니다.");
        }
        return size;
    }
    
    private int exam_print(String open_subject_id) {
        int size = 0;
 
        List<Exam> list = this.eDAO.list1("open_subject_id", new Exam(null, open_subject_id, null, this.instructor_id));
        size = list.size();
        if(size > 0) {
            System.out.println("-------------------------------");    
            System.out.println("시험 번호 / 출결 배점 / 필기 배점 / 실기 배점 / 시험 날짜 / 시험 문제");
            for(Exam e : list) {
                System.out.println(e.print1());
            }
            System.out.println("-------------------------------");
            System.out.printf("총 %d건\n", size);
        } else {
            System.out.println("등록된 배점 정보가 없습니다.");
        }
 
        return size;
    }
    
    private int exam_list_print(String open_subject_id) {
        int size = 0;
 
        List<Exam> list = this.eDAO.list3("open_subject_idANDinstructor_id", new Exam(null, open_subject_id, null, this.instructor_id));
        size = list.size();
        if(size > 0) {
            System.out.println("-------------------------------");    
            System.out.println("시험 번호 / 출결 배점 / 필기 배점 / 실기 배점 / 시험 날짜 / 시험 문제 / 성적 등록 인원수 / 성적 입력 여부");
            for(Exam e : list) {
                System.out.println(e.print3());
            }
            System.out.println("-------------------------------");
            System.out.printf("총 %d건\n", size);
        } else {
            System.out.println("시험 정보가 없습니다.");
        }
 
        return size;
    }
    
    private int exam_detail_print(String open_subject_id, String exam_id) {
        int size = 0;
 
        List<Exam> list = eDAO.list3("open_subject_idANDinstructor_idANDexam_id", new Exam(exam_id, open_subject_id, null, this.instructor_id));
        size = list.size();
        if(size > 0) {
            System.out.println("** 시험 정보 **");
            for(Exam e : list) {
                System.out.printf("시험 번호 : %s\n", e.getExam_id());
                System.out.printf("출결 배점 : %d\n", e.getAttendance_point());
                System.out.printf("필기 배점 : %d\n", e.getWrite_point());
                System.out.printf("실기 배점 : %d\n", e.getSkill_point());
                System.out.printf("시험 날짜 : %s\n", e.getExam_date());
                System.out.printf("시험 문제 : %s\n", e.getExam_file());
                System.out.printf("성적 등록 인원수 : %d명\n", e.getClass_count());
                System.out.printf("시험 입력 여부 : %s\n", e.getScore_status());                
                System.out.println();
            }
        } else {
            System.out.println("시험 정보가 없습니다.");
        }
 
        return size;
    }
    
    private int student_print(String open_subject_id, String instructor_id, String exam_id) {
        int size = 0;
 
        List<Exam> list = this.eDAO.list4("open_subject_idANDinstructor_idANDexam_id", new Exam(exam_id, open_subject_id, null, this.instructor_id));
        size = list.size();
        
        if(list.size() > 0) {
            System.out.println("-------------------------------");
            System.out.println("수강생번호 / 이름 / 휴대폰번호 / 등록일 / 수료여부 / 날짜 / 출결점수 / 필기점수 / 실기점수 / 총점");
            for(Exam e : list) {
                System.out.println(e.print4());
            }
            System.out.println("-------------------------------");
            System.out.printf("총 %d명\n", size);
        } else {
            System.out.println("수강생 정보가 없습니다.");
        }
        return size;
    }
}