package project1.main;
 
import java.util.Scanner;
import project1.service.SubMain;
 
//메인메뉴 운영 클래스
public class Main {
    public static void main(String[] args) {
        
 
        // 메인메뉴 구성 및 운영
        // 김유신 2018-08-10 -> try-catch, default문 추가
        Scanner sc = new Scanner(System.in);
 
        // 서브메뉴 운영 클래스에 대한 객체 생성
        SubMain sub = new SubMain();
 
        while (true) {
            // 1.예약메뉴 2.관리메뉴
            System.out.println("-----------------------------------");
            System.out.println("            렌터카 ver1.0     ");
            System.out.println("-----------------------------------");
            System.out.println("1.예약메뉴 2.관리메뉴 0.종료");
            System.out.print("선택 > ");
            try {
 
                // 콘솔에 의한 외부 입력 준비
                int selectNum = sc.nextInt();
                sc.nextLine();
 
                if (selectNum == 0) {
                    // 직렬화 메소드 호출
                    // 종석 20180813
                    sub.daoSerializables();
                    break;
                }
                switch (selectNum) {
 
                case 1:
                    sub.mainMenu1(sc);
                    break;
                case 2:
                    sub.mainMenu2(sc);
                    break;
                default :
                    System.out.println("다시 선택하세요 (1~2), exit : 0 ");
                    break;
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("잘못된 입력입니다.");
            }
 
        }
        sc.close();
        System.out.println("렌터카 프로그램 종료");
    }
 
}