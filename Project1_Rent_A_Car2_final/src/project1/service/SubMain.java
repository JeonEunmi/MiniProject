package project1.service;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
 
import project1.domain.Car;
import project1.domain.MonthlySale;
import project1.domain.Reservation;
import project1.domain.YearlySale;
import project1.persistance.RentCarDAO;
 
//���� �޴��� �׼� Ŭ����
public class SubMain {
    // RentCarDAO�� Ŭ������ ��ü ���� �� �ʵ� ����
    private RentCarDAO dao = new RentCarDAO();
 
    //����ȭ �޼ҵ� ����(���� �޼ҵ忡�� ȣ���� ����)
 
    public void daoSerializables() {
        dao.daoSerializable();
    }
    
    
    // ���� �޴� � �޼ҵ�
 
    // ����ī ver1.0 > ����޴�
    // 1.���� 2.������� 3.�ݳ� 0.�����ܰ�
    // ������ 2018-08-08
    // ������ 2018-08-10 -> try-catch, default�� �߰�
    // ������ 2018-08-10 -> try-catch, default�� �߰�
    // ���� �۾� �� �ҽ� ������ �ȵǼ� ���̰� �ȵ��� �˰� �� �۾���;;
    public void mainMenu1(Scanner sc) {
        // �ֿܼ� ���� �ܺ� �Է� �غ�
 
        while (true) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("-----------------------------------");
            System.out.println("      ����ī ver1.0 > ����޴�     ");
            System.out.println("-----------------------------------");
            System.out.println("1.���� 2.������� 3.�ݳ� 0.�����ܰ�");
            System.out.print("���� > ");
            try {
                int selectNum = sc.nextInt();
                sc.nextLine();
 
                if (selectNum == 0) break;
 
                switch (selectNum) {
 
                case 1:
                    this.mainMenu1_sub1(sc);
                    break;
                case 2:
                    this.mainMenu1_sub2(sc);
                    break;
                case 3:
                    this.mainMenu1_sub3(sc);
                    break;
                default:
                    System.out.println("�ٽ� �����ϼ��� (1~3), exit : 0 ");
                    break;
 
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("�͢� �߸��� �޴� �����Դϴ�.");
            }
 
        }
 
    }
 
    // ����ī ver1.0 > ����޴� > ����
    // ������ 2018-08-09 �׽�Ʈ�Ϸ� ������������
    private void mainMenu1_sub1(Scanner sc) {
        System.out.println();
        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println("  ����ī ver1.0 > ����޴� > ����");
        System.out.println("-----------------------------------");
        try {
            // �뿩������, �뿩�ݳ��� �ܺ��Է�
            String startDate = "";
            String endDate = "";
 
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
 
            while (true) {
                sf.setLenient(false);
                // �뿩������, �뿩�ݳ��� �ܺ��Է�
                System.out.print("�뿩������(yyyy-MM-dd) > ");
                startDate = sc.nextLine();
                try {
                    sf.parse(startDate);
                    break;
                } catch (Exception e1) {
                    // Ʋ�� ��¥�� ��쿹�ܹ߻�
                    System.out.println("����¥ ���Ŀ� �°� �Է��ϼ���(yyyy-MM-dd) \n");
                }
            }
 
            while (true) {
                System.out.print("�뿩�ݳ���(yyyy-mm-dd) > ");
                endDate = sc.nextLine();
 
                try {
                    sf.parse(endDate);
                    break;
                } catch (Exception e2) {
                    System.out.println("����¥ ���Ŀ� �°� �Է��ϼ���. (yyyy-MM-dd) \n");
                }
            }
            System.out.println();
            
            // ���డ������ ���
            this.printBookAble(startDate, endDate);
            System.out.println();
 
            String regExp1 = "(CAR)\\d{3}";
 
            while (true) {
                // ������ȣ���� �� �������� �ܺ��Է�
                System.out.print("������ȣ ����(CARxxx) > ");
                String carId = sc.nextLine();
 
                if (Pattern.matches(regExp1, carId) == false) {
                    System.out.println("������ȣ ���Ŀ� �°� �Է��ϼ���. (CARxxx) \n");
                } else {
                    int rentFee = this.dao.carSearch(carId).getRentFee();
                    String carName = this.dao.carSearch(carId).getCarName();
                    int totalFee = dao.rentMoney(rentFee, startDate, endDate);
 
                    if (carName == null) {
                        System.out.println
                        ("������ �߸� �����ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
                    } else if (carName != null) {
                        System.out.printf
                        ("�����Ͻ� [%s]�� �ݾ��� %,d�� �Դϴ�. ���� �Ͻðڽ��ϱ�? (Y/N)%n"
                                , carName, totalFee);
                        System.out.print("���� > ");
                        String select = sc.nextLine();
 
                        // �����ȣ �ڵ�����
                        String reservationId = this.dao.autoReservationId();
 
                        String name = "";
                        String phone = "";
                        String licenseId = "";
 
                        // ���� ����
                        String reservationStatus = "����Ȯ��";
 
                        // ������ �޾ƿ���
                        String payDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
 
                        System.out.println();
                        if (select.equalsIgnoreCase("Y")) {
                            System.out.println("������ �Է����ּ���.");
                            System.out.print("�����ڸ� > ");
                            name = sc.nextLine();
 
                            while (true) {
 
                                String regExp2 = "(010)-\\d{3,4}-\\d{4}";
 
                                System.out.print("����ó > ");
                                phone = sc.nextLine();
 
                                if (Pattern.matches(regExp2, phone) == false) {
                                    System.out.println("����ó ������ �����ּ���. (010-xxxx-xxxx)");
                                } else {
 
                                    while (true) {
 
                                        String regExp3 = "[��-����-�R]{2}\\d{2}-\\d{4}";
                                        System.out.print("��������ȣ (������XX-XXXX) > ");
                                        licenseId = sc.nextLine();
                                        if (Pattern.matches(regExp3, licenseId) == false) {
                                            System.out.println
                                            ("��������ȣ ������ �����ּ���. (������XX-XXXX)");
                                        } else {
 
                                            Reservation r = new Reservation(reservationId, name
                                                    , phone, licenseId,    carId, startDate, endDate
                                                    , payDate, totalFee, reservationStatus);
 
                                            // List�÷��� ����ҿ� ����
                                            this.dao.reservationAdd(r);
 
                                            // ���� ���� Ȯ�� ��,�޽��� ���
                                            System.out.println();
                                            System.out.println
                                            ("�Է��� �Ϸ�Ǿ����ϴ�. ������ �����Ͻðڽ��ϱ�? (Y/N)");
 
                                            System.out.print("���� > ");
 
                                            String select2 = sc.nextLine();
 
                                            if (select2.equalsIgnoreCase("Y")) {
 
                                                this.dao.sumPay();
 
                                                System.out.println();
                                                System.out.println("������ �Ϸ�Ǿ����ϴ�.");
 
                                                // ���� ���� ���
                                                this.printResult(reservationId);
 
                                            } else if (select2.equalsIgnoreCase("N")) {
                                                System.out.println("������ ��ҵǾ����ϴ�.");
                                            }
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
 
                        } else if (select.equalsIgnoreCase("N")) {
                            System.out.println("������ ��ҵǾ����ϴ�.");
                        } else {
                            System.out.println("�߸� �Է��ϼ̽��ϴ�.");
                            break;
                        }
 
                    }
                    break;
                }
            }
 
        } catch (Exception e) {
            System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �������ּ���.");
 
        }
    }
 
    // ����ī ver1.0 > ����޴� > �������
    // �Լ��� 2018-08-08
    // ������ 2018-08-09 �׽�Ʈ�Ϸ� ������������
    // ������ 2018-08-10 �׽�Ʈ�Ϸ� �������׾���
    private void mainMenu1_sub2(Scanner sc) {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println("����ī ver1.0 > ����޴� > �������");
        System.out.println("-----------------------------------");
        
        // �����ȣ �ܺ��Է�
        while (true) {
            System.out.println("�����ȣ�� �Է����ּ���.");
            System.out.print("�Է�>");
            String regExp4 = "(R)\\d{4}";
            String reservationId = sc.nextLine();
 
            if (Pattern.matches(regExp4, reservationId) == false) {
                System.out.println("�߸��� �����ȣ�Դϴ�. �ٽ��Է����ּ���. (Rxxxx)");
                System.out.println();
            } else {
                System.out.println();
                
                // �����������
                List<Reservation> r = this.printResult(reservationId);
                System.out.println();
 
                // ��� ���� Ȯ��
                // ->Yes :������� �޼ҵ� ȣ��
                // ����޽��� ���
                //���� ��ҽ� �����ݾ� 0������ ������ ���� �޼ҵ� ȣ��
                System.out.println("������ ����Ͻðڽ��ϱ�? (Y/N)");
                System.out.print("���� > ");
                String select = sc.nextLine();
                if (select.equalsIgnoreCase("Y")) {
                    this.dao.reservationCancel(r);
                    System.out.println("��Ұ� �Ϸ� �Ǿ����ϴ�.");
                } else {
                    System.out.println("������Ұ� ��ҵǾ����ϴ�.");
                }
                break;
            }
        }
    }
 
    // ����ī ver1.0 > ����޴� > �ݳ�
    // �Լ��� 2018-08-08
    // ������20180809 �׽�Ʈ�Ϸ� - ������������
    // ������20180810 �׽�Ʈ�Ϸ�
    private void mainMenu1_sub3(Scanner sc) {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("-------------------------------");
        System.out.println("����ī ver1.0 > ����޴� > �ݳ�");
        System.out.println("-------------------------------");
        // �����ȣ �ܺ��Է�
        while (true) {
            System.out.println("�����ȣ�� �Է����ּ���.");
            System.out.print("�Է�>");
            String regExp4 = "(R)\\d{4}";
            String reservationId = sc.nextLine();
 
            if (Pattern.matches(regExp4, reservationId) == false) {
                System.out.println("�߸��� �����ȣ�Դϴ�. �ٽ��Է����ּ���. (Rxxxx)");
                System.out.println();
            } else {
 
                System.out.println();
                // �����������
                List<Reservation> r = this.printResult(reservationId);
                System.out.println();
 
                // �ݳ� ���� Ȯ��
                // ->Yes :������� �޼ҵ� ȣ��
                // ����޽��� ���
                System.out.println("������ �ݳ��Ͻðڽ��ϱ�? (Y/N) ");
                System.out.print("���� > ");
                String select = sc.nextLine();
                if (select.equalsIgnoreCase("Y")) {
                    this.dao.returnCar(r);
                    System.out.println("�ݳ��� �Ϸ� �Ǿ����ϴ�.");
                } else {
                    System.out.println("�����ݳ��� ��ҵǾ����ϴ�.");
                }
                break;
            }
        }
    }
 
    /********************************************************************/
 
    // ����ī ver1.0 > �����޴�
    // 1.������� 2.�������� 3.������� 0.�����ܰ�
    public void mainMenu2(Scanner sc) {
        // �ֿܼ� ���� �ܺ� �Է� �غ�
        // ������ 2018-08-10 try-catch�� �߰�
        while (true) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("-------------------------------------------");
            System.out.println("         ����ī ver1.0 > �����޴�          ");
            System.out.println("-------------------------------------------");
            System.out.println("1.������� 2.�������� 3.������� 0.�����ܰ�");
            System.out.print("���� > ");
            try {
                int selectNum = sc.nextInt();
                sc.nextLine();
 
                if (selectNum == 0)
                    break;
 
                switch (selectNum) {
 
                case 1:
                    this.dao.autoChangeStatus();
                    this.mainMenu2_sub1(sc);
                    break;
                case 2:
                    this.dao.autoChangeStatus();
                    this.mainMenu2_sub2();
                    break;
                case 3:
                    this.dao.autoChangeStatus();
                    this.mainMenu2_sub3(sc);
                    break;
 
                default:
                    System.out.println("�ٽ� �����ϼ��� (1~3), exit : 0 ");
                    break;
 
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("�߸��� �Է��Դϴ�.");
            }
        }
 
    }
 
    // ����ī ver1.0 > �����޴� > �������
    // 1.Ư���� 2.Ư���� 3.�����ȣ 4.�����ں� 5.������ 6.��ü��� 0.�����ܰ�
    // �̸��� 2018-08-09
    // ������ 2018-08-10 try-catch�� �߰�
    private void mainMenu2_sub1(Scanner sc) {
        // �ֿܼ� ���� �ܺ� �Է� �غ�
        while (true) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("-----------------------------------");
            System.out.println("����ī ver1.0 > �����޴� > �������");
            System.out.println("-----------------------------------");
            System.out.println("1.Ư���� 2.Ư���� 3.�����ȣ 4.�����ں� 5.������ 6.��ü��� 0.�����ܰ�");
            System.out.print("���� > ");
            try {
                int selectNum = sc.nextInt();
                sc.nextLine();
 
                if (selectNum == 0)
                    break;
                switch (selectNum) {
                case 1:
                    this.mainMenu2_sub1_sub1(sc);
                    break;
                case 2:
                    this.mainMenu2_sub1_sub2(sc);
                    break;
                case 3:
                    this.mainMenu2_sub1_sub3(sc);
                    break;
                case 4:
                    this.mainMenu2_sub1_sub4(sc);
                    break;
                case 5:
                    this.mainMenu2_sub1_sub5(sc);
                    break;
                case 6:
                    this.mainMenu2_sub1_sub6();
                    break;
                default:
                    System.out.println("�ٽ� �����ϼ��� (1~6), exit : 0 ");
                    break;
 
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("�߸��� �Է��Դϴ�.");
            }
        }
    }
 
    // ����ī ver1.0 > �����޴� > ������� > Ư����
    // �̸��� 2018-08-09
    private void mainMenu2_sub1_sub1(Scanner sc) {
 
        // �ܺ��Է����� Ư������ �޾ƿ´�.
        // -> ����)��¥(YYYY-MM)�� �Է��ϼ���
        // -> 2018-08
        // ���� ������ �����ȣ�� �Ѵ�.
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("����ī ver1.0 > �����޴� > ������� > Ư����");
        System.out.println("--------------------------------------------");
 
        // Ư������ �ش�Ǵ� �ڷ� �˻�
        // ------------------------------------
        // try-catch OR Pattern �߰� �Ұ�!!!!!!(����)
        // ------------------------------------
        System.out.print("��¥(YYYY-MM)�� �Է��ϼ���> ");
        String month = sc.nextLine();
        
        // �˻� ������ �뿩�����Ϸ� �Ѵ�.
        // ���� ���� ������ ���� ��¥�� �������� �Ѵ�.
        // ��� ��� -> ��� �޼ҵ� ȣ��
        this.printReservationStatus(this.dao.reservationSearch(month), "month");
 
    }
 
    // ����ī ver1.0 > �����޴� > ������� > Ư����
    // �̸��� 2018-08-09
    // ������ 2018-08-10 try-catch�� �Է�
    private void mainMenu2_sub1_sub2(Scanner sc) {
 
        // �ܺ��Է����� Ư������ �޾ƿ´�.
        // -> ����)��¥(YYYY-MM-dd)�� �Է��ϼ���
        // -> 2018-08-03
        // ���� ������ �����ȣ�� �Ѵ�.
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("����ī ver1.0 > �����޴� > ������� > Ư����");
        System.out.println("--------------------------------------------");
 
        // Ư���Ͽ� �ش�Ǵ� �ڷ� �˻�
         String date = "";
          while (true) {
             SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
             sf.setLenient(false);
             // Ư���Ͽ� �ش�Ǵ� �ڷ� �˻�
             System.out.print("��¥(YYYY-MM-dd)�� �Է��ϼ���> ");
             date = sc.nextLine();
             try {
                sf.parse(date);
                break;
             } catch (ParseException e1) {
                // Ʋ�� ��¥�� ��쿹�ܹ߻�
                System.out.println("��¥ ���Ŀ� �°� �Է��ϼ���(yyyy-MM-dd)\n");
             }
          }
        // �ش� �ڷ���� ����Ⱓ �߿� �ش�Ǹ� ��µȴ�.
        // ���� ���� ������ �ܺ��Է��� ���� �Է� ���� ��¥�� �������� �Ѵ�.
        // ��� ��� -> ��� �޼ҵ� ȣ��
        this.printReservationStatus(this.dao.reservationSearch(date), "date");
 
    }
 
    // ����ī ver1.0 > �����޴� > ������� > �����ȣ
    // �̸��� 2018-08-09
    private void mainMenu2_sub1_sub3(Scanner sc) {
        // �ܺ��Է����� �����ȣ�� �޾ƿ´�.
        // -> ����)�����ȣ�� �Է��ϼ���.
        // -> R0003
        // ���� ������ �����ȣ�� �Ѵ�.
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("����ī ver1.0 > �����޴� > ������� > �����ȣ");
        System.out.println("----------------------------------------------");
 
        // �����ȣ�� �ش�Ǵ� �ڷ� �˻�
        // ------------------------------------
        // try-catch OR Pattern �߰� �Ϸ� 20180813 �Լ���
        // ------------------------------------
        while (true) {
            System.out.print("�����ȣ�� �Է��ϼ���> ");
            String reservationId = sc.nextLine();
            String regExp = "(R)\\d{4}";
            if (Pattern.matches(regExp, reservationId) == false) {
                System.out.println("�߸��� �����ȣ�Դϴ�. �ٽ��Է����ּ���. (Rxxxx)");
            } else {
                // ���� ���� ������ ���� ��¥�� �������� �Ѵ�.
                // ��� ��� -> ��� �޼ҵ� ȣ��
                this.printReservationStatus(this.dao.reservationSearch(reservationId), "reservationId");
                break;
            }
        }
    }
 
    // ����ī ver1.0 > �����޴� > ������� > ������
    // �̸��� 2018-08-09
    private void mainMenu2_sub1_sub4(Scanner sc) {
 
        // �ܺ��Է����� �̸��� ����ó�� �޾ƿ´�.
        // -> ����)�̸�>ȫ�浿
        // -> ����ó>010-1234-1234
        // ���� ������ �����ȣ�� �Ѵ�.
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("����ī ver1.0 > �����޴� > ������� > ������");
        System.out.println("--------------------------------------------");
 
        // �����ڿ� �ش�Ǵ� �ڷ� �˻�
        System.out.print("�̸��� �Է��ϼ���> ");
        String name = sc.nextLine();
        
        // ------------------------------------
        // try-catch OR Pattern �߰� �Ϸ� 20180813 �Լ���
        // ------------------------------------
        while (true) {
            System.out.print("����ó�� �Է��ϼ���> ");
            String phone = sc.nextLine();
            String regExp = "(010)-\\d{3,4}-\\d{4}";
            if (Pattern.matches(regExp, phone) == false) {
                System.out.println("����ó ������ �����ּ���. (010-xxxx-xxxx)");
            } else {
                // ���� ���� ������ ���� ��¥�� �������� �Ѵ�.
                // ��� ��� -> ��� �޼ҵ� ȣ��
                this.printReservationStatus(this.dao.reservationSearch(name, phone), "name");
                break;
            }
        }
    }
 
    // ����ī ver1.0 > �����޴� > ������� > ������
    // �̸��� 2018-08-09
    private void mainMenu2_sub1_sub5(Scanner sc) {
 
        // �ܺ��Է����� ���� ���� ���� �޾ƿ´�.
        // -> ����)CAR003
        // ���� ������ �����ȣ�� �Ѵ�.
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("����ī ver1.0 > �����޴� > ������� > ������");
        System.out.println("--------------------------------------------");
        // Ư��������ȣ�� �ش�Ǵ� �ڷ� �˻�.
        // ------------------------------------
        // try-catch OR Pattern �߰� �Ϸ� 20180813 �Լ���
        // ------------------------------------
        while (true) {
            System.out.print("������ȣ�� �Է��ϼ���> ");
            String carId = sc.nextLine();
            String regExp = "(CAR)\\d{3}";
            if (Pattern.matches(regExp, carId) == false) {
                System.out.println("������ȣ ���Ŀ� �°� �Է��ϼ���. (CARxxx) \n");
            } else {
                // ���� ���� ������ ���� ��¥�� �������� �Ѵ�.
                // ��� ��� -> ��� �޼ҵ� ȣ��
                this.printReservationStatus(this.dao.reservationSearch(carId), "carId");
                break;
            }
        }
    }
 
    // ����ī ver1.0 > �����޴� > ������� > ��ü���
    // ������ 2018.08.08
    private void mainMenu2_sub1_sub6() {
        System.out.println("****************************");
        System.out.println("��ü ���� ��Ȳ");
        System.out.println("�����ȣ / �����ڸ� / ����ó / ������ȣ / ���� / ����Ⱓ / ����");
        // ��ü ���� ��Ȳ ��� -> ��� �޼ҵ� ȣ��
        // ���� ���� ������ ���� ��¥�� �������� �Ѵ�.
        List<Reservation> temp = this.dao.reservationList();
        for (Reservation r : temp) {
            System.out.println(r);
        }
        System.out.println("****************************");
    }
 
    /********************************************************************/
 
    // ����ī ver1.0 > �����޴� > ��������
    // ������ 2018.08.08 / ��ü ���� ���� ��Ȳ ���
    private void mainMenu2_sub2() {
        // ��ü ���� ������Ȳ ��� (toString)
        System.out.println("****************************");
        System.out.println("��ü ���� ���� ��Ȳ");
        System.out.println("������ȣ /  ����  /  ũ��  / ž���ο� / 1�� ��Ʈ��");
 
        List<Car> temp = this.dao.carList();
        for (Car c : temp) {
            System.out.println(c);
        }
        System.out.println("****************************");
    }
 
    /********************************************************************/
 
    // ����ī ver1.0 > �����޴� > �������
    // 1.������ ������ ��Ȳ 2.���� ���� ��Ȳ 0. �����ܰ�
    // ������ 2018-08-10 try-catch�� �߰�
    // ������ 20180810 try-catch �߰�
    // �̰� ���� �ҽ� ������ �ȵǾ� �ߺ����� �ϰԵ�;;;
    private void mainMenu2_sub3(Scanner sc) {
        // �ֿܼ� ���� �ܺ� �Է� �غ�
        while (true) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("-------------------------------------------------");
            System.out.println("       ����ī ver1.0 > �����޴� > �������       ");
            System.out.println("-------------------------------------------------");
            System.out.println("1.������ ������ ��Ȳ 2.���� ���� ��Ȳ 0. �����ܰ�");
 
            System.out.print("���� > ");
            try {
                int selectNum = sc.nextInt();
                sc.nextLine();
 
                if (selectNum == 0)
                    break;
 
                switch (selectNum) {
                case 1:
                    this.mainMenu2_sub3_sub1(sc);
                    break;
                case 2:
                    this.mainMenu2_sub3_sub2(sc);
                    break;
                default:
                    System.out.println("�ٽ� �����ϼ��� (1~2), exit : 0 ");
                    break;
 
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("�߸��� �Է��Դϴ�.");
            }
        }
    }
 
    // ����ī ver1.0 > �����޴� > ������� > ������ ������ ����
       // ������ 2018-08-08
       private void mainMenu2_sub3_sub1(Scanner sc) {
 
          // �˻��� ���� �ܺ��Է�
          System.out.print("�˻��⵵(YYYY)>");
          String year = sc.nextLine();
 
          // �˻������� ������ ������ ��Ȳ ��¸޼ҵ� ȣ��
          List<YearlySale> sales = dao.annualSalesSearch(year);
          int count = 0;
          int total = 0;
          System.out.println("***************************");
          System.out.println("***" + year + "�⵵ �� ������Ȳ***");
          System.out.println("���� ��� / �Ǽ� / ���� �ݾ�");
          for (YearlySale s : sales) {
             System.out.println(s);
             count += s.getCount();
             total += s.getTotalPay();
          }
          System.out.println("===========================");
          System.out.println("�� �Ǽ� / �� ���� �ݾ�");
          System.out.printf("%s�� / %,d��%n", count, total);
          System.out.println("***************************");
       }
 
       // ����ī ver1.0 > �����޴� > �������-��������
       // 2018-08-09 ���� ����
       private void mainMenu2_sub3_sub2(Scanner sc) {
          // �˻��� ��� �ܺ��Է�
          System.out.print("��¥(YYYY-MM)>");
          String month = sc.nextLine();
          List<MonthlySale> sales = dao.monthlySalesSearch(month);
 
          Collections.sort(sales, new Comparator<MonthlySale>() {
             @Override
             public int compare(MonthlySale o1, MonthlySale o2) {
                return o1.getMonth().compareTo(o2.getMonth());
             }
          });
 
          int count = 0;
          int total = 0;
          System.out.println("***************************");
          System.out.println("��¥ / �Ǽ� / ���� �ݾ�");
          for (MonthlySale s : sales) {
             System.out.println(s);
             count += s.getCount();
             total += s.getTotalPay();
          }
          System.out.println("===========================");
          System.out.println("�� �Ǽ� / �� ���� �ݾ�");
          System.out.printf("%s�� / %,d��%n", count, total);
          System.out.println("***************************");
       }
 
    /********************************************************************/
 
    // ����,�������,�ݳ� ��� ���� �޼ҵ�
    // 2018-08-09 ����
    private List<Reservation> printResult(String reservationId) {
        // �����ȣ / �����ڸ� / ����ó / ���� / ����Ⱓ / ������ / �����ݾ�
        List<Reservation> temp = dao.reservationList(reservationId);
 
        Reservation reservation = null;
 
        for (Reservation r : temp) {
            reservation = r;
        }
        
        System.out.println("*******************************************"
                + "***********************");
        System.out.println("�����ȣ / �����ڸ� / ����ó / ���� / ����Ⱓ "
                + "/ ������ / �����ݾ�");
        System.out.printf("%s/ %s/ %s/ %s/ %s ~ %s/ %s/ %,d�� %n"
                , reservation.getReservationId(), reservation.getName()
                , reservation.getPhone()
                , dao.carSearch(reservation.getCarId()).getCarName()
                , reservation.getStartDate(),
                reservation.getEndDate(), reservation.getPayDate()
                , reservation.getPay());
        System.out.println("********************************************"
                + "**********************");
        return temp;
    }
 
    // ���డ������ ���
    private void printBookAble(String sDate, String eDate) {
        // car Ŭ�������� toString ���
        System.out.println("***************************");
        System.out.println("[���డ������]");
        System.out.println("������ȣ / ���� / ũ�� / ž���ο� / 1�Ϸ�Ʈ��");
 
        // �������� ���
        for(Car c : dao.reservationCarAble(sDate, eDate)) {
             System.out.println(c);
          }
        System.out.println("***************************");
    }
 
    // ���� ��Ȳ ���
    // �̸��� 18-08-09
    private void printReservationStatus(List<Reservation> reservation, String key) {
        // �����ȣ / �����ڸ� / ����ó / ������ȣ / ���� / ����Ⱓ / ����
        if (key.equals("date")) {
            System.out.println();
            System.out.println("������ ��Ȳ��");
            System.out.println("*****************************************************************************");
            System.out.println("�����ȣ / �����ڸ� / ����ó / ������ȣ / ���� /   ����Ⱓ /     ����(�˻� ����)");
            for (Reservation r : reservation) {
                System.out.printf("%s   / %s / %s / %s / %s / %s ~ %s / %s%n", r.getReservationId(), r.getName(),
                        r.getPhone(), r.getCarId(), dao.carSearch(r.getCarId()).getCarName(), r.getStartDate(),
                        r.getEndDate(), r.getReservationStatus());
            }
            System.out.println("*****************************************************************************");
 
        } else {
            System.out.println();
            System.out.println("������ ��Ȳ��");
            System.out.println("*****************************************************************************");
            System.out.println("�����ȣ / �����ڸ� / ����ó / ������ȣ / ���� /   ����Ⱓ /     ����(���� ����)");
            for (Reservation r : reservation) {
                System.out.printf("%s   / %s / %s / %s / %s / %s ~ %s / %s%n", r.getReservationId(), r.getName(),
                        r.getPhone(), r.getCarId(), dao.carSearch(r.getCarId()).getCarName(), r.getStartDate(),
                        r.getEndDate(), r.getReservationStatus());
            }
            System.out.println("*****************************************************************************");
        }
 
    }
    /********************************************************************/
 
}