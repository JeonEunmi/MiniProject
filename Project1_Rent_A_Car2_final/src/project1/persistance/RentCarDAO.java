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
 
//�ڷ� ����� � Ŭ����
public class RentCarDAO {
 
    // �ʵ尪
 
    // �ڷ� ����� -> �ڷ��� Ŭ�������� ���� �غ�
    public List<Car> carList = new ArrayList<Car>();
    public List<Reservation> reservationList = new ArrayList<Reservation>();
 
    // ������ -> �ʱ�ȭ
    public RentCarDAO() {
        // Car, Customer, Reservation �ʱⰪ ����
 
        // Reservation (String reservationId, String name, String phone, String
        // licenseId, String
        // carId, String startDate, String endDate, String payDate, int pay, String
        // reservationStatus)
        this.reservationList.add(new Reservation("R0001", "ȫ�浿", "010-1234-1234", "����01-1234", "CAR008", "2017-12-10",
                "2017-12-12", "2017-12-04", 270000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0002", "���Ѽ�", "010-7410-8520", "���24-9047", "CAR001", "2017-12-23",
                "2017-12-26", "2017-12-19", 240000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0003", "�Լ���", "010-5579-4373", "����08-9720", "CAR001", "2017-12-30",
                "2018-01-02", "2017-12-19", 240000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0004", "��ƽ�", "010-9510-7530", "���91-7501", "CAR008", "2018-01-10",
                "2018-01-13", "2018-12-28", 360000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0005", "�����", "010-5918-1156", "����11-7511", "CAR014", "2018-01-20",
                "2018-01-25", "2018-01-04", 720000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0006", "������", "010-1234-9630", "���71-0147", "CAR002", "2018-02-17",
                "2018-02-19", "2018-02-02", 180000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0007", "Ȳ����", "010-8547-1222", "����61-1479", "CAR005", "2018-02-05",
                "2018-02-06", "2018-02-04", 140000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0008", "���Ѽ�", "010-7410-8520", "���24-9047", "CAR014", "2018-02-09",
                "2018-02-13", "2018-02-07", 600000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0009", "��ī��", "010-8520-7410", "����17-1268", "CAR001", "2018-02-21",
                "2018-02-25", "2018-02-16", 300000, "�������"));
        this.reservationList.add(new Reservation("R0010", "��ƽ�", "010-9510-7530", "���91-7501", "CAR008", "2018-02-17",
                "2018-02-20", "2018-02-16", 360000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0011", "������", "010-6899-3253", "����10-1111", "CAR015", "2018-03-01",
                "2018-03-03", "2018-02-28", 360000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0012", "�Լ���", "010-5579-4373", "���12-4885", "CAR001", "2018-03-05",
                "2018-03-10", "2018-03-03", 360000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0013", "������", "010-2947-3782", "����12-3333", "CAR015", "2018-03-07",
                "2018-03-14", "2018-03-03", 960000, "�������"));
        this.reservationList.add(new Reservation("R0014", "�̸���", "010-4643-8440", "���11-4567", "CAR010", "2018-03-15",
                "2018-03-19", "2018-03-13", 450000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0015", "������", "010-1994-0210", "���14-3434", "CAR007", "2018-03-23",
                "2018-03-25", "2018-03-21", 210000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0016", "���Ѽ�", "010-3214-0774", "����09-4682", "CAR014", "2018-04-01",
                "2018-04-03", "2018-04-01", 360000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0017", "���ϳ�", "010-9876-0854", "���01-1226", "CAR002", "2018-04-11",
                "2018-04-12", "2018-04-07", 120000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0018", "��ȿ��", "010-1234-9630", "��õ03-4948", "CAR002", "2018-04-23",
                "2018-04-24", "2018-04-21", 120000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0019", "������", "010-6994-0210", "���14-3434", "CAR011", "2018-05-03",
                "2018-05-05", "2018-05-01", 270000, "�������"));
        this.reservationList.add(new Reservation("R0020", "������", "010-8401-6843", "���08-1800", "CAR005", "2018-05-07",
                "2018-05-08", "2018-05-04", 70000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0021", "������", "010-1984-9808", "���73-9870", "CAR009", "2018-05-09",
                "2018-05-12", "2018-05-07", 480000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0022", "������", "010-7695-0857", "���60-6854", "CAR010", "2018-05-18",
                "2018-05-18", "2018-05-15", 90000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0023", "������", "010-0708-6802", "���54-0968", "CAR004", "2018-05-24",
                "2018-05-28", "2018-05-24", 300000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0024", "������", "010-2687-3102", "����95-0951", "CAR011", "2018-06-08",
                "2018-06-08", "2018-06-08", 90000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0025", "ȫ�浿", "010-1234-1234", "����01-1234", "CAR003", "2018-06-12",
                "2018-06-15", "2018-06-12", 480000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0026", "���߱�", "010-9987-9872", "����14-0814", "CAR012", "2018-06-20",
                "2018-06-21", "2018-06-20", 180000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0027", "�ڼ���", "010-4876-0000", "��õ30-1800", "CAR013", "2018-07-02",
                "2018-07-02", "2018-07-01", 70000, "�������"));
        this.reservationList.add(new Reservation("R0028", "������", "010-3872-6870", "�泲78-9852", "CAR008", "2018-07-03",
                "2018-07-07", "2018-07-03", 450000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0029", "������", "010-6994-0210", "���14-3434", "CAR001", "2018-07-15",
                "2018-07-18", "2018-07-15", 240000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0030", "������", "010-6899-3253", "����89-4980", "CAR014", "2018-07-22",
                "2018-07-28", "2018-07-22", 840000, "�ݳ��Ϸ�"));
        this.reservationList.add(new Reservation("R0031", "��ȿ��", "010-1479-0283", "����17-0284", "CAR005", "2018-08-09",
                "2018-08-11", "2018-08-02", 210000, "�뿩��"));
        this.reservationList.add(new Reservation("R0032", "����ǥ", "010-6279-5327", "����17-1751", "CAR013", "2018-08-08",
                "2018-08-15", "2018-08-04", 720000, "�뿩��"));
        this.reservationList.add(new Reservation("R0033", "������", "010-1842-0745", "�泲92-7162", "CAR003", "2018-08-13",
                "2018-08-17", "2018-08-04", 300000, "����Ȯ��"));
        this.reservationList.add(new Reservation("R0034", "������", "010-9300-1122", "���15-9214", "CAR014", "2018-08-28",
                "2018-08-31", "2018-08-15", 480000, "����Ȯ��"));
        this.reservationList.add(new Reservation("R0035", "�輭��", "010-1112-2225", "����96-4065", "CAR010", "2018-09-04",
                "2018-09-06", "2018-08-21", 270000, "����Ȯ��"));
        this.reservationList.add(new Reservation("R0036", "������", "010-6899-3253", "����10-1111", "CAR003", "2018-08-08",
                "2018-08-10", "2018-08-08", 180000, "�뿩��"));
        this.reservationList.add(new Reservation("R0037", "���ϳ�", "010-1515-8783", "���29-7180", "CAR002", "2018-08-07",
                "2018-08-11", "2018-08-06", 300000, "�뿩��"));
        this.reservationList.add(new Reservation("R0038", "������", "010-6899-3253", "����89-4980", "CAR006", "2018-08-11",
                "2018-08-12", "2018-08-07", 140000, "����Ȯ��"));
        this.reservationList.add(new Reservation("R0039", "�����", "010-0710-0981", "���14-0860", "CAR009", "2018-08-13",
                "2018-08-13", "2018-08-08", 90000, "����Ȯ��"));
        this.reservationList.add(new Reservation("R0040", "������", "010-7269-0371", "����04-9885", "CAR014", "2018-08-15",
                "2018-08-17", "2018-08-09", 360000, "����Ȯ��"));
        this.reservationList.add(new Reservation("R0041", "�׽�Ʈ", "010-7269-0371", "����04-8411", "CAR014", "2018-08-08",
                "2018-08-09", "2018-08-07", 360000, "����Ȯ��"));
 
        //Car (String carId, String carName, String carSize, int capacity, int rentFee)
        this.carList.add(new Car("CAR001", "��  ��", "����", 4, 60000));
        this.carList.add(new Car("CAR002", "��  ��", "����", 4, 60000));
        this.carList.add(new Car("CAR003", "��  ��", "����", 4, 60000));
        this.carList.add(new Car("CAR004", "��  ��", "����", 4, 60000));
        this.carList.add(new Car("CAR005", "����Ʈ", "����", 4, 70000));
        this.carList.add(new Car("CAR006", "����Ʈ", "����", 4, 70000));
        this.carList.add(new Car("CAR007", "����Ʈ", "����", 4, 70000));
        this.carList.add(new Car("CAR008", "�ƹݶ�", "������", 5, 90000));
        this.carList.add(new Car("CAR009", "�ƹݶ�", "������", 5, 90000));
        this.carList.add(new Car("CAR010", "�ƹݶ�", "������", 5, 90000));
        this.carList.add(new Car("CAR011", "�ƹݶ�", "������", 5, 90000));
        this.carList.add(new Car("CAR012", "�ƹݶ�", "������", 5, 90000));
        this.carList.add(new Car("CAR013", "�ƹݶ�", "������", 5, 90000));
        this.carList.add(new Car("CAR014", "ī�Ϲ�", "����", 8, 120000));
        this.carList.add(new Car("CAR015", "ī�Ϲ�", "����", 8, 120000));
        
        this.daoReSerializable();
        
    }
    
    /********************************************************************/
 
    // ������ȭ �޼ҵ� �߰�
    // �̸��� 2018-08-13
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
 
    // ����ȭ �޼ҵ� �߰�
    // ������ 2018-08-13
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
 
    // �����ȣ �ڵ� ���� �޼ҵ�
    // ���� 20180808
    public String autoReservationId() {
        String temp = String.format("R%04d", (this.reservationList.size() + 1));
        return temp;
    }
 
    // �������� �Է�
    // ���� 20180808
    public void reservationAdd(Reservation r) {
        this.reservationList.add(r);
    }
 
    // �������� ���
    // ���� 20180808
    public List<Reservation> reservationList(String value) {
        List<Reservation> revList = new ArrayList<Reservation>();
        for (Reservation rv : this.reservationList) {
            if (value.equals(rv.getReservationId())) {
                revList.add(rv);
            }
        }
        return revList;
    }
 
    // �������
    // ������ҽ� �����ݾ� 0������(���⿡ ������ ��ġ�� ����)
    // ���� 20180808
    // ���� 21080813 ����
    public void reservationCancel(List<Reservation> r) {
        //����Ȯ�� ->�������
        for (Reservation rev : r) {
            rev.setReservationStatus("�������");
            rev.setPay(0);
        }
        
    }
 
    // �ݳ�
    // ���� 20180808
    public void returnCar(List<Reservation> r) {
        // �뿩�� -> �ݳ�
        for (Reservation rev : r) {
            rev.setReservationStatus("�ݳ��Ϸ�");
        }
 
    }
 
    // *****�������� ���� ���� �޼ҵ�*****
    // ���� 20180808
    public void autoChangeStatus() {
        // ���� �ð�
        List<Reservation> rv = this.reservationList;
        DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String nowDate = sdFormat.format(date);
        for (Reservation rev : rv) {
            if (rev.getStartDate().compareTo(nowDate) == 0) {
                // ����Ȯ�� -> �뿩��
                rev.setReservationStatus("�뿩��");
            } else if (rev.getEndDate().compareTo(nowDate) < 0 ) {
                // �뿩�� -> �ݳ�
                rev.setReservationStatus("�ݳ��Ϸ�");
            }
        }
    }
    
    /********************************************************************/
    // ���� �˻� -> Ư����, Ư����, �����ȣ, ������
    // �̸��� 2018-08-09
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
 
    // ���� �˻� -> ������
    // �̸��� 18-08-09
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
    //Ư������ ȣ�� �޼ҵ�
    //���డ������ ����ϴ� �޼ҵ忡�� �ʿ�
    //���� 2108-08-10
    public List<Reservation> reservationSearchSub(String value) {
        List<Reservation> reservations = new ArrayList<Reservation>();
 
        for (Reservation r : reservationList) {
            if (r.getCarId().equals(value)) {
                if (r.getReservationStatus().equals("�뿩��") || r.getReservationStatus().equals("����Ȯ��")) {
                    reservations.add(new Reservation(r.getReservationId(), r.getName(), r.getPhone(), r.getLicenseId(),
                            r.getCarId(), r.getStartDate(), r.getEndDate(), r.getPayDate(), r.getPay(),
                            r.getReservationStatus()));
                }
            }
        }
        return reservations;
    }
    /********************************************************************/
    
    // ��ü ���� ��Ȳ ������� this.reservationList�� ���� <- SubMain - mainMenu2_sub1_sub6()
    // ������ 2018.08.08 ���� �߰�
    public List<Reservation> reservationList() {
        return this.reservationList;
    }
    
    // ��ü ���� ��� ���� ���� ������ �ִ� this.carList�� ���� <-SubMain - mainMenu2_sub()
    // ������ 2018.08.08 ���� �߰�
    public List<Car> carList() {
        return this.carList;
    }
 
    /********************************************************************/
 
    // ������ ������ ��Ȳ �˻�
    // ������ 2018-08-08
    // Ư�� �⵵�� ���� �޾Ƽ� Ư�� �⵵�� �����ϴ� ���� ��Ȳ�� 
    // ������ ������ �� ��� ��ȯ
    public List<YearlySale> annualSalesSearch(String year) {
 
        List<YearlySale> resultList = null;
        List<Reservation> temp = new ArrayList<Reservation>();
        Map<String, YearlySale> map = new HashMap<String, YearlySale>();
        // �Ű������� �޾ƿ� �ش� year�� �߻��� ���� ����Ʈ ���θ� ã��
        for (Reservation r : this.reservationList) {
            if (r.getPayDate().contains(year)) {
                if (r.getPay() != 0) {
                    temp.add(r);
                    String carName = this.carNameSearch(r.getCarId());
                    map.put(carName, new YearlySale(carName, 0, 0));
                }
            }
        }
 
        // Ư�� ������ ���� ��Ȳ�� �ջ��ϴ� ����
        for (String carName : map.keySet()) {
            for (Reservation r : temp) {
                String carName2 = this.carNameSearch(r.getCarId());
                if (carName2.equals(carName)) {
                    // Map<String, YearlySale> �� ����� Ư�� ������
                    // count ���� +1 ����
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
    // ���� ���� ��Ȳ �˻�
    // Ư�� ����� ���� �޾Ƽ� Ư�� ����� �����ϴ� ���� ��Ȳ�� �Ϻ� ������ �� ��� ��ȯ
    // ����,���� 2018-08-13
    public List<MonthlySale> monthlySalesSearch(String month) {
 
        List<MonthlySale> resultList = null;
        List<Reservation> temp = new ArrayList<Reservation>();
        Map<String, MonthlySale> map = new HashMap<String, MonthlySale>();
        // �Ű������� �޾ƿ� �ش� year�� �߻��� ���� ����Ʈ ���θ� ã��
        for (Reservation r : this.reservationList) {
            String date = r.getPayDate();
            if (date.contains(month)) {
                if (r.getPay() != 0) {
                    temp.add(r);
                    map.put(date, new MonthlySale(date, 0, 0));
                }
            }
        }
 
        // Ư�� �Ϻ� ���� ��Ȳ�� �ջ��ϴ� ����
        for (String date1 : map.keySet()) {
            for (Reservation r : temp) {
                String date2 = r.getPayDate();
                if (date1.equals(date2)) {
                    // Map<String, MonthlySale> �� ����� Ư�� ���� count ���� +1 ����
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
 
    // ���డ������ ���
    @SuppressWarnings("unchecked")
    public List<Car> reservationCarAble(String sdate, String edate) {
 
        // reservation���� ������ȣ ������ �ش�Ǵ� �����Ͱ�������
        // �� �߿� �뿩������ �Է°�
 
        List<Reservation> reservations = new ArrayList<Reservation>();
        List<Car> cars = new ArrayList<Car>();
        for (int i = 0; i < carList.size(); ++i) {
            String carId = String.format("CAR%03d", i + 1);
            // Ư������ �˻� �޼ҵ� ȣ��
            reservations = reservationSearchSub(carId);
            int temp; // ��¥ �� �޼ҵ� ȣ�� �� ��ȯ �� ���� ����
            String temp1 = ""; // ���� �뿩������
            String temp2 = ""; // ���� �뿩�ݳ���
 
            // �뿩�����Ϸ� �����ϱ�
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
                    // ��¥ �� �޼ҵ� ȣ�� �� ��ȯ �� ����
                    temp = this.reservationCarAble_sub(rev, sdate, edate);
                    if (reservations.size() == 1) {
                        if (temp < 0) {
                            cars.add(this.carSearch(carId));
                        } else if (temp > 0) {
                            cars.add(this.carSearch(carId));
                        } else {
                            // ���� ������(�ݳ���)���� �Է� ������(������)��
                            // �� ū ��¥ �� �� (�� = 2018-08-08.compareTo(2018-08-09))
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
 
    // ��¥ �� �޼ҵ� �߰�
    // ���� 20180813
    private int reservationCarAble_sub(Reservation rev, String sDate, String eDate) {
        int temp = 0;
        // ���� ������(���ۿ�) ���� �Է� ������(���ۿ�)�� �� ū ��¥ �� �� (�� = 2018-07.compareTo(2018-08))
        if (rev.getStartDate().substring(0, 7).compareTo(sDate.substring(0, 7)) < 0) {
            temp = -1;
 
            // Ư�������� �����Ͱ� �� �� �� ��� �� �� �ʿ� ����
            // ���� ������(���ۿ�) ���� �Է� ������(���ۿ�)�� �� ���� ��¥ �� �� (�� = 2018-08.compareTo(2018-07))
        } else if (rev.getStartDate().substring(0, 7).compareTo(sDate.substring(0, 7)) > 0) {
            temp = 1;
 
            // ���� ������(���ۿ�)�� �Է� ������(���ۿ�)�� ���� ���(�� = 2018-08.compareTo(2018-08))
            // �̶��� ���� �� �ڼ��� ���ϱ� ���� if�� �߰�
        } else {
            temp = 0;
        }
        return temp;
    }
 
    /********************************************************************/
 
    // �����ݾ� ��� �޼ҵ�
    // ���� 20180808
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
 
    // ���� �޼ҵ�
    public void sumPay() {
        String year = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
                .substring(0, 4);
        this.annualSalesSearch(year);
    }
 
    /********************************************************************/
 
    // ���� ���� �˻� �޼ҵ�
    // �̸��� 2018.08.09
    public Car carSearch(String value) {
        Car car = new Car();
        for (Car a : this.carList) {
            if (a.getCarId().equals(value)) {
                car = a;
            }
        }
        return car;
    }
 
    // carId�� ���� �̸��� ã�� �޼ҵ�
    // 20180810 ������ - ���� �߰��� �޼ҵ�
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