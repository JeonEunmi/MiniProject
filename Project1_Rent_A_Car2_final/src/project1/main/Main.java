package project1.main;
 
import java.util.Scanner;
import project1.service.SubMain;
 
//���θ޴� � Ŭ����
public class Main {
    public static void main(String[] args) {
        
 
        // ���θ޴� ���� �� �
        // ������ 2018-08-10 -> try-catch, default�� �߰�
        Scanner sc = new Scanner(System.in);
 
        // ����޴� � Ŭ������ ���� ��ü ����
        SubMain sub = new SubMain();
 
        while (true) {
            // 1.����޴� 2.�����޴�
            System.out.println("-----------------------------------");
            System.out.println("            ����ī ver1.0     ");
            System.out.println("-----------------------------------");
            System.out.println("1.����޴� 2.�����޴� 0.����");
            System.out.print("���� > ");
            try {
 
                // �ֿܼ� ���� �ܺ� �Է� �غ�
                int selectNum = sc.nextInt();
                sc.nextLine();
 
                if (selectNum == 0) {
                    // ����ȭ �޼ҵ� ȣ��
                    // ���� 20180813
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
                    System.out.println("�ٽ� �����ϼ��� (1~2), exit : 0 ");
                    break;
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("�߸��� �Է��Դϴ�.");
            }
 
        }
        sc.close();
        System.out.println("����ī ���α׷� ����");
    }
 
}