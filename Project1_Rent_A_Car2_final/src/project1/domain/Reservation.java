package project1.domain;
 
import java.io.Serializable;
 
public class Reservation implements Serializable {
    
    private static final long serialVersionUID = 4109260190659700349L;
 
    //�����ȣ / �����ڸ� / ����ó / ��������ȣ / ������ȣ / �뿩������ 
    // / �뿩�ݳ��� / ������ / �����ݾ� / ����
    private String reservationId, name, phone, licenseId
            , carId, startDate, endDate, payDate, reservationStatus;
    private int pay;
    
    public Reservation(){
        
    }
    
    public Reservation(String reservationId, String name, String phone, String licenseId, String carId, String startDate,
            String endDate, String payDate, int pay, String reservationStatus) {
        super();
        this.reservationId = reservationId;
        this.name = name;
        this.phone = phone;
        this.licenseId = licenseId;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.payDate = payDate;
        this.pay = pay;
        this.reservationStatus = reservationStatus;
    }
 
 
    public String getReservationId() {
        return reservationId;
    }
 
    public String getName() {
        return name;
    }
 
    public String getPhone() {
        return phone;
    }
    
    public String getLicenseId() {
        return licenseId;
    }
 
    public String getCarId() {
        return carId;
    }
 
    public String getStartDate() {
        return startDate;
    }
 
    public String getEndDate() {
        return endDate;
    }
 
    public String getPayDate() {
        return payDate;
    }
 
    public int getPay() {
        return pay;
    }
    
    public void setPay(int pay) {
        this.pay = pay;
    }
    
    public String getReservationStatus() {
        return reservationStatus;
    }
    
    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
 
    //�����ȣ / �����ڸ� / ����ó / ������ȣ /  ����Ⱓ / ����
    @Override
    public String toString() {
        return String.format("%s / %s / %s / %s / %s ~ %s / %s",
                this.getReservationId(), this.getName(), this.getPhone(),
                this.getCarId(), this.getStartDate(), this.getEndDate(), this.getReservationStatus());
    }
}