package project1.domain;
 
import java.io.Serializable;
 
public class Car implements Serializable {
    
    private static final long serialVersionUID = 4109260190659700349L;
 
    //차량번호, 차종, 크기(경차,소형,준중형,대형),탑승인원, 1일렌트비
    private String carId, carName, carSize;
    private int capacity, rentFee;
    
    public Car() {
        
    }
 
    public Car(String carId, String carName, String carSize, int capacity, int rentFee) {
        this.carId = carId;
        this.carName = carName;
        this.carSize = carSize;
        this.capacity = capacity;
        this.rentFee = rentFee;
    }
 
    //차량번호, 차종, 크기, 탑승인원, 1일렌트비는 읽기 전용
    public String getCarId() {
        return carId;
    }
 
    public String getCarName() {
        return carName;
    }
 
    public String getCarSize() {
        return carSize;
    }
 
    public int getCapacity() {
        return capacity;
    }
 
    public int getRentFee() {
        return rentFee;
    }
 
 
    @Override
    public String toString() {
        return String.format("%s / %s / %s / %s인 / %,d원%n ", this.getCarId(), this.getCarName(),
                this.getCarSize(), this.getCapacity(), this.getRentFee());
    }
    
}
 
