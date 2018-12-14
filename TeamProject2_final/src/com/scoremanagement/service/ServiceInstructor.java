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
    
    // ���� ó�� �ý��� v6.0 > 2. ���� �α���
    public void login(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.println("���� ó�� �ý��� v6.0 > 2. ���� �α���");
        System.out.print("�̸� > ");
        this.instructor_name = sc.nextLine();
        
        System.out.print("��й�ȣ > ");
        String instructor_pw = sc.nextLine();
        
        Instructor i = new Instructor(this.instructor_name, instructor_pw, null);
        this.instructor_id = this.iDAO.login(i);
        
        if(this.instructor_id != null) {
            System.out.printf("���� '%s'���� �α��εǾ����ϴ�.\n", this.instructor_name);
            this.main(sc);
        } else {
             System.out.println("���̵� �Ǵ� �н����尡 Ʋ�Ƚ��ϴ�.");
        }
    }
    
    // ���� ó�� �ý��� v6.0 (���� : OOO) 
    private void main(Scanner sc) {
        
        boolean run = true;
        while(run) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("���� ó�� �ý��� v6.0 (���� : %s)\n", this.instructor_name);
            System.out.println("1. ���� ������ ��ȸ  2. ���� ����  3. ���� ����  4. ���� ����");
            System.out.print("���� > ");
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
                System.out.printf("���� '%s'���� �α׾ƿ� �Ǿ����ϴ�.\n", this.instructor_name);
                break;
                
            default:
                System.out.println("���� ��ȣ�Դϴ�.");
                break;
            }
        }
    }
    
    // ���� ó�� �ý��� v6.0 (���� : OOO) > 1. ���� ������ ��ȸ
    private void m1(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("���� ó�� �ý��� v6.0 (���� : %s) > 1. ���� ������ ��ȸ\n", this.instructor_name);
        int size = this.schedule_print();
        
        if(size > 0) {
            
            System.out.print("���� ���� ��ȣ > ");
            String open_subject_id = sc.nextLine();
            
            int size2 = this.open_subject_print(open_subject_id);
            
            if(size2 > 0) {
                List<Student> list3 = this.stDAO.list2("open_subject_id", open_subject_id, this.instructor_id);
                if(list3.size() > 0) {
                    System.out.println("** ������ ���� **");
                    System.out.println("��������ȣ / �̸� / ��ȭ��ȣ / ����� / ���Ῡ�� / ��¥");
                    for(Student s : list3) {
                        System.out.println(s.print2());
                    }
                    System.out.println();
                } else {
                    System.out.println("�˻� ����� �����ϴ�.");
                }
            } else {
                System.out.println("�˻� ����� �����ϴ�.");
            }
        } else {
            System.out.println("�˻� ����� �����ϴ�.");
        }
    }
    
    // ���� ó�� �ý��� v6.0 (���� : OOO) > 2. ���� ����
    private void m2(Scanner sc) {
        
        boolean run = true;
        while(run) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("���� ó�� �ý��� v6.0 (���� : %s) > 2. ���� ����\n", this.instructor_name);
            System.out.println("1. ���� �Է�  2. ���� ���  3. ���� ����");
            System.out.print("���� > ");
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
                System.out.println("���� ��ȣ�Դϴ�.");
                break;
            }
        }
    }
    
    // ���� ó�� �ý��� v6.0 (���� : OOO) > 2. ���� ���� > 1. ���� �Է�
    private void m2_s1(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("���� ó�� �ý��� v6.0 (���� : %s) > 2. ���� ���� > 1. ���� �Է�\n", this.instructor_name);
 
        int size1 = this.schedule_print();
        
        if(size1 > 0) {
            System.out.print("���� ���� ��ȣ > ");
            String open_subject_id = sc.nextLine();
 
            int size2 = this.open_subject_print(open_subject_id);
            
            if(size2 > 0) {
                int size3 = this.exam_print(open_subject_id);
                
                int selectNum = 0;
                
                if (size3 > 0) {
                    System.out.println("�̹� ���� ������ ��ϵǾ� �ֽ��ϴ�.");
                    System.out.print("�߰��� �����Ͻðڽ��ϱ�? (0/1) > ");
                    selectNum = sc.nextInt();
                    sc.nextLine();
                }
                
                if((selectNum == 1) || (size3 == 0)) {
                    System.out.print("��� ���� > ");
                    int attendance_point = sc.nextInt();
                    sc.nextLine();
                    
                    System.out.print("�ʱ� ���� > ");
                    int write_point = sc.nextInt();
                    sc.nextLine();
                    
                    System.out.print("�Ǳ� ���� > ");
                    int skill_point = sc.nextInt();
                    sc.nextLine();
                    
                    System.out.print("���� ��¥ > ");
                    Date exam_date = Date.valueOf(sc.nextLine());
                    
                    System.out.print("���� ���� > ");
                    String exam_file = sc.nextLine();
                    
                    Exam e = new Exam("", open_subject_id, attendance_point, write_point, skill_point, exam_date,
                            exam_file);
                    int result = this.eDAO.insertPoint(e);
                    
                    if (result > 0) {
                        System.out.println("��ϵǾ����ϴ�.");
                    } else {
                        System.out.println("�����߽��ϴ�.");
                    }
                }
            }
        }
    }
    
    // ���� ó�� �ý��� v6.0 (���� : OOO) > 2. ���� ���� > 2. ���� ���
    private void m2_s2(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("���� ó�� �ý��� v6.0 (���� : %s) > 2. ���� ���� > 2. ���� ���\n", this.instructor_name);
        int size1 = this.schedule_print();
        
        if(size1 > 0) {
            System.out.print("���� ���� ��ȣ > ");
            String open_subject_id = sc.nextLine();
            
            int size2 = this.open_subject_print(open_subject_id);
            
            if(size2 > 0) {
                this.exam_print(open_subject_id);
            }
        }
    }
    
    // ���� ó�� �ý��� v6.0 (���� : OOO) > 2. ���� ���� > 3. ���� ����
    private void m2_s3(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("���� ó�� �ý��� v6.0 (���� : %s) > 2. ���� ���� > 3. ���� ����\n", this.instructor_name);
 
        int size1 = this.schedule_print();
        
        if(size1 > 0) {
            System.out.print("���� ���� ��ȣ > ");
            String open_subject_id = sc.nextLine();
            
            int size2 = this.open_subject_print(open_subject_id);
            if(size2 > 0) {
                int size3 = this.exam_print(open_subject_id);
                
                if(size3 > 0) {
                    System.out.print("���� ��ȣ > ");
                    String exam_id = sc.nextLine();
                    System.out.print("����(�������� ����)�� �����Ͻðڽ��ϱ�? (0/1) > ");
                    int selectNum = sc.nextInt();
                    sc.nextLine();
                    
                    if(selectNum == 1) {
                        Exam e = new Exam(exam_id, null, null, this.instructor_id);
                        int result = this.eDAO.removePoint(e);
                        
                        if(result > 0) {
                            System.out.println("���� ������ �����Ǿ����ϴ�.");
                        } else {
                            System.out.println("���� ���� ������ �����߽��ϴ�.");
                        }
                    }
                }
            }
        }
    }
    
    // ���� ó�� �ý��� v6.0 (���� : OOO) > 3. ���� ����
    private void m3(Scanner sc) {
        
        boolean run = true;
        while(run) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("���� ó�� �ý��� v6.0 (���� : %s) > 3. ���� ����\n", this.instructor_name);
            System.out.println("1. ���� �Է�  2. ���� ���  3. ���� ����");
            System.out.print("���� > ");
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
                System.out.println("���� ��ȣ�Դϴ�.");
                break;
            }
        }
    }
    
    // ���� ó�� �ý��� v6.0 (���� : OOO) > 3. ���� ���� > 1. ���� �Է�
    private void m3_s1(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("���� ó�� �ý��� v6.0 (���� : %s) > 3. ���� ���� > 1. ���� �Է�\n", this.instructor_name);
 
        boolean run = true;
        int size1 = this.schedule_print();
        
        if(size1 > 0) {
            System.out.print("���� ���� ��ȣ > ");
            String open_subject_id = sc.nextLine();
            
            int size2 = this.open_subject_print(open_subject_id);
            
            if(size2 > 0) {
                int size3 = this.exam_list_print(open_subject_id);
                
                if(size3 > 0) {
                    System.out.print("���� ��ȣ > ");
                    String exam_id = sc.nextLine();
                    int size4 = this.exam_detail_print(open_subject_id, exam_id);
                    
                    if(size4 > 0) {
                        
                        this.student_print(open_subject_id, this.instructor_id, exam_id);
                        while(run) {
                            System.out.print("������ ��ȣ > ");
                            String student_id = sc.nextLine();
                            if(student_id.equals("0")) {
                                break;
                            }
                            
                            List<Student> list = this.stDAO.search("student_id", 
                                    new Student(student_id, null, null, null, null));
                            
                            for(Student s : list) {                                
                                System.out.printf("������ ��ȣ : %s\n", s.getStudent_id());
                                System.out.printf("������ �̸� : %s\n", s.getStudent_name());
                                System.out.printf("������ �޴�����ȣ : %s\n", s.getStudent_phone());
                                System.out.printf("������ ����� : %s\n", s.getStudent_regDate());
                            }
                            
                            System.out.print("��� ���� > ");
                            int attendance_score = sc.nextInt();
                            sc.nextLine();
                            System.out.print("�ʱ� ���� > ");
                            int write_score = sc.nextInt();
                            sc.nextLine();
                            System.out.print("�Ǳ� ���� > ");
                            int skill_score = sc.nextInt();
                            sc.nextLine();
                            
                            System.out.print("����Ͻðڽ��ϱ�?(0/1) > ");
                            int selectNum = sc.nextInt();
                            sc.nextLine();
                            
                            if(selectNum == 1) {
                                Exam e = new Exam(exam_id, student_id, attendance_score, write_score, skill_score);
                                int result = this.eDAO.insertScore(e);
                                
                                if(result > 0) {
                                    System.out.println("��ϵǾ����ϴ�.");
                                } else {
                                    System.out.println("�����Ͽ����ϴ�.");
                                }
                            }
                        }
                    }
                }
            } else {
                System.out.println("���� ���� ������ �����ϴ�.");
            }
        }
    }
    
    // ���� ó�� �ý��� v6.0 (���� : OOO) > 3. ���� ���� > 2. ���� ���
    private void m3_s2(Scanner sc) {
        System.out.println("---------------------------------------------------------------");
        System.out.printf("���� ó�� �ý��� v6.0 (���� : %s) > 3. ���� ���� > 2. ���� ���\n", this.instructor_name);
 
        int size1 = this.schedule_print();
        
        if(size1 > 0) {
            System.out.print("���� ���� ��ȣ > ");
            String open_subject_id = sc.nextLine();
            
            int size2 = this.open_subject_print(open_subject_id);
            
            if(size2 > 0) {
                int size3 = this.exam_list_print(open_subject_id);
                
                if(size3 > 0) {
                    System.out.print("���� ��ȣ > ");
                    String exam_id = sc.nextLine();
                    int size4 = this.exam_detail_print(open_subject_id, exam_id);
                    if(size4 > 0) {
                        this.student_print(open_subject_id, this.instructor_id, exam_id);
                    }
                }
            }
        }
    }
    
    // ���� ó�� �ý��� v6.0 (���� : OOO) > 3. ���� ���� > 3. ���� ����
    private void m3_s3(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("���� ó�� �ý��� v6.0 (���� : %s) > 3. ���� ���� > 3. ���� ����\n", this.instructor_name);
 
        int size1 = this.schedule_print();
        
        if(size1 > 0) {
            System.out.print("���� ���� ��ȣ > ");
            String open_subject_id = sc.nextLine();
            
            int size2 = this.open_subject_print(open_subject_id);
            
            if(size2 > 0) {
                int size3 = this.exam_list_print(open_subject_id);
                
                if(size3 > 0) {
                    System.out.print("���� ��ȣ > ");
                    String exam_id = sc.nextLine();
                    int size4 = this.exam_detail_print(open_subject_id, exam_id);
                    
                    if(size4 > 0) {
                        this.student_print(open_subject_id, this.instructor_id, exam_id);
                        System.out.print("������ ��ȣ > ");
                        String student_id = sc.nextLine();
                        
                        List<Student> list = this.stDAO.search("student_id", 
                                new Student(student_id, null, null, null, null));
                        for(Student s : list) {                                
                            System.out.printf("������ ��ȣ : %s\n", s.getStudent_id());
                            System.out.printf("������ �̸� : %s\n", s.getStudent_name());
                            System.out.printf("������ �޴�����ȣ : %s\n", s.getStudent_phone());
                            System.out.printf("������ ����� : %s\n", s.getStudent_regDate());
                        }
                        
                        System.out.print("���� ������ �����Ͻðڽ��ϱ�? (0/1) > ");
                        int selectNum = sc.nextInt();
                        sc.nextLine();
                        
                        if(selectNum == 1) {
                            Exam e = new Exam(null, null, student_id, null);
                            int result = this.eDAO.removeScore(e);
                            
                            if(result > 0) {
                                System.out.println("�����Ǿ����ϴ�.");
                            } else {
                                System.out.println("�����Ͽ����ϴ�.");
                            }
                        }
                    }
                }
            }
        }
    }
    
    // ���� ó�� �ý��� v6.0 (���� : OOO) > 4. ���� ����
    private void m4(Scanner sc) {
        
        boolean run = true;
        while(run) {
            System.out.println("---------------------------------------------------------------");
            System.out.printf("���� ó�� �ý��� v6.0 (���� : %s) > 4. ���� ����\n", this.instructor_name);
            System.out.println("1. ���� ���� ��ȸ  2. ��� ��ȣ ����");
            System.out.print("���� > ");
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
                System.out.println("���� ��ȣ�Դϴ�.");
                break;
            }
        }
    }
    
    // ���� ó�� �ý��� v6.0 (���� : OOO) > 4. ���� ���� > 1. ���� ���� ��ȸ
    private void m4_s1() {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("���� ó�� �ý��� v6.0 (���� : %s) > 4. ���� ���� > 1. ���� ���� ��ȸ\n", this.instructor_name);
        List<Instructor> list = this.iDAO.search("instructor_id", this.instructor_id);
        for(Instructor i : list) {
            System.out.printf("�����ȣ : %s\n", i.getInstructor_id());
            System.out.printf("�̸� : %s\n", i.getInstructor_name());
            System.out.printf("�޴�����ȣ : %s\n", i.getInstructor_phone());
            System.out.printf("����� : %s\n", i.getInstructor_regDate());
            System.out.println();
        }
        
        List<InstructorPossible> list2 = this.ipDAO.list1(this.instructor_id);
        if(list2.size() > 0) {
            System.out.println("** ���� ���� ���� **");
            System.out.println("-------------------------------");
            System.out.println("���� ��ȣ / �����");
            for(InstructorPossible ip : list2) {
                System.out.printf("%s - %s\n", ip.getSubject_id(), ip.getSubject_name());
            }
            System.out.println("-------------------------------");
            System.out.printf("�� %d��\n", list2.size());
        }
    }
    
    // ���� ó�� �ý��� v6.0 (���� : OOO) > 4. ���� ���� > 2. ��� ��ȣ ����
    private void m4_s2(Scanner sc) {
        
        System.out.println("---------------------------------------------------------------");
        System.out.printf("���� ó�� �ý��� v6.0 (���� : %s) > 4. ���� ���� > 2. ��� ��ȣ ����\n", this.instructor_name);
        System.out.print("���� ��й�ȣ > ");
        String instructor_pw = sc.nextLine();
        System.out.print("�ű� ��й�ȣ > ");
        String instructor_new_pw = sc.nextLine();
        System.out.print("��й�ȣ Ȯ�� > ");
        String intructor_new_pw2 = sc.nextLine();
        
        System.out.print("��й�ȣ�� �����Ͻðڽ��ϱ�? (0/1) > ");
        int selectNum = sc.nextInt();
        sc.nextLine();
        
        if(instructor_new_pw.equals(intructor_new_pw2)) {
            if(selectNum == 1) {
                int result = this.iDAO.modify(new Instructor(this.instructor_name, instructor_pw, instructor_new_pw));
                
                if(result > 0) {
                    System.out.printf("���� '%s'�� ��й�ȣ�� ����Ǿ����ϴ�.\n", this.instructor_name);
                } else {
                    System.out.println("�����߽��ϴ�.");
                }
            }
        } else {
            System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
        }
    }
    
    private int schedule_print() {
        
        System.out.println("** ���� ���� **");
        System.out.println("���� ��¥ : " + LocalDate.now().format(this.formatter));
    
        List<OpenSubject> list = this.osDAO.list4(this.instructor_id);
        if(list.size() > 0) {
            System.out.println("���������ȣ / ����� / ���� �Ⱓ / ���");
            for(OpenSubject os : list) {
                System.out.printf("%s / %s / %s ~ %s / %s\n", 
                        os.getOpen_subject_id(), os.getSubject_name(), 
                        os.getSubject_start_date(), os.getSubject_end_date(), os.getInstructor_status());
            }
            System.out.println();
        } else {
            System.out.println("���� �������� �����ϴ�.");
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
                System.out.printf("���� ���� ��ȣ : %s\n", open_subject_id);
                System.out.printf("���� ������ : %s\n", os.getCourse_name());
                System.out.printf("���� ���� �Ⱓ : %s ~ %s\n", os.getOpen_course_start_date(), os.getOpen_course_end_date());
                System.out.printf("���ǽ� : %s\n", os.getClass_room_name());
                System.out.printf("���� ����� : %s\n", os.getSubject_name());
                System.out.printf("���� ���� �Ⱓ : %s ~ %s\n", os.getSubject_start_date(), os.getSubject_end_date());
                System.out.printf("����� : %s\n", os.getSubjectbook_name());    
                System.out.printf("������ ��� �ο� : %d��\n", os.getStudent_count());
                System.out.println();
            }
        } else {
            System.out.println("���� ���� ������ �����ϴ�.");
        }
        return size;
    }
    
    private int exam_print(String open_subject_id) {
        int size = 0;
 
        List<Exam> list = this.eDAO.list1("open_subject_id", new Exam(null, open_subject_id, null, this.instructor_id));
        size = list.size();
        if(size > 0) {
            System.out.println("-------------------------------");    
            System.out.println("���� ��ȣ / ��� ���� / �ʱ� ���� / �Ǳ� ���� / ���� ��¥ / ���� ����");
            for(Exam e : list) {
                System.out.println(e.print1());
            }
            System.out.println("-------------------------------");
            System.out.printf("�� %d��\n", size);
        } else {
            System.out.println("��ϵ� ���� ������ �����ϴ�.");
        }
 
        return size;
    }
    
    private int exam_list_print(String open_subject_id) {
        int size = 0;
 
        List<Exam> list = this.eDAO.list3("open_subject_idANDinstructor_id", new Exam(null, open_subject_id, null, this.instructor_id));
        size = list.size();
        if(size > 0) {
            System.out.println("-------------------------------");    
            System.out.println("���� ��ȣ / ��� ���� / �ʱ� ���� / �Ǳ� ���� / ���� ��¥ / ���� ���� / ���� ��� �ο��� / ���� �Է� ����");
            for(Exam e : list) {
                System.out.println(e.print3());
            }
            System.out.println("-------------------------------");
            System.out.printf("�� %d��\n", size);
        } else {
            System.out.println("���� ������ �����ϴ�.");
        }
 
        return size;
    }
    
    private int exam_detail_print(String open_subject_id, String exam_id) {
        int size = 0;
 
        List<Exam> list = eDAO.list3("open_subject_idANDinstructor_idANDexam_id", new Exam(exam_id, open_subject_id, null, this.instructor_id));
        size = list.size();
        if(size > 0) {
            System.out.println("** ���� ���� **");
            for(Exam e : list) {
                System.out.printf("���� ��ȣ : %s\n", e.getExam_id());
                System.out.printf("��� ���� : %d\n", e.getAttendance_point());
                System.out.printf("�ʱ� ���� : %d\n", e.getWrite_point());
                System.out.printf("�Ǳ� ���� : %d\n", e.getSkill_point());
                System.out.printf("���� ��¥ : %s\n", e.getExam_date());
                System.out.printf("���� ���� : %s\n", e.getExam_file());
                System.out.printf("���� ��� �ο��� : %d��\n", e.getClass_count());
                System.out.printf("���� �Է� ���� : %s\n", e.getScore_status());                
                System.out.println();
            }
        } else {
            System.out.println("���� ������ �����ϴ�.");
        }
 
        return size;
    }
    
    private int student_print(String open_subject_id, String instructor_id, String exam_id) {
        int size = 0;
 
        List<Exam> list = this.eDAO.list4("open_subject_idANDinstructor_idANDexam_id", new Exam(exam_id, open_subject_id, null, this.instructor_id));
        size = list.size();
        
        if(list.size() > 0) {
            System.out.println("-------------------------------");
            System.out.println("��������ȣ / �̸� / �޴�����ȣ / ����� / ���Ῡ�� / ��¥ / ������� / �ʱ����� / �Ǳ����� / ����");
            for(Exam e : list) {
                System.out.println(e.print4());
            }
            System.out.println("-------------------------------");
            System.out.printf("�� %d��\n", size);
        } else {
            System.out.println("������ ������ �����ϴ�.");
        }
        return size;
    }
}