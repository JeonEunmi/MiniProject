package project1.persistance;
 
import java.io.File; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
 
import project1.domain.Car;
import project1.domain.MonthlySale;
import project1.domain.Reservation;
import project1.domain.YearlySale;
 
//자료 저장소 운영 클래스
public class RentCarDAO {
 
    // 필드값
 
    // 자료 저장소 -> 자료형 클래스별로 별도 준비
    public List<Car> carList = new ArrayList<Car>();
    public List<Reservation> reservationList = new ArrayList<Reservation>();
 
    // 생성자 -> 초기화
    public RentCarDAO() {
        // Car, Customer, Reservation 초기값 설정
 
        // Reservation (String reservationId, String name, String phone, String
        // licenseId, String
        // carId, String startDate, String endDate, String payDate, int pay, String
        // reservationStatus)
        this.reservationList.add(new Reservation("R0001", "홍길동", "010-1234-1234", "서울01-1234", "CAR008", "2017-12-10",
                "2017-12-12", "2017-12-04", 270000, "반납완료"));
        this.reservationList.add(new Reservation("R0002", "김한솔", "010-7410-8520", "경기24-9047", "CAR001", "2017-12-23",
                "2017-12-26", "2017-12-19", 240000, "반납완료"));
        this.reservationList.add(new Reservation("R0003", "함수훈", "010-5579-4373", "서울08-9720", "CAR001", "2017-12-30",
                "2018-01-02", "2017-12-19", 240000, "반납완료"));
        this.reservationList.add(new Reservation("R0004", "김맥심", "010-9510-7530", "경북91-7501", "CAR008", "2018-01-10",
                "2018-01-13", "2018-12-28", 360000, "반납완료"));
        this.reservationList.add(new Reservation("R0005", "김대한", "010-5918-1156", "강원11-7511", "CAR014", "2018-01-20",
                "2018-01-25", "2018-01-04", 720000, "반납완료"));
        this.reservationList.add(new Reservation("R0006", "이현희", "010-1234-9630", "충북71-0147", "CAR002", "2018-02-17",
                "2018-02-19", "2018-02-02", 180000, "반납완료"));
        this.reservationList.add(new Reservation("R0007", "황서연", "010-8547-1222", "서울61-1479", "CAR005", "2018-02-05",
                "2018-02-06", "2018-02-04", 140000, "반납완료"));
        this.reservationList.add(new Reservation("R0008", "김한솔", "010-7410-8520", "경기24-9047", "CAR014", "2018-02-09",
                "2018-02-13", "2018-02-07", 600000, "반납완료"));
        this.reservationList.add(new Reservation("R0009", "박카누", "010-8520-7410", "전남17-1268", "CAR001", "2018-02-21",
                "2018-02-25", "2018-02-16", 300000, "예약취소"));
        this.reservationList.add(new Reservation("R0010", "김맥심", "010-9510-7530", "경북91-7501", "CAR008", "2018-02-17",
                "2018-02-20", "2018-02-16", 360000, "반납완료"));
        this.reservationList.add(new Reservation("R0011", "백종석", "010-6899-3253", "서울10-1111", "CAR015", "2018-03-01",
                "2018-03-03", "2018-02-28", 360000, "반납완료"));
        this.reservationList.add(new Reservation("R0012", "함수훈", "010-5579-4373", "충북12-4885", "CAR001", "2018-03-05",
                "2018-03-10", "2018-03-03", 360000, "반납완료"));
        this.reservationList.add(new Reservation("R0013", "전은미", "010-2947-3782", "강원12-3333", "CAR015", "2018-03-07",
                "2018-03-14", "2018-03-03", 960000, "예약취소"));
        this.reservationList.add(new Reservation("R0014", "이명재", "010-4643-8440", "경기11-4567", "CAR010", "2018-03-15",
                "2018-03-19", "2018-03-13", 450000, "반납완료"));
        this.reservationList.add(new Reservation("R0015", "강슬기", "010-1994-0210", "경기14-3434", "CAR007", "2018-03-23",
                "2018-03-25", "2018-03-21", 210000, "반납완료"));
        this.reservationList.add(new Reservation("R0016", "김한솔", "010-3214-0774", "서울09-4682", "CAR014", "2018-04-01",
                "2018-04-03", "2018-04-01", 360000, "반납완료"));
        this.reservationList.add(new Reservation("R0017", "김하나", "010-9876-0854", "경기01-1226", "CAR002", "2018-04-11",
                "2018-04-12", "2018-04-07", 120000, "반납완료"));
        this.reservationList.add(new Reservation("R0018", "한효주", "010-1234-9630", "인천03-4948", "CAR002", "2018-04-23",
                "2018-04-24", "2018-04-21", 120000, "반납완료"));
        this.reservationList.add(new Reservation("R0019", "강슬기", "010-6994-0210", "경기14-3434", "CAR011", "2018-05-03",
                "2018-05-05", "2018-05-01", 270000, "예약취소"));
        this.reservationList.add(new Reservation("R0020", "옥보라", "010-8401-6843", "경기08-1800", "CAR005", "2018-05-07",
                "2018-05-08", "2018-05-04", 70000, "반납완료"));
        this.reservationList.add(new Reservation("R0021", "강동원", "010-1984-9808", "충북73-9870", "CAR009", "2018-05-09",
                "2018-05-12", "2018-05-07", 480000, "반납완료"));
        this.reservationList.add(new Reservation("R0022", "강혜진", "010-7695-0857", "경북60-6854", "CAR010", "2018-05-18",
                "2018-05-18", "2018-05-15", 90000, "반납완료"));
        this.reservationList.add(new Reservation("R0023", "정해인", "010-0708-6802", "경기54-0968", "CAR004", "2018-05-24",
                "2018-05-28", "2018-05-24", 300000, "반납완료"));
        this.reservationList.add(new Reservation("R0024", "김유신", "010-2687-3102", "서울95-0951", "CAR011", "2018-06-08",
                "2018-06-08", "2018-06-08", 90000, "반납완료"));
        this.reservationList.add(new Reservation("R0025", "홍길동", "010-1234-1234", "서울01-1234", "CAR003", "2018-06-12",
                "2018-06-15", "2018-06-12", 480000, "반납완료"));
        this.reservationList.add(new Reservation("R0026", "송중기", "010-9987-9872", "서울14-0814", "CAR012", "2018-06-20",
                "2018-06-21", "2018-06-20", 180000, "반납완료"));
        this.reservationList.add(new Reservation("R0027", "박서준", "010-4876-0000", "인천30-1800", "CAR013", "2018-07-02",
                "2018-07-02", "2018-07-01", 70000, "예약취소"));
        this.reservationList.add(new Reservation("R0028", "박지은", "010-3872-6870", "충남78-9852", "CAR008", "2018-07-03",
                "2018-07-07", "2018-07-03", 450000, "반납완료"));
        this.reservationList.add(new Reservation("R0029", "강슬기", "010-6994-0210", "경기14-3434", "CAR001", "2018-07-15",
                "2018-07-18", "2018-07-15", 240000, "반납완료"));
        this.reservationList.add(new Reservation("R0030", "최정희", "010-6899-3253", "강원89-4980", "CAR014", "2018-07-22",
                "2018-07-28", "2018-07-22", 840000, "반납완료"));
        this.reservationList.add(new Reservation("R0031", "한효주", "010-1479-0283", "강원17-0284", "CAR005", "2018-08-09",
                "2018-08-11", "2018-08-02", 210000, "대여중"));
        this.reservationList.add(new Reservation("R0032", "전성표", "010-6279-5327", "서울17-1751", "CAR013", "2018-08-08",
                "2018-08-15", "2018-08-04", 720000, "대여중"));
        this.reservationList.add(new Reservation("R0033", "박형용", "010-1842-0745", "충남92-7162", "CAR003", "2018-08-13",
                "2018-08-17", "2018-08-04", 300000, "예약확인"));
        this.reservationList.add(new Reservation("R0034", "장형주", "010-9300-1122", "경북15-9214", "CAR014", "2018-08-28",
                "2018-08-31", "2018-08-15", 480000, "예약확인"));
        this.reservationList.add(new Reservation("R0035", "김서연", "010-1112-2225", "강원96-4065", "CAR010", "2018-09-04",
                "2018-09-06", "2018-08-21", 270000, "예약확인"));
        this.reservationList.add(new Reservation("R0036", "백종석", "010-6899-3253", "서울10-1111", "CAR003", "2018-08-08",
                "2018-08-10", "2018-08-08", 180000, "대여중"));
        this.reservationList.add(new Reservation("R0037", "강하나", "010-1515-8783", "충북29-7180", "CAR002", "2018-08-07",
                "2018-08-11", "2018-08-06", 300000, "대여중"));
        this.reservationList.add(new Reservation("R0038", "최정희", "010-6899-3253", "강원89-4980", "CAR006", "2018-08-11",
                "2018-08-12", "2018-08-07", 140000, "예약확인"));
        this.reservationList.add(new Reservation("R0039", "장발장", "010-0710-0981", "경기14-0860", "CAR009", "2018-08-13",
                "2018-08-13", "2018-08-08", 90000, "예약확인"));
        this.reservationList.add(new Reservation("R0040", "최정윤", "010-7269-0371", "서울04-9885", "CAR014", "2018-08-15",
                "2018-08-17", "2018-08-09", 360000, "예약확인"));
        this.reservationList.add(new Reservation("R0041", "테스트", "010-7269-0371", "서울04-8411", "CAR014", "2018-08-08",
                "2018-08-09", "2018-08-07", 360000, "예약확인"));
 
        //Car (String carId, String carName, String carSize, int capacity, int rentFee)
        this.carList.add(new Car("CAR001", "레  이", "경차", 4, 60000));
        this.carList.add(new Car("CAR002", "레  이", "경차", 4, 60000));
        this.carList.add(new Car("CAR003", "레  이", "경차", 4, 60000));
        this.carList.add(new Car("CAR004", "레  이", "경차", 4, 60000));
        this.carList.add(new Car("CAR005", "엑센트", "소형", 4, 70000));
        this.carList.add(new Car("CAR006", "엑센트", "소형", 4, 70000));
        this.carList.add(new Car("CAR007", "엑센트", "소형", 4, 70000));
        this.carList.add(new Car("CAR008", "아반떼", "준중형", 5, 90000));
        this.carList.add(new Car("CAR009", "아반떼", "준중형", 5, 90000));
        this.carList.add(new Car("CAR010", "아반떼", "준중형", 5, 90000));
        this.carList.add(new Car("CAR011", "아반떼", "준중형", 5, 90000));
        this.carList.add(new Car("CAR012", "아반떼", "준중형", 5, 90000));
        this.carList.add(new Car("CAR013", "아반떼", "준중형", 5, 90000));
        this.carList.add(new Car("CAR014", "카니발", "대형", 8, 120000));
        this.carList.add(new Car("CAR015", "카니발", "대형", 8, 120000));
        
        this.daoReSerializable();
        
    }
    
    /********************************************************************/
 
    // 역직렬화 메소드 추가
    // 이명재 2018-08-13
    @SuppressWarnings("unchecked")
    public void daoReSerializable() {
 
        File file1 = new File("Reservation.dat");
        File file2 = new File("Car.dat");
 
        FileInputStream fis = null;
        ObjectInputStream ois = null;
 
        if (file1.exists() && file2.exists()) {
            try {
                fis = new FileInputStream(file1);
                ois = new ObjectInputStream(fis);
                this.reservationList = (List<Reservation>) ois.readObject();
 
                fis = new FileInputStream(file2);
                ois = new ObjectInputStream(fis);
                this.carList = (List<Car>) ois.readObject();
 
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
 
    // 직렬화 메소드 추가
    // 백종석 2018-08-13
    public void daoSerializable() {
 
        File file1 = new File("Reservation.dat");
        File file2 = new File("Car.dat");
 
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
 
            fos = new FileOutputStream(file1);
            oos = new ObjectOutputStream(fos);
            List<Reservation> reservation = this.reservationList;
            oos.writeObject(reservation);
 
            fos = new FileOutputStream(file2);
            oos = new ObjectOutputStream(fos);
            List<Car> cars = this.carList;
            oos.writeObject(cars);
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
 
    }
 
    /********************************************************************/
 
    // 예약번호 자동 생성 메소드
    // 수훈 20180808
    public String autoReservationId() {
        String temp = String.format("R%04d", (this.reservationList.size() + 1));
        return temp;
    }
 
    // 예약정보 입력
    // 은미 20180808
    public void reservationAdd(Reservation r) {
        this.reservationList.add(r);
    }
 
    // 예약정보 출력
    // 수훈 20180808
    public List<Reservation> reservationList(String value) {
        List<Reservation> revList = new ArrayList<Reservation>();
        for (Reservation rv : this.reservationList) {
            if (value.equals(rv.getReservationId())) {
                revList.add(rv);
            }
        }
        return revList;
    }
 
    // 예약취소
    // 예약취소시 결제금액 0원으로(매출에 영향을 미치기 때문)
    // 종석 20180808
    // 수훈 21080813 수정
    public void reservationCancel(List<Reservation> r) {
        //예약확인 ->예약취소
        for (Reservation rev : r) {
            rev.setReservationStatus("예약취소");
            rev.setPay(0);
        }
        
    }
 
    // 반납
    // 종석 20180808
    public void returnCar(List<Reservation> r) {
        // 대여중 -> 반납
        for (Reservation rev : r) {
            rev.setReservationStatus("반납완료");
        }
 
    }
 
    // *****예약정보 상태 변경 메소드*****
    // 종석 20180808
    public void autoChangeStatus() {
        // 현재 시간
        List<Reservation> rv = this.reservationList;
        DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String nowDate = sdFormat.format(date);
        for (Reservation rev : rv) {
            if (rev.getStartDate().compareTo(nowDate) == 0) {
                // 예약확인 -> 대여중
                rev.setReservationStatus("대여중");
            } else if (rev.getEndDate().compareTo(nowDate) < 0 ) {
                // 대여중 -> 반납
                rev.setReservationStatus("반납완료");
            }
        }
    }
    
    /********************************************************************/
    // 예약 검색 -> 특정월, 특정일, 예약번호, 차량별
    // 이명재 2018-08-09
    public List<Reservation> reservationSearch(String value) {
        List<Reservation> reservations = new ArrayList<Reservation>();
        for (Reservation r : this.reservationList) {
            if (r.getStartDate().substring(0, 7).equals(value) || r.getEndDate().substring(0, 7).equals(value)) {
                reservations.add(new Reservation(r.getReservationId(), r.getName(), r.getPhone(), r.getLicenseId(),
                        r.getCarId(), r.getStartDate(), r.getEndDate(), r.getPayDate(), r.getPay(),
                        r.getReservationStatus()));
            } else if (r.getStartDate().compareTo(value) <= 0 && r.getEndDate().compareTo(value) >= 0) {
                reservations.add(new Reservation(r.getReservationId(), r.getName(), r.getPhone(), r.getLicenseId(),
                        r.getCarId(), r.getStartDate(), r.getEndDate(), r.getPayDate(), r.getPay(),
                        r.getReservationStatus()));
            }
            if (r.getReservationId().equals(value)) {
                reservations.add(new Reservation(r.getReservationId(), r.getName(), r.getPhone(), r.getLicenseId(),
                        r.getCarId(), r.getStartDate(), r.getEndDate(), r.getPayDate(), r.getPay(),
                        r.getReservationStatus()));
            }
            if (r.getCarId().equals(value)) {
                reservations.add(new Reservation(r.getReservationId(), r.getName(), r.getPhone(), r.getLicenseId(),
                        r.getCarId(), r.getStartDate(), r.getEndDate(), r.getPayDate(), r.getPay(),
                        r.getReservationStatus()));
            }
        }
        return reservations;
 
    }
 
    // 예약 검색 -> 예약자
    // 이명재 18-08-09
    public List<Reservation> reservationSearch(String name, String phone) {
        List<Reservation> reservations = new ArrayList<Reservation>();
 
        for (Reservation r : this.reservationList) {
            if (r.getName().equals(name) && r.getPhone().equals(phone)) {
                reservations.add(new Reservation(r.getReservationId(), r.getName(), r.getPhone(), r.getLicenseId(),
                        r.getCarId(), r.getStartDate(), r.getEndDate(), r.getPayDate(), r.getPay(),
                        r.getReservationStatus()));
            }
        }
        return reservations;
    }
    
    /********************************************************************/
    //특정차량 호출 메소드
    //예약가능차량 출력하는 메소드에서 필요
    //수훈 2108-08-10
    public List<Reservation> reservationSearchSub(String value) {
        List<Reservation> reservations = new ArrayList<Reservation>();
 
        for (Reservation r : reservationList) {
            if (r.getCarId().equals(value)) {
                if (r.getReservationStatus().equals("대여중") || r.getReservationStatus().equals("예약확인")) {
                    reservations.add(new Reservation(r.getReservationId(), r.getName(), r.getPhone(), r.getLicenseId(),
                            r.getCarId(), r.getStartDate(), r.getEndDate(), r.getPayDate(), r.getPay(),
                            r.getReservationStatus()));
                }
            }
        }
        return reservations;
    }
    /********************************************************************/
    
    // 전체 예약 현황 출력위해 this.reservationList를 리턴 <- SubMain - mainMenu2_sub1_sub6()
    // 옥보라 2018.08.08 새로 추가
    public List<Reservation> reservationList() {
        return this.reservationList;
    }
    
    // 전체 차량 출력 위해 현재 가지고 있는 this.carList를 리턴 <-SubMain - mainMenu2_sub()
    // 옥보라 2018.08.08 새로 추가
    public List<Car> carList() {
        return this.carList;
    }
 
    /********************************************************************/
 
    // 차종별 연매출 현황 검색
    // 김유신 2018-08-08
    // 특정 년도를 전달 받아서 특정 년도를 만족하는 매출 현황의 
    // 차종별 매출의 합 목록 반환
    public List<YearlySale> annualSalesSearch(String year) {
 
        List<YearlySale> resultList = null;
        List<Reservation> temp = new ArrayList<Reservation>();
        Map<String, YearlySale> map = new HashMap<String, YearlySale>();
        // 매개변수로 받아온 해당 year에 발생한 예약 리스트 전부를 찾음
        for (Reservation r : this.reservationList) {
            if (r.getPayDate().contains(year)) {
                if (r.getPay() != 0) {
                    temp.add(r);
                    String carName = this.carNameSearch(r.getCarId());
                    map.put(carName, new YearlySale(carName, 0, 0));
                }
            }
        }
 
        // 특정 차종별 매출 현황을 합산하는 과정
        for (String carName : map.keySet()) {
            for (Reservation r : temp) {
                String carName2 = this.carNameSearch(r.getCarId());
                if (carName2.equals(carName)) {
                    // Map<String, YearlySale> 에 저장된 특정 차종의
                    // count 값을 +1 연산
                    YearlySale ys = map.get(carName2);
                    ys.setCount(ys.getCount() + 1);
                    ys.setTotalPay(ys.getTotalPay() + r.getPay());
                }
            }
 
        }
        resultList = new ArrayList<YearlySale>(map.values());
 
        return resultList;
    }
 
    /********************************************************************/
    // 월별 매출 현황 검색
    // 특정 년월를 전달 받아서 특정 년월를 만족하는 매출 현황의 일별 매출의 합 목록 반환
    // 보라,유신 2018-08-13
    public List<MonthlySale> monthlySalesSearch(String month) {
 
        List<MonthlySale> resultList = null;
        List<Reservation> temp = new ArrayList<Reservation>();
        Map<String, MonthlySale> map = new HashMap<String, MonthlySale>();
        // 매개변수로 받아온 해당 year에 발생한 예약 리스트 전부를 찾음
        for (Reservation r : this.reservationList) {
            String date = r.getPayDate();
            if (date.contains(month)) {
                if (r.getPay() != 0) {
                    temp.add(r);
                    map.put(date, new MonthlySale(date, 0, 0));
                }
            }
        }
 
        // 특정 일별 매출 현황을 합산하는 과정
        for (String date1 : map.keySet()) {
            for (Reservation r : temp) {
                String date2 = r.getPayDate();
                if (date1.equals(date2)) {
                    // Map<String, MonthlySale> 에 저장된 특정 일의 count 값을 +1 연산
                    MonthlySale ms = map.get(date1);
                    ms.setCount(ms.getCount() + 1);
                    ms.setTotalPay(ms.getTotalPay() + r.getPay());
                }
            }
 
        }
        resultList = new ArrayList<MonthlySale>(map.values());
 
        return resultList;
    }
 
    /********************************************************************/
 
    // 예약가능차량 출력
    @SuppressWarnings("unchecked")
    public List<Car> reservationCarAble(String sdate, String edate) {
 
        // reservation에서 차량번호 가져와 해당되는 데이터가져오기
        // 그 중에 대여시작일 입력값
 
        List<Reservation> reservations = new ArrayList<Reservation>();
        List<Car> cars = new ArrayList<Car>();
        for (int i = 0; i < carList.size(); ++i) {
            String carId = String.format("CAR%03d", i + 1);
            // 특정차량 검색 메소드 호출
            reservations = reservationSearchSub(carId);
            int temp; // 날짜 비교 메소드 호출 후 반환 값 받을 변수
            String temp1 = ""; // 과거 대여시작일
            String temp2 = ""; // 과거 대여반납일
 
            // 대여시작일로 정렬하기
            Collections.sort(reservations, new Comparator<Reservation>() {
 
                @Override
                public int compare(Reservation arg0, Reservation arg1) {
                    return arg1.getStartDate().compareTo(arg0.getStartDate());
                }
 
            });
 
            if (reservations.isEmpty()) {
                cars.add(this.carSearch(carId));
            } else {
                for (Reservation rev : reservations) {
                    // 날짜 비교 메소드 호출 후 반환 값 저장
                    temp = this.reservationCarAble_sub(rev, sdate, edate);
                    if (reservations.size() == 1) {
                        if (temp < 0) {
                            cars.add(this.carSearch(carId));
                        } else if (temp > 0) {
                            cars.add(this.carSearch(carId));
                        } else {
                            // 기존 데이터(반납일)보다 입력 데이터(시작일)가
                            // 더 큰 날짜 일 때 (예 = 2018-08-08.compareTo(2018-08-09))
                            if (rev.getEndDate().compareTo(sdate) < 0) {
                                cars.add(this.carSearch(carId));
                            } else if (rev.getStartDate().compareTo(edate) > 0) {
                                cars.add(this.carSearch(carId));
                            }
                        }
                    } else if (reservations.size() > 1) {
                        if (temp < 0) {
                            cars.add(this.carSearch(carId));
                        } else if (temp == 0) {
                            if (temp2 != "" && temp1 != "") {
                                cars.remove(this.carSearch(carId));
                                if (temp2.compareTo(sdate) > 0 
                                        && rev.getEndDate().compareTo(sdate) < 0
                                        && temp1.compareTo(sdate) > 0 
                                        && temp1.compareTo(edate) > 0) {
                                    cars.add(this.carSearch(carId));
                                }
                            } else {
                                if (rev.getEndDate().compareTo(sdate) < 0) {
                                    cars.add(this.carSearch(carId));
                                } else if (rev.getStartDate().compareTo(edate) > 0) {
                                    cars.add(this.carSearch(carId));
                                }
                            }
                        }
                    }
                    temp1 = rev.getStartDate();
                    temp2 = rev.getEndDate();
                }
            }
        }
        @SuppressWarnings("rawtypes")
        List result1 = cars.stream().distinct().collect(Collectors.toList());
        return result1;
    }
 
    // 날짜 비교 메소드 추가
    // 수훈 20180813
    private int reservationCarAble_sub(Reservation rev, String sDate, String eDate) {
        int temp = 0;
        // 기존 데이터(시작월) 보다 입력 데이터(시작월)가 더 큰 날짜 일 때 (예 = 2018-07.compareTo(2018-08))
        if (rev.getStartDate().substring(0, 7).compareTo(sDate.substring(0, 7)) < 0) {
            temp = -1;
 
            // 특정차량의 데이터가 한 개 일 경우 비교 할 필요 없음
            // 기존 데이터(시작월) 보다 입력 데이터(시작월)가 더 작은 날짜 일 때 (예 = 2018-08.compareTo(2018-07))
        } else if (rev.getStartDate().substring(0, 7).compareTo(sDate.substring(0, 7)) > 0) {
            temp = 1;
 
            // 기존 데이터(시작월)와 입력 데이터(시작월)가 같은 경우(예 = 2018-08.compareTo(2018-08))
            // 이때는 조금 더 자세히 비교하기 위해 if문 추가
        } else {
            temp = 0;
        }
        return temp;
    }
 
    /********************************************************************/
 
    // 결제금액 계산 메소드
    // 은미 20180808
    public int rentMoney(int rentFee, String startDate, String endDate) {
 
        int startYear = Integer.parseInt(startDate.substring(0, 4));
        int startMonth = Integer.parseInt(startDate.substring(5, 7));
        int startDay = Integer.parseInt(startDate.substring(8, 10));
 
        int endYear = Integer.parseInt(endDate.substring(0, 4));
        int endMonth = Integer.parseInt(endDate.substring(5, 7));
        int endDay = Integer.parseInt(endDate.substring(8, 10));
 
        LocalDate startDateTmp = LocalDate.of(startYear, startMonth, startDay);
        LocalDate endDateTmp = LocalDate.of(endYear, endMonth, endDay);
        Period period = startDateTmp.until(endDateTmp);
        
        int result = (period.getDays() + 1) * rentFee;
 
        return result;
    }
 
    // 결제 메소드
    public void sumPay() {
        String year = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
                .substring(0, 4);
        this.annualSalesSearch(year);
    }
 
    /********************************************************************/
 
    // 차량 정보 검색 메소드
    // 이명재 2018.08.09
    public Car carSearch(String value) {
        Car car = new Car();
        for (Car a : this.carList) {
            if (a.getCarId().equals(value)) {
                car = a;
            }
        }
        return car;
    }
 
    // carId로 차종 이름을 찾는 메소드
    // 20180810 옥보라 - 새로 추가된 메소드
    public String carNameSearch(String carId) {
        String carName = "";
        for (Car c : this.carList) {
            if (c.getCarId().equals(carId)) {
                carName = c.getCarName();
            }
        }
        return carName;
    }
}