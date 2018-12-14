package com.scoremanagement.service;
 
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
 
import com.scoremanagement.domain.Admin;
import com.scoremanagement.domain.ClassRoom;
import com.scoremanagement.domain.Course;
import com.scoremanagement.domain.Exam;
import com.scoremanagement.domain.Instructor;
import com.scoremanagement.domain.InstructorPossible;
import com.scoremanagement.domain.OpenCourse;
import com.scoremanagement.domain.OpenSubject;
import com.scoremanagement.domain.Student;
import com.scoremanagement.domain.StudentHistory;
import com.scoremanagement.domain.Subject;
import com.scoremanagement.domain.Subjectbook;
import com.scoremanagement.persistance.AdminDAO;
import com.scoremanagement.persistance.ClassRoomDAO;
import com.scoremanagement.persistance.CourseDAO;
import com.scoremanagement.persistance.ExamDAO;
import com.scoremanagement.persistance.InstructorDAO;
import com.scoremanagement.persistance.InstructorPossibleDAO;
import com.scoremanagement.persistance.OpenCourseDAO;
import com.scoremanagement.persistance.OpenSubjectDAO;
import com.scoremanagement.persistance.StudentDAO;
import com.scoremanagement.persistance.SubjectDAO;
import com.scoremanagement.persistance.SubjectbookDAO;
 
public class ServiceAdmin {
 
    private AdminDAO aDAO = new AdminDAO();
    private CourseDAO cDAO = new CourseDAO();
    private ClassRoomDAO crDAO = new ClassRoomDAO();
    private OpenCourseDAO ocDAO = new OpenCourseDAO();
    private OpenSubjectDAO osDAO = new OpenSubjectDAO();
    private StudentDAO stDAO = new StudentDAO();
    private SubjectDAO sDAO = new SubjectDAO();
    private SubjectbookDAO sbDAO = new SubjectbookDAO();
    private InstructorDAO iDAO = new InstructorDAO();
    private InstructorPossibleDAO ipDAO = new InstructorPossibleDAO();
    private ExamDAO eDAO = new ExamDAO();
 
    private String admin_id = null;
    
    // 성적 처리 시스템 v6.0 > 3. 관리자 로그인
    public void login(Scanner sc) {
        // 로그인 과정 귀찮으므로 주석 처리
        
        System.out.println("---------------------------------------------------------------");
        System.out.println("성적 처리 시스템 v6.0 > 3. 관리자 로그인");
        System.out.print("아이디 > ");
        admin_id = sc.nextLine();
        
        System.out.print("비밀번호 > ");
        String admin_pw = sc.nextLine();
        
        Admin a = new Admin(admin_id, admin_pw);
        admin_id = this.aDAO.login(a);
        
        if(admin_id != null) {
            System.out.printf("관리자 '%s'님이 로그인되었습니다.\n", this.admin_id);
            this.main(sc);
        } else {
             System.out.println("아이디 또는 패스워드가 틀렸습니다.");
        }
        
        //this.main(sc);
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin)
    private void main(Scanner sc) {
 
        boolean run = true;
        while (run) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s)\n", this.admin_id);
            System.out.println("1. 기초 정보 관리  2. 강사 계정 관리  3. 개설 과정 관리  4. 개설 과목 관리  5. 수강생 관리  6. 성적 조회  7. 비밀번호 변경");
            System.out.print("선택 > ");
 
            int selectNum = sc.nextInt();
            sc.nextLine();
 
            switch (selectNum) {
 
            case 0:
                run = false;
                System.out.printf("관리자 '%s'님이 로그아웃 되었습니다.\n", this.admin_id);
                break;
 
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
 
            case 5:
                this.m5(sc);
                break;
 
            case 6:
                this.m6(sc);
                break;
 
            case 7:
                this.m7(sc);
                break;
 
            default:
                System.out.println("없는 번호입니다.");
                break;
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 1. 기초 정보 관리
    private void m1(Scanner sc) {
 
        boolean run = true;
        while (run) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 1. 기초 정보 관리\n", this.admin_id);
            System.out.println("1. 과정 관리  2. 과목 관리  3. 강의실 관리  4. 교재 관리");
            System.out.print("선택 > ");
 
            int selectNum = sc.nextInt();
            sc.nextLine();
 
            switch (selectNum) {
 
            case 0:
                run = false;
                break;
                
            case 1:
                this.m1_s1(sc);
                break;
                
            case 2:
                this.m1_s2(sc);
                break;
                
            case 3:
                this.m1_s3(sc);
                break;
                
            case 4:
                this.m1_s4(sc);
                break;
                
            default:
                System.out.println("없는 번호입니다.");
                break;
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 1. 기초 정보 관리 > 1. 과정 관리
    private void m1_s1(Scanner sc) {
 
        boolean run = true;
        while (run) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 1. 기초 정보 관리 > 1. 과정 관리\n", this.admin_id);
            System.out.println("1. 과정 입력  2. 과정 출력  3. 과정 삭제");
            System.out.print("선택 > ");
 
            int selectNum = sc.nextInt();
            sc.nextLine();
 
            switch (selectNum) {
 
            case 0:
                run = false;
                break;
                
            case 1:
                this.m1_s1_s1(sc);
                break;
                
            case 2:
                this.m1_s1_s2();
                break;
                
            case 3:
                this.m1_s1_s3(sc);
                break;
 
            default:
                System.out.println("없는 번호입니다.");
                break;
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 1. 기초 정보 관리 > 1. 과정 관리 > 1. 과정 입력
    private void m1_s1_s1(Scanner sc) {
 
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 1. 기초 정보 관리 > 1. 과정 관리 > 1. 과정 입력\n", this.admin_id);
 
        System.out.print("과정명 > ");
        String course_name = sc.nextLine();
 
        System.out.print("등록하시겠습니까?(1/0) > ");
        int selectNum = sc.nextInt();
        sc.nextLine();
        
        if(selectNum == 1) {
            Course c = new Course("", course_name);
            int result = this.cDAO.insert(c);
            
            if(result > 0) {
                System.out.println("과정이 추가되었습니다.");
            } else {
                System.out.println("실패하였습니다.");
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 1. 기초 정보 관리 > 1. 과정 관리 > 2. 과정 출력
    private void m1_s1_s2() {
 
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 1. 기초 정보 관리 > 1. 과정 관리 > 2. 과정 출력\n", this.admin_id);
        List<Course> list = this.cDAO.list1();
        if(list.size() > 0) {
            System.out.println("--------------------");
            System.out.println("과정 번호 / 과정명");
            for(Course c : list) {
                System.out.println(c.print1());
            }
            System.out.println("--------------------");
            System.out.printf("총 %d건\n", list.size());
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 1. 기초 정보 관리 > 1. 과정 관리 > 3. 과정 삭제
    private void m1_s1_s3(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 1. 기초 정보 관리 > 1. 과정 관리 > 3. 과정 삭제\n", this.admin_id);
        List<Course> list = this.cDAO.list2();
        if(list.size() > 0) {
            System.out.println("--------------------");
            System.out.println("과정 번호 / 과정명 / 삭제 가능 여부");
            for(Course c : list) {
                System.out.println(c.print2());
            }
            System.out.println("--------------------");
            System.out.printf("총 %d건\n", list.size());
            
            System.out.print("과정 번호 > ");
            String course_id = sc.nextLine();
 
            List<Course> list2 = this.cDAO.search("course_id", course_id);
            
            if(list2.size() > 0) {
                for(Course c : list2) {
                    System.out.printf("과정명 : %s\n", c.getCourse_name());
                    
                }
                System.out.print("위의 과정을 삭제하시겠습니까?(0/1) > ");
                int selectNum = sc.nextInt();
                sc.nextLine();
                
                if(selectNum == 1) {
                    Course c = new Course(course_id, "");
                    int result = this.cDAO.remove(c);
                    
                    if(result > 0) {
                        System.out.println("삭제되었습니다.");
                    } else {
                        System.out.println("실패하였습니다.");
                    }
                }
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 1. 기초 정보 관리 > 2. 과목 관리
    private void m1_s2(Scanner sc) {
 
        boolean run = true;
        while (run) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 1. 기초 정보 관리 > 2. 과목 관리\n", this.admin_id);
            System.out.println("1. 과목 입력  2. 과목 출력  3. 과목 삭제");
            System.out.print("선택 > ");
 
            int selectNum = sc.nextInt();
            sc.nextLine();
 
            switch (selectNum) {
 
            case 0:
                run = false;
                break;
                
            case 1:
                this.m1_s2_s1(sc);
                break;
                
            case 2:
                this.m1_s2_s2();
                break;
                
            case 3:
                this.m1_s2_s3(sc);
                break;
 
            default:
                System.out.println("없는 번호입니다.");
                break;
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 1. 기초 정보 관리 > 2. 과목 관리 > 1. 과목 입력
    private void m1_s2_s1(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 1. 기초 정보 관리 > 2. 과목 관리 > 1. 과목 입력\n", this.admin_id);
 
        System.out.print("과목명 > ");
        String subject_name = sc.nextLine();
 
        System.out.print("등록하시겠습니까?(1/0) > ");
        int selectNum = sc.nextInt();
        sc.nextLine();
        
        if(selectNum == 1) {
            Subject s = new Subject("", subject_name);
            int result = this.sDAO.insert(s);
            
            if(result > 0) {
                System.out.println("과목이 추가되었습니다.");
            } else {
                System.out.println("실패하였습니다.");
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 1. 기초 정보 관리 > 2. 과목 관리 > 2. 과목 출력
    private void m1_s2_s2() {
 
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 1. 기초 정보 관리 > 2. 과목 관리 > 2. 과목 출력\n", this.admin_id);
        List<Subject> list = this.sDAO.list1();
        if(list.size() > 0) {
            System.out.println("--------------------");
            System.out.println("과목 번호 / 과목명");
            for(Subject s : list) {
                System.out.println(s.print1());
            }
            System.out.println("--------------------");
            System.out.printf("총 %d건\n", list.size());
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 1. 기초 정보 관리 > 2. 과목 관리 > 3. 과목 삭제
    private void m1_s2_s3(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 1. 기초 정보 관리 > 2. 과목 관리 > 3. 과목 삭제\n", this.admin_id);
        List<Subject> list = this.sDAO.list2();
        if(list.size() > 0) {
            System.out.println("--------------------");
            System.out.println("과목 번호 / 과목명 / 삭제 가능 여부");
            for(Subject s : list) {
                System.out.println(s.print2());
            }
            System.out.println("--------------------");
            System.out.printf("총 %d건\n", list.size());
            
            System.out.print("과목 번호 > ");
            String subject_id = sc.nextLine();
 
            List<Subject> list2 = this.sDAO.search("subject_id", subject_id);
            
            if(list2.size() > 0) {
                for(Subject s : list2) {
                    System.out.printf("과목명 : %s\n", s.getSubject_name());
                    
                }
                System.out.print("위의 과목을 삭제하시겠습니까?(0/1) > ");
                int selectNum = sc.nextInt();
                sc.nextLine();
                
                if(selectNum == 1) {
                    Subject s = new Subject(subject_id, "");
                    int result = this.sDAO.remove(s);
                    
                    if(result > 0) {
                        System.out.println("삭제되었습니다.");
                    } else {
                        System.out.println("실패하였습니다.");
                    }
                }
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 1. 기초 정보 관리 > 3. 강의실 관리
    private void m1_s3(Scanner sc) {
        boolean run = true;
        while (run) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 1. 기초 정보 관리 > 1. 강의실 관리\n", this.admin_id);
            System.out.println("1. 강의실 입력  2. 강의실 출력  3. 강의실 삭제");
            System.out.print("선택 > ");
 
            int selectNum = sc.nextInt();
            sc.nextLine();
 
            switch (selectNum) {
 
            case 0:
                run = false;
                break;
                
            case 1:
                this.m1_s3_s1(sc);
                break;
                
            case 2:
                this.m1_s3_s2();
                break;
                
            case 3:
                this.m1_s3_s3(sc);
                break;
 
            default:
                System.out.println("없는 번호입니다.");
                break;
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 1. 기초 정보 관리 > 3. 강의실 관리 > 1. 강의실 입력
    private void m1_s3_s1(Scanner sc) {
 
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 1. 기초 정보 관리 > 3. 강의실 관리 > 1. 강의실 입력\n", this.admin_id);
 
        System.out.print("강의실명 > ");
        String class_room_name = sc.nextLine();
 
        System.out.print("최대 정원 > ");
        int max_number = sc.nextInt();
        sc.nextLine();
        
        System.out.print("등록하시겠습니까?(1/0) > ");
        int selectNum = sc.nextInt();
        sc.nextLine();
        
        if(selectNum == 1) {
            ClassRoom cr = new ClassRoom("", class_room_name, max_number);
            int result = this.crDAO.insert(cr);
            
            if(result > 0) {
                System.out.println("강의실이 추가되었습니다.");
            } else {
                System.out.println("실패하였습니다.");
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 1. 기초 정보 관리 > 3. 강의실 관리 > 2. 강의실 출력
    private void m1_s3_s2() {
 
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 1. 기초 정보 관리 > 3. 강의실 관리 > 2. 강의실 출력\n", this.admin_id);
        List<ClassRoom> list = this.crDAO.list1();
        if(list.size() > 0) {
            System.out.println("--------------------");
            System.out.println("강의실 번호 / 강의실명 / 최대 정원");
            for(ClassRoom cr : list) {
                System.out.println(cr.print1());
            }
            System.out.println("--------------------");
            System.out.printf("총 %d건\n", list.size());
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 1. 기초 정보 관리 > 3. 강의실 관리 > 3. 강의실 삭제
    private void m1_s3_s3(Scanner sc) {
 
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 1. 기초 정보 관리 > 3. 강의실 관리 > 3. 강의실 삭제\n", this.admin_id);
        List<ClassRoom> list = this.crDAO.list2();
        if(list.size() > 0) {
            System.out.println("--------------------");
            System.out.println("강의실 번호 / 강의실명 / 최대 정원 / 삭제 가능 여부");
            for(ClassRoom cr : list) {
                System.out.println(cr.print2());
            }
            System.out.println("--------------------");
            System.out.printf("총 %d건\n", list.size());
            
            System.out.print("강의실 번호 > ");
            String class_room_id = sc.nextLine();
 
            List<ClassRoom> list2 = this.crDAO.search("class_room_id", class_room_id);
            
            if(list2.size() > 0) {
                for(ClassRoom cr : list2) {
                    System.out.printf("강의실명 : %s\n", cr.getClass_room_name());
                    System.out.printf("최대 정원 : %d\n", cr.getMax_number());
                    
                }
                System.out.print("위의 강의실을 삭제하시겠습니까?(0/1) > ");
                int selectNum = sc.nextInt();
                sc.nextLine();
                
                if(selectNum == 1) {
                    ClassRoom cr = new ClassRoom(class_room_id, "", 0);
                    int result = this.crDAO.remove(cr);
                    
                    if(result > 0) {
                        System.out.println("삭제되었습니다.");
                    } else {
                        System.out.println("실패하였습니다.");
                    }
                }
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 1. 기초 정보 관리 > 4. 교재 관리
    private void m1_s4(Scanner sc) {
        boolean run = true;
        while (run) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 1. 기초 정보 관리 > 4. 교재 관리\n", this.admin_id);
            System.out.println("1. 교재 입력  2. 교재 출력  3. 교재 삭제");
            System.out.print("선택 > ");
 
            int selectNum = sc.nextInt();
            sc.nextLine();
 
            switch (selectNum) {
 
            case 0:
                run = false;
                break;
                
            case 1:
                this.m1_s4_s1(sc);
                break;
                
            case 2:
                this.m1_s4_s2();
                break;
                
            case 3:
                this.m1_s4_s3(sc);
                break;
 
            default:
                System.out.println("없는 번호입니다.");
                break;
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 1. 기초 정보 관리 > 4. 교재 관리 > 1. 교재 입력
    private void m1_s4_s1(Scanner sc) {
 
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 1. 기초 정보 관리 > 4. 교재 관리 > 1. 교재 입력\n", this.admin_id);
 
        System.out.print("교재명 > ");
        String subjectbook_name = sc.nextLine();
 
        System.out.print("교재 출판사 > ");
        String subjectbook_publisher = sc.nextLine();
        System.out.print("등록하시겠습니까?(1/0) > ");
        int selectNum = sc.nextInt();
        sc.nextLine();
        
        if(selectNum == 1) {
            Subjectbook sb = new Subjectbook("", subjectbook_name, subjectbook_publisher);
            int result = this.sbDAO.insert(sb);
            
            if(result > 0) {
                System.out.println("교재가 추가되었습니다.");
            } else {
                System.out.println("실패하였습니다.");
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 1. 기초 정보 관리 > 4. 교재 관리 > 2. 교재 출력
    private void m1_s4_s2() {
 
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 1. 기초 정보 관리 >  4. 교재 관리 > 2. 교재 출력\n", this.admin_id);
        List<Subjectbook> list = this.sbDAO.list1();
        if(list.size() > 0) {
            System.out.println("--------------------");
            System.out.println("교재 번호 / 교재명 / 교재 출판사");
            for(Subjectbook sb : list) {
                System.out.println(sb.print1());
            }
            System.out.println("--------------------");
            System.out.printf("총 %d건\n", list.size());
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 1. 기초 정보 관리 > 4. 교재 관리 > 3. 교재 삭제
    private void m1_s4_s3(Scanner sc) {
 
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 1. 기초 정보 관리 > 4. 교재 관리 > 3. 교재 삭제\n", this.admin_id);
        List<Subjectbook> list = this.sbDAO.list2();
        if(list.size() > 0) {
            System.out.println("--------------------");
            System.out.println("교재 번호 / 교재명 / 교재 출판사 / 삭제 가능 여부");
            for(Subjectbook sb : list) {
                System.out.println(sb.print2());
            }
            System.out.println("--------------------");
            System.out.printf("총 %d건\n", list.size());
            
            System.out.print("교재 번호 > ");
            String subjectbook_id = sc.nextLine();
 
            List<Subjectbook> list2 = this.sbDAO.search("subjectbook_id", subjectbook_id);
            
            if(list2.size() > 0) {
                for(Subjectbook sb : list2) {
                    System.out.printf("교재명 : %s\n", sb.getSubjectbook_name());
                    System.out.printf("교재 출판사 : %s\n", sb.getSubjectbook_publisher());                    
                }
                System.out.print("위의 교재를 삭제하시겠습니까?(0/1) > ");
                int selectNum = sc.nextInt();
                sc.nextLine();
                
                if(selectNum == 1) {
                    Subjectbook sb = new Subjectbook(subjectbook_id, "", "");
                    int result = this.sbDAO.remove(sb);
                    
                    if(result > 0) {
                        System.out.println("삭제되었습니다.");
                    } else {
                        System.out.println("실패하였습니다.");
                    }
                }
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 2. 강사 계정 관리
    private void m2(Scanner sc) {
 
        boolean run = true;
        while (run) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 2. 강사 계정 관리\n", this.admin_id);
            System.out.println("1. 강사 입력  2. 강사 검색 및 출력  3. 강사 삭제  4. 비밀번호 초기화  5. 강의 가능 과목 관리");
            System.out.print("선택 > ");
 
            int selectNum = sc.nextInt();
            sc.nextLine();
 
            switch (selectNum) {
 
            case 0:
                run = false;
                break;
                
            case 1:
                this.m2_s1(sc);
                break;
                
            case 2:
                this.m2_s2(sc);
                break;
                
            case 3:
                this.m2_s3(sc);
                break;
                
            case 4:
                this.m2_s4(sc);
                break;
                
            case 5:
                this.m2_s5(sc);
                break;
 
            default:
                System.out.println("없는 번호입니다.");
                break;
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 2. 강사 계정 관리 > 1. 강사 입력
    private void m2_s1(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 2. 강사 계정 관리 > 1. 강사 입력\n", this.admin_id);
 
        System.out.print("강사 이름 > ");
        String instructor_name = sc.nextLine();
        
        System.out.print("강사 휴대폰번호 > ");
        String instructor_phone = sc.nextLine();
        
        System.out.print("강사 비밀번호 > ");
        String instructor_pw = sc.nextLine();
        
        System.out.print("강사 등록일 > ");
        Date instructor_regDate = Date.valueOf(sc.nextLine());
        
        System.out.print("등록하시겠습니까? (0/1) > ");
        int selectNum = sc.nextInt();
        sc.nextLine();
        
        if(selectNum == 1) {
            
            Instructor i = new Instructor(null, instructor_name, instructor_phone, instructor_regDate, instructor_pw);
            int result = this.iDAO.insert(i);
            
            if(result > 0) {
                System.out.println("강사가 추가되었습니다.");                
            } else {
                System.out.println("실패하였습니다.");
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 2. 강사 계정 관리 > 2. 강사 검색 및 출력
    private void m2_s2(Scanner sc) {
 
        while (true) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 2. 강사 계정 관리 > 2. 강사 검색 및 출력\n", this.admin_id);
            System.out.println("1. 강사 번호  2. 강사 이름  3. 강사 전체 출력");
            System.out.print("선택 > ");
 
            int select = sc.nextInt();
            sc.nextLine();
 
            if (select == 0) {
                break;
            }
 
            switch (select) {
 
            case 1:
                this.m2_s2_s1(sc);
                break;
                
            case 2:
                this.m2_s2_s2(sc);
                break;
                
            case 3:
                this.m2_s2_s3();
                break;
 
            default:
                System.out.println("없는 번호입니다.");
                break;
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 2. 강사 계정 관리 > 2. 강사 검색 및 출력 > 1. 강사 번호
    private void m2_s2_s1(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 2. 강사 계정 관리 > 2. 강사 검색 및 출력 > 1. 강사 번호\n", this.admin_id);
 
        System.out.print("강사 번호 > ");
        String instructor_id = sc.nextLine();
 
        List<Instructor> list1 = this.iDAO.search("instructor_id", instructor_id);
 
        if (list1.size() > 0) {
            
            for (Instructor i : list1) {
                System.out.printf("강사 번호 : %s\n", i.getInstructor_id());
                System.out.printf("강사 이름 : %s\n", i.getInstructor_name());
                System.out.printf("강사 휴대폰번호 : %s\n", i.getInstructor_phone());
                System.out.printf("강사 등록일 : %s\n", i.getInstructor_regDate());
                System.out.println();
            }            
        }
        
        List<OpenSubject> list2 = this.osDAO.list6("instructor_id", instructor_id);
        
        if(list2.size() > 0) {
            System.out.println("-------------------------------");
            System.out.println("개설 과목명 / 개설 과목 기간 / 개설 과정명 / 개설 과정 기간 / 강의실 / 강의 진행 여부");
            for (OpenSubject os : list2) {
                System.out.println(os.print6());
            }
            System.out.println("-------------------------------");
            System.out.printf("총 %d건\n", list2.size());
        } else {
            System.out.println("검색 결과가 없습니다.");
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 2. 강사 계정 관리 > 2. 강사 검색 및 출력 > 2. 강사 이름
    private void m2_s2_s2(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 2. 강사 계정 관리 > 2. 강사 검색 및 출력 > 2. 강사 이름\n", this.admin_id);
 
        System.out.print("강사 이름 > ");
        String instructor_name = sc.nextLine();
 
        List<Instructor> list1 = this.iDAO.search("instructor_name", instructor_name);
        
        if (list1.size() == 1) {
            
            for (Instructor i : list1) {
                System.out.printf("강사 번호 : %s\n", i.getInstructor_id());
                System.out.printf("강사 이름 : %s\n", i.getInstructor_name());
                System.out.printf("강사 휴대폰번호 : %s\n", i.getInstructor_phone());
                System.out.printf("강사 등록일 : %s\n", i.getInstructor_regDate());
                System.out.println();
            }
 
            List<OpenSubject> list2 = this.osDAO.list6("instructor_name", instructor_name);
 
            if (list2.size() > 0) {
                System.out.println("-------------------------------");
                System.out.println("개설 과목명 / 개설 과목 기간 / 개설 과정명 / 개설 과정 기간 / 강의실 / 강의 진행 여부");
                for (OpenSubject os : list2) {
                    System.out.println(os.print6());
                }
                System.out.println("-------------------------------");
                System.out.printf("총 %d건\n", list2.size());
            }
        } else if (list1.size() > 1) {
 
            System.out.println("-------------------------------");
            System.out.println("강사 번호 / 강사 이름 / 강사 휴대폰 번호 / 등록일");
            for (Instructor i : list1) {
                System.out.println(i.print());
            }
            System.out.println("-------------------------------");
            System.out.printf("총 %d명\n", list1.size());
            System.out.println("검색 결과가 여러 건입니다.");
            this.m2_s2_s1(sc);
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 2. 강사 계정 관리 > 2. 강사 검색 및 출력 > 3. 강사 전체 출력
    private void m2_s2_s3() {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 2. 강사 계정 관리 > 2. 강사 검색 및 출력 > 3. 강사 전체 출력\n", this.admin_id);
 
        List<Instructor> list1 = this.iDAO.list1("all", null);
        
        if(list1.size() > 0) {
            System.out.println("--------------------");
            System.out.println("강사 번호 / 강사 이름 / 강사 휴대폰 번호 / 강사 등록일 / 강의 가능 과목");
            for(Instructor i : list1) {
                System.out.println(i.print2());
            }
            System.out.println("--------------------");
            System.out.printf("총 %d명\n", list1.size());
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 2. 강사 계정 관리 > 3. 강사 삭제
    private void m2_s3(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 2. 강사 계정 관리 > 3. 강사 삭제\n", this.admin_id);
        
        List<Instructor> list1 = this.iDAO.list2();
        
        if(list1.size() > 0) {
            System.out.println("--------------------");
            System.out.println("강사 번호 / 강사 이름 / 강사 휴대폰 번호 / 강사 등록일 / 강의 가능 과목 / 삭제 가능 여부");
            for(Instructor i : list1) {
                System.out.println(i.print1());
            }
            System.out.println("--------------------");
            System.out.printf("총 %d명\n", list1.size());
            
            System.out.print("강사 번호 > ");
            String instructor_id = sc.nextLine();
            
            List<Instructor> list2 = this.iDAO.search("instructor_id", instructor_id);
            
            if(list2.size() > 0) {
                for(Instructor i : list2) {
                    System.out.printf("강사 번호 : %s\n", i.getInstructor_id());
                    System.out.printf("강사 이름 : %s\n", i.getInstructor_name());
                    System.out.printf("강사 휴대폰번호 : %s\n", i.getInstructor_phone());
                    System.out.printf("강사 등록일 : %s\n", i.getInstructor_regDate());
                    System.out.println();
                }
                
                System.out.print("위의 강사를 삭제하시겠습니까? (0/1) > ");
                int selectNum = sc.nextInt();
                sc.nextLine();
                
                if(selectNum == 1) {
                    Instructor i = new Instructor(instructor_id, null, null, null, null);
                    int result = this.iDAO.remove(i);
                    
                    if(result > 0) {
                        System.out.println("삭제되었습니다.");
                    } else {
                        System.out.println("실패하였습니다.");
                    }
                }
            } else {
                System.out.println("검색 결과가 없습니다.");
            }
        } else {
            System.out.println("검색 결과가 없습니다.");
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 2. 강사 계정 관리 > 4. 비밀번호 초기화
    private void m2_s4(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 2. 강사 계정 관리 > 4. 비밀번호 초기화\n", this.admin_id);
 
        List<Instructor> list1 = this.iDAO.list1("all", "");
        if (list1.size() > 0) {
            System.out.println("--------------------");
            System.out.println("강사 번호 / 강사 이름 / 강사 휴대폰 번호 / 강사 등록일 / 강의 가능 과목");
            for (Instructor i : list1) {
                System.out.println(i.print2());
            }
            System.out.println("--------------------");
            System.out.printf("총 %d명\n", list1.size());
        }
 
        System.out.print("강사 번호 > ");
        String instructor_id = sc.nextLine();
        List<Instructor> list2 = this.iDAO.search("instructor_id", instructor_id);
        
        if (list2.size() > 0) {
            
            for (Instructor i : list2) {
                System.out.printf("강사 번호 : %s\n", i.getInstructor_id());
                System.out.printf("강사 이름 : %s\n", i.getInstructor_name());
                System.out.printf("강사 휴대폰번호 : %s\n", i.getInstructor_phone());
                System.out.printf("강사 등록일 : %s\n", i.getInstructor_regDate());
                System.out.println();
            }
            
            System.out.print("강사 비밀번호 > ");
            String instructor_pw = sc.nextLine();
 
            System.out.print("진행하시겠습니까? (0/1) > ");
            int selectNum = sc.nextInt();
            sc.nextLine();
 
            if (selectNum == 1) {
                Instructor i = new Instructor(instructor_id, instructor_pw);
                int result = this.iDAO.reset(i);
 
                if (result > 0) {
                    System.out.printf("'%s'의 비밀번호가 초기화되었습니다.\n", instructor_id);
                } else {
                    System.out.println("실패하였습니다.");
                }
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 2. 강사 계정 관리 > 5. 강의 가능 과목 관리
    private void m2_s5(Scanner sc) {
 
        while (true) {
 
            System.out.println("---------------------------------------------------------------");
            System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 2. 강사 계정 관리 > 5. 강의 가능 과목 관리\n", this.admin_id);
            System.out.println("1. 강의 가능 과목 추가  2. 강의 가능 과목 삭제");
            System.out.print("선택 > ");
 
            int select = sc.nextInt();
            sc.nextLine();
 
            if (select == 0) {
                break;
            }
 
            switch (select) {
 
            case 1:
                this.m2_s5_s1(sc);
                break;
                
            case 2:
                this.m2_s5_s2(sc);
                break;
 
            default:
                System.out.println("없는 번호입니다.");
                break;
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 2. 강사 계정 관리 > 5. 강의 가능 과목 관리 > 1. 강의 가능 과목 추가
    private void m2_s5_s1(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 2. 강사 계정 관리 > 5. 강의 가능 과목 관리 > 1. 강의 가능 과목 추가\n", this.admin_id);
 
        System.out.print("강사 번호 > ");
        String instructor_id = sc.nextLine();
 
        List<Instructor> list1 = this.iDAO.search("instructor_id", instructor_id);
        
        if (list1.size() > 0) {
            
            for(Instructor i : list1) {
                System.out.printf("강사 번호 : %s\n", i.getInstructor_id());
                System.out.printf("강사 이름 : %s\n", i.getInstructor_name());
                System.out.printf("강사 휴대폰번호 : %s\n", i.getInstructor_phone());
                System.out.printf("강사 등록일 : %s\n", i.getInstructor_regDate());
                System.out.println();
            }
            
            List<InstructorPossible> list2 = this.ipDAO.list1(instructor_id);
            
            if(list2.size() > 0) {
                System.out.println("-------------------------------");
                System.out.println("강의 가능 과목");
                for(InstructorPossible ip : list2) {
                    System.out.println(ip.print());
                }
                System.out.println("-------------------------------");
                System.out.printf("총 %d건\n", list2.size());
            }
            
 
            while(true) {
                System.out.print("과목 번호 > ");
                String subject_id = sc.nextLine();
 
                if(subject_id.equals("0")) {
                    break;
                }
                
                else if (subject_id.equals("1")) {
                    List<Subject> listAll = this.sDAO.list1();
                    
                    if(listAll.size() > 0) {
                        System.out.println("-------------------------------");
                        System.out.println("과목 번호 / 과목명");
                        for(Subject s : listAll) {
                            System.out.println(s.print1());
                        }
                        System.out.println("-------------------------------");
                        System.out.printf("총 %d건\n", listAll.size());                    
                    }
                }
                
                else {
                    List<Subject> list3 = this.sDAO.search("subject_id", subject_id);
                    
                    if(list3.size() > 0) {
                        for(Subject s : list3) {
                            System.out.printf("과목 번호 : %s\n", s.getSubject_id());
                            System.out.printf("과목명 : %s\n", s.getSubject_name());
                        }
                        
                        System.out.print("강의 가능 과목에 추가하시겠습니까? (0/1) >");
                        int selectNum = sc.nextInt();
                        sc.nextLine();
                        
                        if(selectNum == 1) {
                            InstructorPossible ip = new InstructorPossible(instructor_id, subject_id);
                            int result = this.ipDAO.subjectPossibleAdd(ip);
                            
                            if(result > 0 ) {
                                System.out.println("강의 가능 과목이 추가되었습니다.");
                            } else {
                                System.out.println("실패하였습니다.");
                            }
                        }
                    }
                }
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 2. 강사 계정 관리 > 5. 강의 가능 과목 관리 > 2. 강의 가능 과목 삭제
    // 여기까지다
    private void m2_s5_s2(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 2. 강사 계정 관리 > 5. 강의 가능 과목 관리 > 2. 강의 가능 과목 삭제\n", this.admin_id);
 
        System.out.print("강사 번호 > ");
        String instructor_id = sc.nextLine();
 
        List<Instructor> list1 = this.iDAO.search("instructor_id", instructor_id);
        
        if (list1.size() > 0) {
            
            for(Instructor i : list1) {
                System.out.printf("강사 번호 : %s\n", i.getInstructor_id());
                System.out.printf("강사 이름 : %s\n", i.getInstructor_name());
                System.out.printf("강사 휴대폰번호 : %s\n", i.getInstructor_phone());
                System.out.printf("강사 등록일 : %s\n", i.getInstructor_regDate());
                System.out.println();
            }
            
            List<InstructorPossible> list2 = this.ipDAO.list1(instructor_id);
            
            if(list2.size() > 0) {
                System.out.println("-------------------------------");
                System.out.println("강의 가능 과목");
                for(InstructorPossible ip : list2) {
                    System.out.println(ip.print());
                }
                System.out.println("-------------------------------");
                System.out.printf("총 %d건\n", list2.size());
            }
            
 
            while(true) {
                System.out.print("삭제할 과목 번호 > ");
                String subject_id = sc.nextLine();
 
                if(subject_id.equals("0")) {
                    break;
                }
                
                else {
                    List<Subject> list3 = this.sDAO.search("subject_id", subject_id);
                    
                    if(list3.size() > 0) {
                        for(Subject s : list3) {
                            System.out.printf("과목 번호 : %s\n", s.getSubject_id());
                            System.out.printf("과목명 : %s\n", s.getSubject_name());
                        }
                        
                        System.out.print("삭제하시겠습니까? (0/1) >");
                        int selectNum = sc.nextInt();
                        sc.nextLine();
                        
                        if(selectNum == 1) {
                            InstructorPossible ip = new InstructorPossible(instructor_id, subject_id);
                            int result = this.ipDAO.subjectPossibleRemove(ip);
                            
                            if(result > 0 ) {
                                System.out.println("강의 가능 과목이 삭제되었습니다.");
                            } else {
                                System.out.println("실패하였습니다.");
                            }
                        }
                    }
                }
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 3. 개설 과정 관리
    private void m3(Scanner sc) {
        
        boolean run = true;
        while (run) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 3. 개설 과정 관리\n", this.admin_id);
            System.out.println("1. 개설 과정 추가  2. 개설 과정 검색 및 출력  3. 개설 과정 삭제");
            System.out.print("선택 > ");
 
            int selectNum = sc.nextInt();
            sc.nextLine();
 
            switch (selectNum) {
 
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
                System.out.println("잘못 입력하셨습니다.");
                break;
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 3. 개설 과정 관리 > 1. 개설 과정 추가
    private void m3_s1(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 3. 개설 과정 관리 > 1. 개설 과정 추가\n", this.admin_id);
        List<Course> list1 = this.cDAO.list1();         // 과정번호, 과정이름 출력
        if(list1.size() > 0) {
            System.out.println("--------------------");
            System.out.println("과정 번호 / 과정명");
            for(Course c : list1) {
                System.out.println(c.print1());
            }
            System.out.println("--------------------");
            System.out.printf("총 %d건\n", list1.size());
            
            System.out.print("과정 번호 > ");
            String course_id = sc.nextLine();
            
            System.out.print("개설 과정 시작일 > ");
            Date open_course_start_date = Date.valueOf(sc.nextLine());
            
            System.out.print("개설 과정 종료일 > ");
            Date open_course_end_date = Date.valueOf(sc.nextLine());
 
            List<ClassRoom> list2 = this.crDAO.list1(); // 강의실 아이디, 강의실 이름, 최대정원 출력
            if(list2.size() > 0) {
                System.out.println("--------------------");
                System.out.println("강의실 번호 / 강의실명 / 최대 정원");
                for(ClassRoom cr : list2) {
                    System.out.println(cr.print1());
                }
                System.out.println("--------------------");
                System.out.printf("총 %d건\n", list2.size());
                
                System.out.print("강의실 번호 > ");
                String class_room_id = sc.nextLine();
 
                System.out.print("등록하시겠습니까? (0/1) > ");
                int selectNum = sc.nextInt();
                sc.nextLine();
                
                if (selectNum == 1) {
                    // 개설 과정 등록
                    OpenCourse oc = new OpenCourse(null, course_id, null, open_course_start_date, open_course_end_date, class_room_id, null);
                    int result = this.ocDAO.insert(oc);
                    if(result > 0) {
                        System.out.println("개설 과정이 추가되었습니다.");                
                    } else {
                        System.out.println("실패하였습니다.");
                    }
                }
            } else {
                System.out.println("검색 결과가 없습니다.");
            }    
        } else {
            System.out.println("검색 결과가 없습니다.");
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 3. 개설 과정 관리 > 2. 개설 과정 검색 및 출력
    private void m3_s2(Scanner sc) {
        
        boolean run = true;
        while (run) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 3. 개설 과정 관리 > 2. 개설 과정 검색 및 출력\n", this.admin_id);
            System.out.println("1. 개설 과정 번호  2. 개설 과정명  3. 개설 과정 전체 출력");
            System.out.print("선택 > ");
 
            int selectNum = sc.nextInt();
            sc.nextLine();
 
            switch (selectNum) {
 
            case 1:
                this.m3_s2_s1(sc);
                break;
            case 2:
                this.m3_s2_s2(sc);
                break;
            case 3:
                this.m3_s2_s3();
                break;
            case 0:
                run = false;
                break;
 
            default:
                System.out.println("잘못 입력하셨습니다.");
                break;
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 3. 개설 과정 관리 > 2. 개설 과정 검색 및 출력 > 1. 개설 과정 번호
    private void m3_s2_s1(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 3. 개설 과정 관리 > 2. 개설 과정 검색 및 출력 > 1. 개설 과정 번호\n", this.admin_id);
        System.out.print("개설 과정 번호 > ");
        String open_course_id = sc.nextLine();
 
        List<OpenCourse> list1 = this.ocDAO.search("open_course_id", open_course_id);
        if(list1.size() > 0) {
            System.out.println("--------------------");
            for(OpenCourse oc : list1) {
                System.out.printf("개설 과정 번호 : %s\n", oc.getOpen_course_id());
                System.out.printf("개설 과정명 : %s\n", oc.getCourse_name());
                System.out.printf("개설 과정 기간 : %s ~ %s\n", oc.getOpen_course_start_date(), oc.getOpen_course_end_date());
                System.out.printf("강의실명 : %s\n", oc.getClass_room_name());
            }
            System.out.println("--------------------");
            
            List<OpenSubject> list2 = this.osDAO.search("open_course_id", open_course_id);
            
            if(list2.size() > 0) {
                System.out.println("** 개설 과목 정보 **");
                System.out.println("--------------------");
                System.out.println("개설 과목 번호 / 개설 과목명 / 개설 과목 기간 / 교재명 / 강사명");
                String open_subject_id = null;
                for(OpenSubject os : list2) {
                    open_subject_id = os.getOpen_subject_id();
                    System.out.println(os.print1());
                }
                System.out.println("--------------------");
                System.out.printf("총 %d건\n", list2.size());
                
                List<Student> list3 = this.stDAO.list2("open_course_id", open_course_id, open_subject_id);
                if(list3.size() > 0) {
                    System.out.println(" ** 수강생 정보 **");
                    System.out.println("--------------------");
                    System.out.println("수강생 번호 / 수강생 이름 / 전화번호 / 등록일 / 수료여부 / 날짜");
                    for(Student s : list3) {
                        System.out.println(s.print2());
                    }
                    System.out.println("--------------------");
                    System.out.printf("총 %d건\n", list3.size());
                } else {
                    System.out.println("수강생 정보가 없습니다.");
                }
            } else {
                System.out.println("개설 과목 정보가 없습니다.");
            }
        } else {
            System.out.println("개설 과정 정보가 없습니다.");
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 3. 개설 과정 관리 > 2. 개설 과정 검색 및 출력 > 2. 개설 과정명
    private void m3_s2_s2(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 3. 개설 과정 관리 > 2. 개설 과정 검색 및 출력 > 2. 개설 과정명\n", this.admin_id);
        System.out.print("개설 과정명 > ");
        String course_name = sc.nextLine();
        
        List<OpenCourse> list1 = this.ocDAO.search("course_name", course_name);
        if(list1.size() > 0) {
            System.out.println("--------------------");
            System.out.println("개설 과정 번호 / 과정명 / 개설 과정 기간 / 강의실명");
            for(OpenCourse oc : list1) {
                System.out.println(oc.print1());
            }
            System.out.println("--------------------");
            System.out.printf("총 %d건\n", list1.size());
            
            // 검색 결과가 여러개일 경우
            if(list1.size() > 1) {
                System.out.println("검색 결과가 여러개입니다.");
                this.m3_s2_s1(sc); // 개설 과정 번호 검색 메소드로 넘어감
            // 검색 결과가 하나인 경우
            } else {
                List<OpenSubject> list2 = this.osDAO.search("course_name", course_name);
                if(list2.size() > 0) {
                    System.out.print("** 개설 과목 정보 **");
                    System.out.println("--------------------");
                    System.out.println("개설 과목 번호 / 개설 과목명 / 개설 과목 기간 / 교재명 / 강사명");
                    String open_subject_id = null;
                    for(OpenSubject os : list2) {
                        open_subject_id = os.getOpen_subject_id();
                        System.out.println(os.print1());
                    }
                    System.out.println("--------------------");
                    System.out.printf("총 %d건\n", list2.size());
                    
                    List<Student> list3 = this.stDAO.list2("course_name", course_name, open_subject_id);
                    if(list3.size() > 0) {
                        System.out.print(" ** 수강생 정보 **");
                        System.out.println("--------------------");
                        System.out.println("수강생 번호 / 수강생 이름 / 전화번호 / 등록일 / 수료여부 / 날짜");
                        for(Student s : list3) {
                            System.out.println(s.print2());
                        }
                        System.out.println("--------------------");
                        System.out.printf("총 %d건\n", list3.size());
                    } else {
                        System.out.println("수강생 정보가 없습니다.");
                    }
                } else {
                    System.out.println("개설 과목 정보가 없습니다.");
                }
            }
        } else {
            System.out.println("개설 과정 정보가 없습니다.");
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 3. 개설 과정 관리 > 2. 개설 과정 검색 및 출력 > 3. 개설 과정 전체
    // 출력
    private void m3_s2_s3() {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 3. 개설 과정 관리 > 2. 개설 과정 검색 및 출력 > 3. 개설 과정 전체\n", this.admin_id);
        List<OpenCourse> list1 = this.ocDAO.list2();
        if(list1.size() > 0) {
            System.out.println("--------------------");
            System.out.println("개설 과정 번호 / 과정명 / 개설 과정 기간 / 강의실명 / 개설 과목 등록 갯수 / 수강생 등록 인원");
            for(OpenCourse oc : list1) {
                System.out.println(oc.print2());
            }
            System.out.println("--------------------");
            System.out.printf("총 %s건\n", list1.size());
        } else {
            System.out.println("개설 과정 정보가 없습니다.");
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 3. 개설 과정 관리 > 3. 개설 과정 삭제
    private void m3_s3(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 3. 개설 과정 관리 > 3. 개설 과정 관리 > 3. 개설 과정 삭제\n", this.admin_id);
        List<OpenCourse> list1 = this.ocDAO.list3();
        if(list1.size() > 0) {
            System.out.println("--------------------");
            System.out.println("개설 과정 번호 / 강의실명 / 과정명 / 개설 과정 기간 / 삭제 가능 여부");
            for(OpenCourse oc : list1) {
                System.out.println(oc.print3());
            }
            System.out.println("--------------------");
            System.out.printf("총 %s건\n", list1.size());
            
            System.out.print("개설 과정 번호 > ");
            String open_course_id = sc.nextLine();
            
            // 개설 과정 번호에 따른 개설 과정명, 개설 과정 기간, 강의실명 출력
            List<OpenCourse> list2 = this.ocDAO.search("open_course_id", open_course_id);
            
            if(list2.size() > 0) {
                System.out.println("--------------------");
                for(OpenCourse oc : list2) {
                    System.out.printf("개설 과정 번호 : %s\n", oc.getOpen_course_id());
                    System.out.printf("개설 과정명 : %s\n", oc.getCourse_name());
                    System.out.printf("개설 과정 기간 : %s ~ %s\n", oc.getOpen_course_start_date(), oc.getOpen_course_end_date());
                    System.out.printf("강의실명 : %s\n", oc.getClass_room_name());
                }
                System.out.println("--------------------");
                
                System.out.print("위의 개설 과정을 삭제하시겠습니까? (0/1) > ");
                int selectNum = sc.nextInt();
                sc.nextLine();
                if (selectNum == 1) {
                    // 개설 과정 삭제
                    OpenCourse oc = new OpenCourse(open_course_id, null, null, null, null, null, "");
                    int result = this.ocDAO.remove(oc);
                    if(result > 0) {
                        System.out.println("삭제되었습니다.");                    
                    } else {
                        System.out.println("실패하였습니다.");
                    }
                } 
            }
        } else {
            System.out.println("검색 결과가 없습니다.");
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 4. 개설 과목 관리
    private void m4(Scanner sc) {
        
        boolean run = true;
        
        while (run) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 4. 개설 과목 관리\n", this.admin_id);
            System.out.println("1. 개설 과목 추가  2. 개설 과목 검색 및 출력  3. 개설 과목 삭제");
            System.out.print("선택 > ");
 
            int selectNum = sc.nextInt();
            sc.nextLine();
 
            switch (selectNum) {
 
            case 1:
                this.m4_s1(sc);
                break;
                
            case 2:
                this.m4_s2(sc);
                break;
                
            case 3:
                this.m4_s3(sc);
                break;
                
            case 0:
                run = false;
                break;
 
            default:
                System.out.println("잘못 입력하셨습니다.");
                break;
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 4. 개설 과목 관리 > 1. 개설 과목 추가
    private void m4_s1(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 4. 개설 과목 관리 > 1. 개설 과목 추가\n", this.admin_id);
        
        List<OpenCourse> list1 = this.ocDAO.list4();
        
        if (list1.size() > 0) {
            System.out.println("-------------------------------");
            System.out.println("개설 과정 번호 / 개설 과정명 / 개설 과정 기간 / 강의실명 / 개설 과목명");
            for (OpenCourse oc : list1) {
                System.out.println(oc.print4());
            }
            System.out.println("-------------------------------");
            System.out.printf("총 %s건\n", list1.size());
 
            System.out.print("개설 과정 번호 > ");
            String open_course_id = sc.nextLine();
 
            List<Subject> list2 = this.sDAO.list1();
            
            if (list2.size() > 0) {
                System.out.println("-------------------------------");
                System.out.println("과목 번호 / 과목명");
                for (Subject s : list2) {
                    System.out.println(s.print1());
                }
                
                System.out.println("-------------------------------");
                System.out.printf("총 %s건\n", list2.size());
 
                System.out.print("과목 번호 > ");
                String subject_id = sc.nextLine();
 
                System.out.print("개설 과목 시작일 > ");
                Date subject_start_date = Date.valueOf(sc.nextLine());
                
                System.out.print("개설 과목 종료일 > ");
                Date subject_end_date = Date.valueOf(sc.nextLine());
 
                List<Subjectbook> list3 = this.sbDAO.list1();
                if (list3.size() > 0) {
                    System.out.println("-------------------------------");
                    System.out.println("교재 번호 / 교재명 / 교재 출판사");
                    for (Subjectbook sb : list3) {
                        System.out.println(sb.print1());
                    }
                    
                    System.out.println("-------------------------------");
                    System.out.printf("총 %s건\n", list3.size());
 
                    System.out.print("교재 번호 > ");
                    String subjectbook_id = sc.nextLine();
 
                    List<Instructor> list4 = this.iDAO.list1("all", null);
                    
                    if (list4.size() > 0) {
                        System.out.println("-------------------------------");
                        System.out.println("강사 번호 / 강사 이름 / 강사 휴대폰 번호 / 강사 등록일 / 강의 가능 과목");
                        for (Instructor i : list4) {
                            System.out.println(i.print2());
                        }
                        
                        System.out.println("-------------------------------");
                        System.out.printf("총 %s건\n", list4.size());
 
                        System.out.print("강사 번호 > ");
                        String instructor_id = sc.nextLine();
 
                        System.out.print("등록하시겠습니까? (0/1) > ");
                        int iNum = sc.nextInt();
                        sc.nextLine();
                        
                        if (iNum == 1) {
                            OpenSubject os = new OpenSubject(null, open_course_id, subject_id, 
                                    subject_start_date, subject_end_date, subjectbook_id, instructor_id);
                            int result = this.osDAO.insert(os);
                            
                            if (result > 0) {
                                System.out.println("개설 과목이 추가되었습니다.");
                            } else {
                                System.out.println("실패하였습니다.");
                            }
                        }
                    } else {
                        System.out.println("검색 결과가 없습니다.");
                    }
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
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 4. 개설 과목 관리 > 2. 개설 과목 검색 및 출력
    private void m4_s2(Scanner sc) {
        
        boolean run = true;
        while (run) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 4. 개설 과목 관리 > 2. 개설 과목 검색 및 출력\n", this.admin_id);
            System.out.println("1. 개설 과목 번호  2. 개설 과목명  3. 개설 과목 전체 출력");
            System.out.print("선택 > ");
 
            int selectNum = sc.nextInt();
            sc.nextLine();
 
            switch (selectNum) {
 
            case 1:
                this.m4_s2_s1(sc);
                break;
                
            case 2:
                this.m4_s2_s2(sc);
                break;
                
            case 3:
                this.m4_s2_s3();
                break;
                
            case 0:
                run = false;
                break;
 
            default:
                System.out.println("잘못 입력하셨습니다.");
                break;
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 4. 개설 과목 관리 > 2. 개설 과목 검색 및 출력 > 1. 개설 과목 번호
    private void m4_s2_s1(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 4. 개설 과목 관리 > 2. 개설 과목 검색 및 출력 > 1. 개설 과목 번호\n", this.admin_id);
        
        System.out.print("개설 과목 번호 > ");
        String open_subject_id = sc.nextLine();
 
        List<OpenSubject> list1 = this.osDAO.list2("open_subject_id", open_subject_id);
        
        if(list1.size() > 0) {
            System.out.println("--------------------");
            System.out.println("개설 과목 번호 / 개설 과목명 / 개설 과목 기간 / 교재명 / 강사명 / 개설 과정명 / 개설 과정 기간 / 강의실명");
            for(OpenSubject os : list1) {
                System.out.println(os.print1());
            }
            System.out.println("--------------------");
            System.out.printf("총 %d건\n", list1.size());
        } else {
            System.out.println("검색 결과가 없습니다.");
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 4. 개설 과목 관리 > 2. 개설 과목 검색 및 출력 > 2. 개설 과목명
    private void m4_s2_s2(Scanner sc) {
 
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 4. 개설 과목 관리 > 2. 개설 과목 검색 및 출력 > 2. 개설 과목명\n", this.admin_id);
        
        System.out.print("개설 과목명 > ");
        String subject_name = sc.nextLine();
 
        List<OpenSubject> list1 = this.osDAO.list2("subject_name", subject_name);
        
        if(list1.size() > 0) {
            System.out.println("--------------------");
            System.out.println("개설 과목 번호 / 개설 과목명 / 개설 과목 기간 / 교재명 / 강사명 / 개설 과정명 / 개설 과정 기간 / 강의실명");
            for(OpenSubject os : list1) {
                System.out.println(os.print1());
            }
            System.out.println("--------------------");
            System.out.printf("총 %d건\n", list1.size());
        } else {
            System.out.println("검색 결과가 없습니다.");
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 4. 개설 과목 관리 > 2. 개설 과목 검색 및 출력 > 3. 개설 과목 전체
    // 출력
    private void m4_s2_s3() {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 4. 개설 과목 관리 > 2. 개설 과목 검색 및 출력 > 3. 개설 과목 전체\n", this.admin_id);
        
        List<OpenSubject> list1 = this.osDAO.list2("all", null);
        if(list1.size() > 0) {
            System.out.println("--------------------");
            System.out.println("개설 과목 번호 / 개설 과목명 / 개설 과목 기간 / 교재명 / 강사명 / 개설 과정명 / 개설 과정 기간 / 강의실명");
            for(OpenSubject os : list1) {
                System.out.println(os.print1());
            }
            System.out.println("--------------------");
            System.out.printf("총 %d건\n", list1.size());
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 4. 개설 과목 관리 > 3. 개설 과목 삭제
    private void m4_s3(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 4. 개설 과목 관리 > 3. 개설 과목 삭제\n", this.admin_id);
 
        List<OpenSubject> list1 = this.osDAO.list3();
        
        if (list1.size() > 0) {
            System.out.println("-------------------------------");
            System.out.println("개설 과목 번호 / 개설 과목명 / 개설 과목 기간 / 교재명 / 강사명 / 개설 과정명 / 개설 과정 기간 / 강의실 / 삭제 가능 여부");
 
            for (OpenSubject os : list1) {
                System.out.println(os.print3());
            }
 
            System.out.println("-------------------------------");
            System.out.printf("총 %s건\n", list1.size());
 
            System.out.print("개설 과목 번호 > ");
            String open_subject_id = sc.nextLine();
 
            List<OpenSubject> list2 = this.osDAO.search("open_subject_id", open_subject_id);
            if (list2.size() > 0) {
 
                for (OpenSubject os : list2) {                    
                    System.out.printf("개설 과목 번호 : %s\n", os.getOpen_subject_id());
                    System.out.printf("개설 과목명 : %s\n", os.getSubject_name());
                    System.out.printf("개설 과목 기간 : %s ~ %s\n", os.getSubject_start_date(), os.getSubject_end_date());
                    System.out.printf("교재명 : %s\n", os.getSubjectbook_name());
                    System.out.printf("강사명 : %s\n", os.getInstructor_name());
                }
                
                System.out.print("위의 개설 과목을 삭제하시겠습니까? (0/1) > ");
                int iNum = sc.nextInt();
                sc.nextLine();
                
                if (iNum == 1) {
                    // 개설 과목 삭제
                    
                    OpenSubject os = new OpenSubject(open_subject_id, null, null, null, null, null, null);
                    int result = this.osDAO.remove(os);
                    if (result > 0) {
                        System.out.println("삭제되었습니다.");
                    } else {
                        System.out.println("실패하였습니다.");
                    }
                }
            }    
        } else {
            System.out.println("검색 결과가 없습니다.");
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 5. 수강생 관리
    private void m5(Scanner sc) {
        
        boolean run = true;
        while (run) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 5. 수강생 관리\n", this.admin_id);
            System.out.println("1. 수강생 입력  2. 수강생 검색 및 출력  3. 수강생 삭제  4. 비밀번호 초기화  5. 수강생 과정 관리");
            System.out.print("선택 > ");
 
            int selectNum = sc.nextInt();
            sc.nextLine();
 
            switch (selectNum) {
 
            case 0:
                run = false;
                break;
                
            case 1:
                this.m5_s1(sc);
                break;
                
            case 2:
                this.m5_s2(sc);
                break;
                
            case 3:
                this.m5_s3(sc);
                break;
                
            case 4:
                this.m5_s4(sc);
                break;
                
            case 5:
                this.m5_s5(sc);
                break;
                
            default:
                System.out.println("없는 번호입니다.");
                break;
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 5. 수강생 관리 > 1. 수강생 입력
    private void m5_s1(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 5. 수강생 관리 > 1. 수강생 입력\n", this.admin_id);
 
        System.out.print("수강생 이름 > ");
        String student_name = sc.nextLine();
        
        System.out.print("수강생 전화번호 > ");
        String student_phone = sc.nextLine();
        
        System.out.print("수강생 등록일 > ");
        Date student_regdate = Date.valueOf(sc.nextLine());
        
        System.out.print("수강생 비밀번호 > ");
        String student_pw = sc.nextLine();
 
        System.out.print("등록하시겠습니까? (0/1) > ");
        int check = sc.nextInt();
        sc.nextLine();
        
        if (check == 1) {
 
            Student s1 = new Student(null, student_name, student_phone, student_regdate, student_pw);
            
            List<Student> list = this.stDAO.search("student_idANDstudent_phone", s1);
            
            if (list.size() == 0) {
                int result = this.stDAO.insert(s1);
                
                if(result > 0) {
                    System.out.println("등록되었습니다.");
                    
                    System.out.print("기존 개설 과정에 같이 등록하시겠습니까? (0/1) > ");
                    int check2 = sc.nextInt();
                    sc.nextLine();
                    
                    if (check2 == 1) {
 
                        List<OpenCourse> list2 = this.ocDAO.list2();
                        if (list2.size() > 0) {
                            System.out.println("-------------------------------");
                            System.out.println("개설 과정 번호 / 과정명 / 개설 과정 기간 / 강의실명 / 개설 과목 등록 갯수 / 수강생 등록 인원");
                            for (OpenCourse oc : list2) {
                                System.out.println(oc.print2());
                            }
                            System.out.println("-------------------------------");
                            System.out.printf("총 %d건 \n", list2.size());
                        }
 
                        System.out.println("개설 과정 번호 > ");
                        String open_course_id = sc.nextLine();
 
                        List<OpenCourse> list3 = this.ocDAO.search("open_course_id", open_course_id);
                        if (list3.size() > 0) {
                            for (OpenCourse oc : list3) {
                                System.out.printf("개설 과정 번호 : %s\n", oc.getOpen_course_id());
                                System.out.printf("개설 과정명 : %s\n", oc.getCourse_name());
                                System.out.printf("개설 과정 기간 : %tF ~ %tF\n", oc.getOpen_course_start_date(),
                                        oc.getOpen_course_end_date());
                                System.out.printf("강의실명 : %s\n", oc.getClass_room_name());
                            }
                        }
 
                        System.out.print("등록하시겠습니까? (0/1) > ");
                        int check4 = sc.nextInt();
                        sc.nextLine();
                        
                        if (check4 == 1) {
                            List<Student> list4 = this.stDAO.search("student_idANDstudent_phone", s1);
                            String student_id = null;
                            for (Student s : list4) {
                                student_id = s.getStudent_id();
                            }
                            StudentHistory s = new StudentHistory(student_id, open_course_id);
                            int result2 = this.stDAO.studentHistoryAdd(s);
                            
                            if(result2 > 0) {
                                System.out.println("등록되었습니다.");                                
                            } else {
                                System.out.println("실패하였습니다.");
                            }
                        }
                    }
                } else {
                    System.out.println("실패하였습니다.");
                }
            } else {
                System.out.println("이미 등록된 수강생입니다.");
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 5. 수강생 관리 > 2. 수강생 검색 및 출력
    private void m5_s2(Scanner sc) {
        boolean run = true;
        while (run) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 5. 수강생 관리 > 2. 수강생 검색 및 출력\n", this.admin_id);
            System.out.println("1. 수강생 번호  2. 수강생 이름  3. 수강생 전체 출력");
            System.out.print("선택 > ");
 
            int selectNum = sc.nextInt();
            sc.nextLine();
 
            switch (selectNum) {
 
            case 0:
                run = false;
                break;
                
            case 1:
                this.m5_s2_s1(sc);
                break;
                
            case 2:
                this.m5_s2_s2(sc);
                break;
                
            case 3:
                this.m5_s2_s3();
                break;
                
            default:
                System.out.println("없는 번호입니다.");
                break;
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 5. 수강생 관리 > 2. 수강생 검색 및 출력 > 1. 수강생 번호
    private void m5_s2_s1(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 5. 수강생 관리 > 2. 수강생 검색 및 출력 > 1. 수강생 번호\n", this.admin_id);
    
        System.out.print("수강생 번호 > ");
        String student_id = sc.nextLine();
        
        List<Student> list1 = this.stDAO.search("student_id", new Student(student_id, null, null, null, null));
        
        if (list1.size() > 0) {
        
            for (Student s : list1) {
                System.out.printf("수강생 번호 : %s\n", s.getStudent_id());
                System.out.printf("수강생 이름 : %s\n", s.getStudent_name());
                System.out.printf("수강생 휴대폰번호 : %s\n", s.getStudent_phone());
                System.out.printf("수강생 등록일 : %s\n", s.getStudent_regDate());
                System.out.println();
            }
            
            List<OpenCourse> list2 = this.ocDAO.list5("student_id", new OpenCourse(null, null, student_id, null));
            if(list2.size() > 0) {
                
                for(OpenCourse oc : list2) {
                    System.out.println("--------------------");
                    System.out.printf("개설 과정 번호 : %s\n", oc.getOpen_course_id());
                    System.out.printf("개설 과정명 : %s\n", oc.getCourse_name());
                    System.out.printf("개설 과정 기간 : %s ~ %s\n", oc.getOpen_course_start_date(), oc.getOpen_course_end_date());
                    System.out.printf("강의실명 : %s\n", oc.getClass_room_name());
                    System.out.printf("수료여부 : %s\n", oc.getCompletion_status());
                    System.out.printf("%s 날짜 : %s\n", oc.getCompletion_status(), oc.getDropout_date());
                    System.out.println("--------------------");
                }
                System.out.printf("총 %d건\n", list2.size());
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 5. 수강생 관리 > 2. 수강생 검색 및 출력 > 2. 수강생 이름
    private void m5_s2_s2(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 5. 수강생 관리 > 2. 수강생 검색 및 출력 > 2. 수강생 이름\n", this.admin_id);
    
        System.out.print("수강생 이름 > ");
        String student_name = sc.nextLine();
        
        List<Student> list1 = this.stDAO.search("student_name", new Student(null, student_name, null, null, null));
        
        if(list1.size() > 0) {
 
            if(list1.size() > 1) {
                System.out.println("-------------------------------");
                System.out.println("수강생 번호 / 수강생 이름 / 수강생 휴대폰 번호 / 수강생 등록일");
                for (Student s : list1) {
                    System.out.println(s.print());
                }
                System.out.println("-------------------------------");
                System.out.printf("총 %d명\n", list1.size());
                System.out.println("검색 결과가 여러건 입니다.");
                this.m5_s2_s1(sc);
                
            } else {
                
                String student_id = null;
                for (Student s : list1) {
                    System.out.printf("수강생 번호 : %s\n", s.getStudent_id());
                    System.out.printf("수강생 이름 : %s\n", s.getStudent_name());
                    System.out.printf("수강생 휴대폰번호 : %s\n", s.getStudent_phone());
                    System.out.printf("수강생 등록일 : %s\n", s.getStudent_regDate());
                    student_id = s.getStudent_id();
                    System.out.println();
                }
                
                List<OpenCourse> list2 = this.ocDAO.list5("student_id", new OpenCourse(null, null, student_id, null));
                if(list2.size() > 0) {
                    
                    for(OpenCourse oc : list2) {
                        System.out.println("--------------------");
                        System.out.printf("개설 과정 번호 : %s\n", oc.getOpen_course_id());
                        System.out.printf("개설 과정명 : %s\n", oc.getCourse_name());
                        System.out.printf("개설 과정 기간 : %s ~ %s\n", oc.getOpen_course_start_date(), oc.getOpen_course_end_date());
                        System.out.printf("강의실명 : %s\n", oc.getClass_room_name());
                        System.out.printf("수료여부 : %s\n", oc.getCompletion_status());
                        System.out.printf("%s 날짜 : %s\n", oc.getCompletion_status(), oc.getDropout_date());
                        System.out.println("--------------------");
                        System.out.println();
                    }
                    System.out.printf("총 %d건\n", list2.size());
                }
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 5. 수강생 관리 > 2. 수강생 검색 및 출력 > 3. 수강생 전체 출력
    private void m5_s2_s3() {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 5. 수강생 관리 > 2. 수강생 검색 및 출력 > 3. 수강생 전체 출력\n", this.admin_id);
        
        List<Student> list1 = this.stDAO.list1("all", "");
        if (list1.size() > 0) {
            System.out.println("--------------------");
            System.out.println("수강생 번호 / 수강생 이름 / 수강생 전화번호 / 수강생 등록일 / 수강횟수");
            for (Student s : list1) {
                System.out.println(s.print1());
            }
            System.out.println("--------------------");
            System.out.printf("총 %d명\n", list1.size());
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 5. 수강생 관리 > 3. 수강생 삭제
    private void m5_s3(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 5. 수강생 관리 > 3. 수강생 삭제\n", this.admin_id);
 
        List<Student> list1 = this.stDAO.list1("all", null);
        
        if(list1.size() > 0) {
            System.out.println("-------------------------------");
            System.out.println("수강생 번호 / 수강생 이름 / 수강생 전화번호 / 수강생 등록일 / 삭제 가능 여부");
            for(Student s : list1) {
                System.out.printf("%s / %s / %s / %s / %s\n",
                        s.getStudent_id(), s.getStudent_name(), s.getStudent_phone(), s.getStudent_regDate(),
                        (s.getCount_() > 0 ? 'X' : 'O'));
            }
            System.out.println("-------------------------------");
            System.out.printf("총 %d명\n", list1.size());
            
            System.out.print("수강생 번호 > ");
            String student_id = sc.nextLine();
            
            List<Student> list2 = this.stDAO.search("student_id", new Student(student_id, null, null, null, null));
            
            if(list2.size() > 0) {
                for(Student s : list2) {
                    System.out.printf("수강생 번호 : %s\n", s.getStudent_id());
                    System.out.printf("수강생 이름 : %s\n", s.getStudent_name());
                    System.out.printf("수강생 휴대폰번호 : %s\n", s.getStudent_phone());
                    System.out.printf("수강생 등록일 : %s\n", s.getStudent_regDate());
                }
                
                System.out.print("위의 수강생을 삭제하시겠습니까? (0/1) > ");
                int selectNum = sc.nextInt();
                sc.nextLine();
                
                if(selectNum == 1) {
                    Student s = new Student(student_id, null, null, null, null);
                    int result = this.stDAO.remove(s);
                    
                    if(result > 0) {
                        System.out.println("삭제되었습니다.");
                    } else {
                        System.out.println("실패하였습니다.");
                    }
                }
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 5. 수강생 관리 > 4. 비밀번호 초기화
    private void m5_s4(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 5. 수강생 관리 > 4. 비밀번호 초기화\n", this.admin_id);
                
        List<Student> list1 = this.stDAO.list1("all", "");
        if (list1.size() > 0) {
            System.out.println("--------------------");
            System.out.println("수강생 번호 / 수강생 이름 / 수강생 전화번호 / 수강생 등록일 / 수강횟수");
            for (Student s : list1) {
                System.out.println(s.print1());
            }
            System.out.println("--------------------");
            System.out.printf("총 %d명\n", list1.size());
        }
 
        System.out.print("수강생 번호 > ");
        String student_id = sc.nextLine();
        List<Student> list2 = this.stDAO.search("student_id", new Student(student_id, null, null, null, null));
        
        if (list2.size() > 0) {
            
            for (Student s : list2) {
                System.out.printf("수강생 번호 : %s\n", s.getStudent_id());
                System.out.printf("수강생 이름 : %s\n", s.getStudent_name());
                System.out.printf("수강생 휴대폰번호 : %s\n", s.getStudent_phone());
                System.out.printf("수강생 등록일 : %s\n", s.getStudent_regDate());
                System.out.println();
            }
            
            System.out.print("수강생 비밀번호 > ");
            String student_pw = sc.nextLine();
 
            System.out.print("진행하시겠습니까? (0/1) > ");
            int selectNum = sc.nextInt();
            sc.nextLine();
 
            if (selectNum == 1) {
                Student s = new Student(student_id, student_pw);
                int result = this.stDAO.reset(s);
 
                if (result > 0) {
                    System.out.printf("'%s'의 비밀번호가 초기화되었습니다.\n", student_id);
                } else {
                    System.out.println("실패하였습니다.");
                }
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 5. 수강생 관리 > 5. 수강생 과정 관리
    private void m5_s5(Scanner sc) {
        
        while (true) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 5. 수강생 관리 > 5. 수강생 과정 관리\n", this.admin_id);
            System.out.println("1. 수강생 과정 등록  2. 수강생 과정 취소  3. 수강생 중도 탈락");
            System.out.print("선택 > ");
            int m = sc.nextInt();
            sc.nextLine();
 
            if (m == 0) {
                break;
            }
            
            switch (m) {
            case 1:
                this.m5_s5_s1(sc);
                break;
                
            case 2:
                this.m5_s5_s2(sc);
                break;
                
            case 3:
                this.m5_s5_s3(sc);
                break;
                
            default:
                System.out.println("없는 번호입니다.");
                break;
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 5. 수강생 관리 > 5. 수강생 과정 관리 > 1. 수강생 과정 등록
    private void m5_s5_s1(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 5. 수강생 관리 > 5. 수강생 과정 관리 > 1. 수강생 과정 등록\n", this.admin_id);
 
        System.out.print("수강생 번호 > ");
        String student_id = sc.nextLine();
 
        List<Student> list = this.stDAO.list1("student_id", student_id);
        
        if (list.size() > 0) {
            System.out.println("-------------------------------");
            for (Student s : list) {                
                System.out.printf("수강생 번호 : %s\n", s.getStudent_id());
                System.out.printf("수강생 이름 : %s\n", s.getStudent_name());
                System.out.printf("수강생 휴대폰번호 : %s\n", s.getStudent_phone());
                System.out.printf("수강생 등록일 : %s\n", s.getStudent_regDate());
                System.out.printf("수강 횟수 : %s\n", s.getCount_());
                System.out.println();
            }
            System.out.println("-------------------------------");
            
            List<OpenCourse> list2 = this.ocDAO.list2();
            if (list2.size() > 0) {
                System.out.println("-------------------------------");
                System.out.println("개설 과정 번호 / 과정명 / 개설 과정 기간 / 강의실명 / 개설 과목 등록 갯수 / 수강생 등록 인원");
                for (OpenCourse oc : list2) {
                    System.out.println(oc.print2());
                }
                System.out.println("-------------------------------");
                System.out.printf("총 %d건\n", list.size());
                
                System.out.print("개설 과정 번호 > ");
                String open_course_id = sc.nextLine();
 
                List<OpenCourse> list3 = this.ocDAO.search("open_course_id", open_course_id);
                if (list3.size() > 0) {
                    for (OpenCourse oc : list3) {
                        System.out.printf("개설 과정 번호 : %s\n", open_course_id);
                        System.out.printf("개설 과정명 : %s\n", oc.getCourse_name());
                        System.out.printf("개설 과정 기간 : %tF ~ %tF\n", oc.getOpen_course_start_date(),
                                oc.getOpen_course_end_date());
                        System.out.printf("강의실명 : %s\n", oc.getClass_room_name());
                    }
                    
                    System.out.print("등록하시겠습니까? (0/1) > ");
                    int check = sc.nextInt();
                    sc.nextLine();
                    
                    if (check == 1) {
                        StudentHistory s = new StudentHistory(student_id, open_course_id);
                        int result = stDAO.studentHistoryAdd(s);
                        if(result > 0) {
                            System.out.println("등록되었습니다.");                            
                        } else {
                            System.out.println("실패하였습니다.");
                        }
                    }
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
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 5. 수강생 관리 > 5. 수강생 과정 관리 > 2. 수강생 과정 취소
    private void m5_s5_s2(Scanner sc) {
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 5. 수강생 관리 > 5. 수강생 과정 관리 > 2. 수강생 과정 취소\n", this.admin_id);
 
        System.out.print("수강생 번호 > ");
        String student_id = sc.nextLine();
 
        List<Student> list = this.stDAO.list1("student_id", student_id);
        
        if (list.size() > 0) {
            System.out.println("-------------------------------");
            for (Student s : list) {
                System.out.printf("수강생 번호 : %s\n", s.getStudent_id());
                System.out.printf("수강생 이름 : %s\n", s.getStudent_name());
                System.out.printf("수강생 휴대폰번호 : %s\n", s.getStudent_phone());
                System.out.printf("수강생 등록일 : %s\n", s.getStudent_regDate());
                System.out.printf("수강 횟수 : %s\n", s.getCount_());
                System.out.println();
            }
            System.out.println("-------------------------------");
            
            List<OpenCourse> list2 = this.ocDAO.list5("student_id", new OpenCourse(null, null, student_id, null));
            if (list2.size() > 0) {
                
                System.out.println("-------------------------------");
                for (OpenCourse oc : list2) {
                    System.out.printf("개설 과정 번호 : %s\n", oc.getOpen_course_id());
                    System.out.printf("개설 과정명 : %s\n", oc.getCourse_name());
                    System.out.printf("개설 과정 기간 : %tF ~ %tF\n", oc.getOpen_course_start_date(), oc.getOpen_course_end_date());
                    System.out.printf("강의실명 : %s\n", oc.getClass_room_name());
                    System.out.printf("수료여부 : %s\n", oc.getCompletion_status());
                    System.out.printf("날짜 : %s\n", oc.getDropout_date());
                }
                System.out.println("-------------------------------");
                System.out.printf("총 %d건\n", list2.size());
                
                System.out.print("개설 과정 번호 > ");
                String open_course_id = sc.nextLine();
                List<OpenCourse> list3 = this.ocDAO.search("open_course_id", open_course_id);
                if (list3.size() > 0) {
                    for (OpenCourse oc : list3) {
                        System.out.printf("개설 과정 번호 : %s\n", open_course_id);
                        System.out.printf("개설 과정명 : %s\n", oc.getCourse_name());
                        System.out.printf("개설 과정 기간 : %tF ~ %tF\n", oc.getOpen_course_start_date(),
                                oc.getOpen_course_end_date());
                        System.out.printf("강의실명 : %s\n", oc.getClass_room_name());
                    }
 
                    System.out.print("취소하시겠습니까? (0/1) > ");
                    int check = sc.nextInt();
                    sc.nextLine();
 
                    if (check == 1) {
                        StudentHistory s = new StudentHistory(student_id, open_course_id);
                        int result = stDAO.studentHistoryRemove(s);
                        if(result > 0) {
                            System.out.println("취소되었습니다.");
                        } else {
                            System.out.println("실패하였습니다.");
                        }
                    }
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
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 5. 수강생 관리 > 5. 수강생 과정 관리 > 3. 수강생 중도 탈락
    private void m5_s5_s3(Scanner sc) {
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 5. 수강생 관리 > 5. 수강생 과정 관리 > 3. 수강생 중도 탈락\n", this.admin_id);
 
        System.out.print("수강생 번호 > ");
        String student_id = sc.nextLine();
 
        List<Student> list = this.stDAO.list1("student_id", student_id);
        if (list.size() > 0) {
            System.out.println("-------------------------------");
            for (Student s : list) {
                System.out.printf("수강생 번호 : %s\n", s.getStudent_id());
                System.out.printf("수강생 이름 : %s\n", s.getStudent_name());
                System.out.printf("수강생 휴대폰번호 : %s\n", s.getStudent_phone());
                System.out.printf("수강생 등록일 : %s\n", s.getStudent_regDate());
                System.out.printf("수강 횟수 : %s\n", s.getCount_());
                System.out.println();
            }
            System.out.println("-------------------------------");
            
            List<OpenCourse> list2 = this.ocDAO.list5("student_id", new OpenCourse(null, null, student_id, null));
            if (list2.size() > 0) {
                System.out.println("-------------------------------");
                for (OpenCourse oc : list2) {
                    System.out.printf("개설 과정 번호 : %s\n", oc.getOpen_course_id());
                    System.out.printf("개설 과정명 : %s\n", oc.getCourse_name());
                    System.out.printf("개설 과정 기간 : %tF ~ %tF\n", oc.getOpen_course_start_date(), oc.getOpen_course_end_date());
                    System.out.printf("강의실명 : %s\n", oc.getClass_room_name());
                    System.out.printf("수료여부 : %s\n", oc.getCompletion_status());
                    System.out.printf("날짜 : %s\n", oc.getDropout_date());
                }
                System.out.println("-------------------------------");
                System.out.printf("총 %d건\n", list2.size());
                
                System.out.print("개설 과정 번호 > ");
                String open_course_id = sc.nextLine();
                List<OpenCourse> list3 = this.ocDAO.search("open_course_id", open_course_id);
                if (list3.size() > 0) {
                    for (OpenCourse oc : list3) {
                        System.out.printf("개설 과정 번호 : %s\n", open_course_id);
                        System.out.printf("개설 과정명 : %s\n", oc.getCourse_name());
                        System.out.printf("개설 과정 기간 : %tF ~ %tF \n", oc.getOpen_course_start_date(),
                                oc.getOpen_course_end_date());
                        System.out.printf("강의실명 : %s\n", oc.getClass_room_name());
                    }
 
                    System.out.print("중도 탈락 날짜 > ");
                    Date drop_date = Date.valueOf(sc.nextLine());
                    
                    System.out.print("진행하시겠습니까? (0/1) > ");
                    int check = sc.nextInt();
                    sc.nextLine();
 
                    if (check == 1) {
                        StudentHistory s = new StudentHistory(student_id, open_course_id, drop_date);
                        int result = this.stDAO.processCompleteAdd(s);
                        if(result > 0) {
                            System.out.println("중도 탈락되었습니다.");
                        } else {
                            System.out.println("실패하였습니다.");
                        }
                    }
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
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 6. 성적 조회
    private void m6(Scanner sc) {
 
        boolean run = true;
    
        while (run) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 6. 성적 조회\n", this.admin_id);
            System.out.println("1. 개설 과정 성적 조회  2. 수강생 개인 성적 조회");
            System.out.print("선택 > ");
 
            int selectNum = sc.nextInt();
            sc.nextLine();
 
            switch (selectNum) {
 
            case 0:
                run = false;
                break;
 
            case 1:
                this.m6_s1(sc);
                break;
 
            case 2:
                this.m6_s2(sc);
                break;
 
            default:
                System.out.println("없는 번호입니다.");
                break;
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 6. 성적 조회 > 1. 개설 과정 성적 조회
    private void m6_s1(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 6. 성적 조회 > 1. 개설 과정 성적 조회%n", this.admin_id);
 
        System.out.print("개설 과정 번호 > ");
        String open_course_id = sc.nextLine();
 
        List<OpenCourse> list1 = this.ocDAO.search("open_course_id", open_course_id);
 
        if (list1.size() > 0) {
            System.out.println("---------------------------");
            System.out.println("** 개설 과정 정보 **");
            for (OpenCourse oc : list1) {
                System.out.printf("개설 과정 번호 : %s\n", oc.getOpen_course_id());
                System.out.printf("개설 과정명 : %s\n", oc.getCourse_name());
                System.out.printf("개설 과정 기간 : %tF ~ %tF\n", oc.getOpen_course_start_date(),
                        oc.getOpen_course_end_date());
                System.out.printf("강의실명 : %s\n", oc.getClass_room_name());
            }
            
            List<OpenSubject> list2 = this.osDAO.search("open_course_id", open_course_id);
        
            if(list2.size() > 0) {
                System.out.println("---------------------------");
                System.out.println("개설 과목 번호 / 개설 과목명 / 개설 과목 기간 / 교재명 / 강사명");
                
                for (OpenSubject os : list2) {
                    System.out.println(os.print1());
                }
                
                System.out.println("---------------------------");
                System.out.printf("총 %d건\n", list2.size());
 
                System.out.print("개설 과목 번호 > ");
                String open_subject_id = sc.nextLine();
 
                List<OpenSubject> list3 = this.osDAO.search("open_subject_id", open_subject_id);
 
                if (list3.size() > 0) {
                    for (OpenSubject os : list3) {
                        System.out.printf("개설 과목 번호 : %s%n", os.getOpen_subject_id());
                        System.out.printf("개설 과목명 : %s%n", os.getSubject_name());
                        System.out.printf("개설 과목 기간 : %tF ~ %tF%n", os.getSubject_start_date(), os.getSubject_end_date());
                        System.out.printf("강사명 : %s%n", os.getInstructor_name());
                        System.out.printf("교재명 : %s%n", os.getSubjectbook_name());
                    }
                    
                    List<Exam> list4 = this.eDAO.list1("exam", new Exam(null, open_subject_id, null, null));
 
                    if (list4.size() > 0) {
                        for (Exam e1 : list4) {
                            System.out.println("-----------------------");
                            System.out.printf("시험번호 : %s%n", e1.getExam_id());
                            System.out.printf("출결배점 : %s%n", e1.getAttendance_point());
                            System.out.printf("필기배점 : %s%n", e1.getWrite_point());
                            System.out.printf("실기배점 : %s%n", e1.getSkill_point());
                            System.out.printf("시험날짜 : %tF%n", e1.getExam_date());
                            System.out.printf("시험문제파일 : %s%n", e1.getExam_file());
                            System.out.println("-----------------------");
 
                            List<Exam> list5 = this.eDAO.list4("open_subject_idANDexam_id", new Exam(e1.getExam_id(), open_subject_id, null, null));
                            if (list5.size() > 0) {
                                System.out.println("수강생 번호 / 수강생 이름 / 전화번호 / 출결점수 / 필기점수 / 실기점수 / 총점");
                                for (Exam e2 : list5) {
                                    System.out.println(e2.print4());
                                }
                            } else {
                                System.out.println("수강생 정보가 없습니다.");
                            }
                        }
                    } else {
                        System.out.println("시험 정보가 없습니다.");
                    }
                } else {
                    System.out.println("개설 과목 정보가 없습니다.");
                }
            } else {
                System.out.println("개설 과목 정보가 없습니다.");
            }
        } else {
            System.out.println("개설 과정 정보가 없습니다.");
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 6. 성적 조회 > 2. 수강생 개인 성적 조회
    private void m6_s2(Scanner sc) {
        
        boolean run = true;
        while (run) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 6. 성적 조회 > 2. 수강생 개인 성적 조회\n", this.admin_id);
            System.out.println("1. 수강생 번호  2. 수강생 이름");
            System.out.print("선택 > ");
 
            int selectNum = sc.nextInt();
            sc.nextLine();
 
            switch (selectNum) {
 
            case 0:
                run = false;
                break;
 
            case 1:
                this.m6_s2_s1(sc);
                break;
 
            case 2:
                this.m6_s2_s2(sc);
                break;
 
            default:
                System.out.println("없는 번호입니다.");
                break;
            }
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 6. 성적 조회 > 2. 수강생 개인 성적 조회 > 1. 수강생 번호
    private void m6_s2_s1(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 6. 성적 조회 > 2. 수강생 개인 성적 조회 > 1. 수강생 번호\n", this.admin_id);
        System.out.print("수강생 번호 > ");
        String student_id = sc.nextLine();
        
        List<Student> list1 = this.stDAO.list1("student_id", student_id);
        
        if (list1.size() > 0) {
            System.out.println(" ** 수강생 정보 **");
            for (Student s : list1) {
                System.out.printf("수강생 번호 : %s\n", s.getStudent_id());
                System.out.printf("수강생 이름 : %s\n", s.getStudent_name());
                System.out.printf("수강생 휴대폰번호 : %s\n", s.getStudent_phone());
                System.out.printf("수강생 등록일 : %s\n", s.getStudent_regDate());
                System.out.printf("수강 횟수 : %s\n", s.getCount_());
                System.out.println();
            }
            
            System.out.println(" ** 개설 과정 정보 **");
            
            List<OpenCourse> list2 = this.ocDAO.list1(student_id);
            if (list2.size() > 0) {
                for (OpenCourse oc : list2) {
                    System.out.printf("개설 과정 번호 : %s%n", oc.getOpen_course_id());
                    System.out.printf("개설 과정명 : %s%n", oc.getCourse_name());
                    System.out.printf("개설 과정기간 : %tF ~ %tF %n", oc.getOpen_course_start_date(), oc.getOpen_course_end_date());
                    System.out.printf("강의실명 : %s%n", oc.getClass_room_name());
                    System.out.println();
                }
                
                System.out.println();
                
                List<Exam> list3 = this.eDAO.list5("student_id", new Exam(null, null, student_id, null));
                if (list3.size() > 0) {
                    System.out.println(" ** 개설 과목 성적 정보 **");
                    for (Exam e : list3) {
                        System.out.println(e.print5());
                    }
                } else {
                    System.out.println("개설 과목 정보가 없습니다.");
                }
            } else {
                System.out.println("개설 과정 정보가 없습니다.");
            }
        } else {
            System.out.println("수강생 정보가 없습니다.");
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 6. 성적 조회 > 2. 수강생 개인 성적 조회 > 2. 수강생 이름
    private void m6_s2_s2(Scanner sc) {
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 6. 성적 조회 > 2. 수강생 개인 성적 조회 > 2. 수강생 이름\n", this.admin_id);
        System.out.print("수강생 이름 > ");
        String student_name = sc.nextLine();
        System.out.println();
        
        List<Student> list1 = this.stDAO.search("student_name", new Student(null, student_name, null, null, null));
            
        if (list1.size() > 0) {
 
            if(list1.size() > 1) {
                System.out.println("수강생 번호 / 수강생 이름 / 수강생 휴대폰 번호 / 수강생 등록일");
                System.out.println("----------------------");
                for (Student s : list1) {
                    System.out.println(s.print());
                }
                System.out.println("----------------------");
                System.out.printf("총 %s명%n", list1.size());
                System.out.println();
                System.out.println("검색 결과가 여러건 입니다.");
                this.m6_s2_s1(sc);
            } else {
                String student_id = null;
                for (Student s : list1) {
                    System.out.printf("수강생 번호 : %s\n", s.getStudent_id());
                    System.out.printf("수강생 이름 : %s\n", s.getStudent_name());
                    System.out.printf("수강생 휴대폰번호 : %s\n", s.getStudent_phone());
                    System.out.printf("수강생 등록일 : %s\n", s.getStudent_regDate());
                    System.out.printf("수강 횟수 : %s\n", s.getCount_());
                    System.out.println();
                    student_id = s.getStudent_id();
                }
                
                List<OpenCourse> list2 = this.ocDAO.list1(student_id);
                
                if (list2.size() > 0) {
                    System.out.println(" ** 개설 과정 정보 **");
                    for (OpenCourse oc : list2) {
                        System.out.printf("개설 과정 번호 : %s%n", oc.getOpen_course_id());
                        System.out.printf("개설 과정명 : %s%n", oc.getCourse_name());
                        System.out.printf("개설 과정기간 : %tF ~ %tF %n", oc.getOpen_course_start_date(), oc.getOpen_course_end_date());
                        System.out.printf("강의실명 : %s%n", oc.getClass_room_name());
                        System.out.println();
                    }
                    
                    System.out.println();
                    System.out.println(" ** 개설 과목 성적 정보 **");
                    List<Exam> list3 = this.eDAO.list5("student_id", new Exam(null, null, student_id, null));
                    if (list3.size() > 0) {
                        for (Exam e : list3) {
                            System.out.println(e.print5());
                        }
                    } else {
                        System.out.println("개설 과목 정보가 없습니다.");
                    }
                } else {
                    System.out.println("개설 과정 정보가 없습니다.");
                }
            }
        } else {
            System.out.println("수강생 정보가 없습니다.");
        }
    }
 
    // 성적 처리 시스템 v6.0 (관리자 : admin) > 7. 비밀번호 변경
    private void m7(Scanner sc) {
        System.out.println("---------------------------------------------------------------");
        System.out.printf("성적 처리 시스템 v6.0 (관리자 : %s) > 7. 비밀번호 변경\n", this.admin_id);
        System.out.print("현재 비밀번호 > ");
        String admin_pw = sc.nextLine();
        System.out.print("신규 비밀번호 > ");
        String admin_new_pw = sc.nextLine();
        System.out.print("비밀번호 확인 > ");
        String admin_new_pw2 = sc.nextLine();
        
        System.out.print("비밀번호를 변경하시겠습니까? (0/1) > ");
        int selectNum = sc.nextInt();
        sc.nextLine();
        
        if(admin_new_pw.equals(admin_new_pw2)) {
            if(selectNum == 1) {
                int result = this.aDAO.modify(new Admin(this.admin_id, admin_pw, admin_new_pw));
                
                if(result > 0) {
                    System.out.printf("관리자 '%s'의 비밀번호가 변경되었습니다.\n", this.admin_id);
                } else {
                    System.out.println("실패했습니다.");
                }
            }
        } else {
            System.out.println("비밀번호가 일치하지 않습니다.");
        }
    }
}
