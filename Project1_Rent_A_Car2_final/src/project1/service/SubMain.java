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
 
//메인 메뉴별 액션 클래스
public class SubMain {
    // RentCarDAO에 클래스의 객체 생성 및 필드 구성
    private RentCarDAO dao = new RentCarDAO();
 
    //직렬화 메소드 생성(메인 메소드에서 호출을 위해)
 
    public void daoSerializables() {
        dao.daoSerializable();
    }
    
    
    // 서브 메뉴 운영 메소드
 
    // 렌터카 ver1.0 > 예약메뉴
    // 1.예약 2.예약취소 3.반납 0.이전단계
    // 전은미 2018-08-08
    // 김유신 2018-08-10 -> try-catch, default문 추가
    // 전은미 2018-08-10 -> try-catch, default문 추가
    // 유신 작업 후 소스 결합이 안되서 은미가 안된줄 알고 또 작업함;;
    public void mainMenu1(Scanner sc) {
        // 콘솔에 의한 외부 입력 준비
 
        while (true) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("-----------------------------------");
            System.out.println("      렌터카 ver1.0 > 예약메뉴     ");
            System.out.println("-----------------------------------");
            System.out.println("1.예약 2.예약취소 3.반납 0.이전단계");
            System.out.print("선택 > ");
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
                    System.out.println("다시 선택하세요 (1~3), exit : 0 ");
                    break;
 
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("♨♨ 잘못된 메뉴 선택입니다.");
            }
 
        }
 
    }
 
    // 렌터카 ver1.0 > 예약메뉴 > 예약
    // 전은미 2018-08-09 테스트완료 수정사항있음
    private void mainMenu1_sub1(Scanner sc) {
        System.out.println();
        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println("  렌터카 ver1.0 > 예약메뉴 > 예약");
        System.out.println("-----------------------------------");
        try {
            // 대여시작일, 대여반납일 외부입력
            String startDate = "";
            String endDate = "";
 
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
 
            while (true) {
                sf.setLenient(false);
                // 대여시작일, 대여반납일 외부입력
                System.out.print("대여시작일(yyyy-MM-dd) > ");
                startDate = sc.nextLine();
                try {
                    sf.parse(startDate);
                    break;
                } catch (Exception e1) {
                    // 틀린 날짜인 경우예외발생
                    System.out.println("▶날짜 형식에 맞게 입력하세요(yyyy-MM-dd) \n");
                }
            }
 
            while (true) {
                System.out.print("대여반납일(yyyy-mm-dd) > ");
                endDate = sc.nextLine();
 
                try {
                    sf.parse(endDate);
                    break;
                } catch (Exception e2) {
                    System.out.println("▶날짜 형식에 맞게 입력하세요. (yyyy-MM-dd) \n");
                }
            }
            System.out.println();
            
            // 예약가능차량 출력
            this.printBookAble(startDate, endDate);
            System.out.println();
 
            String regExp1 = "(CAR)\\d{3}";
 
            while (true) {
                // 차량번호선택 및 개인정보 외부입력
                System.out.print("차량번호 선택(CARxxx) > ");
                String carId = sc.nextLine();
 
                if (Pattern.matches(regExp1, carId) == false) {
                    System.out.println("차량번호 형식에 맞게 입력하세요. (CARxxx) \n");
                } else {
                    int rentFee = this.dao.carSearch(carId).getRentFee();
                    String carName = this.dao.carSearch(carId).getCarName();
                    int totalFee = dao.rentMoney(rentFee, startDate, endDate);
 
                    if (carName == null) {
                        System.out.println
                        ("차량을 잘못 선택하셨습니다. 다시 입력해주세요.");
                    } else if (carName != null) {
                        System.out.printf
                        ("선택하신 [%s]의 금액은 %,d원 입니다. 예약 하시겠습니까? (Y/N)%n"
                                , carName, totalFee);
                        System.out.print("선택 > ");
                        String select = sc.nextLine();
 
                        // 예약번호 자동생성
                        String reservationId = this.dao.autoReservationId();
 
                        String name = "";
                        String phone = "";
                        String licenseId = "";
 
                        // 예약 상태
                        String reservationStatus = "예약확인";
 
                        // 결제일 받아오기
                        String payDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
 
                        System.out.println();
                        if (select.equalsIgnoreCase("Y")) {
                            System.out.println("정보를 입력해주세요.");
                            System.out.print("예약자명 > ");
                            name = sc.nextLine();
 
                            while (true) {
 
                                String regExp2 = "(010)-\\d{3,4}-\\d{4}";
 
                                System.out.print("연락처 > ");
                                phone = sc.nextLine();
 
                                if (Pattern.matches(regExp2, phone) == false) {
                                    System.out.println("연락처 형식을 맞춰주세요. (010-xxxx-xxxx)");
                                } else {
 
                                    while (true) {
 
                                        String regExp3 = "[ㄱ-ㅎ가-힣]{2}\\d{2}-\\d{4}";
                                        System.out.print("면허증번호 (지역명XX-XXXX) > ");
                                        licenseId = sc.nextLine();
                                        if (Pattern.matches(regExp3, licenseId) == false) {
                                            System.out.println
                                            ("면허증번호 형식을 맞춰주세요. (지역명XX-XXXX)");
                                        } else {
 
                                            Reservation r = new Reservation(reservationId, name
                                                    , phone, licenseId,    carId, startDate, endDate
                                                    , payDate, totalFee, reservationStatus);
 
                                            // List컬렉션 저장소에 저장
                                            this.dao.reservationAdd(r);
 
                                            // 결제 여부 확인 후,메시지 출력
                                            System.out.println();
                                            System.out.println
                                            ("입력이 완료되었습니다. 결제를 진행하시겠습니까? (Y/N)");
 
                                            System.out.print("선택 > ");
 
                                            String select2 = sc.nextLine();
 
                                            if (select2.equalsIgnoreCase("Y")) {
 
                                                this.dao.sumPay();
 
                                                System.out.println();
                                                System.out.println("결제가 완료되었습니다.");
 
                                                // 예약 정보 출력
                                                this.printResult(reservationId);
 
                                            } else if (select2.equalsIgnoreCase("N")) {
                                                System.out.println("예약이 취소되었습니다.");
                                            }
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
 
                        } else if (select.equalsIgnoreCase("N")) {
                            System.out.println("예약이 취소되었습니다.");
                        } else {
                            System.out.println("잘못 입력하셨습니다.");
                            break;
                        }
 
                    }
                    break;
                }
            }
 
        } catch (Exception e) {
            System.out.println("잘못 입력하셨습니다. 다시 예약해주세요.");
 
        }
    }
 
    // 렌터카 ver1.0 > 예약메뉴 > 예약취소
    // 함수훈 2018-08-08
    // 전은미 2018-08-09 테스트완료 수정사항있음
    // 전은미 2018-08-10 테스트완료 수정사항없음
    private void mainMenu1_sub2(Scanner sc) {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println("렌터카 ver1.0 > 예약메뉴 > 예약취소");
        System.out.println("-----------------------------------");
        
        // 예약번호 외부입력
        while (true) {
            System.out.println("예약번호를 입력해주세요.");
            System.out.print("입력>");
            String regExp4 = "(R)\\d{4}";
            String reservationId = sc.nextLine();
 
            if (Pattern.matches(regExp4, reservationId) == false) {
                System.out.println("잘못된 예약번호입니다. 다시입력해주세요. (Rxxxx)");
                System.out.println();
            } else {
                System.out.println();
                
                // 예약정보출력
                List<Reservation> r = this.printResult(reservationId);
                System.out.println();
 
                // 취소 여부 확인
                // ->Yes :취소전용 메소드 호출
                // 결과메시지 출력
                //예약 취소시 결제금액 0원으로 데이터 수정 메소드 호출
                System.out.println("예약을 취소하시겠습니까? (Y/N)");
                System.out.print("선택 > ");
                String select = sc.nextLine();
                if (select.equalsIgnoreCase("Y")) {
                    this.dao.reservationCancel(r);
                    System.out.println("취소가 완료 되었습니다.");
                } else {
                    System.out.println("예약취소가 취소되었습니다.");
                }
                break;
            }
        }
    }
 
    // 렌터카 ver1.0 > 예약메뉴 > 반납
    // 함수훈 2018-08-08
    // 전은미20180809 테스트완료 - 수정사항있음
    // 전은미20180810 테스트완료
    private void mainMenu1_sub3(Scanner sc) {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("-------------------------------");
        System.out.println("렌터카 ver1.0 > 예약메뉴 > 반납");
        System.out.println("-------------------------------");
        // 예약번호 외부입력
        while (true) {
            System.out.println("예약번호를 입력해주세요.");
            System.out.print("입력>");
            String regExp4 = "(R)\\d{4}";
            String reservationId = sc.nextLine();
 
            if (Pattern.matches(regExp4, reservationId) == false) {
                System.out.println("잘못된 예약번호입니다. 다시입력해주세요. (Rxxxx)");
                System.out.println();
            } else {
 
                System.out.println();
                // 예약정보출력
                List<Reservation> r = this.printResult(reservationId);
                System.out.println();
 
                // 반납 여부 확인
                // ->Yes :취소전용 메소드 호출
                // 결과메시지 출력
                System.out.println("차량을 반납하시겠습니까? (Y/N) ");
                System.out.print("선택 > ");
                String select = sc.nextLine();
                if (select.equalsIgnoreCase("Y")) {
                    this.dao.returnCar(r);
                    System.out.println("반납이 완료 되었습니다.");
                } else {
                    System.out.println("차량반납이 취소되었습니다.");
                }
                break;
            }
        }
    }
 
    /********************************************************************/
 
    // 렌터카 ver1.0 > 관리메뉴
    // 1.예약관리 2.차량관리 3.매출관리 0.이전단계
    public void mainMenu2(Scanner sc) {
        // 콘솔에 의한 외부 입력 준비
        // 김유신 2018-08-10 try-catch문 추가
        while (true) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("-------------------------------------------");
            System.out.println("         렌터카 ver1.0 > 관리메뉴          ");
            System.out.println("-------------------------------------------");
            System.out.println("1.예약관리 2.차량관리 3.매출관리 0.이전단계");
            System.out.print("선택 > ");
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
                    System.out.println("다시 선택하세요 (1~3), exit : 0 ");
                    break;
 
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("잘못된 입력입니다.");
            }
        }
 
    }
 
    // 렌터카 ver1.0 > 관리메뉴 > 예약관리
    // 1.특정월 2.특정일 3.예약번호 4.예약자별 5.차량별 6.전체출력 0.이전단계
    // 이명재 2018-08-09
    // 김유신 2018-08-10 try-catch문 추가
    private void mainMenu2_sub1(Scanner sc) {
        // 콘솔에 의한 외부 입력 준비
        while (true) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("-----------------------------------");
            System.out.println("렌터카 ver1.0 > 관리메뉴 > 예약관리");
            System.out.println("-----------------------------------");
            System.out.println("1.특정월 2.특정일 3.예약번호 4.예약자별 5.차량별 6.전체출력 0.이전단계");
            System.out.print("선택 > ");
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
                    System.out.println("다시 선택하세요 (1~6), exit : 0 ");
                    break;
 
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
 
    // 렌터카 ver1.0 > 관리메뉴 > 예약관리 > 특정월
    // 이명재 2018-08-09
    private void mainMenu2_sub1_sub1(Scanner sc) {
 
        // 외부입력으로 특정월을 받아온다.
        // -> 예시)날짜(YYYY-MM)를 입력하세요
        // -> 2018-08
        // 정렬 기준은 예약번호로 한다.
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("렌터카 ver1.0 > 관리메뉴 > 예약관리 > 특정월");
        System.out.println("--------------------------------------------");
 
        // 특정월에 해당되는 자료 검색
        // ------------------------------------
        // try-catch OR Pattern 추가 할것!!!!!!(질문)
        // ------------------------------------
        System.out.print("날짜(YYYY-MM)를 입력하세요> ");
        String month = sc.nextLine();
        
        // 검색 기준은 대여시작일로 한다.
        // 상태 값의 기준은 오늘 날짜를 기준으로 한다.
        // 결과 출력 -> 출력 메소드 호출
        this.printReservationStatus(this.dao.reservationSearch(month), "month");
 
    }
 
    // 렌터카 ver1.0 > 관리메뉴 > 예약관리 > 특정일
    // 이명재 2018-08-09
    // 김유신 2018-08-10 try-catch문 입력
    private void mainMenu2_sub1_sub2(Scanner sc) {
 
        // 외부입력으로 특정일을 받아온다.
        // -> 예시)날짜(YYYY-MM-dd)를 입력하세요
        // -> 2018-08-03
        // 정렬 기준은 예약번호로 한다.
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("렌터카 ver1.0 > 관리메뉴 > 예약관리 > 특정일");
        System.out.println("--------------------------------------------");
 
        // 특정일에 해당되는 자료 검색
         String date = "";
          while (true) {
             SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
             sf.setLenient(false);
             // 특정일에 해당되는 자료 검색
             System.out.print("날짜(YYYY-MM-dd)를 입력하세요> ");
             date = sc.nextLine();
             try {
                sf.parse(date);
                break;
             } catch (ParseException e1) {
                // 틀린 날짜인 경우예외발생
                System.out.println("날짜 형식에 맞게 입력하세요(yyyy-MM-dd)\n");
             }
          }
        // 해당 자료들은 예약기간 중에 해당되면 출력된다.
        // 상태 값의 기준은 외부입력을 통해 입력 받은 날짜를 기준으로 한다.
        // 결과 출력 -> 출력 메소드 호출
        this.printReservationStatus(this.dao.reservationSearch(date), "date");
 
    }
 
    // 렌터카 ver1.0 > 관리메뉴 > 예약관리 > 예약번호
    // 이명재 2018-08-09
    private void mainMenu2_sub1_sub3(Scanner sc) {
        // 외부입력으로 예약번호를 받아온다.
        // -> 예시)예약번호를 입력하세요.
        // -> R0003
        // 정렬 기준은 예약번호로 한다.
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("렌터카 ver1.0 > 관리메뉴 > 예약관리 > 예약번호");
        System.out.println("----------------------------------------------");
 
        // 예약번호에 해당되는 자료 검색
        // ------------------------------------
        // try-catch OR Pattern 추가 완료 20180813 함수훈
        // ------------------------------------
        while (true) {
            System.out.print("예약번호를 입력하세요> ");
            String reservationId = sc.nextLine();
            String regExp = "(R)\\d{4}";
            if (Pattern.matches(regExp, reservationId) == false) {
                System.out.println("잘못된 예약번호입니다. 다시입력해주세요. (Rxxxx)");
            } else {
                // 상태 값의 기준은 오늘 날짜를 기준으로 한다.
                // 결과 출력 -> 출력 메소드 호출
                this.printReservationStatus(this.dao.reservationSearch(reservationId), "reservationId");
                break;
            }
        }
    }
 
    // 렌터카 ver1.0 > 관리메뉴 > 예약관리 > 예약자
    // 이명재 2018-08-09
    private void mainMenu2_sub1_sub4(Scanner sc) {
 
        // 외부입력으로 이름과 연락처를 받아온다.
        // -> 예시)이름>홍길동
        // -> 연락처>010-1234-1234
        // 정렬 기준은 예약번호로 한다.
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("렌터카 ver1.0 > 관리메뉴 > 예약관리 > 예약자");
        System.out.println("--------------------------------------------");
 
        // 예약자에 해당되는 자료 검색
        System.out.print("이름을 입력하세요> ");
        String name = sc.nextLine();
        
        // ------------------------------------
        // try-catch OR Pattern 추가 완료 20180813 함수훈
        // ------------------------------------
        while (true) {
            System.out.print("연락처를 입력하세요> ");
            String phone = sc.nextLine();
            String regExp = "(010)-\\d{3,4}-\\d{4}";
            if (Pattern.matches(regExp, phone) == false) {
                System.out.println("연락처 형식을 맞춰주세요. (010-xxxx-xxxx)");
            } else {
                // 상태 값의 기준은 오늘 날짜를 기준으로 한다.
                // 결과 출력 -> 출력 메소드 호출
                this.printReservationStatus(this.dao.reservationSearch(name, phone), "name");
                break;
            }
        }
    }
 
    // 렌터카 ver1.0 > 관리메뉴 > 예약관리 > 차량별
    // 이명재 2018-08-09
    private void mainMenu2_sub1_sub5(Scanner sc) {
 
        // 외부입력으로 차량 고유 값을 받아온다.
        // -> 예시)CAR003
        // 정렬 기준은 예약번호로 한다.
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("렌터카 ver1.0 > 관리메뉴 > 예약관리 > 차량별");
        System.out.println("--------------------------------------------");
        // 특정차량번호에 해당되는 자료 검색.
        // ------------------------------------
        // try-catch OR Pattern 추가 완료 20180813 함수훈
        // ------------------------------------
        while (true) {
            System.out.print("차량번호를 입력하세요> ");
            String carId = sc.nextLine();
            String regExp = "(CAR)\\d{3}";
            if (Pattern.matches(regExp, carId) == false) {
                System.out.println("차량번호 형식에 맞게 입력하세요. (CARxxx) \n");
            } else {
                // 상태 값의 기준은 오늘 날짜를 기준으로 한다.
                // 결과 출력 -> 출력 메소드 호출
                this.printReservationStatus(this.dao.reservationSearch(carId), "carId");
                break;
            }
        }
    }
 
    // 렌터카 ver1.0 > 관리메뉴 > 예약관리 > 전체출력
    // 옥보라 2018.08.08
    private void mainMenu2_sub1_sub6() {
        System.out.println("****************************");
        System.out.println("전체 예약 현황");
        System.out.println("예약번호 / 예약자명 / 연락처 / 차량번호 / 차종 / 예약기간 / 상태");
        // 전체 예약 현황 출력 -> 출력 메소드 호출
        // 상태 값의 기준은 오늘 날짜를 기준으로 한다.
        List<Reservation> temp = this.dao.reservationList();
        for (Reservation r : temp) {
            System.out.println(r);
        }
        System.out.println("****************************");
    }
 
    /********************************************************************/
 
    // 렌터카 ver1.0 > 관리메뉴 > 차량관리
    // 옥보라 2018.08.08 / 전체 차량 보유 현황 출력
    private void mainMenu2_sub2() {
        // 전체 차량 보유현황 출력 (toString)
        System.out.println("****************************");
        System.out.println("전체 차량 보유 현황");
        System.out.println("차량번호 /  차량  /  크기  / 탑승인원 / 1일 렌트비");
 
        List<Car> temp = this.dao.carList();
        for (Car c : temp) {
            System.out.println(c);
        }
        System.out.println("****************************");
    }
 
    /********************************************************************/
 
    // 렌터카 ver1.0 > 관리메뉴 > 매출관리
    // 1.차종별 연매출 현황 2.월별 매출 현황 0. 이전단계
    // 김유신 2018-08-10 try-catch문 추가
    // 전은미 20180810 try-catch 추가
    // 이거 또한 소스 통합이 안되어 중복으로 하게됨;;;
    private void mainMenu2_sub3(Scanner sc) {
        // 콘솔에 의한 외부 입력 준비
        while (true) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("-------------------------------------------------");
            System.out.println("       렌터카 ver1.0 > 관리메뉴 > 매출관리       ");
            System.out.println("-------------------------------------------------");
            System.out.println("1.차종별 연매출 현황 2.월별 매출 현황 0. 이전단계");
 
            System.out.print("선택 > ");
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
                    System.out.println("다시 선택하세요 (1~2), exit : 0 ");
                    break;
 
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
 
    // 렌터카 ver1.0 > 관리메뉴 > 매출관리 > 차종별 연매출 매출
       // 김유신 2018-08-08
       private void mainMenu2_sub3_sub1(Scanner sc) {
 
          // 검색할 연도 외부입력
          System.out.print("검색년도(YYYY)>");
          String year = sc.nextLine();
 
          // 검색연도의 차종별 연매출 현황 출력메소드 호출
          List<YearlySale> sales = dao.annualSalesSearch(year);
          int count = 0;
          int total = 0;
          System.out.println("***************************");
          System.out.println("***" + year + "년도 총 매출현황***");
          System.out.println("차종 목록 / 건수 / 누적 금액");
          for (YearlySale s : sales) {
             System.out.println(s);
             count += s.getCount();
             total += s.getTotalPay();
          }
          System.out.println("===========================");
          System.out.println("총 건수 / 총 누적 금액");
          System.out.printf("%s건 / %,d원%n", count, total);
          System.out.println("***************************");
       }
 
       // 렌터카 ver1.0 > 관리메뉴 > 매출관리-월별매출
       // 2018-08-09 유신 보라
       private void mainMenu2_sub3_sub2(Scanner sc) {
          // 검색할 년월 외부입력
          System.out.print("날짜(YYYY-MM)>");
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
          System.out.println("날짜 / 건수 / 누적 금액");
          for (MonthlySale s : sales) {
             System.out.println(s);
             count += s.getCount();
             total += s.getTotalPay();
          }
          System.out.println("===========================");
          System.out.println("총 건수 / 총 누적 금액");
          System.out.printf("%s건 / %,d원%n", count, total);
          System.out.println("***************************");
       }
 
    /********************************************************************/
 
    // 예약,예약취소,반납 출력 전용 메소드
    // 2018-08-09 은미
    private List<Reservation> printResult(String reservationId) {
        // 예약번호 / 예약자명 / 연락처 / 차종 / 예약기간 / 결제일 / 결제금액
        List<Reservation> temp = dao.reservationList(reservationId);
 
        Reservation reservation = null;
 
        for (Reservation r : temp) {
            reservation = r;
        }
        
        System.out.println("*******************************************"
                + "***********************");
        System.out.println("예약번호 / 예약자명 / 연락처 / 차종 / 예약기간 "
                + "/ 결제일 / 결제금액");
        System.out.printf("%s/ %s/ %s/ %s/ %s ~ %s/ %s/ %,d원 %n"
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
 
    // 예약가능차량 출력
    private void printBookAble(String sDate, String eDate) {
        // car 클래스에서 toString 출력
        System.out.println("***************************");
        System.out.println("[예약가능차량]");
        System.out.println("차량번호 / 차종 / 크기 / 탑승인원 / 1일렌트비");
 
        // 가능차량 출력
        for(Car c : dao.reservationCarAble(sDate, eDate)) {
             System.out.println(c);
          }
        System.out.println("***************************");
    }
 
    // 예약 현황 출력
    // 이명재 18-08-09
    private void printReservationStatus(List<Reservation> reservation, String key) {
        // 예약번호 / 예약자명 / 연락처 / 차량번호 / 차종 / 예약기간 / 상태
        if (key.equals("date")) {
            System.out.println();
            System.out.println("▶예약 현황◀");
            System.out.println("*****************************************************************************");
            System.out.println("예약번호 / 예약자명 / 연락처 / 차량번호 / 차종 /   예약기간 /     상태(검색 기준)");
            for (Reservation r : reservation) {
                System.out.printf("%s   / %s / %s / %s / %s / %s ~ %s / %s%n", r.getReservationId(), r.getName(),
                        r.getPhone(), r.getCarId(), dao.carSearch(r.getCarId()).getCarName(), r.getStartDate(),
                        r.getEndDate(), r.getReservationStatus());
            }
            System.out.println("*****************************************************************************");
 
        } else {
            System.out.println();
            System.out.println("▶예약 현황◀");
            System.out.println("*****************************************************************************");
            System.out.println("예약번호 / 예약자명 / 연락처 / 차량번호 / 차종 /   예약기간 /     상태(오늘 기준)");
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