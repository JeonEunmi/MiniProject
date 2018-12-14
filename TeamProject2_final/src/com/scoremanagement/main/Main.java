package com.scoremanagement.main;
 
import java.util.Scanner;
 
import com.scoremanagement.service.ServiceAdmin;
import com.scoremanagement.service.ServiceInstructor;
import com.scoremanagement.service.ServiceStudent;
 
public class Main {
 
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        
        System.out.println("---------------------------------------------------------------");
        System.out.println("성적 처리 시스템 v6.0");
        while(run) {
            System.out.println("---------------------------------------------------------------");
            System.out.println("성적 처리 시스템 v6.0");
            System.out.println("1. 수강생 로그인  2. 강사 로그인  3. 관리자 로그인");
            System.out.print("선택 > ");
            int selectNum = sc.nextInt();
            sc.nextLine();
            
            switch(selectNum) {
            case 1:
                new ServiceStudent().login(sc);
                break;
                
            case 2:
                new ServiceInstructor().login(sc);
                break;
                
            case 3:
                new ServiceAdmin().login(sc);
                break;
                
            case 0:
                System.out.println("---------------------------------------------------------------");
                run = false;
                break;
                
            default:
                break;
            }
        }
        sc.close();
        System.out.println("프로그램이 종료되었습니다.");
    }
}